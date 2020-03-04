package com.epam.jdi.httptests.utils;

public class Defaults {
    public static OauthCustomAuthScheme defaultOauthScheme() {
        return new OauthCustomAuthScheme().set(o -> {
            o.consumerKey = "\"RKCGzna7bv9YD57c\"";
            o.signatureMethod = "\"HMAC-SHA1\"";
            o.timestamp = "\"1472121255\"";
            o.nonce = "\"e5VR16\"";
            o.version = "\"1.0\"";
            o.signature = "\"Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D\"";
        });
    }
}
