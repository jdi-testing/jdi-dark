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
import lombok.experimental.Tolerate;

import java.util.ArrayList;

/**
 * Represents all HTTP request data.
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
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
     * Set Content-Type to HTTP request.
     *
     * @param ct Rest Assured Content-Type
     */
    @Tolerate
    public RequestData setContentType(ContentType ct) {
        setContentType(ct.toString());
        return this;
    }

    /**
     * Add multipart spec to request data.
     *
     * @param multiPartSpecBuilder MultiPartSpecBuilder
     */
    @Tolerate
    public RequestData setMultiPart(MultiPartSpecBuilder multiPartSpecBuilder) {
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
    @Tolerate
    public RequestData setProxySpec(String scheme, String host, int port) {
        setProxySpec(ProxySpecification.host(host).and().withPort(port).and().withScheme(scheme));
        return this;
    }

    /**
     * Set proxy parameters to the request.
     *
     * @param proxyParams proxyParams
     */
    @Tolerate
    public RequestData setProxySpec(Proxy proxyParams) {
        setProxySpec(proxyParams.scheme(), proxyParams.host(), proxyParams.port());
        return this;
    }

    /**
     * Set trustStore parameters to request data.
     *
     * @param pathToJks pathToJks
     * @param password  password
     */
    @Tolerate
    public RequestData setTrustStore(String pathToJks, String password) {
        setTrustStore(new Pair<>(pathToJks, password));
        return this;
    }

    /**
     * Set trustStore parameters to the request.
     *
     * @param trustStore trustStore
     */
    @Tolerate
    public RequestData setTrustStore(TrustStore trustStore) {
        setTrustStore(new Pair<>(trustStore.pathToJks(), trustStore.password()));
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
