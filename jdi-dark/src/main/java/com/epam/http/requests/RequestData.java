package com.epam.http.requests;

import com.epam.http.requests.updaters.*;
import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.map.MultiMap;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.*;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
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

    public CookieUpdater addCookies() {
        return new CookieUpdater(() -> this);
    }

    public HeaderUpdater addHeaders() {
        return new HeaderUpdater(() -> this);
    }

    public QueryParamsUpdater addQueryParams() {
        return new QueryParamsUpdater(() -> this);
    }

    public PathParamsUpdater addPathParams() {
        return new PathParamsUpdater(() -> this);
    }

    public FormParamsUpdater addFormParams() {
        return new FormParamsUpdater(() -> this);
    }

    /**
     * Set request body to request data.
     *
     * @param body as formatted String
     * @return generated request data with provided request body
     */
    public RequestData requestBody(Object body) {
        this.body = body;
        return this;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType content type as string
     */
    public RequestData setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType content type as ContentType
     */
    public RequestData setContentType(ContentType contentType) {
        this.contentType = contentType.toString();
        return this;
    }

    /**
     * Add multipart spec to request data.
     *
     * @param multiPartSpecBuilder MultiPartSpecBuilder
     */
    public RequestData setMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
        this.multiPartSpecifications.add(multiPartSpecBuilder.build());
        return this;
    }

    /**
     * Set authentication scheme to request data
     * This allows authentication for requests
     *
     * @param authScheme authentication scheme: from restassured or custom
     */

    public RequestData setAuth(AuthenticationScheme authScheme) {
        this.authenticationScheme = authScheme;
        return this;
    }

    /**
     * Set proxy parameters to request data.
     *
     * @param scheme scheme
     * @param host   host
     * @param port   port
     */
    public RequestData setProxySpecification(String scheme, String host, int port) {
        this.proxySpecification = ProxySpecification.host(host).and().withPort(port).and().withScheme(scheme);
        return this;
    }

    /**
     * Set trustStore parameters to request data.
     *
     * @param pathToJks pathToJks
     * @param password  password
     */
    public RequestData setTrustStore(String pathToJks, String password) {
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
