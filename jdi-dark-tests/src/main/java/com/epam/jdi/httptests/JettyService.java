package com.epam.jdi.httptests;

import com.epam.http.annotations.ContentType;
import com.epam.http.annotations.Cookie;
import com.epam.http.annotations.Cookies;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.FormParameter;
import com.epam.http.annotations.FormParameters;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.Header;
import com.epam.http.annotations.MultiPart;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import com.epam.http.annotations.Proxy;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.annotations.QueryParameters;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.annotations.URL;
import com.epam.http.requests.RestMethod;
import com.epam.http.response.RestResponse;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;

import java.util.*;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.RequestData.requestPathParams;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static io.restassured.http.ContentType.URLENC;

@ServiceDomain("http://localhost:8080")
public class JettyService {

    @GET("/multiCookie")
    public static RestMethod getMultiCookie;

    @GET("/multiCookieRequest")
    public static RestMethod getMultiCookieRequest;

    public static RestResponse getMultiCookiesArray(Object[][] cookiesArray) {
        return getMultiCookieRequest.call(requestData(
                requestData -> requestData.addCookies(cookiesArray)));
    }

    public static RestResponse getMultiCookieWithOneName(String name, String value1, String value2) {
        return getMultiCookieRequest.call(requestData(
                requestData -> requestData.addCookie(name, value1, value2)));
    }

    public static RestResponse getMultiCookieSpecified(String name, String value) {
        return getMultiCookieRequest.call(requestData(requestData -> requestData.addCookies(name, value)));
    }

    @GET("/setCookies")
    public static RestMethod setCookies;

    @GET("/cookie_with_no_value")
    public static RestMethod getCookieWithNoValue;

    public static RestResponse getCookieWithOnlyName(String name) {
        return getCookieWithNoValue.call(requestData(requestData -> requestData.addCookie(name)));
    }

    @GET("/response_cookie_with_no_value")
    public static RestMethod getResponseCookieWithNoValue;

    @GET("/cookie")
    public static RestMethod getCookie;

    public static RestResponse getCookieSpecifiedUsingMap(Map<String, String> cookieMap) {
        return getCookie.call(requestData(
                requestData -> requestData.addCookies(cookieMap)));
    }

    public static RestResponse getMultipleCookieSpecifiedUsingMap(Map<String, String> cookieMap, String addCookieName, String addCookieValue) {
        return JettyService.getCookie.call(requestData(
                requestData -> requestData.addCookies(cookieMap).addCookies(addCookieName, addCookieValue)));
    }

    public static RestResponse getSpecifiedCookiePairs(String namePair1, String valuePair1, String namePair2, String valuePair2) {
        return getCookie.call(requestData(
                requestData -> requestData.addCookies(namePair1, valuePair1, namePair2, valuePair2)));
    }

    @GET("/cookie")
    @Cookies({@Cookie(name = "username", value = "John"),
            @Cookie(name = "token", value = "1234")})
    public static RestMethod getCookieWithCookies;

    public void s(String c1, String v1) {
        getCookieWithCookies.call();
    }

    public static RestResponse getCookieWithNameValuePair(String name, String value) {
        return getCookieWithCookies.call(requestData(requestData -> requestData.addCookies(name, value)));
    }

    @PUT("/cookie")
    public static RestMethod putCookie;

    @POST("/reflect")
    public static RestMethod postReflect;

    public static RestResponse postEmptyCookie(String name) {
        return postReflect.call(requestData(requestData -> requestData.addCookie(name)));
    }

    public static RestResponse postSpecifiedCookie(String name, String value) {
        return postReflect.call(requestData(requestData ->
                requestData.addCookie(name, value)));
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
        return getMultiHeaderReflect.call(
                requestData(requestData -> requestData.addHeaders(headerObjects)));
    }

    public static RestResponse getWithMultipleHeaders(
            Object[][] headers) {
        return getMultiHeaderReflect.call(requestData(requestData ->
                requestData.addHeaders(headers)));
    }

    public static RestResponse getWithSingleHeader(
            String name, String value, String... additionalValues) {
        return getMultiHeaderReflect.call(requestData(requestData ->
                requestData.addHeader(name, value, additionalValues)));
    }

    @DELETE("/cookie")
    public static RestMethod deleteCookie;

    @GET("/greet")
    public static RestMethod getGreet;

    public static RestResponse getGreetWithMapOfQueryParams(Map<String, String> queryParamsMap) {
        return getGreet.call(requestData(d -> d.queryParams.addAll(queryParamsMap)));
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

    @POST("/secured/hello")
    public static RestMethod unauthorizedPost;

    @POST("/cookie")
    public static RestMethod cookiePost;

    @POST("/param-reflect")
    public static RestMethod paramUrlPost;

    @ContentType(TEXT)
    @POST("/body")
    public static RestMethod bodyPost;

    @POST("/greet")
    public static RestMethod greetPost;

    public static RestResponse greetPostWithContentTypeAndMapOfFormParams(String contentType,
                                                                          Map<String, String> formParamsMap) {
        return greetPost.call(requestData(rd -> {
            rd.setContentType(contentType);
            rd.formParams.addAll(formParamsMap);
        }));
    }

    @POST("/notexist")
    public static RestMethod notFoundedURIPost;

    @POST("/header")
    @Header(name = "MyHeader", value = "Something")
    public static RestMethod headerPost;

    @GET("/hello")
    public static RestMethod getHello;

    @QueryParameters({
            @QueryParameter(name = "firstName", value = "John"),
            @QueryParameter(name = "lastName", value = "Doe")
    })
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
        return getNoValueParam.call(requestData(d -> d.queryParams.add(paramName, paramValue)));
    }

    public static RestResponse getNoValueParamWithStringQueryParams(String queryParam) {
        return getNoValueParam.call(queryParam);
    }

    @PUT("/noValueParam")
    public static RestMethod putNoValueParam;

    public static RestResponse putNoValueParamWithKeyValueFormParam(String formParamKey,
                                                                    String formParamValue) {
        return putNoValueParam.call(requestData(rd -> rd.formParams.add(formParamKey, formParamValue)));
    }

    @POST("/noValueParam")
    public static RestMethod postNoValueParam;

    public static RestResponse postNoValueParamWithKeyValueFormParam(String formParamKey,
                                                                     String formParamValue) {
        return postNoValueParam.call(requestData(rd -> rd.formParams.add(formParamKey, formParamValue)));
    }

    public static RestResponse postNoValueParamWithMapOfFormParams(Map<String, String> formParamsMap) {
        return postNoValueParam.call(requestData(rd -> rd.formParams.addAll(formParamsMap)));
    }

    @POST("/noValueParam")
    @FormParameters(
            @FormParameter(name = "some1", value = "one")
    )
    public static RestMethod postNoValueParamWithPreDefinedFormParam;

    public static RestResponse postNoValueParamWithPreDefinedFormParamAndNewKeyValueParam(String addedFormParamKey,
                                                                                          String addedFormParamValue) {
        return postNoValueParamWithPreDefinedFormParam.call(requestData(rd -> {
            rd.formParams.add(addedFormParamKey, addedFormParamValue);
        }));
    }

    @POST("/charEncoding")
    public static RestMethod postCharEncoding;

    public static RestResponse postCharEncodingWithContentTypeAndKeyValueFormParam(String contentType,
                                                                                   String formParamKey,
                                                                                   String formParamValue) {
        return postCharEncoding.call(requestData(rd -> {
            rd.contentType = contentType;
            rd.formParams.add(formParamKey, formParamValue);
        }));
    }

    @POST("/reflect")
    @ContentType(JSON)
    public static RestMethod postObject;

    @GET("/redirect")
    @Header(name = "Redirect_test_header", value = "Redirect_test_header_value")
    public static RestMethod getRedirect;

    @GET("/{firstName}/{lastName}")
    public static RestMethod getUser;

    public static RestResponse getUserPathParamsSetByArray(Object[][] array) {
        return getUser
                .call(requestPathParams(array));
    }

    public static RestResponse getUserPathParamsSetByMap(Map<String, String> params) {
        return getUser
                .call(requestData(d -> d.pathParams.addAll(params)));
    }

    public static void getUserPassPathParamsSetByArray(Object[][] array) {
        getUser
                .call(requestPathParams(array));
    }

    @GET("/{firstName}/{firstName}")
    public static RestMethod getUserSameParameters;

    public static RestResponse getUserSameParametersSetByArray(Object[][] array) {
        return getUserSameParameters
                .call(requestPathParams(array));
    }

    @GET("/{firstName}/{middleName}/{lastName}")
    public static RestMethod getUserWithLastName;

    @URL("http://www.google.se")
    @GET("/search?q={query}&hl=en")
    public static RestMethod searchGoogle;

    public static RestResponse searchGoogleSpecificParam(String param) {
        return searchGoogle.call(param);
    }

    @GET("/{channelName}/item-import/rss/import?source={url}")
    public static RestMethod getMixedparam;

    @GET("/{path}.json")
    public static RestMethod getParamBeforePath;

    public static RestResponse getNamedParamBeforePath(String paramName, String paramValue) {
        return getParamBeforePath.call(requestPathParams(paramName, paramValue));
    }

    @GET("/something.{format}")
    public static RestMethod getParamAfterPath;

    public static RestResponse getNamedParamAfterPath(String paramName, String paramValue) {
        return getParamAfterPath.call(requestPathParams(paramName, paramValue));
    }

    @GET("/matrix;{abcde}={value}")
    public static RestMethod getMatrix;

    public static RestResponse getMatrixPathParamsSetByArray(Object[][] array) {
        return getMatrix
                .call(requestPathParams(array));
    }

    @GET("/cookie_with_no_value")
    @Cookie(name = "some_cookie")
    public static RestMethod getCookieWithNoValueWithCookies;

    @GET("/multiCookieRequest")
    @Cookie(name = "key1", value = "value1", additionalValues = "value2")
    public static RestMethod getMultiCookieWithCookies;

    @GET("/multiCookieRequest")
    @Cookies({@Cookie(name = "key1", value = "value1", additionalValues = "value2"),
            @Cookie(name = "key2"),
            @Cookie(name = "key3", value = "value3")})
    @Cookie(name = "key4", value = "value4")
    public static RestMethod getMultiCookieWithManyCookies;

    @POST("/multipart/file")
    @MultiPart(controlName = "file", fileName = "myFile")
    public static RestMethod postMultipartFile;

    public static RestResponse postMultipartFile(byte[] file) {
        ((MultiPartSpecificationImpl) postMultipartFile.getData().multiPartSpecifications.get(0)).setContent(file);
        return postMultipartFile.call();
    }

    public static RestResponse postMultipartFile(byte[] file, String fileName) {
        ((MultiPartSpecificationImpl) postMultipartFile.getData().multiPartSpecifications.get(0)).setContent(file);
        ((MultiPartSpecificationImpl) postMultipartFile.getData().multiPartSpecifications.get(0)).setFileName(fileName);
        return postMultipartFile.call();
    }

    @POST("/multipart/text")
    public static RestMethod postMultipartText;

    @GET("/textHTML")
    public static RestMethod getTextHtml;

    @POST("multipart/multiple")
    public static RestMethod postMultipartMultiple;

    public static RestResponse postMultipartMultipleWithFormParamsAndMultiPartBuilders(Map<String, String> formParamsMap,
                                                                                       MultiPartSpecBuilder... multiPartSpecBuilders) {
        return postMultipartMultiple.call(requestData(rd -> {
            rd.formParams.addAll(formParamsMap);
            Arrays.stream(multiPartSpecBuilders).forEach(rd::setMultiPart);
        }));
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

    @GET("/greetJSON")
    @Proxy(host = "127.0.0.1", port = 8888, scheme = "http")
    public static RestMethod getGreenJSONWithProxyParams;

    @GET("/greetJSON")
    public static RestMethod getGreenJSON;
}
