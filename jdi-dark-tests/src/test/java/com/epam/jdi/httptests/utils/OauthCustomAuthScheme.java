package com.epam.jdi.httptests.utils;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.internal.http.HTTPBuilder;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class OauthCustomAuthScheme implements AuthenticationScheme {
    @Override
    public void authenticate(HTTPBuilder httpBuilder) {
        httpBuilder.getClient().addRequestInterceptor(
                (request, context) -> {
//                    BasicHttpEntity entity = new BasicHttpEntity();
//                    if (request instanceof BasicHttpEntityEnclosingRequest) {
                        request.addHeader("Authorization", "OAuth oauth_consumer_key=\"RKCGzna7bv9YD57c\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1472121255\",oauth_nonce=\"e5VR16\",oauth_version=\"1.0\",oauth_signature=\"Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D\"");
//                        request.addHeader("oauth_signature_method", "HMAC-SHA1");
//                        request.addHeader("oauth_timestamp", "1472121255");
//                        request.addHeader("oauth_nonce", "e5VR16");
//                        request.addHeader("oauth_version","1.0");
//                        request.addHeader("oauth_signature", "Or%2F2PqPg21wp967CASJtoo%2BF5Kk%3D");
//                    }else {
//                        throw new HttpException();
//                    }
//               InputStream in = new ByteArrayInputStream();
//               entity.setContent();
//               request.addHeader("", "");
//               EntityBuilder builder = EntityBuilder.create();
//               builder.setParameters(new NameValuePair() {
//               }air())
//               HttpEntity entity = builder.build();

//                        //request
//                        if(request.containsHeader("sample-header")) {
//                            System.out.println("Contains header sample-header, removing it..");
//                            request.removeHeaders("sample-header");
//                        }
//                        //Printing remaining list of headers
//                        Header[] headers= request.getAllHeaders();
                });
            }



    }

