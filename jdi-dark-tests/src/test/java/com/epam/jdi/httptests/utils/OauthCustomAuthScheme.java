package com.epam.jdi.httptests.utils;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;

public class OauthCustomAuthScheme implements AuthenticationScheme {

    private String oauthConsumerKey;
    private String oauthSignatureMethod;
    private String oauthTimestamp;
    private String oauthNonce;
    private String oauthVersion;
    private String oauthSignature;

    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }

    public void setOauthSignatureMethod(String oauthSignatureMethod) {
        this.oauthSignatureMethod = oauthSignatureMethod;
    }

    public void setOauthTimestamp(String oauthTimestamp) {
        this.oauthTimestamp = oauthTimestamp;
    }

    public void setOauthNonce(String oauthNonce) {
        this.oauthNonce = oauthNonce;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public void setOauthSignature(String oauthSignature) {
        this.oauthSignature = oauthSignature;
    }

    @Override
    public void authenticate(HTTPBuilder httpBuilder) {
        httpBuilder.getClient().addRequestInterceptor(
                (request, context) ->
                        request.addHeader("Authorization", "OAuth oauth_consumer_key="+oauthConsumerKey+",oauth_signature_method="
                                +oauthSignatureMethod+",oauth_timestamp="
                                +oauthTimestamp+",oauth_nonce="+oauthNonce+",oauth_version="
                                +oauthVersion+",oauth_signature="+oauthSignature));
    }
}

