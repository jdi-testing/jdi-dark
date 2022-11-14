package com.epam.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestDataMethod;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import com.epam.jdi.dto.Product;
import com.epam.jdi.dto.GreetingItself;
import com.epam.jdi.dto.Hello;
import com.epam.jdi.dto.User;
import io.restassured.builder.MultiPartSpecBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.epam.http.requests.RequestDataFactory.cookies;
import static com.epam.http.requests.RequestDataFactory.formParams;
import static com.epam.http.requests.RequestDataFactory.headers;
import static com.epam.http.requests.RequestDataFactory.pathParams;
import static com.epam.http.requests.RequestDataFactory.queryParams;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static io.restassured.http.ContentType.URLENC;

@ServiceDomain("http://localhost:8081")
public class JettyService {

    @GET("/multiCookie")
    public RestMethod getMultiCookie;

    @GET("/multiCookieRequest")
    public RestMethod getMultiCookieRequest;

    @GET("/setCookies")
    public RestMethod setCookies;

    @GET("/cookie_with_no_value")
    public RestMethod getCookieWithNoValue;

    @GET("/response_cookie_with_no_value")
    public RestMethod getResponseCookieWithNoValue;

    @GET("/cookie")
    public RestMethod getCookie;

    public RestResponse getMultipleCookieSpecifiedUsingMap(Map<String, String> cookieMap, String addCookieName, String addCookieValue) {
        return getCookie.call(cookies().addAll(cookieMap).cookieUpdater().add(addCookieName, addCookieValue));
    }

    public RestResponse getSpecifiedCookiePairs(String namePair1, String valuePair1, String namePair2, String valuePair2) {
        return getCookie.call(cookies().addAll(new Object[][]{{namePair1, valuePair1}, {namePair2, valuePair2}}));
    }

    @GET("/cookie")
    @Cookie(name = "username", value = "John")
    @Cookie(name = "token", value = "1234")
    public RestMethod getCookieWithCookies;

    @PUT("/cookie")
    public RestMethod putCookie;

    @POST("/reflect")
    public RestMethod postReflect;

    public RestResponse postReflectWithBody(Object body) {
        return postReflect.call(rd -> rd.setBody(body));
    }

    public RestResponse postEmptyCookie(String name) {
        return postReflect.call(cookies().add(name));
    }

    public RestResponse postSpecifiedCookie(String name, String value) {
        return postReflect.call(cookies().add(name, value));
    }

    @GET("/html_with_cookie")
    public RestMethod getHtmlWithCookie;

    @GET("/setCommonIdCookies")
    public RestMethod getCommonIdCookies;

    @GET("/header")
    public RestMethod getHeader;

    @GET("/multiValueHeader")
    public RestMethod getMultiValueHeader;

    @GET("/multiHeaderReflect")
    public RestMethod getMultiHeaderReflect;

    public RestResponse getWithMultipleHeaders(
            io.restassured.http.Header... headerObjects) {
        return getMultiHeaderReflect.call(headers().addAll(headerObjects));
    }

    public RestResponse getWithMultipleHeaders(
            Object[][] headers) {
        return getMultiHeaderReflect.call(headers().addAll(headers));
    }

    public RestResponse getWithSingleHeader(
            String name, String value, String... additionalValues) {
        return getMultiHeaderReflect.call(headers().add(name, value).headerUpdater().add(name, additionalValues));
    }

    @DELETE("/cookie")
    public RestMethod deleteCookie;

    @GET("/greet")
    public RestDataMethod<GreetingItself> getGreetingItselfObject;

    @GET("/greet")
    public RestMethod getGreet;

    public RestResponse getGreetWithMapOfQueryParams(Map<String, String> queryParamsMap) {
        return getGreet.call(rd -> rd.queryParams.addAll(queryParamsMap));
    }

    @DELETE("/greet")
    public RestMethod deleteGreet;

    @DELETE("/body")
    public RestMethod deleteBody;

    @POST("/greetXML")
    @ContentType(URLENC)
    public RestMethod postGreetXml;

    @ContentType(JSON)
    @POST("/jsonBody")
    public RestMethod jsonBodyPost;

    public RestResponse jsonBodyPost(String body) {
        return jsonBodyPost.call(rd -> rd.setBody(body));
    }

    @POST("/secured/hello")
    public RestMethod unauthorizedPost;

    @POST("/cookie")
    public RestMethod cookiePost;

    public RestResponse cookiePost(Map<String, Object> cookiesMap) {
        return cookiePost.call(cookies().addAll(cookiesMap));
    }

    @POST("/param-reflect")
    public RestMethod paramUrlPost;

    @ContentType(TEXT)
    @POST("/body")
    public RestMethod bodyPost;

    public RestResponse bodyPost(Object body) {
        return postReflect.call(rd -> rd.setBody(body));
    }

    @POST("/greet")
    public RestMethod greetPost;

    public RestResponse greetPostWithContentTypeAndMapOfFormParams(
            String contentType, Map<String, String> formParamsMap) {
        return greetPost.call(rd -> {
            rd.setContentType(contentType);
            rd.formParams.addAll(formParamsMap);
        });
    }

    public RestResponse greetPostWithStringOfQueryParams(String queryParams) {
        return greetPost.queryParams(queryParams).call();
    }

    public RestResponse greetPost(Object[][] queryParams) {
        return greetPost.call(queryParams().addAll(queryParams));
    }

    @POST("/notexist")
    public RestMethod notFoundedURIPost;

    @POST("/header")
    @Header(name = "MyHeader", value = "Something")
    public RestMethod headerPost;

    @GET("/hello")
    public RestMethod getHello;

    @GET("/secured/hello")
    public RestDataMethod<Hello> getSecuredHelloObject;

    @GET("/secured/hello")
    public RestMethod getSecuredHello;

    @QueryParameter(name = "firstName", value = "John")
    @QueryParameter(name = "lastName", value = "Doe")
    @GET("/greetXML")
    public RestMethod getGreetXml;

    @GET("/mimeTypeWithPlusJson")
    public RestMethod getMimeType;

    @GET("/shopping")
    public RestMethod getShopping;

    @GET("/products")
    public RestMethod getProducts;

    public List<Product> getProducts() {
        return Arrays.asList(getProducts.callAsData(Product[].class));
    }

    @GET("/products")
    public RestDataMethod<List<Product>> getProductsAsList;

    @GET("/products")
    public RestDataMethod<Product[]> getProductsAsArray;

    @GET("/jsonStore")
    public RestMethod getJsonStore;

    @GET("/contentTypeAsBody")
    public RestMethod getContentTypeAsBody;

    @POST("/return204WithContentType")
    @ContentType(JSON)
    public RestMethod postReturn204WithContentType;

    @GET("/headersWithValues")
    public RestMethod getHeadersWithValues;

    @GET("/lotto")
    public RestMethod getLotto;

    @GET("/noValueParam")
    public RestMethod getNoValueParam;

    public RestResponse getNoValueParamWithKeyValueQueryParam(String paramName, String paramValue) {
        return getNoValueParam.call(rd -> rd.queryParams.add(paramName, paramValue));
    }

    public RestResponse getNoValueParamWithStringQueryParams(String queryParam) {
        return getNoValueParam.queryParams(queryParam).call();
    }

    @PUT("/noValueParam")
    public RestMethod putNoValueParam;

    public RestResponse putNoValueParamWithKeyValueFormParam(
            String formParamKey, String formParamValue) {
        return putNoValueParam.call(formParams().add(formParamKey, formParamValue));
    }

    @POST("/noValueParam")
    public RestMethod postNoValueParam;

    public RestResponse postNoValueParamWithKeyValueFormParam(
            String formParamKey, String formParamValue) {
        return postNoValueParam.call(formParams().add(formParamKey, formParamValue));
    }

    public RestResponse postNoValueParamWithMapOfFormParams(Map<String, String> formParamsMap) {
        return postNoValueParam.call(formParams().addAll(formParamsMap));
    }

    @POST("/noValueParam")
    @FormParameter(name = "some1", value = "one")
    public RestMethod postNoValueParamWithPreDefinedFormParam;

    public RestResponse postNoValueParamWithPreDefinedFormParamAndNewKeyValueParam(
            String formParamKey, String formParamValue) {
        return postNoValueParamWithPreDefinedFormParam.call(formParams().add(formParamKey, formParamValue));
    }

    @POST("/charEncoding")
    public RestMethod postCharEncoding;

    public RestResponse postCharEncodingWithContentTypeAndKeyValueFormParam(
            String contentType, String formParamKey, String formParamValue) {
        return postCharEncoding.call(rd -> {
            rd.setContentType(contentType);
            rd.formParams.add(formParamKey, formParamValue);
        });
    }

    @POST("/reflect")
    @ContentType(JSON)
    public RestMethod postObject;

    @GET("/redirect")
    @Header(name = "Redirect_test_header", value = "Redirect_test_header_value")
    public RestMethod getRedirect;

    @GET("/{firstName}/{lastName}")
    public RestDataMethod<User> getUserObject;

    @GET("/{firstName}/{lastName}")
    public RestMethod getUser;

    public RestResponse getUserPathParamsSetByArray(Object[][] array) {
        return getUser.call(pathParams().addAll(array));
    }

    public RestResponse getUserPathParamsSetByMap(Map<String, String> params) {
        return getUser.call(pathParams().addAll(params));
    }

    @GET("/{firstName}/{firstName}")
    public RestMethod getUserSameParameters;

    public RestResponse getUserSameParametersSetByArray(Object[][] array) {
        return getUserSameParameters
                .call(pathParams().addAll(array));
    }

    @GET("/{firstName}/{middleName}/{lastName}")
    public RestMethod getUserWithLastName;

    @URL("http://www.google.se")
    @GET("/search?q={query}&hl=en")
    public RestMethod searchGoogle;

    @GET("/{channelName}/item-import/rss/import?source={url}")
    public RestMethod getMixedParam;

    @GET("/{path}.json")
    public RestMethod getParamBeforePath;

    @GET("/something.{format}")
    public RestMethod getParamAfterPath;

    @GET("/matrix;{abcde}={value}")
    public RestMethod getMatrix;

    @GET("/cookie_with_no_value")
    @Cookie(name = "some_cookie")
    public RestMethod getCookieWithNoValueWithCookies;

    @GET("/multiCookieRequest")
    @Cookie(name = "key1", value = "value1", additionalValues = "value2")
    public RestMethod getMultiCookieWithCookies;

    @GET("/multiCookieRequest")
    @Cookie(name = "key1", value = "value1", additionalValues = "value2")
    @Cookie(name = "key2")
    @Cookie(name = "key3", value = "value3")
    @Cookie(name = "key4", value = "value4")
    public RestMethod getMultiCookieWithManyCookies;

    @POST("/multipart/file")
    @MultiPart(controlName = "file", fileName = "myFile")
    public RestMethod postMultiPartFile;

    public RestResponse postMultiPartFile(byte[] file, String fileName) {
        postMultiPartFile.multipart(file);
        postMultiPartFile.multipart(fileName);
        return postMultiPartFile.call();
    }

    @POST("/multipart/file")
    @MultiPart(filePath = "/src/test/resources/car-records.xsd")
    public RestMethod postMultipartFileCar;

    @POST("/multipart/text")
    public RestMethod postMultiPartText;

    @GET("/textHTML")
    public RestMethod getTextHtml;

    @POST("/multipart/multiple")
    public RestMethod postMultiPartMultiple;

    public RestResponse postMultiPartMultipleWithFormParamsAndMPBuilders(Map<String, String> formParamsMap,
                                                                                MultiPartSpecBuilder... multiPartSpecBuilders) {
        return postMultiPartMultiple.call(rd -> {
            rd.formParams.addAll(formParamsMap);
            Arrays.stream(multiPartSpecBuilders).forEach(rd::setMultiPart);
        });
    }

    @GET("/returnContentTypeAsBody")
    public RestMethod getReturnContentTypeAsBody;

    @POST("/textUriList")
    public RestMethod postTextUriList;

    @PUT("/reflect")
    public RestMethod putReflect;

    @POST("/contentTypeAsBody")
    public RestMethod postContentTypeAsBody;

    @GET("/reflect")
    public RestMethod getReflect;

    @POST("/returnContentTypeAsBody")
    public RestMethod postReturnContentTypeAsBody;

    @POST("/jsonBodyAcceptHeader")
    public RestMethod postJsonBodyAcceptHeader;

    public RestResponse postJsonBodyAcceptHeader(String headerName, String headerValue, String body) {
        return postJsonBodyAcceptHeader.call(headers().add(headerName, headerValue).setBody(body));
    }

    @GET("/greetJSON")
    @Proxy(host = "127.0.0.1", port = 8888, scheme = "http")
    public RestMethod getGreenJSONWithProxyParams;

    @GET("/greetJSON")
    public RestMethod getGreenJSON;

}
