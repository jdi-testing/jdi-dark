package com.epam.http.requests;

import com.epam.http.annotations.Proxy;
import com.epam.http.annotations.TrustStore;
import com.epam.http.requests.updaters.CookieUpdater;
import com.epam.http.requests.updaters.FormParamsUpdater;
import com.epam.http.requests.updaters.HeaderUpdater;
import com.epam.http.requests.updaters.PathParamsUpdater;
import com.epam.http.requests.updaters.QueryParamsUpdater;
import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.map.MultiMap;
import com.epam.jdi.tools.pairs.Pair;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;

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
    public ArrayList<MultiPartSpecification> multiPartSpec = new ArrayList<>();
    public ArrayList<Filter> filters = new ArrayList<>();
    public ProxySpecification proxySpec = null;
    public AuthenticationScheme authScheme = null;
    public Pair<String, String> trustStore = null;

    public CookieUpdater cookieUpdater() {
        return new CookieUpdater(() -> this);
    }

    public HeaderUpdater headerUpdater() {
        return new HeaderUpdater(() -> this);
    }

    public QueryParamsUpdater queryParamsUpdater() {
        return new QueryParamsUpdater(() -> this);
    }

    public PathParamsUpdater pathParamsUpdater() {
        return new PathParamsUpdater(() -> this);
    }

    public FormParamsUpdater formParamsUpdater() {
        return new FormParamsUpdater(() -> this);
    }

    /**
     * Set request body to request data.
     *
     * @param body as formatted String
     * @return generated request data with provided request body
     */
    public RequestData setBody(Object body) {
        this.empty = false;
        this.body = body;
        return this;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType content type as string
     * @return generated request data with provided request body
     */
    public RequestData setContentType(String contentType) {
        this.empty = false;
        this.contentType = contentType;
        return this;
    }

    /**
     * Set content type to request data.
     *
     * @param contentType Rest Assured Content-Type
     * @return generated request data with provided request body
     */
    public RequestData setContentType(ContentType contentType) {
        this.empty = false;
        this.contentType = contentType.toString();
        return this;
    }

    /**
     * Add multipart spec to request data.
     *
     * @param multiPartSpecBuilder MultiPartSpecBuilder
     * @return generated request data with provided request body
     */
    public RequestData setMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
        this.empty = false;
        this.multiPartSpec.add(multiPartSpecBuilder.build());
        return this;
    }

    /**
     * Add multipart spec content to request data.
     *
     * @param multiPartContent multiPartContent
     * @return generated request data with provided request body
     */
    public RequestData setMultiPart(Object multiPartContent) {
        this.empty = false;
        this.multiPartSpec.add(new MultiPartSpecBuilder(multiPartContent).build());
        return this;
    }

    /**
     * Set authentication scheme to request data
     * This allows authentication for requests
     *
     * @param authScheme authentication scheme: from restassured or custom
     * @return generated request data with provided request body
     */

    public RequestData setAuthScheme(AuthenticationScheme authScheme) {
        this.empty = false;
        this.authScheme = authScheme;
        return this;
    }

    /**
     * Set proxy parameters to request data.
     *
     * @param scheme scheme
     * @param host   host
     * @param port   port
     * @return generated request data with provided request body
     */
    public RequestData setProxySpec(String scheme, String host, int port) {
        this.empty = false;
        this.proxySpec = ProxySpecification.host(host).and().withPort(port).and().withScheme(scheme);
        return this;
    }

    /**
     * Set proxy parameters to the request.
     *
     * @param proxyParams proxyParams
     * @return generated request data with provided request body
     */
    public RequestData setProxySpec(Proxy proxyParams) {
        setProxySpec(proxyParams.scheme(), proxyParams.host(), proxyParams.port());
        return this;
    }

    /**
     * Set trustStore parameters to request data.
     *
     * @param pathToJks pathToJks
     * @param password  password
     * @return generated request data with provided request body
     */
    public RequestData setTrustStore(String pathToJks, String password) {
        this.empty = false;
        this.trustStore = new Pair<>(pathToJks, password);
        return this;
    }

    /**
     * Set trustStore parameters to the request.
     *
     * @param trustStore trustStore
     * @return generated request data with provided request body
     */
    public RequestData setTrustStore(TrustStore trustStore) {
        setTrustStore(trustStore.pathToJks(), trustStore.password());
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
        multiPartSpec.clear();
        proxySpec = null;
        authScheme = null;
        trustStore = null;
    }
}
