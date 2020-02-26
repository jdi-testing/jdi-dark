package com.epam.jdi.httptests.utils;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;

public class OauthCustomAuthScheme implements AuthenticationScheme {

    private String oauth_consumer_key;
    private String oauth_signature_method;
    private String oauth_timestamp;
    private String oauth_nonce;
    private String oauth_version;
    private String oauth_signature;

    public String getOauth_consumer_key() {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key) {
        this.oauth_consumer_key = oauth_consumer_key;
    }

    public String getOauth_signature_method() {
        return oauth_signature_method;
    }

    public void setOauth_signature_method(String oauth_signature_method) {
        this.oauth_signature_method = oauth_signature_method;
    }

    public String getOauth_timestamp() {
        return oauth_timestamp;
    }

    public void setOauth_timestamp(String oauth_timestamp) {
        this.oauth_timestamp = oauth_timestamp;
    }

    public String getOauth_nonce() {
        return oauth_nonce;
    }

    public void setOauth_nonce(String oauth_nonce) {
        this.oauth_nonce = oauth_nonce;
    }

    public String getOauth_version() {
        return oauth_version;
    }

    public void setOauth_version(String oauth_version) {
        this.oauth_version = oauth_version;
    }

    public String getOauth_signature() {
        return oauth_signature;
    }

    public void setOauth_signature(String oauth_signature) {
        this.oauth_signature = oauth_signature;
    }

    @Override
    public void authenticate(HTTPBuilder httpBuilder) {
        httpBuilder.getClient().addRequestInterceptor(
                (request, context) ->
                        request.addHeader("Authorization", "OAuth oauth_consumer_key="+oauth_consumer_key+",oauth_signature_method="
                                +oauth_signature_method+",oauth_timestamp="
                                +oauth_timestamp+",oauth_nonce="+oauth_nonce+",oauth_version="
                                +oauth_version+",oauth_signature="+oauth_signature));
    }
}

