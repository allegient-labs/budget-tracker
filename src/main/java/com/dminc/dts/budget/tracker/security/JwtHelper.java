package com.dminc.dts.budget.tracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JwtHelper {

    private static final String KEYS_URL = "https://login.microsoftonline.com/common/discovery/keys";

    // TODO more robust token validation (e.g. expiration, nonce, issuer, audience)
    public static boolean isValidJwt(String token) {
        return verifyJwtSignature(token);
    }

    private static boolean verifyJwtSignature(String token) {
        DecodedJWT jwt;
        try {
            jwt = JWT.decode(token);
        } catch(Exception e) {
            return false;
        }

        String kid = jwt.getHeaderClaim("kid").asString();

        Certificate certificate = getCertificateForString(getCertificateStringForKid(kid));
        PublicKey publicKey = certificate.getPublicKey();

        try {
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException | ExpiredJwtException e) {
            return false;
        }
    }

    private static String getCertificateStringForKid(String kid) {
        String json = getJsonForUrl(KEYS_URL);
        return extractCertForKidFromKeysJson(kid, json);
    }

    private static Certificate getCertificateForString(String cert) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            InputStream certInputStream = new ByteArrayInputStream(Base64.decodeBase64(cert));
            return certFactory.generateCertificate(certInputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't get certificate");
    }

    private static String getJsonForUrl(String url) {
        return new RestTemplate().getForEntity(url, String.class).getBody();
    }

    private static String extractCertForKidFromKeysJson(String kid, String json) {
        ObjectMapper om = new ObjectMapper();

        try {
            JsonNode root = om.readTree(json).findValue("keys");
            if (root.isArray()) {
                Iterator<JsonNode> keys = root.elements();
                while(keys.hasNext()) {
                    JsonNode key = keys.next();
                    String otherKid = key.findValue("kid").asText("");

                    if (otherKid.equals(kid)) {
                        JsonNode certNode = key.findValue("x5c");
                        return certNode.get(0).asText("");
                    }
                }
            } else {
                return root.findValue("x5c").asText("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static IdToken parseToken(String credentials) {
        DecodedJWT decodedJWT = JWT.decode(credentials);

        IdToken parsedToken = new IdToken();
        parsedToken.setEmail(decodedJWT.getClaim("email").asString());

        List<String> groups = decodedJWT.getClaim("groups").asList(String.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        groups.forEach(g -> authorities.add(new SimpleGrantedAuthority(g)));
        parsedToken.setGroups(authorities);

        return parsedToken;
    }
}
