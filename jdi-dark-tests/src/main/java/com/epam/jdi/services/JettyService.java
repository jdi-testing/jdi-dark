package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestDataMethod;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Product;
import com.epam.jdi.dto.GreetingItself;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.User;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.builder.MultiPartSpecBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.*;
import static io.restassured.http.ContentType.*;

@ServiceDomain("http://localhost:8080")
public class JettyService {

    @GET("/multiCookie")
    public static RestMethod getMultiCookie;

    @GET("/multiCookieRequest")
    public static RestMethod getMultiCookieRequest;

    @GET("/setCookies")
    public static RestMethod setCookies;

    @GET("/cookie_with_no_value")
    public static RestMethod getCookieWithNoValue;

    @GET("/response_cookie_with_no_value")
    public static RestMethod getResponseCookieWithNoValue;

    @GET("/cookie")
    public static RestMethod getCookie;

    public static RestResponse getMultipleCookieSpecifiedUsingMap(Map<String, String> cookieMap, String addCookieName, String addCookieValue) {
        return JettyService.getCookie.call(cookies().addAll(cookieMap).cookieUpdater().add(addCookieName, addCookieValue));
    }

    public static RestResponse getSpecifiedCookiePairs(String namePair1, String valuePair1, String namePair2, String valuePair2) {
        return getCookie.call(cookies().addAll(new Object[][]{{namePair1, valuePair1}, {namePair2, valuePair2}}));
    }

    @GET("/cookie")
    @Cookie(name = "username", value = "John")
    @Cookie(name = "token", value = "1234")
    public static RestMethod getCookieWithCookies;

    @PUT("/cookie")
    public static RestMethod putCookie;

    @POST("/reflect")
    public static RestMethod postReflect;

    public static RestResponse postReflectWithBody(Object body) {
        return postReflect.call(rd -> rd.setBody(body));
    }

    public static RestResponse postEmptyCookie(String name) {
        return postReflect.call(cookies().add(name));
    }

    public static RestResponse postSpecifiedCookie(String name, String value) {
        return postReflect.call(cookies().add(name, value));
    }

    @GET("/html_with_cookie")
    public static RestMethod getHtmlWithCookie;

    @GET("/setCommonIdCookies")
    public static RestMethod getCommonIdCookies;

    @GET("/header")
    public static RestMethod getHeader;

    @GET("/multiValueHeader")
    public static RestMethod getMultiValueHeader;

    @GET("/multiHeaderReflect")
    public static RestMethod getMultiHeaderReflect;

    public static RestResponse getWithMultipleHeaders(
            io.restassured.http.Header... headerObjects) {
        return getMultiHeaderReflect.call(headers().addAll(headerObjects));
    }

    public static RestResponse getWithMultipleHeaders(
            Object[][] headers) {
        return getMultiHeaderReflect.call(headers().addAll(headers));
    }

    public static RestResponse getWithSingleHeader(
            String name, String value, String... additionalValues) {
        return getMultiHeaderReflect.call(headers().add(name, value).headerUpdater().add(name, additionalValues));
    }

    @DELETE("/cookie")
    public static RestMethod deleteCookie;

    @GET("/greet")
    public static RestDataMethod<GreetingItself> getGreetingItselfObject;

    @GET("/greet")
    public static RestMethod getGreet;

    public static RestResponse getGreetWithMapOfQueryParams(Map<String, String> queryParamsMap) {
        return getGreet.call(rd -> rd.queryParams.addAll(queryParamsMap));
    }

    @DELETE("/greet")
    public static RestMethod deleteGreet;

    @DELETE("/body")
    public static RestMethod deleteBody;

    @POST("/greetXML")
    @ContentType(URLENC)
    public static RestMethod postGreetXml;

    @ContentType(JSON)
    @POST("/jsonBody")
    public static RestMethod jsonBodyPost;

    public static RestResponse jsonBodyPost(String body) {
        return jsonBodyPost.call(rd -> rd.setBody(body));
    }

    @POST("/secured/hello")
    public static RestMethod unauthorizedPost;

    @POST("/cookie")
    public static RestMethod cookiePost;

    public static RestResponse cookiePost(Map<String, Object> cookiesMap) {
        return cookiePost.call(cookies().addAll(cookiesMap));
    }

    @POST("/param-reflect")
    public static RestMethod paramUrlPost;

    @ContentType(TEXT)
    @POST("/body")
    public static RestMethod bodyPost;

    public static RestResponse bodyPost(Object body) {
        return postReflect.call(rd -> rd.setBody(body));
    }

    @POST("/greet")
    public static RestMethod greetPost;

    public static RestResponse greetPostWithContentTypeAndMapOfFormParams(
            String contentType, Map<String, String> formParamsMap) {
        return greetPost.call(rd -> {
            rd.setContentType(contentType);
            rd.formParams.addAll(formParamsMap);
        });
    }

    public static RestResponse greetPostWithStringOfQueryParams(String queryParams) {
        return greetPost.queryParams(queryParams).call();
    }

    public static RestResponse greetPost(Object[][] queryParams) {
        return greetPost.call(queryParams().addAll(queryParams));
    }

    @POST("/notexist")
    public static RestMethod notFoundedURIPost;

    @POST("/header")
    @Header(name = "MyHeader", value = "Something")
    public static RestMethod headerPost;

    @GET("/hello")
    public static RestMethod getHello;

    @GET("/secured/hello")
    public static RestDataMethod<Hello> getSecuredHelloObject;

    @GET("/secured/hello")
    public static RestMethod getSecuredHello;

    @QueryParameter(name = "firstName", value = "John")
    @QueryParameter(name = "lastName", value = "Doe")
    @GET("/greetXML")
    public static RestMethod getGreetXml;

    @GET("/mimeTypeWithPlusJson")
    public static RestMethod getMimeType;

    @GET("/shopping")
    public static RestMethod getShopping;

    @GET("/products")
    public static RestMethod getProducts;

    public static List<Product> getProducts() {
        return Arrays.asList(getProducts.callAsData(Product[].class));
    }

    @GET("/products")
    public static RestDataMethod<List<Product>> getProductsAsList;

    @GET("/products")
    public static RestDataMethod<Product[]> getProductsAsArray;

    @GET("/jsonStore")
    public static RestMethod getJsonStore;

    @GET("/contentTypeAsBody")
    public static RestMethod getContentTypeAsBody;

    @POST("/return204WithContentType")
    @ContentType(JSON)
    public static RestMethod postReturn204WithContentType;

    @GET("/headersWithValues")
    public static RestMethod getHeadersWithValues;

    @GET("/lotto")
    public static RestMethod getLotto;

    @GET("/noValueParam")
    public static RestMethod getNoValueParam;

    public static RestResponse getNoValueParamWithKeyValueQueryParam(String paramName, String paramValue) {
        return getNoValueParam.call(rd -> rd.queryParams.add(paramName, paramValue));
    }

    public static RestResponse getNoValueParamWithStringQueryParams(String queryParam) {
        return getNoValueParam.queryParams(queryParam).call();
    }

    @PUT("/noValueParam")
    public static RestMethod putNoValueParam;

    public static RestResponse putNoValueParamWithKeyValueFormParam(
            String formParamKey, String formParamValue) {
        return putNoValueParam.call(formParams().add(formParamKey, formParamValue));
    }

    @POST("/noValueParam")
    public static RestMethod postNoValueParam;

    public static RestResponse postNoValueParamWithKeyValueFormParam(
            String formParamKey, String formParamValue) {
        return postNoValueParam.call(formParams().add(formParamKey, formParamValue));
    }

    public static RestResponse postNoValueParamWithMapOfFormParams(Map<String, String> formParamsMap) {
        return postNoValueParam.call(formParams().addAll(formParamsMap));
    }

    @POST("/noValueParam")
    @FormParameter(name = "some1", value = "one")
    public static RestMethod postNoValueParamWithPreDefinedFormParam;

    public static RestResponse postNoValueParamWithPreDefinedFormParamAndNewKeyValueParam(
            String formParamKey, String formParamValue) {
        return postNoValueParamWithPreDefinedFormParam.call(formParams().add(formParamKey, formParamValue));
    }

    @POST("/charEncoding")
    public static RestMethod postCharEncoding;

    public static RestResponse postCharEncodingWithContentTypeAndKeyValueFormParam(
            String contentType, String formParamKey, String formParamValue) {
        return postCharEncoding.call(rd -> {
            rd.setContentType(contentType);
            rd.formParams.add(formParamKey, formParamValue);
        });
    }

    @POST("/reflect")
    @ContentType(JSON)
    public static RestMethod postObject;

    @GET("/redirect")
    @Header(name = "Redirect_test_header", value = "Redirect_test_header_value")
    public static RestMethod getRedirect;

    @GET("/{firstName}/{lastName}")
    public static RestDataMethod<User> getUserObject;

    @GET("/{firstName}/{lastName}")
    public static RestMethod getUser;

    public static RestResponse getUserPathParamsSetByArray(Object[][] array) {
        return getUser.call(pathParams().addAll(array));
    }

    public static RestResponse getUserPathParamsSetByMap(Map<String, String> params) {
        return getUser.call(pathParams().addAll(params));
    }

    @GET("/{firstName}/{firstName}")
    public static RestMethod getUserSameParameters;

    public static RestResponse getUserSameParametersSetByArray(Object[][] array) {
        return getUserSameParameters
                .call(pathParams().addAll(array));
    }

    @GET("/{firstName}/{middleName}/{lastName}")
    public static RestMethod getUserWithLastName;

    @URL("http://www.google.se")
    @GET("/search?q={query}&hl=en")
    public static RestMethod searchGoogle;

    @GET("/{channelName}/item-import/rss/import?source={url}")
    public static RestMethod getMixedParam;

    @GET("/{path}.json")
    public static RestMethod getParamBeforePath;

    @GET("/something.{format}")
    public static RestMethod getParamAfterPath;

    @GET("/matrix;{abcde}={value}")
    public static RestMethod getMatrix;

    @GET("/cookie_with_no_value")
    @Cookie(name = "some_cookie")
    public static RestMethod getCookieWithNoValueWithCookies;

    @GET("/multiCookieRequest")
    @Cookie(name = "key1", value = "value1", additionalValues = "value2")
    public static RestMethod getMultiCookieWithCookies;

    @GET("/multiCookieRequest")
    @Cookie(name = "key1", value = "value1", additionalValues = "value2")
    @Cookie(name = "key2")
    @Cookie(name = "key3", value = "value3")
    @Cookie(name = "key4", value = "value4")
    public static RestMethod getMultiCookieWithManyCookies;

    @POST("/multipart/file")
    @MultiPart(controlName = "file", fileName = "myFile")
    public static RestMethod postMultiPartFile;

    public static RestResponse postMultiPartFile(byte[] file, String fileName) {
        postMultiPartFile.multipart(file);
        postMultiPartFile.multipart(fileName);
        return postMultiPartFile.call();
    }

    @POST("/multipart/file")
    @MultiPart(filePath = "/src/test/resources/car-records.xsd")
    public static RestMethod postMultipartFileCar;

    @POST("/multipart/text")
    public static RestMethod postMultiPartText;

    @GET("/textHTML")
    public static RestMethod getTextHtml;

    @POST("multipart/multiple")
    public static RestMethod postMultiPartMultiple;

    public static RestResponse postMultiPartMultipleWithFormParamsAndMPBuilders(Map<String, String> formParamsMap,
                                                                                MultiPartSpecBuilder... multiPartSpecBuilders) {
        return postMultiPartMultiple.call(rd -> {
            rd.formParams.addAll(formParamsMap);
            Arrays.stream(multiPartSpecBuilders).forEach(rd::setMultiPart);
        });
    }

    @GET("/returnContentTypeAsBody")
    public static RestMethod getReturnContentTypeAsBody;

    @POST("/textUriList")
    public static RestMethod postTextUriList;

    @PUT("/reflect")
    public static RestMethod putReflect;

    @POST("contentTypeAsBody")
    public static RestMethod postContentTypeAsBody;

    @GET("/reflect")
    public static RestMethod getReflect;

    @POST("/returnContentTypeAsBody")
    public static RestMethod postReturnContentTypeAsBody;

    @POST("/jsonBodyAcceptHeader")
    public static RestMethod postJsonBodyAcceptHeader;

    public static RestResponse postJsonBodyAcceptHeader(String headerName, String headerValue, String body) {
        return postJsonBodyAcceptHeader.call(headers().add(headerName, headerValue).setBody(body));
    }

    @GET("/greetJSON")
    @Proxy(host = "127.0.0.1", port = 8888, scheme = "http")
    public static RestMethod getGreenJSONWithProxyParams;

    @GET("/greetJSON")
    public static RestMethod getGreenJSON;

}
