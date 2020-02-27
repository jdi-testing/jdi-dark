package com.epam.jdi.httptests.utils;

import com.epam.http.requests.authhandler.AuthenticationHandler;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public class CustomAuthHandler implements AuthenticationHandler {

    private String oauthConsumerKey;
    private String oauthSignatureMethod;
    private String oauthTimestamp;
    private String oauthNonce;
    private String oauthVersion;
    private String oauthSignature;

    public String getOauthConsumerKey() {
        return oauthConsumerKey;
    }

    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }

    public String getOauthSignatureMethod() {
        return oauthSignatureMethod;
    }

    public void setOauthSignatureMethod(String oauthSignatureMethod) {
        this.oauthSignatureMethod = oauthSignatureMethod;
    }

    public String getOauthTimestamp() {
        return oauthTimestamp;
    }

    public void setOauthTimestamp(String oauthTimestamp) {
        this.oauthTimestamp = oauthTimestamp;
    }

    public String getOauthNonce() {
        return oauthNonce;
    }

    public void setOauthNonce(String oauthNonce) {
        this.oauthNonce = oauthNonce;
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public String getOauthSignature() {
        return oauthSignature;
    }

    public void setOauthSignature(String oauthSignature) {
        this.oauthSignature = oauthSignature;
    }

    public Header generateHeader() {
        String HeaderValue = "OAuth oauth_consumer_key="+this.oauthConsumerKey+",oauth_signature_method="
                +this.oauthSignatureMethod+",oauth_timestamp="
                +this.oauthTimestamp+",oauth_nonce="+this.oauthNonce+",oauth_version="
                +this.oauthVersion+",oauth_signature="+this.oauthSignature;
        return new Header("Authorization", HeaderValue);
    }

    public RequestSpecification handleAuth(RequestSpecification requestSpecification) {
        return requestSpecification.header(generateHeader());
    }
}
