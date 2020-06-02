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
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
@Data
@Accessors(chain = true)
public class RequestData extends DataClass<RequestData> {
    private boolean empty = true;
    private String uri = null;
    private String path = null;
    private Object body = null;
    private String contentType = null;
    private Headers headers = new Headers();
    private Cookies cookies = new Cookies();
    private MultiMap<String, String> pathParams = new MultiMap<>();
    private MultiMap<String, String> queryParams = new MultiMap<>();
    private MultiMap<String, String> formParams = new MultiMap<>();
    private ArrayList<MultiPartSpecification> multiPartSpec = new ArrayList<>();
    private ArrayList<Filter> filters = new ArrayList<>();
    private ProxySpecification proxySpec = null;
    private AuthenticationScheme authScheme = null;
    private Pair<String, String> trustStore = null;

    public CookieUpdater cookiesUpdater() {
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
     * Set Content-Type to HTTP request.
     *
     * @param ct Rest Assured Content-Type
     */
    public RequestData addContentType(ContentType ct) {
        this.setContentType(ct.toString());
        return this;
    }

    /**
     * Add multipart spec to request data.
     *
     * @param multiPartSpecBuilder MultiPartSpecBuilder
     */
    public RequestData addMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
        this.multiPartSpec.add(multiPartSpecBuilder.build());
        return this;
    }

    /**
     * Set proxy parameters to request data.
     *
     * @param scheme scheme
     * @param host   host
     * @param port   port
     */
    public RequestData addProxySpec(String scheme, String host, int port) {
        this.setProxySpec(ProxySpecification.host(host).and().withPort(port).and().withScheme(scheme));
        return this;
    }

    /**
     * Set proxy parameters to the request.
     *
     * @param proxyParams proxyParams
     */
    public RequestData addProxySpec(Proxy proxyParams) {
        addProxySpec(proxyParams.scheme(), proxyParams.host(), proxyParams.port());
        return this;
    }

    /**
     * Set trustStore parameters to the request.
     *
     * @param trustStore trustStore
     */
    public RequestData addTrustStore(TrustStore trustStore) {
        this.setTrustStore(new Pair<>(trustStore.pathToJks(), trustStore.password()));
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
