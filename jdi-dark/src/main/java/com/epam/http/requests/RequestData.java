package com.epam.http.requests;

import com.epam.http.requests.updaters.CookieUpdater;
import com.epam.http.requests.updaters.PathParamsUpdater;
import com.epam.http.requests.updaters.QueryParamsUpdater;
import com.epam.http.requests.updaters.HeaderUpdater;
import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.map.MultiMap;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.*;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;

import java.io.File;
import java.util.ArrayList;
/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class RequestData extends DataClass<RequestData> {
    public boolean empty = true;
    public String uri = null;
    public String path = null;
    public Object body = null;
    public String contentType = null;
    public Headers headers = new Headers();
    public Cookies cookies = new Cookies();
    public MultiMap<String, String> pathParams = new MultiMap<>();
    public MultiMap<String, String> queryParams = new MultiMap<>();
    public MultiMap<String, String> formParams = new MultiMap<>();
    public ArrayList<MultiPartSpecification> multiPartSpecifications = new ArrayList<>();
    public ProxySpecification proxySpecification = null;
    public AuthenticationScheme authenticationScheme = null;
    public Pair<String, String> trustStore = null;
    public CookieUpdater addCookies() { return new CookieUpdater(() -> this); }
    public HeaderUpdater addHeaders() { return new HeaderUpdater(() -> this); }
    public QueryParamsUpdater addQueryParams() { return new QueryParamsUpdater(() -> this); }
    public PathParamsUpdater addPathParams() { return new PathParamsUpdater(() -> this); }
    /**
     * Set content type to request data.
     *
     * @param contentType  content type as string
     */
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType  content type as ContentType
     */
    public void setContentType(ContentType contentType){
        this.contentType = contentType.toString();
    }

    /**
     * Set multipart parameters to request data.
     *
     * @param multiPartSpecBuilder  MultiPartSpecBuilder
     */
    public void setMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
        multiPartSpecifications.add(multiPartSpecBuilder.build());
    }

    /**
     * Set authentication scheme to request data
     * This allows authentcation for requests
     * @param authScheme authentication scheme: from restassured or custom
     */

    public void setAuth(AuthenticationScheme authScheme) {
        authenticationScheme = authScheme;
    }

    /**
     * Set multipart parameters to request data.
     *
     * @param file  File parameter
     */
    public void setMultiPart(File file) {
        multiPartSpecifications.add(new MultiPartSpecBuilder(file).build());
    }

    /**
     * Set proxy parameters to request data.
     *
     * @param scheme scheme
     * @param host host
     * @param port port
     */
    public void setProxySpecification(String scheme, String host, int port) {
        this.proxySpecification = ProxySpecification.host(host).and().withPort(port).and().withScheme(scheme);
    }

    /**
     * Set trustStore parameters to request data.
     *
     * @param pathToJks pathToJks
     * @param password password
     */
    public RequestData requestTrustStore(String pathToJks, String password){
        this.trustStore = new Pair<>(pathToJks, password);
        return this;
    }

    /**
     * Clean Custom user Request data to avoid using old data in new requests
     */
    public void clear() {
        headers = new Headers();
        pathParams.clear();
        queryParams.clear();
        formParams.clear();
        cookies = new Cookies();
        body = null;
        path = null;
        uri = null;
        contentType = null;
        empty = true;
        multiPartSpecifications.clear();
        proxySpecification = null;
        authenticationScheme = null;
        trustStore = null;
    }
}
