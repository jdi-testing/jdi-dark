package com.epam.generator;

import java.util.List;
import java.util.Map;

public class GeneratedSecurity {
    public String name;
    public String type;
    public Boolean hasMore, isBasic, isOAuth, isApiKey;
    public Map<String, Object> vendorExtensions;
    // ApiKey specific
    public String keyParamName;
    public Boolean isKeyInQuery, isKeyInHeader;
    // Oauth specific
    public String flow, authorizationUrl, tokenUrl;
    public List<Map<String, Object>> scopes;
    public Boolean isCode, isPassword, isApplication, isImplicit;

    @Override
    public String toString() {
        return String.format("%s(%s)", name, type);
    }

}
