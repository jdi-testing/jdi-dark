package com.epam.generator;

import io.swagger.models.ExternalDocs;
import io.swagger.models.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneratedOperation {
    public final List<GeneratedProperty> responseHeaders = new ArrayList<GeneratedProperty>();
    public boolean hasAuthMethods, hasConsumes, hasProduces, hasParams, hasOptionalParams, hasRequiredParams,
            returnTypeIsPrimitive, returnSimpleType, isMapContainer,
            isListContainer, isMultipart, hasMore = true,
            isResponseBinary = false, isResponseFile = false, hasReference = false,
            isRestfulIndex, isRestfulShow, isRestfulCreate, isRestfulUpdate, isRestfulDestroy,
            isRestful, isDeprecated;
    public String path, testPath, operationId, returnType, httpMethod, returnBaseType,
            returnContainer, summary, unescapedNotes, notes, baseName, defaultResponse, discriminator;
    public GeneratedParameter bodyParam;
    public List<Map<String, String>> consumes, produces;
    public List<GeneratedParameter> allParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> bodyParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> pathParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> queryParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> headerParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> formParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedParameter> requiredParams = new ArrayList<GeneratedParameter>();
    public List<GeneratedSecurity> authMethods;
    public List<Tag> tags;
    public List<GeneratedResponse> responses = new ArrayList<GeneratedResponse>();
    public Set<String> imports = new HashSet<String>();
    public List<Map<String, String>> examples;
    public List<Map<String, String>> requestBodyExamples;
    public ExternalDocs externalDocs;
    public Map<String, Object> vendorExtensions;
    public String nickname; // legacy support

    /**
     * Check if there's at least one parameter
     *
     * @return true if parameter exists, false otherwise
     */
    private static boolean nonempty(List<?> params) {
        return params != null && params.size() > 0;
    }
    
    /**
     * Check if there's at least one body parameter
     *
     * @return true if body parameter exists, false otherwise
     */
    public boolean getHasBodyParam() {
        return nonempty(bodyParams);
    }

    /**
     * Check if there's at least one query parameter
     *
     * @return true if query parameter exists, false otherwise
     */
    public boolean getHasQueryParams() {
        if (nonempty(queryParams)) {
            return true;
        }

        if (authMethods == null || authMethods.isEmpty()) {
            return false;
        }

        // Check if one of the authMethods is a query param
        for (GeneratedSecurity sec : authMethods) {
            if (sec.isKeyInQuery) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if there's at least one header parameter
     *
     * @return true if header parameter exists, false otherwise
     */
    public boolean getHasHeaderParams() {
        return nonempty(headerParams);
    }

    /**
     * Check if there's at least one path parameter
     *
     * @return true if path parameter exists, false otherwise
     */
    public boolean getHasPathParams() {
        return nonempty(pathParams);
    }

    /**
     * Check if there's at least one form parameter
     *
     * @return true if any form parameter exists, false otherwise
     */
    public boolean getHasFormParams() {
        return nonempty(formParams);
    }

    /**
     * Check if there's at least one example parameter
     *
     * @return true if examples parameter exists, false otherwise
     */
    public boolean getHasExamples() {
        return nonempty(examples);
    }

    /**
     * Check if act as Restful index method
     *
     * @return true if act as Restful index method, false otherwise
     */
    public boolean isRestfulIndex() {
        return "GET".equalsIgnoreCase(httpMethod) && "".equals(pathWithoutBaseName());
    }

    /**
     * Check if act as Restful show method
     *
     * @return true if act as Restful show method, false otherwise
     */
    public boolean isRestfulShow() {
        return "GET".equalsIgnoreCase(httpMethod) && isMemberPath();
    }

    /**
     * Check if act as Restful create method
     *
     * @return true if act as Restful create method, false otherwise
     */
    public boolean isRestfulCreate() {
        return "POST".equalsIgnoreCase(httpMethod) && "".equals(pathWithoutBaseName());
    }

    /**
     * Check if act as Restful update method
     *
     * @return true if act as Restful update method, false otherwise
     */
    public boolean isRestfulUpdate() {
        return Arrays.asList("PUT", "PATCH").contains(httpMethod.toUpperCase()) && isMemberPath();
    }

    /**
     * Check if body param is allowed for the request method
     *
     * @return true request method is PUT, PATCH or POST; false otherwise
     */
    public boolean isBodyAllowed() {
        return Arrays.asList("PUT", "PATCH", "POST").contains(httpMethod.toUpperCase());
    }

    /**
     * Check if act as Restful destroy method
     *
     * @return true if act as Restful destroy method, false otherwise
     */
    public boolean isRestfulDestroy() {
        return "DELETE".equalsIgnoreCase(httpMethod) && isMemberPath();
    }

    /**
     * Check if Restful-style
     *
     * @return true if Restful-style, false otherwise
     */
    public boolean isRestful() {
        return isRestfulIndex() || isRestfulShow() || isRestfulCreate() || isRestfulUpdate() || isRestfulDestroy();
    }

    /**
     * Get the substring except baseName from path
     *
     * @return the substring
     */
    private String pathWithoutBaseName() {
        return baseName != null ? path.replace("/" + baseName.toLowerCase(), "") : path;
    }

    /**
     * Check if the path match format /xxx/:id
     *
     * @return true if path act as member
     */
    private boolean isMemberPath() {
        if (pathParams.size() != 1) return false;
        String id = pathParams.get(0).baseName;
        return ("/{" + id + "}").equals(pathWithoutBaseName());
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", baseName, path);
    }
}
