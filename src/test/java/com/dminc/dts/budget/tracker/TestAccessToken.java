package com.dminc.dts.budget.tracker;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestAccessToken {

    private String compactToken = "";

    private TestAccessToken(TestAccessTokenBuilder builder) throws Exception {
        PEMParser pemParser = new PEMParser(Files.newBufferedReader(Paths.get("testJwtPrivateKey.pem")));
        PEMKeyPair keyPair = (PEMKeyPair) pemParser.readObject();
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        PrivateKey privateKey = converter.getPrivateKey(keyPair.getPrivateKeyInfo());
        compactToken = Jwts.builder()
                .setHeader(builder.header)
                .setClaims(builder.bodyClaims)
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }

    public String getCompactToken() {
        return compactToken;
    }

    public static class TestAccessTokenBuilder {
        // Default expiration time one hour after creation
        private Map<String, Object> bodyClaims = new HashMap<>();
        private Map<String, Object> header = new HashMap<>();

        {
            header.put("typ", "JWT");
            header.put("alg", "RS256");
            header.put("kid", "123456");

            bodyClaims.put("email", "timtester@budgetTracker.onmicrosoft.com");
            bodyClaims.put("groups", Collections.singletonList("e2577ae3-b64b-4585-a8bd-eb3180a11ee4"));
            bodyClaims.put("name", "Tim Tester");
            bodyClaims.put("iss", "https://sts.windows.net/40d2219d-b636-4bed-9185-e876d026d77b/");
            bodyClaims.put("aud", "295103ad-e41c-45a3-a14b-9dc7ddfbf2b1");
            bodyClaims.put("exp", System.currentTimeMillis() / 1000 + (60 * 60) + "");
        }

        public TestAccessTokenBuilder withExpiration(long epochSeconds) {
            bodyClaims.put("exp", epochSeconds);
            return this;
        }

        public TestAccessTokenBuilder withAudience(String aud) {
            bodyClaims.put("aud", aud);
            return this;
        }

        public TestAccessTokenBuilder withIssuer(String iss) {
            bodyClaims.put("iss", iss);
            return this;
        }

        public TestAccessTokenBuilder withKid(String kid) {
            header.put("kid", kid);
            return this;
        }

        public String buildAndGetAsString() throws Exception {
            return new TestAccessToken(this).getCompactToken();
        }

    }

}
