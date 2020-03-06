package com.epam.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JavaServiceGenerator extends AbstractJavaCodegen
{
    static final String MEDIA_TYPE = "mediaType";

    public static final String PARCELABLE_MODEL = "parcelableModel";
    public static final String USE_RUNTIME_EXCEPTION = "useRuntimeException";

    protected String gradleWrapperPackage = "gradle.wrapper";
    protected boolean parcelableModel = false;
    protected boolean useRuntimeException = false;

    public JavaServiceGenerator() {
        super();
        outputFolder = "generated-code" + File.separator + "java";
        embeddedTemplateDir = templateDir = "templates/Java";
        invokerPackage = "io.swagger.client";
        artifactId = "swagger-java-client";
        apiPackage = invokerPackage + ".api";
        modelPackage = invokerPackage + ".model";

        cliOptions.add(CliOption.newBoolean(PARCELABLE_MODEL, "Whether to generate models for Android that implement Parcelable with the okhttp-gson library."));
        cliOptions.add(CliOption.newBoolean(USE_RUNTIME_EXCEPTION, "Use RuntimeException instead of Exception"));

        supportedLibraries.put("okhttp-gson", "HTTP client: OkHttp 2.7.5. JSON processing: Gson 2.8.1. Enable Parcelable models on Android using '-DparcelableModel=true'. Enable gzip request encoding using '-DuseGzipFeature=true'.");
        supportedLibraries.put("resttemplate", "HTTP client: Spring RestTemplate 4.3.9-RELEASE. JSON processing: Jackson 2.10.1");

        CliOption libraryOption = new CliOption(GeneratorConstants.LIBRARY, "library template (sub-template) to use");
        libraryOption.setEnum(supportedLibraries);
        // set okhttp-gson as the default
        libraryOption.setDefault("okhttp-gson");
        cliOptions.add(libraryOption);
        setLibrary("okhttp-gson");

    }

    @Override
    public String getName() {
        return "java";
    }

    @Override
    public String getHelp() {
        return "Generates a Java client library.";
    }

    @Override
    public void processOpts() {
        super.processOpts();

        if (additionalProperties.containsKey(PARCELABLE_MODEL)) {
            this.setParcelableModel(Boolean.valueOf(additionalProperties.get(PARCELABLE_MODEL).toString()));
        }
        // put the boolean value back to PARCELABLE_MODEL in additionalProperties
        additionalProperties.put(PARCELABLE_MODEL, parcelableModel);

        if (additionalProperties.containsKey(USE_RUNTIME_EXCEPTION)) {
            this.setUseRuntimeException(convertPropertyToBooleanAndWriteBack(USE_RUNTIME_EXCEPTION));
        }

        final String invokerFolder = (sourceFolder + '/' + invokerPackage).replace(".", "/");

        //Common files
        writeOptional(outputFolder, new SupportingFile("pom.mustache", "", "pom.xml"));
        writeOptional(outputFolder, new SupportingFile("README.mustache", "", "README.md"));
        //writeOptional(outputFolder, new SupportingFile("build.gradle.mustache", "", "build.gradle"));
        //writeOptional(outputFolder, new SupportingFile("build.sbt.mustache", "", "build.sbt"));
        //writeOptional(outputFolder, new SupportingFile("settings.gradle.mustache", "", "settings.gradle"));
        //writeOptional(outputFolder, new SupportingFile("gradle.properties.mustache", "", "gradle.properties"));
        //writeOptional(outputFolder, new SupportingFile("manifest.mustache", projectFolder, "AndroidManifest.xml"));
        //supportingFiles.add(new SupportingFile("travis.mustache", "", ".travis.yml"));
        //supportingFiles.add(new SupportingFile("ApiClient.mustache", invokerFolder, "ApiClient.java"));

        //supportingFiles.add(new SupportingFile( "gradlew.mustache", "", "gradlew") );
        //supportingFiles.add(new SupportingFile( "gradlew.bat.mustache", "", "gradlew.bat") );
        //supportingFiles.add(new SupportingFile( "gradle-wrapper.properties.mustache",
        //        gradleWrapperPackage.replace( ".", File.separator ), "gradle-wrapper.properties") );
        //supportingFiles.add(new SupportingFile( "gradle-wrapper.jar",
        //        gradleWrapperPackage.replace( ".", File.separator ), "gradle-wrapper.jar") );
        //supportingFiles.add(new SupportingFile("gitignore.mustache", "", ".gitignore"));
        supportingFiles.add(new SupportingFile("test.mustache", "src/test/java/resources", "test.properties"));
        supportingFiles.add(new SupportingFile("general.mustache", "src/test/java/resources", "general.xml"));
        supportingFiles.add(new SupportingFile("pomproperties.mustache", "src/test/java/resources", "pom.properties"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        super.postProcessOperations(objs);

        return objs;
    }

    @Override
    public String apiFilename(String templateName, String tag) {
        return super.apiFilename(templateName, tag);
    }

    @Override
    public String toEndpointVarName(String name) {
        return super.toEndpointVarName(name);
    }

    private static boolean isMultipartType(List<Map<String, String>> consumes) {
        Map<String, String> firstType = consumes.get(0);
        if (firstType != null) {
            if ("multipart/form-data".equals(firstType.get(MEDIA_TYPE))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> postProcessModelsEnum(Map<String, Object> objs) {
        objs = super.postProcessModelsEnum(objs);
        //Needed import for Gson based libraries
        if (additionalProperties.containsKey("gson")) {
            List<Map<String, String>> imports = (List<Map<String, String>>)objs.get("imports");
            List<Object> models = (List<Object>) objs.get("models");
            for (Object _mo : models) {
                Map<String, Object> mo = (Map<String, Object>) _mo;
                GeneratedModel cm = (GeneratedModel) mo.get("model");
                // for enum model
                if (Boolean.TRUE.equals(cm.isEnum) && cm.allowableValues != null) {
                    cm.imports.add(importMapping.get("SerializedName"));
                    Map<String, String> item = new HashMap<String, String>();
                    item.put("import", importMapping.get("SerializedName"));
                    imports.add(item);
                }
            }
        }
        return objs;
    }

    public void setParcelableModel(boolean parcelableModel) {
        this.parcelableModel = parcelableModel;
    }

    public void setUseRuntimeException(boolean useRuntimeException) {
        this.useRuntimeException = useRuntimeException;
    }

}
