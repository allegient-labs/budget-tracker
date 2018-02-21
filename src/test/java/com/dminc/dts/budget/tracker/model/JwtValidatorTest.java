package com.dminc.dts.budget.tracker.model;

import com.dminc.dts.budget.tracker.security.JwtValidator;
import com.dminc.dts.jwt.AccessTokenJwt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JwtValidatorTest {

    private final String CERT_URL = "https://api.myjson.com/bins/190bjl";

    @Before
    public void setJWS_URL() {
        JwtValidator.setCertUrl(CERT_URL);
    }

    @Test
    public void validatorCorrectlyValidatesWellFormedToken() throws Exception {
        AccessTokenJwt jwt = new AccessTokenJwt.AccessTokenJwtBuilder().build();
        String token = jwt.getCompactToken();
        assertEquals(JwtValidator.isValidJwt(token), true);
    }

    @Test
    public void validatorRejectsExpiredToken() throws Exception {
        AccessTokenJwt expiredJwt = new AccessTokenJwt.AccessTokenJwtBuilder()
                .withExpiration(System.currentTimeMillis() / 1000 - 1)  // Token expired one second ago
                .build();
        String token = expiredJwt.getCompactToken();
        assertEquals(JwtValidator.isValidJwt(token), false);
    }

    @Test
    public void validatorRejectsMalformedToken() throws Exception {
        AccessTokenJwt jwt = new AccessTokenJwt.AccessTokenJwtBuilder()
                .build();
        String compactToken = jwt.getCompactToken();
        String malformedToken = compactToken.replace(".", "period");
        assertEquals(JwtValidator.isValidJwt(malformedToken), false);
    }

    @Test
    public void validatorRejectsTokenWithIncorrectKid() throws Exception {
        AccessTokenJwt jwt = new AccessTokenJwt.AccessTokenJwtBuilder()
                .withKid("654321")
                .build();
        String badToken = jwt.getCompactToken();
        assertEquals(JwtValidator.isValidJwt(badToken), false);
    }

    @Test
    public void validatorRejectsTokenWithIncorrectAudience() throws Exception {
        AccessTokenJwt jwt = new AccessTokenJwt.AccessTokenJwtBuilder()
                .withAudience("ajcklj-jksllkj-398)(*&$)~")
                .build();
        String badToken = jwt.getCompactToken();
        assertEquals(JwtValidator.isValidJwt(badToken), false);
    }

    @Test
    public void validatorRejectsTokenWithBadIssuer() throws Exception {
        AccessTokenJwt jwt = new AccessTokenJwt.AccessTokenJwtBuilder()
                .withIssuer("2398s--sdkjn-skljdfkl...?>JKHD")
                .build();
        String badIssToken = jwt.getCompactToken();
        assertEquals(JwtValidator.isValidJwt(badIssToken), false);
    }

}
