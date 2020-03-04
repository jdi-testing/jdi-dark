package com.epam.jdi.httptests.utils;

import com.epam.jdi.tools.DataClass;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;

/**
 * Here's an example of a custom authentication scheme.
 */
public class OauthCustomAuthScheme extends DataClass<OauthCustomAuthScheme> implements AuthenticationScheme {
    public String consumerKey, signatureMethod, timestamp, nonce, version, signature;

    @Override
    public void authenticate(HTTPBuilder httpBuilder) {
        httpBuilder.getClient().addRequestInterceptor(
                (request, context) ->
                        request.addHeader("Authorization", "OAuth oauth_consumer_key="+ consumerKey +",oauth_signature_method="
                + signatureMethod +",oauth_timestamp="
                + timestamp +",oauth_nonce="+ nonce +",oauth_version="
                + version +",oauth_signature="+ signature));
    }
}

