//package com.epam.jdi.httptests.utils;
//
//import io.restassured.config.OAuthConfig;
//import io.restassured.internal.http.AuthConfig;
//import io.restassured.internal.http.HTTPBuilder;
//import org.apache.http.Header;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpException;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpRequestInterceptor;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.EntityBuilder;
//import org.apache.http.entity.BasicHttpEntity;
//import org.apache.http.message.BasicHttpEntityEnclosingRequest;
//import org.apache.http.protocol.HttpContext;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//
//public class CustomAuthConfig extends AuthConfig {
//
//    private String auth
//
//    private
//
//    public CustomAuthConfig(HTTPBuilder builder, OAuthConfig restAssuredOAuthConfig) {
//        super(builder, restAssuredOAuthConfig);
//    }
//   public void oath1verify() {
//       HttpRequestInterceptor requestInterceptor = new HttpRequestInterceptor() {
//           @Override
//           public void process(HttpRequest request, HttpContext context) throws
//                   HttpException, IOException {
//               BasicHttpEntity entity = new BasicHttpEntity();
//               if (request instanceof BasicHttpEntityEnclosingRequest) {
//                    request.addHeader();
//               }else {
//                   throw new HttpException();
//               }
////               InputStream in = new ByteArrayInputStream();
////               entity.setContent();
////               request.addHeader("", "");
////               EntityBuilder builder = EntityBuilder.create();
////               builder.setParameters(new NameValuePair() {
////               }air())
////               HttpEntity entity = builder.build();
//
//           //request
//               if(request.containsHeader("sample-header")) {
//                   System.out.println("Contains header sample-header, removing it..");
//                   request.removeHeaders("sample-header");
//               }
//               //Printing remaining list of headers
//               Header[] headers= request.getAllHeaders();
//           }
//       };
//   }
//
//}
