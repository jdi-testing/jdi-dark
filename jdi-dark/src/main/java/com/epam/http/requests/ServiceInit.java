package com.epam.http.requests;

import com.epam.http.annotations.*;
import com.epam.http.annotations.DELETE;
import com.epam.http.annotations.GET;
import com.epam.http.annotations.PATCH;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.PUT;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestMethodTypes.*;
import static com.epam.jdi.tools.LinqUtils.where;
import static java.lang.reflect.Modifier.isStatic;

/**
 * The entry point for initialising the Service Object classes.
 * In order to effectively use JDI HTTP it's recommended to statically import:
 * <pre>
 *     {@code com.epam.http.requests.ServiceInit.init}
 * </pre>
 *
 * @author <a href="mailto:roman.iovlev.jdi@gmail.com">Roman_Iovlev</a>
 */
public class ServiceInit {


    /**
     * Initialise the Service Object class.
     * @param c     class describing Service
     * @return      initialised Service Object
     */
    public static <T> T init(Class<T> c) {
        return init(c, null);
    }

    /**
     * Initialise the Service Object class.
     * @param c     class describing Service
     * @param requestSpecification custom request specification
     * @return      initialised Service Object
     */
    public static <T> T init(Class<T> c, RequestSpecification requestSpecification) {
        List<Field> methods = where(c.getDeclaredFields(),
                f -> f.getType().equals(RestMethod.class));
        for (Field method: methods) {
            try {
                method.setAccessible(true);
                if (isStatic(method.getModifiers()))
                    method.set(null, getRestMethod(method, c, requestSpecification));
                if (!isStatic(method.getModifiers()) && method.get(getService(c)) == null)
                    method.set(getService(c), getRestMethod(method, c));
            } catch (IllegalAccessException ex) {
                throw exception("Can't init method %s for class %s", method.getName(), c.getName()); }
        }
        return getService(c);
    }

    private static Object service;

    /**
     * Helper method to instantiate the class.
     * @param c     class describing Service
     * @return      instantiated service
     */
    private static <T> T getService(Class<T> c) {
        if (service != null) return (T) service;
        try {
            return (T) (service = c.newInstance());
        } catch (IllegalAccessException|InstantiationException ex) {
            throw exception(
                "Can't instantiate class %s, Service class should have empty constructor",
                    c.getSimpleName()); }
    }

    /**
     * Check whether the annotation present and add these values to request data.
     * @param field     HTTP method described in Service Object class as a field
     * @param c         class describing service
     * @return          http method with request data
     */
    private static <T> RestMethod getRestMethod(Field field, Class<T> c) {
        return getRestMethod(field, c, null);
    }

    /**
     * Check whether the annotation present and add these values to request data.
     * @param field     HTTP method described in Service Object class as a field
     * @param c         class describing service
     * @param requestSpecification custom request specification
     * @return          http method with request data
     */
    private static <T> RestMethod getRestMethod(Field field, Class<T> c, RequestSpecification requestSpecification) {
        MethodData mtData = getMethodData(field);
        String url = getUrlFromDomain(getDomain(c), mtData.getUrl(), field.getName(), c.getSimpleName());
        RestMethod method = new RestMethod(mtData.getType(), url, requestSpecification);
        if (field.isAnnotationPresent(ContentType.class))
            method.setContentType(field.getAnnotation(ContentType.class).value());
        if (field.isAnnotationPresent(Header.class))
            method.addHeader(field.getAnnotation(Header.class));
        if (field.isAnnotationPresent(Headers.class))
            method.addHeaders(field.getAnnotation(Headers.class).value());
        if (field.isAnnotationPresent(Cookie.class))
            method.addCookie(field.getAnnotation(Cookie.class));
        if (field.isAnnotationPresent(Cookies.class))
            method.addCookies(field.getAnnotation(Cookies.class).value());
        /* Case for class annotations*/
        if (c.isAnnotationPresent(QueryParameter.class))
            method.addQueryParameters(c.getAnnotation(QueryParameter.class));
        if (c.isAnnotationPresent(QueryParameters.class))
            method.addQueryParameters(c.getAnnotation(QueryParameters.class).value());
        /* Case for method annotations*/
        if (field.isAnnotationPresent(QueryParameter.class))
            method.addQueryParameters(field.getAnnotation(QueryParameter.class));
        if (field.isAnnotationPresent(QueryParameters.class))
            method.addQueryParameters(field.getAnnotation(QueryParameters.class).value());
        return method;
    }

    /**
     * Create method data.
     * @param method    annotated method field
     * @return          method data with url and type of request
     */
    private static MethodData getMethodData(Field method) {
        if (method.isAnnotationPresent(GET.class))
            return new MethodData(method.getAnnotation(GET.class).value(),GET);
        if (method.isAnnotationPresent(POST.class))
            return new MethodData(method.getAnnotation(POST.class).value(),POST);
        if (method.isAnnotationPresent(PUT.class))
            return new MethodData(method.getAnnotation(PUT.class).value(),PUT);
        if (method.isAnnotationPresent(DELETE.class))
            return new MethodData(method.getAnnotation(DELETE.class).value(),DELETE);
        if (method.isAnnotationPresent(PATCH.class))
            return new MethodData(method.getAnnotation(PATCH.class).value(),PATCH);
        if (method.isAnnotationPresent(HEAD.class))
            return new MethodData(method.getAnnotation(HEAD.class).value(), HEAD);
        if (method.isAnnotationPresent(OPTIONS.class))
            return new MethodData(method.getAnnotation(OPTIONS.class).value(), OPTIONS);
        return new MethodData(null, GET);
    }

    /**
     * Get and check URL from request data.
     * @param domain
     * @param uri
     * @param methodName
     * @param className
     * @return normalized URL
     */
    private static String getUrlFromDomain(String domain, String uri, String methodName, String className) {
        if (uri == null)
            return null;
        if (uri.contains("://"))
            return uri;
        if (domain == null)
            throw exception(
            "Can't instantiate method '%s' for service '%s'. " +
                    "Domain undefined and method url not contains '://'",
                    methodName, className);
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    /**
     * Get service domain.
     * @param c     Service Object class
     * @return      service domain string
     */
    private static <T> String getDomain(Class<T> c) {
        return c.isAnnotationPresent(ServiceDomain.class)
                ? c.getAnnotation(ServiceDomain.class).value()
                : null;
    }
}
