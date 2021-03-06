package com.dminc.dts.budget.tracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Iterator;

public class AzureADAccessTokenValidator {

    private static String KEYS_URL = "https://login.microsoftonline.com/common/discovery/keys";
    private static final String EXPECTED_ISS = "https://sts.windows.net/40d2219d-b636-4bed-9185-e876d026d77b/";
    private static final String EXPECTED_AUD = "295103ad-e41c-45a3-a14b-9dc7ddfbf2b1";

    private static HashMap<String, String> certificateCache = new HashMap<>();

    public static void setCertUrl(String certUrl) {
        AzureADAccessTokenValidator.KEYS_URL = certUrl;
    }

    public static boolean isValidAccessToken(String token) {
        try {
            Claims bodyClaims = getBodyClaimsForToken(token);

            boolean validAud = bodyClaims.containsKey("aud")
                    && bodyClaims.get("aud").toString().equals(EXPECTED_AUD);
            boolean validIss = bodyClaims.containsKey("iss")
                    && bodyClaims.get("iss").toString().equals(EXPECTED_ISS);

            return validAud && validIss;
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getBodyClaimsForToken(String token) throws Exception {
        DecodedJWT decodedJWT = JWT.decode(token);
        PublicKey publicKey = getPublicKeyForDecodedJwt(decodedJWT);
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    private static PublicKey getPublicKeyForDecodedJwt(DecodedJWT decodedJWT) throws Exception {
        String keyId = decodedJWT.getHeaderClaim("kid").asString();
        Certificate certificate = getCertificateForKeyId(keyId);
        return certificate.getPublicKey();
    }

    private static Certificate getCertificateForKeyId(String kid) throws Exception {
        String cert = getCertificateStringForKeyId(kid);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        InputStream certInputStream = new ByteArrayInputStream(Base64.decodeBase64(cert));
        return certFactory.generateCertificate(certInputStream);
    }

    private static String getCertificateStringForKeyId(String kid) throws Exception {
        String cert = certificateCache.get(kid);

        if (cert != null) {
            return cert;
        }

        String keysJson = getJsonForUrl(KEYS_URL);
        return extractCertWithKidFromKeysJson(kid, keysJson);
    }

    private static String getJsonForUrl(String url) {
        return new RestTemplate().getForEntity(url, String.class).getBody();
    }

    private static String extractCertWithKidFromKeysJson(String kid, String json) throws Exception {
        ObjectMapper om = new ObjectMapper();

        JsonNode root = om.readTree(json).findValue("keys");

        String certText = getCertWithKeyIdFromJsonArray(kid, root);
        if (certText != null) return certText;

        throw new CertificateNotFoundException("Could not find certificate for kid: " + kid);
    }

    private static String getCertWithKeyIdFromJsonArray(String kid, JsonNode array) {
        Iterator<JsonNode> keys = array.elements();
        while (keys.hasNext()) {
            JsonNode key = keys.next();
            String otherKid = key.findValue("kid").asText("");

            if (otherKid.equals(kid)) {
                JsonNode certNode = key.findValue("x5c");
                String certText = certNode.get(0).asText("");

                certificateCache.put(kid, certText);

                if (certText.equals("")) {
                    throw new CertificateNotFoundException("Could not find certificate for kid: " + kid);
                }

                return certText;
            }
        }
        return null;
    }

    private static class CertificateNotFoundException extends RuntimeException {
        CertificateNotFoundException(String message) {
            super(message);
        }
    }
}
