package com.dminc.dts.budget.tracker.security;

import com.dminc.dts.budget.tracker.TestAccessToken;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AzureADAccessTokenValidatorTest {

    private final String CERT_URL = "https://api.myjson.com/bins/190bjl";

    @Before
    public void setJWS_URL() {
        AzureADAccessTokenValidator.setCertUrl(CERT_URL);
    }

    @Test
    public void validatorCorrectlyValidatesWellFormedToken() throws Exception {
        String jwt = new TestAccessToken.TestAccessTokenBuilder().buildAndGetAsString();
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(jwt), true);
    }

    @Test
    public void validatorRejectsExpiredToken() throws Exception {
        String expiredJwt = new TestAccessToken.TestAccessTokenBuilder()
                .withExpiration(System.currentTimeMillis() / 1000 - 1)  // Token expired one second ago
                .buildAndGetAsString();
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(expiredJwt), false);
    }

    @Test
    public void validatorRejectsMalformedToken() throws Exception {
        String jwt = new TestAccessToken.TestAccessTokenBuilder()
                .buildAndGetAsString();
        String malformedToken = jwt.replace(".", "period");
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(malformedToken), false);
    }

    @Test
    public void validatorRejectsTokenWithIncorrectKid() throws Exception {
        String badJwt = new TestAccessToken.TestAccessTokenBuilder()
                .withKid("654321")
                .buildAndGetAsString();
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(badJwt), false);
    }

    @Test
    public void validatorRejectsTokenWithIncorrectAudience() throws Exception {
        String badJwt = new TestAccessToken.TestAccessTokenBuilder()
                .withAudience("ajcklj-jksllkj-398)(*&$)~")
                .buildAndGetAsString();
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(badJwt), false);
    }

    @Test
    public void validatorRejectsTokenWithBadIssuer() throws Exception {
        String badJwt = new TestAccessToken.TestAccessTokenBuilder()
                .withIssuer("2398s--sdkjn-skljdfkl...?>JKHD")
                .buildAndGetAsString();
        assertEquals(AzureADAccessTokenValidator.isValidAccessToken(badJwt), false);
    }

}
