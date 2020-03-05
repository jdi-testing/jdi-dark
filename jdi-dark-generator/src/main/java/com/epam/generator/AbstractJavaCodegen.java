package com.epam.generator;

import com.google.common.base.Strings;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.FormParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.DoubleProperty;
import io.swagger.models.properties.FloatProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.MapProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;


public abstract class AbstractJavaCodegen extends DefaultGenerator implements Generator {

    static Logger LOGGER = LoggerFactory.getLogger(AbstractJavaCodegen.class);
    public static final String FULL_JAVA_UTIL = "fullJavaUtil";
    public static final String JAVA8_MODE = "java8";
    public static final String WITH_XML = "withXml";
    public static final String DISABLE_HTML_ESCAPING = "disableHtmlEscaping";

    protected String invokerPackage = "io.swagger";
    protected String groupId = "io.swagger";
    protected String artifactId = "swagger-java";
    protected String artifactVersion = "1.0.0";
    protected String artifactUrl = "https://github.com/swagger-api/swagger-codegen";
    protected String artifactDescription = "Swagger Java";
    protected String developerName = "Swagger";
    protected String developerOrganization = "Swagger";
    protected String developerOrganizationUrl = "http://swagger.io";
    protected String scmUrl = "https://github.com/swagger-api/swagger-codegen";
    protected String projectFolder = "src" + File.separator + "main";
    protected String projectTestFolder = "src" + File.separator + "test";
    protected String sourceFolder = projectFolder + File.separator + "java";
    protected String testFolder = projectTestFolder + File.separator + "java";
    protected boolean fullJavaUtil;
    protected String javaUtilPrefix = "";
    protected Boolean serializableModel = false;
    protected String apiDocPath = "docs/";
    protected String modelDocPath = "docs/";

    public AbstractJavaCodegen() {
        super();
        supportsInheritance = true;
        modelTemplateFiles.put("templates/Java/model.mustache", ".java");
        apiTemplateFiles.put("templates/Java/api.mustache", ".java");
        apiTestTemplateFiles.put("templates/Java/api_test.mustache", ".java");

        hideGenerationTimestamp = false;

        setReservedWordsLowerCase(
                Arrays.asList(
                        // used as internal variables, can collide with parameter names

                        // language reserved words
                        "abstract", "continue", "for", "new", "switch", "assert",
                        "default", "if", "package", "synchronized", "boolean", "do", "goto", "private",
                        "this", "break", "double", "implements", "protected", "throw", "byte", "else",
                        "import", "public", "throws", "case", "enum", "instanceof", "return", "transient",
                        "catch", "extends", "int", "short", "try", "char", "final", "interface", "static",
                        "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
                        "native", "super", "while", "null")
        );

        languageSpecificPrimitives = new HashSet<String>(
                Arrays.asList(
                        "String",
                        "boolean",
                        "Boolean",
                        "Double",
                        "Integer",
                        "Long",
                        "Float",
                        "Object",
                        "byte[]")
        );
        instantiationTypes.put("array", "ArrayList");
        instantiationTypes.put("map", "HashMap");
        typeMapping.put("date", "Date");
        typeMapping.put("file", "File");

        cliOptions.add(new CliOption(GeneratorConstants.MODEL_PACKAGE, GeneratorConstants.MODEL_PACKAGE_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.API_PACKAGE, GeneratorConstants.API_PACKAGE_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.INVOKER_PACKAGE, GeneratorConstants.INVOKER_PACKAGE_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.GROUP_ID, GeneratorConstants.GROUP_ID_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.ARTIFACT_ID, GeneratorConstants.ARTIFACT_ID_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.ARTIFACT_VERSION, GeneratorConstants.ARTIFACT_VERSION_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.ARTIFACT_URL, GeneratorConstants.ARTIFACT_URL_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.ARTIFACT_DESCRIPTION, GeneratorConstants.ARTIFACT_DESCRIPTION_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.DEVELOPER_NAME, GeneratorConstants.DEVELOPER_NAME_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.DEVELOPER_EMAIL, GeneratorConstants.DEVELOPER_EMAIL_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.DEVELOPER_ORGANIZATION, GeneratorConstants.DEVELOPER_ORGANIZATION_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.DEVELOPER_ORGANIZATION_URL, GeneratorConstants.DEVELOPER_ORGANIZATION_URL_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.LICENSE_NAME, GeneratorConstants.LICENSE_NAME_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.LICENSE_URL, GeneratorConstants.LICENSE_URL_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.SOURCE_FOLDER, GeneratorConstants.SOURCE_FOLDER_DESC));
        cliOptions.add(new CliOption(GeneratorConstants.LOCAL_VARIABLE_PREFIX, GeneratorConstants.LOCAL_VARIABLE_PREFIX_DESC));
        cliOptions.add(CliOption.newBoolean(GeneratorConstants.SERIALIZABLE_MODEL, GeneratorConstants.SERIALIZABLE_MODEL_DESC));
        cliOptions.add(CliOption.newBoolean(GeneratorConstants.SERIALIZE_BIG_DECIMAL_AS_STRING, GeneratorConstants
                .SERIALIZE_BIG_DECIMAL_AS_STRING_DESC));
        cliOptions.add(CliOption.newBoolean(FULL_JAVA_UTIL, "whether to use fully qualified name for classes under java.util. This option only works for Java API client"));
        cliOptions.add(new CliOption("hideGenerationTimestamp", "hides the timestamp when files were generated"));
        cliOptions.add(CliOption.newBoolean(WITH_XML, "whether to include support for application/xml content type and include XML annotations in the model (works with libraries that provide support for JSON and XML)"));

        CliOption java8Mode = new CliOption(JAVA8_MODE, "Option. Use Java8 classes instead of third party equivalents");
        Map<String, String> java8ModeOptions = new HashMap<String, String>();
        java8ModeOptions.put("true", "Use Java 8 classes such as Base64");
        java8ModeOptions.put("false", "Various third party libraries as needed");
        java8Mode.setEnum(java8ModeOptions);
        cliOptions.add(java8Mode);

        cliOptions.add(CliOption.newBoolean(DISABLE_HTML_ESCAPING, "Disable HTML escaping of JSON strings when using gson (needed to avoid problems with byte[] fields)"));
    }

    @Override
    public void processOpts() {
        super.processOpts();

        if (additionalProperties.containsKey(GeneratorConstants.INVOKER_PACKAGE)) {
            this.setInvokerPackage((String) additionalProperties.get(GeneratorConstants.INVOKER_PACKAGE));
        } else if (additionalProperties.containsKey(GeneratorConstants.API_PACKAGE)) {
            // guess from api package
            String derviedInvokerPackage = deriveInvokerPackageName((String)additionalProperties.get(GeneratorConstants.API_PACKAGE));
            this.additionalProperties.put(GeneratorConstants.INVOKER_PACKAGE, derviedInvokerPackage);
            this.setInvokerPackage((String) additionalProperties.get(GeneratorConstants.INVOKER_PACKAGE));
            LOGGER.info("Invoker Package Name, originally not set, is now dervied from api package name: " + derviedInvokerPackage);
        } else if (additionalProperties.containsKey(GeneratorConstants.MODEL_PACKAGE)) {
            // guess from model package
            String derviedInvokerPackage = deriveInvokerPackageName((String)additionalProperties.get(GeneratorConstants.MODEL_PACKAGE));
            this.additionalProperties.put(GeneratorConstants.INVOKER_PACKAGE, derviedInvokerPackage);
            this.setInvokerPackage((String) additionalProperties.get(GeneratorConstants.INVOKER_PACKAGE));
            LOGGER.info("Invoker Package Name, originally not set, is now dervied from model package name: " + derviedInvokerPackage);
        } else {
            //not set, use default to be passed to template
            additionalProperties.put(GeneratorConstants.INVOKER_PACKAGE, invokerPackage);
        }

        if (!additionalProperties.containsKey(GeneratorConstants.MODEL_PACKAGE)) {
            additionalProperties.put(GeneratorConstants.MODEL_PACKAGE, modelPackage);
        }

        if (!additionalProperties.containsKey(GeneratorConstants.API_PACKAGE)) {
            additionalProperties.put(GeneratorConstants.API_PACKAGE, apiPackage);
        }

        if (additionalProperties.containsKey(GeneratorConstants.GROUP_ID)) {
            this.setGroupId((String) additionalProperties.get(GeneratorConstants.GROUP_ID));
        } else {
            //not set, use to be passed to template
            additionalProperties.put(GeneratorConstants.GROUP_ID, groupId);
        }

        if (additionalProperties.containsKey(GeneratorConstants.ARTIFACT_ID)) {
            this.setArtifactId((String) additionalProperties.get(GeneratorConstants.ARTIFACT_ID));
        } else {
            //not set, use to be passed to template
            additionalProperties.put(GeneratorConstants.ARTIFACT_ID, artifactId);
        }

        if (additionalProperties.containsKey(GeneratorConstants.ARTIFACT_VERSION)) {
            this.setArtifactVersion((String) additionalProperties.get(GeneratorConstants.ARTIFACT_VERSION));
        } else {
            //not set, use to be passed to template
            additionalProperties.put(GeneratorConstants.ARTIFACT_VERSION, artifactVersion);
        }

        if (additionalProperties.containsKey(GeneratorConstants.ARTIFACT_URL)) {
            this.setArtifactUrl((String) additionalProperties.get(GeneratorConstants.ARTIFACT_URL));
        } else {
            additionalProperties.put(GeneratorConstants.ARTIFACT_URL, artifactUrl);
        }

        if (additionalProperties.containsKey(GeneratorConstants.ARTIFACT_DESCRIPTION)) {
            this.setArtifactDescription((String) additionalProperties.get(GeneratorConstants.ARTIFACT_DESCRIPTION));
        } else {
            additionalProperties.put(GeneratorConstants.ARTIFACT_DESCRIPTION, artifactDescription);
        }

        if (additionalProperties.containsKey(GeneratorConstants.DEVELOPER_NAME)) {
            this.setDeveloperName((String) additionalProperties.get(GeneratorConstants.DEVELOPER_NAME));
        } else {
            additionalProperties.put(GeneratorConstants.DEVELOPER_NAME, developerName);
        }

        if (additionalProperties.containsKey(GeneratorConstants.DEVELOPER_ORGANIZATION)) {
            this.setDeveloperOrganization((String) additionalProperties.get(GeneratorConstants.DEVELOPER_ORGANIZATION));
        } else {
            additionalProperties.put(GeneratorConstants.DEVELOPER_ORGANIZATION, developerOrganization);
        }

        if (additionalProperties.containsKey(GeneratorConstants.DEVELOPER_ORGANIZATION_URL)) {
            this.setDeveloperOrganizationUrl((String) additionalProperties.get(GeneratorConstants.DEVELOPER_ORGANIZATION_URL));
        } else {
            additionalProperties.put(GeneratorConstants.DEVELOPER_ORGANIZATION_URL, developerOrganizationUrl);
        }

        if (additionalProperties.containsKey(GeneratorConstants.SOURCE_FOLDER)) {
            this.setSourceFolder((String) additionalProperties.get(GeneratorConstants.SOURCE_FOLDER));
        }

        if (fullJavaUtil) {
            javaUtilPrefix = "java.util.";
        }

        importMapping.put("List", "java.util.List");
        importMapping.put("ServiceDomain", "com.epam.http.annotations.ServiceDomain");
        importMapping.put("ContentType", "com.epam.http.annotations.ContentType");
        importMapping.put("get", "com.epam.http.annotations.GET");
        importMapping.put("post", "com.epam.http.annotations.POST");
        importMapping.put("delete", "com.epam.http.annotations.DELETE");
        importMapping.put("put", "com.epam.http.annotations.PUT");
        importMapping.put("options", "com.epam.http.annotations.OPTIONS");
        importMapping.put("head", "com.epam.http.annotations.HEAD");
        importMapping.put("patch", "com.epam.http.annotations.PATCH");
        importMapping.put("QueryParameters", "com.epam.http.annotations.QueryParameters");
        importMapping.put("QueryParameter", "com.epam.http.annotations.QueryParameter");
        importMapping.put("FormParameters", "com.epam.http.annotations.FormParameters");
        importMapping.put("FormParameter", "com.epam.http.annotations.FormParameter");
        importMapping.put("Headers", "com.epam.http.annotations.Headers");
        importMapping.put("Header", "com.epam.http.annotations.Header");
        importMapping.put("Cookie", "com.epam.http.annotations.Cookie");
        importMapping.put("RestMethod", "com.epam.http.requests.RestMethod");
        importMapping.put("JSON", "static io.restassured.http.ContentType.JSON");
        importMapping.put("ANY", "static io.restassured.http.ContentType.ANY");
        importMapping.put("BINARY", "static io.restassured.http.ContentType.BINARY");
        importMapping.put("HTML", "static io.restassured.http.ContentType.HTML");
        importMapping.put("TEXT", "static io.restassured.http.ContentType.TEXT");
        importMapping.put("URLENC", "static io.restassured.http.ContentType.URLENC");
        importMapping.put("XML", "static io.restassured.http.ContentType.XML");

        this.sanitizeConfig();

        // optional jackson mappings for BigDecimal support
        importMapping.put("ToStringSerializer", "com.fasterxml.jackson.databind.ser.std.ToStringSerializer");
        importMapping.put("JsonSerialize", "com.fasterxml.jackson.databind.annotation.JsonSerialize");

        // imports for pojos
        importMapping.put("ApiModelProperty", "io.swagger.annotations.ApiModelProperty");
        importMapping.put("ApiModel", "io.swagger.annotations.ApiModel");
        importMapping.put("JsonProperty", "com.fasterxml.jackson.annotation.JsonProperty");
        importMapping.put("JsonSubTypes", "com.fasterxml.jackson.annotation.JsonSubTypes");
        importMapping.put("JsonTypeInfo", "com.fasterxml.jackson.annotation.JsonTypeInfo");
        importMapping.put("JsonCreator", "com.fasterxml.jackson.annotation.JsonCreator");
        importMapping.put("JsonValue", "com.fasterxml.jackson.annotation.JsonValue");
        importMapping.put("SerializedName", "com.google.gson.annotations.SerializedName");
        importMapping.put("TypeAdapter", "com.google.gson.TypeAdapter");
        importMapping.put("JsonAdapter", "com.google.gson.annotations.JsonAdapter");
        importMapping.put("JsonReader", "com.google.gson.stream.JsonReader");
        importMapping.put("JsonWriter", "com.google.gson.stream.JsonWriter");
        importMapping.put("IOException", "java.io.IOException");
        importMapping.put("Objects", "java.util.Objects");
        importMapping.put("StringUtil", invokerPackage + ".StringUtil");
        // import JsonCreator if JsonProperty is imported
        // used later in recursive import in postProcessingModels
        importMapping.put("com.fasterxml.jackson.annotation.JsonProperty", "com.fasterxml.jackson.annotation.JsonCreator");



        if (this.skipAliasGeneration == null) {
            this.skipAliasGeneration = Boolean.TRUE;
        }
    }

    private void sanitizeConfig() {
        // Sanitize any config options here. We also have to update the additionalProperties because
        // the whole additionalProperties object is injected into the main object passed to the mustache layer

        this.setApiPackage(sanitizePackageName(apiPackage));
        if (additionalProperties.containsKey(GeneratorConstants.API_PACKAGE)) {
            this.additionalProperties.put(GeneratorConstants.API_PACKAGE, apiPackage);
        }

        this.setModelPackage(sanitizePackageName(modelPackage));
        if (additionalProperties.containsKey(GeneratorConstants.MODEL_PACKAGE)) {
            this.additionalProperties.put(GeneratorConstants.MODEL_PACKAGE, modelPackage);
        }

        this.setInvokerPackage(sanitizePackageName(invokerPackage));
        if (additionalProperties.containsKey(GeneratorConstants.INVOKER_PACKAGE)) {
            this.additionalProperties.put(GeneratorConstants.INVOKER_PACKAGE, invokerPackage);
        }
    }

    @Override
    public String escapeReservedWord(String name) {
        if(this.reservedWordsMappings().containsKey(name)) {
            return this.reservedWordsMappings().get(name);
        }
        return "_" + name;
    }

    @Override
    public String apiFileFolder() { return outputFolder + "/" + sourceFolder + "/" + apiPackage().replace('.', '/'); }

    @Override
    public String apiTestFileFolder() {
        return outputFolder + "/" + testFolder + "/" + apiPackage().replace('.', '/');
    }

    @Override
    public String modelFileFolder() { return outputFolder + "/" + sourceFolder + "/" + modelPackage().replace('.', '/'); }

    @Override
    public String apiDocFileFolder() {
        return (outputFolder + "/" + apiDocPath).replace('/', File.separatorChar);
    }

    @Override
    public String modelDocFileFolder() {
        return (outputFolder + "/" + modelDocPath).replace('/', File.separatorChar);
    }

    @Override
    public String toApiDocFilename(String name) {
        return toApiName(name);
    }

    @Override
    public String toModelDocFilename(String name) {
        return toModelName(name);
    }

    @Override
    public String toApiTestFilename(String name) {
        return toApiName(name) + "Test";
    }

    @Override
    public String toApiName(String name) {
        if (name.length() == 0) {
            return "DefaultApi";
        }
        return camelize(name) + "Api";
    }

    @Override
    public String toEndpointVarName(String name) {
        if (name.length() == 0) {
            return "Default";
        }
        return name.toUpperCase();
    }

    @Override
    public String toApiFilename(String name) {
        return toApiName(name);
    }

    @Override
    public String toVarName(String name) {
        // sanitize name
        name = sanitizeName(name); // FIXME: a parameter should not be assigned. Also declare the methods parameters as 'final'.

        if (name.toLowerCase().matches("^_*class$")) {
            return "propertyClass";
        }

        if("_".equals(name)) {
            name = "_u";
        }

        // if it's all uppper case, do nothing
        if (name.matches("^[A-Z_]*$")) {
            return name;
        }

        if(startsWithTwoUppercaseLetters(name)){
            name = name.substring(0, 2).toLowerCase() + name.substring(2);
        }

        // camelize (lower first character) the variable name
        // pet_id => petId
        name = camelize(name, true);

        // for reserved word or word starting with number, append _
        if (isReservedWord(name) || name.matches("^\\d.*")) {
            name = escapeReservedWord(name);
        }

        return name;
    }

    private boolean startsWithTwoUppercaseLetters(String name) {
        boolean startsWithTwoUppercaseLetters = false;
        if(name.length() > 1) {
            startsWithTwoUppercaseLetters = name.substring(0, 2).equals(name.substring(0, 2).toUpperCase());
        }
        return startsWithTwoUppercaseLetters;
    }

    @Override
    public String toParamName(String name) {
        // to avoid conflicts with 'callback' parameter for async call
        if ("callback".equals(name)) {
            return "paramCallback";
        }

        // should be the same as variable name
        return toVarName(name);
    }

    @Override
    public String toModelName(final String name) {
        // We need to check if import-mapping has a different model for this class, so we use it
        // instead of the auto-generated one.
        if (!getIgnoreImportMapping() && importMapping.containsKey(name)) {
            return importMapping.get(name);
        }

        final String sanitizedName = sanitizeName(name);

        String nameWithPrefixSuffix = sanitizedName;
        if (!StringUtils.isEmpty(modelNamePrefix)) {
            // add '_' so that model name can be camelized correctly
            nameWithPrefixSuffix = modelNamePrefix + "_" + nameWithPrefixSuffix;
        }

        if (!StringUtils.isEmpty(modelNameSuffix)) {
            // add '_' so that model name can be camelized correctly
            nameWithPrefixSuffix = nameWithPrefixSuffix + "_" + modelNameSuffix;
        }

        // camelize the model name
        // phone_number => PhoneNumber
        final String camelizedName = camelize(nameWithPrefixSuffix);

        // model name cannot use reserved keyword, e.g. return
        if (isReservedWord(camelizedName)) {
            final String modelName = "Model" + camelizedName;
            LOGGER.warn(camelizedName + " (reserved word) cannot be used as model name. Renamed to " + modelName);
            return modelName;
        }

        // model name starts with number
        if (camelizedName.matches("^\\d.*")) {
            final String modelName = "Model" + camelizedName; // e.g. 200Response => Model200Response (after camelize)
            LOGGER.warn(name + " (model name starts with number) cannot be used as model name. Renamed to " + modelName);
            return modelName;
        }

        return camelizedName;
    }

    @Override
    public String toModelFilename(String name) {
        // should be the same as the model name
        return toModelName(name);
    }

    @Override
    public String getTypeDeclaration(Property p) {
        if (p instanceof ArrayProperty) {
            ArrayProperty ap = (ArrayProperty) p;
            Property inner = ap.getItems();
            if (inner == null) {
                LOGGER.warn(ap.getName() + "(array property) does not have a proper inner type defined");
                // TODO maybe better defaulting to StringProperty than returning null
                return null;
            }
            return getSwaggerType(p) + "<" + getTypeDeclaration(inner) + ">";
        } else if (p instanceof MapProperty) {
            MapProperty mp = (MapProperty) p;
            Property inner = mp.getAdditionalProperties();
            if (inner == null) {
                LOGGER.warn(mp.getName() + "(map property) does not have a proper inner type defined");
                // TODO maybe better defaulting to StringProperty than returning null
                return null;
            }
            return getSwaggerType(p) + "<String, " + getTypeDeclaration(inner) + ">";
        }
        return super.getTypeDeclaration(p);
    }

    @Override
    public String getAlias(String name) {
        if (typeAliases != null && typeAliases.containsKey(name)) {
            return typeAliases.get(name);
        }
        return name;
    }

    @Override
    public String toDefaultValue(Property p) {
        if (p instanceof ArrayProperty) {
            final ArrayProperty ap = (ArrayProperty) p;
            final String pattern;
            if (fullJavaUtil) {
                pattern = "new java.util.ArrayList<%s>()";
            } else {
                pattern = "new ArrayList<%s>()";
            }
            if (ap.getItems() == null) {
                return null;
            }

            String typeDeclaration = getTypeDeclaration(ap.getItems());
            Object java8obj = additionalProperties.get("java8");
            if (java8obj != null) {
                Boolean java8 = Boolean.valueOf(java8obj.toString());
                if (java8 != null && java8) {
                    typeDeclaration = "";
                }
            }

            return String.format(pattern, typeDeclaration);
        } else if (p instanceof MapProperty) {
            final MapProperty ap = (MapProperty) p;
            final String pattern;
            if (fullJavaUtil) {
                pattern = "new java.util.HashMap<%s>()";
            } else {
                pattern = "new HashMap<%s>()";
            }
            if (ap.getAdditionalProperties() == null) {
                return null;
            }

            String typeDeclaration = String.format("String, %s", getTypeDeclaration(ap.getAdditionalProperties()));
            Object java8obj = additionalProperties.get("java8");
            if (java8obj != null) {
                Boolean java8 = Boolean.valueOf(java8obj.toString());
                if (java8 != null && java8) {
                    typeDeclaration = "";
                }
            }

            return String.format(pattern, typeDeclaration);
        } else if (p instanceof IntegerProperty) {
            IntegerProperty dp = (IntegerProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }
            return "null";
        } else if (p instanceof LongProperty) {
            LongProperty dp = (LongProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString()+"l";
            }
            return "null";
        } else if (p instanceof DoubleProperty) {
            DoubleProperty dp = (DoubleProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString() + "d";
            }
            return "null";
        } else if (p instanceof FloatProperty) {
            FloatProperty dp = (FloatProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString() + "f";
            }
            return "null";
        } else if (p instanceof BooleanProperty) {
            BooleanProperty bp = (BooleanProperty) p;
            if (bp.getDefault() != null) {
                return bp.getDefault().toString();
            }
            return "null";
        } else if (p instanceof StringProperty) {
            StringProperty sp = (StringProperty) p;
            if (sp.getDefault() != null) {
                String _default = sp.getDefault();
                if (sp.getEnum() == null) {
                    return "\"" + escapeText(_default) + "\"";
                } else {
                    // convert to enum var name later in postProcessModels
                    return _default;
                }
            }
            return "null";
        }
        return super.toDefaultValue(p);
    }

    @Override
    public void setParameterExampleValue(GeneratedParameter p) {
        String example;

        if (p.defaultValue == null) {
            example = p.example;
        } else {
            example = p.defaultValue;
        }

        String type = p.baseType;
        if (type == null) {
            type = p.dataType;
        }

        if ("String".equals(type)) {
            if (example == null) {
                example = p.paramName + "_example";
            }
            p.testExample = example;
            example = "\"" + escapeText(example) + "\"";
        } else if ("Integer".equals(type) || "Short".equals(type)) {
            if (example == null) {
                example = "56";
            }
        } else if ("Long".equals(type)) {
            if (example == null) {
                example = "56";
            }
            p.testExample = example;
            example = example + "L";
        } else if ("Float".equals(type)) {
            if (example == null) {
                example = "3.4";
            }
            p.testExample = example;
            example = example + "F";
        } else if ("Double".equals(type)) {
            example = "3.4";
            example = example + "D";
            p.testExample = example;
        } else if ("Boolean".equals(type)) {
            if (example == null) {
                example = "true";
            }
        } else if ("File".equals(type)) {
            if (example == null) {
                example = "/path/to/file";
            }
            example = "new File(\"" + escapeText(example) + "\")";
        } else if ("Date".equals(type)) {
            example = "new Date()";
        } else if ("LocalDate".equals(type)) {
            example = "LocalDate.now()";
        } else if ("OffsetDateTime".equals(type)) {
            example = "OffsetDateTime.now()";
        } else if (!languageSpecificPrimitives.contains(type)) {
            // type is a model class, e.g. User
            example = "new " + type + "()";
        }

        if (p.testExample == null) {
            p.testExample = example;
        }

        if (example == null) {
            example = "null";
        } else if (Boolean.TRUE.equals(p.isListContainer)) {
            example = "Arrays.asList(" + example + ")";
        } else if (Boolean.TRUE.equals(p.isMapContainer)) {
            example = "new HashMap()";
        }

        p.example = example;
    }

    @Override
    public String toExampleValue(Property p) {
        if(p.getExample() != null) {
            return escapeText(p.getExample().toString());
        } else {
            return super.toExampleValue(p);
        }
    }

    @Override
    public String getSwaggerType(Property p) {
        String swaggerType = super.getSwaggerType(p);

        swaggerType = getAlias(swaggerType);

        // don't apply renaming on types from the typeMapping
        if (typeMapping.containsKey(swaggerType)) {
            return typeMapping.get(swaggerType);
        }

        if (null == swaggerType) {
            LOGGER.error("No Type defined for Property " + p);
        }
        return toModelName(swaggerType);
    }

    @Override
    public String toOperationId(String operationId) {
        // throw exception if method name is empty
        if (StringUtils.isEmpty(operationId)) {
            throw new RuntimeException("Empty method/operation name (operationId) not allowed");
        }

        operationId = camelize(sanitizeName(operationId), true);

        // method name cannot use reserved keyword, e.g. return
        if (isReservedWord(operationId)) {
            String newOperationId = camelize("call_" + operationId, true);
            LOGGER.warn(operationId + " (reserved word) cannot be used as method name. Renamed to " + newOperationId);
            return newOperationId;
        }

        return operationId;
    }

    @Override
    public GeneratedModel fromModel(String name, Model model, Map<String, Model> allDefinitions) {
        GeneratedModel codegenModel = super.fromModel(name, model, allDefinitions);
        if(codegenModel.description != null) {
            codegenModel.imports.add("ApiModel");
        }
        if (codegenModel.discriminator != null && additionalProperties.containsKey("jackson")) {
            codegenModel.imports.add("JsonSubTypes");
            codegenModel.imports.add("JsonTypeInfo");
        }
        if (allDefinitions != null && codegenModel.parentSchema != null && codegenModel.hasEnums) {
            final Model parentModel = allDefinitions.get(codegenModel.parentSchema);
            final GeneratedModel parentCodegenModel = super.fromModel(codegenModel.parent, parentModel);
            codegenModel = AbstractJavaCodegen.reconcileInlineEnums(codegenModel, parentCodegenModel);
        }
        return codegenModel;
    }

    @Override
    public void postProcessParameter(GeneratedParameter parameter) { }

    @Override
    public Map<String, Object> postProcessModels(Map<String, Object> objs) {
        // recursively add import for mapping one type to multiple imports
        List<Map<String, String>> recursiveImports = (List<Map<String, String>>) objs.get("imports");
        if (recursiveImports == null)
            return objs;

        ListIterator<Map<String, String>> listIterator = recursiveImports.listIterator();
        while (listIterator.hasNext()) {
            String _import = listIterator.next().get("import");
            // if the import package happens to be found in the importMapping (key)
            // add the corresponding import package to the list
            if (importMapping.containsKey(_import)) {
                Map<String, String> newImportMap= new HashMap<String, String>();
                newImportMap.put("import", importMapping.get(_import));
                listIterator.add(newImportMap);
            }
        }

        return postProcessModelsEnum(objs);
    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        // Remove imports of List, ArrayList, Map and HashMap as they are
        // imported in the template already.
        List<Map<String, String>> imports = (List<Map<String, String>>) objs.get("imports");
        Pattern pattern = Pattern.compile("java\\.util\\.(List|ArrayList|Map|HashMap)");
        for (Iterator<Map<String, String>> itr = imports.iterator(); itr.hasNext();) {
            String _import = itr.next().get("import");
            if (pattern.matcher(_import).matches()) {
                itr.remove();
            }
        }
        return objs;
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        if (swagger == null || swagger.getPaths() == null){
            return;
        }
        for (String pathname : swagger.getPaths().keySet()) {
            Path path = swagger.getPath(pathname);
            if (path.getOperations() == null){
                continue;
            }
            for (Operation operation : path.getOperations()) {
                boolean hasFormParameters = false;
                boolean hasBodyParameters = false;
                for (Parameter parameter : operation.getParameters()) {
                    if (parameter instanceof FormParameter) {
                        hasFormParameters = true;
                    }
                    if (parameter instanceof BodyParameter) {
                        hasBodyParameters = true;
                    }
                }
                if (hasBodyParameters || hasFormParameters){
                    String defaultContentType = hasFormParameters ? "application/x-www-form-urlencoded" : "application/json";
                    String contentType =  operation.getConsumes() == null || operation.getConsumes().isEmpty() ? defaultContentType : operation.getConsumes().get(0);
                    operation.setVendorExtension("x-contentType", contentType);
                }
                String accepts = getAccept(operation);
                operation.setVendorExtension("x-accepts", accepts);
            }
        }
    }

    private static String getAccept(Operation operation) {
        String accepts = null;
        String defaultContentType = "application/json";
        if (operation.getProduces() != null && !operation.getProduces().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String produces : operation.getProduces()) {
                if (defaultContentType.equalsIgnoreCase(produces)) {
                    accepts = defaultContentType;
                    break;
                } else {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(produces);
                }
            }
            if (accepts == null) {
                accepts = sb.toString();
            }
        } else {
            accepts = defaultContentType;
        }

        return accepts;
    }

    @Override
    protected boolean needToImport(String type) {
        return super.needToImport(type) && type.indexOf(".") < 0;
    }

    @Override
    public String toEnumName(GeneratedProperty property) {
        return sanitizeName(camelize(property.name)) + "Enum";
    }

    @Override
    public String toEnumVarName(String value, String datatype) {
        if (value.length() == 0) {
            return "EMPTY";
        }

        // for symbol, e.g. $, #
        if (getSymbolName(value) != null) {
            return getSymbolName(value).toUpperCase();
        }

        // number
        if ("Integer".equals(datatype) || "Long".equals(datatype) ||
                "Float".equals(datatype) || "Double".equals(datatype)) {
            String varName = "NUMBER_" + value;
            varName = varName.replaceAll("-", "MINUS_");
            varName = varName.replaceAll("\\+", "PLUS_");
            varName = varName.replaceAll("\\.", "_DOT_");
            return varName;
        }

        // string
        String var = value.replaceAll("\\W+", "_").toUpperCase();
        if (var.matches("\\d.*")) {
            return "_" + var;
        } else {
            return var;
        }
    }

    @Override
    public String toEnumValue(String value, String datatype) {
        if ("Integer".equals(datatype) || "Double".equals(datatype)) {
            return value;
        } else if ("Long".equals(datatype)) {
            // add l to number, e.g. 2048 => 2048l
            return value + "l";
        } else if ("Float".equals(datatype)) {
            // add f to number, e.g. 3.14 => 3.14f
            return value + "f";
        } else {
            return "\"" + escapeText(value) + "\"";
        }
    }

    @Override
    public GeneratedOperation fromOperation(String path, String httpMethod, Operation operation, Map<String, Model> definitions, Swagger swagger) {
        GeneratedOperation op = super.fromOperation(path, httpMethod, operation, definitions, swagger);
        op.path = sanitizePath(op.path);
        return op;
    }

    private static GeneratedModel reconcileInlineEnums(GeneratedModel codegenModel, GeneratedModel parentCodegenModel) {
        // This generator uses inline classes to define enums, which breaks when
        // dealing with models that have subTypes. To clean this up, we will analyze
        // the parent and child models, look for enums that match, and remove
        // them from the child models and leave them in the parent.
        // Because the child models extend the parents, the enums will be available via the parent.

        // Only bother with reconciliation if the parent model has enums.
        if  (!parentCodegenModel.hasEnums) {
            return codegenModel;
        }

        // Get the properties for the parent and child models
        final List<GeneratedProperty> parentModelCodegenProperties = parentCodegenModel.vars;
        List<GeneratedProperty> codegenProperties = codegenModel.vars;

        // Iterate over all of the parent model properties
        boolean removedChildEnum = false;
        for (GeneratedProperty parentModelCodegenPropery : parentModelCodegenProperties) {
            // Look for enums
            if (parentModelCodegenPropery.isEnum) {
                // Now that we have found an enum in the parent class,
                // and search the child class for the same enum.
                Iterator<GeneratedProperty> iterator = codegenProperties.iterator();
                while (iterator.hasNext()) {
                    GeneratedProperty codegenProperty = iterator.next();
                    if (codegenProperty.isEnum && codegenProperty.equals(parentModelCodegenPropery)) {
                        // We found an enum in the child class that is
                        // a duplicate of the one in the parent, so remove it.
                        iterator.remove();
                        removedChildEnum = true;
                    }
                }
            }
        }

        if(removedChildEnum) {
            // If we removed an entry from this model's vars, we need to ensure hasMore is updated
            int count = 0, numVars = codegenProperties.size();
            for(GeneratedProperty codegenProperty : codegenProperties) {
                count += 1;
                codegenProperty.hasMore = (count < numVars) ? true : false;
            }
            codegenModel.vars = codegenProperties;
        }
        return codegenModel;
    }

    private static String sanitizePackageName(String packageName) {
        packageName = packageName.trim(); // FIXME: a parameter should not be assigned. Also declare the methods parameters as 'final'.
        packageName = packageName.replaceAll("[^a-zA-Z0-9_\\.]", "_");
        if(Strings.isNullOrEmpty(packageName)) {
            return "invalidPackageName";
        }
        return packageName;
    }

    public String getInvokerPackage() {
        return invokerPackage;
    }

    public void setInvokerPackage(String invokerPackage) {
        this.invokerPackage = invokerPackage;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    public void setArtifactUrl(String artifactUrl) {
        this.artifactUrl = artifactUrl;
    }

    public void setArtifactDescription(String artifactDescription) {
        this.artifactDescription = artifactDescription;
    }

    public void setScmUrl(String scmUrl) {
        this.scmUrl = scmUrl;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public void setDeveloperOrganization(String developerOrganization) {
        this.developerOrganization = developerOrganization;
    }

    public void setDeveloperOrganizationUrl(String developerOrganizationUrl) {
        this.developerOrganizationUrl = developerOrganizationUrl;
    }

    public void setSourceFolder(String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public void setTestFolder(String testFolder) {
        this.testFolder = testFolder;
    }

    public void setSerializableModel(Boolean serializableModel) {
        this.serializableModel = serializableModel;
    }

    private String sanitizePath(String p) {
        //prefer replace a ", instead of a fuLL URL encode for readability
        return p.replaceAll("\"", "%22");
    }

    @Override
    public String escapeQuotationMark(String input) {
        // remove " to avoid code injection
        return input.replace("\"", "");
    }

    @Override
    public String escapeUnsafeCharacters(String input) {
        return input.replace("*/", "*_/").replace("/*", "/_*");
    }

    /*
     * Derive invoker package name based on the input
     * e.g. foo.bar.model => foo.bar
     *
     * @param input API package/model name
     * @return Derived invoker package name based on API package/model name
     */
    private String deriveInvokerPackageName(String input) {
        String[] parts = input.split(Pattern.quote(".")); // Split on period.

        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (String p : Arrays.copyOf(parts, parts.length-1)) {
            sb.append(delim).append(p);
            delim = ".";
        }
        return sb.toString();
    }

    public String toRegularExpression(String pattern) {
        return escapeText(pattern);
    }

    public boolean convertPropertyToBoolean(String propertyKey) {
        boolean booleanValue = false;
        if (additionalProperties.containsKey(propertyKey)) {
            booleanValue = Boolean.valueOf(additionalProperties.get(propertyKey).toString());
        }

        return booleanValue;
    }

    public void writePropertyBack(String propertyKey, boolean value) {
        additionalProperties.put(propertyKey, value);
    }

    /**
     * Output the partial Getter name for boolean property, e.g. Active
     *
     * @param name the name of the property
     * @return partial getter name based on naming convention
     */
    public String toBooleanGetter(String name) {
        return getterAndSetterCapitalize(name);
    }

    @Override
    public String sanitizeTag(String tag) {
        tag = camelize(underscore(sanitizeName(tag)));

        // tag starts with numbers
        if (tag.matches("^\\d.*")) {
            tag = "Class" + tag;
        }
        return tag;
    }

    public boolean defaultIgnoreImportMappingOption() {
        return true;
    }

}
