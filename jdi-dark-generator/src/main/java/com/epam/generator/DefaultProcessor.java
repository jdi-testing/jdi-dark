package com.epam.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.SecurityRequirement;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.parameters.Parameter;
import io.swagger.util.Json;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DefaultProcessor extends AbstractProcessor {
    protected final Logger LOGGER = LoggerFactory.getLogger(DefaultProcessor.class);
    protected Generator config;
    protected GeneratorOptions opts;
    protected Swagger swagger;
    protected Boolean isGenerateApis = null;
    protected Boolean isGenerateModels = null;
    protected Boolean isGenerateSupportingFiles = null;
    protected Boolean isGenerateApiTests = null;
    protected Boolean isGenerateTests = null;
    protected String basePath;
    protected String basePathWithoutHost;
    protected String contextPath;
    private Map<String, String> generatorPropertyDefaults = new HashMap<>();

    protected Boolean getGeneratorPropertyDefaultSwitch(final String key, final Boolean defaultValue) {
        String result = null;
        if (this.generatorPropertyDefaults.containsKey(key)) {
            result = this.generatorPropertyDefaults.get(key);
        }
        if (result != null) {
            return Boolean.valueOf(result);
        }
        return defaultValue;
    }

    protected String getScheme() {
        String scheme;
        if (swagger.getSchemes() != null && swagger.getSchemes().size() > 0) {
            scheme = config.escapeText(swagger.getSchemes().get(0).toValue());
        } else {
            scheme = "https";
        }
        scheme = config.escapeText(scheme);
        return scheme;
    }

    private String getHost() {
        StringBuilder hostBuilder = new StringBuilder();
        hostBuilder.append(getHostWithoutBasePath());
        if (!StringUtils.isEmpty(swagger.getBasePath()) && !swagger.getBasePath().equals("/")) {
            hostBuilder.append(swagger.getBasePath());
        }
        return hostBuilder.toString();
    }

    private String getHostWithoutBasePath() {
        StringBuilder hostBuilder = new StringBuilder();
        hostBuilder.append(getScheme());
        hostBuilder.append("://");
        if (!StringUtils.isEmpty(swagger.getHost())) {
            hostBuilder.append(swagger.getHost());
        } else {
            hostBuilder.append("localhost");
            LOGGER.warn("'host' not defined in the spec. Default to 'localhost'.");
        }
        return hostBuilder.toString();
    }

    protected void configureGeneratorProperties() {
        // allows generating only models by specifying a CSV of models to generate, or empty for all
        // NOTE: Boolean.TRUE is required below rather than `true` because of JVM boxing constraints and type inference.
        if (System.getProperty(GeneratorConstants.GENERATE_APIS) != null) {
            isGenerateApis = Boolean.valueOf(System.getProperty(GeneratorConstants.GENERATE_APIS));
        } else {
            isGenerateApis = System.getProperty(GeneratorConstants.APIS) != null ? Boolean.TRUE : getGeneratorPropertyDefaultSwitch(GeneratorConstants.APIS, null);
        }
        if (System.getProperty(GeneratorConstants.GENERATE_MODELS) != null) {
            isGenerateModels = Boolean.valueOf(System.getProperty(GeneratorConstants.GENERATE_MODELS));
        } else {
            isGenerateModels = System.getProperty(GeneratorConstants.MODELS) != null ? Boolean.TRUE : getGeneratorPropertyDefaultSwitch(GeneratorConstants.MODELS, null);
        }

        if (isGenerateApis == null && isGenerateModels == null && isGenerateSupportingFiles == null) {
            // no specifics are set, generate everything
            isGenerateApis = isGenerateModels /*= isGenerateSupportingFiles*/ = true;
        } else {
            if(isGenerateApis == null) {
                isGenerateApis = false;
            }
            if(isGenerateModels == null) {
                isGenerateModels = false;
            }
            //if(isGenerateSupportingFiles == null) {
            //    isGenerateSupportingFiles = false;
            //}
        }
        // model/api tests and documentation options rely on parent generate options (api or model) and no other options.
        // They default to true in all scenarios and can only be marked false explicitly
        isGenerateApiTests = System.getProperty(GeneratorConstants.API_TESTS) != null ? Boolean.valueOf(System.getProperty(GeneratorConstants.API_TESTS)) : getGeneratorPropertyDefaultSwitch(GeneratorConstants.API_TESTS, true);

        // Additional properties added for tests to exclude references in project related files
        config.additionalProperties().put(GeneratorConstants.GENERATE_API_TESTS, isGenerateApiTests);

        config.additionalProperties().put(GeneratorConstants.GENERATE_APIS, isGenerateApis);
        config.additionalProperties().put(GeneratorConstants.GENERATE_MODELS, isGenerateModels);

        if (System.getProperty("debugSwagger") != null) {
            Json.prettyPrint(swagger);
        }
        config.processOpts();
        config.preprocessSwagger(swagger);
        config.additionalProperties().put("generatedDate", DateTime.now().toString());
        config.additionalProperties().put("generatedYear", String.valueOf(DateTime.now().getYear()));
        config.additionalProperties().put("generatorClass", config.getClass().getName());
        config.additionalProperties().put("inputSpec", config.getInputSpec());
        config.additionalProperties().put("invokerPackage", config.getInvokerPackage());
        if (swagger.getVendorExtensions() != null) {
            config.vendorExtensions().putAll(swagger.getVendorExtensions());
        }

        contextPath = config.escapeText(swagger.getBasePath() == null ? "" : swagger.getBasePath());
        basePath = config.escapeText(getHost());
        basePathWithoutHost = config.escapeText(swagger.getBasePath());
    }

    protected void configureSwaggerInfo() {
        Info info = swagger.getInfo();
        if (info == null) {
            return;
        }
        if (info.getTitle() != null) {
            config.additionalProperties().put("appName", config.escapeText(info.getTitle()));
        }
        if (info.getVersion() != null) {
            config.additionalProperties().put("appVersion", config.escapeText(info.getVersion()));
        } else {
            LOGGER.error("Missing required field info version. Default appVersion set to 1.0.0");
            config.additionalProperties().put("appVersion", "1.0.0");
        }

        if (StringUtils.isEmpty(info.getDescription())) {
            // set a default description if none if provided
            config.additionalProperties().put("appDescription",
                    "No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)");
            config.additionalProperties().put("unescapedAppDescription", "No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)");
        } else {
            config.additionalProperties().put("appDescription", config.escapeText(info.getDescription()));
            config.additionalProperties().put("unescapedAppDescription", info.getDescription());
        }

        if (info.getContact() != null) {
            Contact contact = info.getContact();
            if (contact.getEmail() != null) {
                config.additionalProperties().put("infoEmail", config.escapeText(contact.getEmail()));
            }
            if (contact.getName() != null) {
                config.additionalProperties().put("infoName", config.escapeText(contact.getName()));
            }
            if (contact.getUrl() != null) {
                config.additionalProperties().put("infoUrl", config.escapeText(contact.getUrl()));
            }
        }

        if (info.getLicense() != null) {
            License license = info.getLicense();
            if (license.getName() != null) {
                config.additionalProperties().put("licenseInfo", config.escapeText(license.getName()));
            }
            if (license.getUrl() != null) {
                config.additionalProperties().put("licenseUrl", config.escapeText(license.getUrl()));
            }
        }

        if (info.getVersion() != null) {
            config.additionalProperties().put("version", config.escapeText(info.getVersion()));
        } else {
            LOGGER.error("Missing required field info version. Default version set to 1.0.0");
            config.additionalProperties().put("version", "1.0.0");
        }

        if (info.getTermsOfService() != null) {
            config.additionalProperties().put("termsOfService", config.escapeText(info.getTermsOfService()));
        }
    }

    protected void generateModels(List<File> files, List<Object> allModels) {

        if (!isGenerateModels) {
            return;
        }

        final Map<String, Model> definitions = swagger.getDefinitions();
        if (definitions == null) {
            return;
        }

        String modelNames = System.getProperty("models");
        Set<String> modelsToGenerate = null;
        if (modelNames != null && !modelNames.isEmpty()) {
            modelsToGenerate = new HashSet<>(Arrays.asList(modelNames.split(",")));
        }

        Set<String> modelKeys = definitions.keySet();
        if (modelsToGenerate != null && !modelsToGenerate.isEmpty()) {
            Set<String> updatedKeys = new HashSet<String>();
            for (String m : modelKeys) {
                if (modelsToGenerate.contains(m)) {
                    updatedKeys.add(m);
                }
            }
            modelKeys = updatedKeys;
        }

        // store all processed models
        Map<String, Object> allProcessedModels = new TreeMap<>();

        // process models only
        for (String name : modelKeys) {
            try {
                //don't generate models that have an import mapping
                if (!config.getIgnoreImportMapping() && config.importMapping().containsKey(name)) {
                    LOGGER.info("Model " + name + " not imported due to import mapping");
                    continue;
                }
                Model model = definitions.get(name);
                Map<String, Model> modelMap = new HashMap<String, Model>();
                modelMap.put(name, model);
                Map<String, Object> models = processModels(config, modelMap, definitions);
                if (models != null) {
                    models.put("classname", config.toModelName(name));
                    models.putAll(config.additionalProperties());
                    allProcessedModels.put(name, models);
                }
            } catch (Exception e) {
                String message = "Could not process model '" + name + "'" + ". Please make sure that your schema is correct!";
                LOGGER.error(message, e);
                throw new RuntimeException(message, e);
            }
        }

        // post process all processed models
        allProcessedModels = config.postProcessAllModels(allProcessedModels);

        final boolean skipAlias = config.getSkipAliasGeneration() != null && config.getSkipAliasGeneration();

        // generate files based on processed models
        for (String modelName : allProcessedModels.keySet()) {
            Map<String, Object> models = (Map<String, Object>) allProcessedModels.get(modelName);
            models.put("modelPackage", config.modelPackage());
            try {
                //don't generate models that have an import mapping
                if (!config.getIgnoreImportMapping() && config.importMapping().containsKey(modelName)) {
                    continue;
                }
                Map<String, Object> modelTemplate = (Map<String, Object>) ((List<Object>) models.get("models")).get(0);
                if (skipAlias) {
                    // Special handling of aliases only applies to Java
                    if (modelTemplate != null && modelTemplate.containsKey("model")) {
                        GeneratedModel m = (GeneratedModel) modelTemplate.get("model");
                        if (m.isAlias) {
                            continue;  // Don't create user-defined classes for aliases
                        }
                    }
                }
                allModels.add(modelTemplate);
                for (String templateName : config.modelTemplateFiles().keySet()) {
                    String suffix = config.modelTemplateFiles().get(templateName);
                    String filename = config.modelFileFolder() + File.separator + config.toModelFilename(modelName) + suffix;
                    if (!config.shouldOverwrite(filename)) {
                        LOGGER.info("Skipped overwriting " + filename);
                        continue;
                    }
                    File written = processTemplateToFile(models, templateName, filename);
                    if (written != null) {
                        files.add(written);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Could not generate model '" + modelName + "'", e);
            }
        }
        if (System.getProperty("debugModels") != null) {
            LOGGER.info("############ Model info ############");
            Json.prettyPrint(allModels);
        }

    }

    protected void generateApis(List<File> files, List<Object> allOperations, List<Object> allModels) {
        if (!isGenerateApis) {
            return;
        }
        Map<String, List<GeneratedOperation>> paths = processPaths(swagger.getPaths());
        Set<String> apisToGenerate = null;
        String apiNames = System.getProperty("apis");
        if (apiNames != null && !apiNames.isEmpty()) {
            apisToGenerate = new HashSet<String>(Arrays.asList(apiNames.split(",")));
        }
        if (apisToGenerate != null && !apisToGenerate.isEmpty()) {
            Map<String, List<GeneratedOperation>> updatedPaths = new TreeMap<String, List<GeneratedOperation>>();
            for (String m : paths.keySet()) {
                if (apisToGenerate.contains(m)) {
                    updatedPaths.put(m, paths.get(m));
                }
            }
            paths = updatedPaths;
        }
        for (String tag : paths.keySet()) {
            try {
                List<GeneratedOperation> ops = paths.get(tag);
                Collections.sort(ops, new Comparator<GeneratedOperation>() {
                    @Override
                    public int compare(GeneratedOperation one, GeneratedOperation another) {
                        return ObjectUtils.compare(one.operationId, another.operationId);
                    }
                });
                Map<String, Object> operation = processOperations(config, tag, ops, allModels);

                operation.put("hostWithoutBasePath", getHostWithoutBasePath());
                operation.put("basePath", basePath);
                operation.put("basePathWithoutHost", basePathWithoutHost);
                operation.put("contextPath", contextPath);
                operation.put("baseName", tag);
                operation.put("apiPackage", config.apiPackage());
                operation.put("modelPackage", config.modelPackage());
                operation.putAll(config.additionalProperties());
                operation.put("classname", config.toApiName(tag));
                operation.put("classVarName", config.toApiVarName(tag));
                operation.put("importPath", config.toApiImport(tag));
                operation.put("classFilename", config.toApiFilename(tag));
                operation.put("endPointName", config.toEndpointVarName(tag));

                allOperations.add(new HashMap<String, Object>(operation));
                for (int i = 0; i < allOperations.size(); i++) {
                    Map<String, Object> oo = (Map<String, Object>) allOperations.get(i);
                    if (i < (allOperations.size() - 1)) {
                        oo.put("hasMore", "true");
                    }
                }

                for (String templateName : config.apiTemplateFiles().keySet()) {
                    String filename = config.apiFilename(templateName, tag);
                    if (!config.shouldOverwrite(filename) && new File(filename).exists()) {
                        LOGGER.info("Skipped overwriting " + filename);
                        continue;
                    }

                    File written = processTemplateToFile(operation, templateName, filename);
                    if (written != null) {
                        files.add(written);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("Could not generate api file for '" + tag + "'", e);
            }
        }
        if (System.getProperty("debugOperations") != null) {
            LOGGER.info("############ Operation info ############");
            Json.prettyPrint(allOperations);
        }

    }

    public List<File> generate(GeneratorOptions opts) {

        this.opts = opts;
        this.swagger = opts.getSwagger();
        this.config = opts.getGenerator();

        if (swagger == null) {
            throw new RuntimeException("missing swagger input or config!");
        }
        configureGeneratorProperties();
        configureSwaggerInfo();

        List<File> files = new ArrayList<File>();
        // models
        List<Object> allModels = new ArrayList<Object>();
        generateModels(files, allModels);
        // apis
        List<Object> allOperations = new ArrayList<Object>();
        generateApis(files, allOperations, allModels);

        config.processSwagger(swagger);
        return files;
    }

    protected File processTemplateToFile(Map<String, Object> templateData, String templateName, String outputFilename) throws IOException {
        String adjustedOutputFilename = outputFilename.replaceAll("//", "/").replace('/', File.separatorChar);
        String template = readTemplate(templateName);
        Mustache.Compiler compiler = Mustache.compiler();
        compiler = config.processCompiler(compiler);
        Template tmpl = compiler
                .withLoader(name -> getTemplateReader(getFullTemplateFile(config, name + ".mustache")))
                .defaultValue("")
                .compile(template);

        writeToFile(adjustedOutputFilename, tmpl.execute(templateData));
        return new File(adjustedOutputFilename);
    }

    public Map<String, List<GeneratedOperation>> processPaths(Map<String, Path> paths) {
        Map<String, List<GeneratedOperation>> ops = new TreeMap<String, List<GeneratedOperation>>();
        for (String resourcePath : paths.keySet()) {
            Path path = paths.get(resourcePath);
            processOperation(resourcePath, "get", path.getGet(), ops, path);
            processOperation(resourcePath, "head", path.getHead(), ops, path);
            processOperation(resourcePath, "put", path.getPut(), ops, path);
            processOperation(resourcePath, "post", path.getPost(), ops, path);
            processOperation(resourcePath, "delete", path.getDelete(), ops, path);
            processOperation(resourcePath, "patch", path.getPatch(), ops, path);
            processOperation(resourcePath, "options", path.getOptions(), ops, path);
        }
        return ops;
    }

    protected void processOperation(String resourcePath, String httpMethod, Operation operation, Map<String, List<GeneratedOperation>> operations, Path path) {
        if (operation == null) {
            return;
        }
        if (System.getProperty("debugOperations") != null) {
            LOGGER.info("processOperation: resourcePath= " + resourcePath + "\t;" + httpMethod + " " + operation + "\n");
        }
        List<Tag> tags = new ArrayList<Tag>();

        List<String> tagNames = operation.getTags();
        List<Tag> swaggerTags = swagger.getTags();
        if (tagNames != null) {
            if (swaggerTags == null) {
                for (String tagName : tagNames) {
                    tags.add(new Tag().name(tagName));
                }
            } else {
                for (String tagName : tagNames) {
                    boolean foundTag = false;
                    for (Tag tag : swaggerTags) {
                        if (tag.getName().equals(tagName)) {
                            tags.add(tag);
                            foundTag = true;
                            break;
                        }
                    }

                    if (!foundTag) {
                        tags.add(new Tag().name(tagName));
                    }
                }
            }
        }

        if (tags.isEmpty()) {
            tags.add(new Tag().name("default"));
        }

        /*
         build up a set of parameter "ids" defined at the operation level
         per the swagger 2.0 spec "A unique parameter is defined by a combination of a name and location"
          i'm assuming "location" == "in"
        */
        Set<String> operationParameters = new HashSet<String>();
        if (operation.getParameters() != null) {
            for (Parameter parameter : operation.getParameters()) {
                operationParameters.add(generateParameterId(parameter));
            }
        }

        //need to propagate path level down to the operation
        if (path.getParameters() != null) {
            for (Parameter parameter : path.getParameters()) {
                //skip propagation if a parameter with the same name is already defined at the operation level
                if (!operationParameters.contains(generateParameterId(parameter))) {
                    operation.addParameter(parameter);
                }
            }
        }

        for (Tag tag : tags) {
            try {
                GeneratedOperation codegenOperation = config.fromOperation(resourcePath, httpMethod, operation, swagger.getDefinitions(), swagger);
                codegenOperation.tags = new ArrayList<Tag>(tags);
                config.addOperationToGroup(config.sanitizeTag(tag.getName()), resourcePath, operation, codegenOperation, operations);

                List<Map<String, List<String>>> securities = operation.getSecurity();
                if (securities == null && swagger.getSecurity() != null) {
                    securities = new ArrayList<Map<String, List<String>>>();
                    for (SecurityRequirement sr : swagger.getSecurity()) {
                        securities.add(sr.getRequirements());
                    }
                }
            } catch (Exception ex) {
                String msg = "Could not process operation:\n" //
                        + "  Tag: " + tag + "\n"//
                        + "  Operation: " + operation.getOperationId() + "\n" //
                        + "  Resource: " + httpMethod + " " + resourcePath + "\n"//
                        + "  Definitions: " + swagger.getDefinitions() + "\n"  //
                        + "  Exception: " + ex.getMessage();
                throw new RuntimeException(msg, ex);
            }
        }

    }

    protected static String generateParameterId(Parameter parameter) {
        return parameter.getName() + ":" + parameter.getIn();
    }


    protected Map<String, Object> processOperations(Generator config, String tag, List<GeneratedOperation> ops, List<Object> allModels) {
        Map<String, Object> operations = new HashMap<String, Object>();
        Map<String, Object> objs = new HashMap<String, Object>();
        objs.put("classname", config.toApiName(tag));
        objs.put("pathPrefix", config.toApiVarName(tag));

        // check for operationId uniqueness
        Set<String> opIds = new HashSet<String>();
        int counter = 0;
        for (GeneratedOperation op : ops) {
            String opId = op.nickname;
            if (opIds.contains(opId)) {
                counter++;
                op.nickname += "_" + counter;
            }
            opIds.add(opId);
        }
        objs.put("operation", ops);

        operations.put("operations", objs);
        operations.put("package", config.apiPackage());


        Set<String> allImports = new TreeSet<String>();
        for (GeneratedOperation op : ops) {
            allImports.addAll(op.imports);
        }

        List<Map<String, String>> imports = new ArrayList<Map<String, String>>();
        for (String nextImport : allImports) {
            Map<String, String> im = new LinkedHashMap<String, String>();
            String mapping = config.importMapping().get(nextImport);
            if (mapping == null) {
                mapping = config.toModelImport(nextImport);
            }
            if (mapping != null) {
                im.put("import", mapping);
                if (!imports.contains(im)) { // avoid duplicates
                    imports.add(im);
                }
            }
        }

        operations.put("imports", imports);

        // add a flag to indicate whether there's any {{import}}
        if (imports.size() > 0) {
            operations.put("hasImport", true);
        }
        config.postProcessOperations(operations);
        config.postProcessOperationsWithModels(operations, allModels);
        if (objs.size() > 0) {
            List<GeneratedOperation> os = (List<GeneratedOperation>) objs.get("operation");

            if (os != null && os.size() > 0) {
                GeneratedOperation op = os.get(os.size() - 1);
                op.hasMore = false;
            }
        }
        return operations;
    }


    protected Map<String, Object> processModels(Generator config, Map<String, Model> definitions, Map<String, Model> allDefinitions) {
        Map<String, Object> objs = new HashMap<String, Object>();
        objs.put("package", config.modelPackage());
        List<Object> models = new ArrayList<Object>();
        Set<String> allImports = new LinkedHashSet<String>();
        for (String key : definitions.keySet()) {
            Model mm = definitions.get(key);
            if(mm.getVendorExtensions() !=  null && mm.getVendorExtensions().containsKey("x-codegen-ignore")) {
                // skip this model
                LOGGER.debug("skipping model " + key);
                return null;
            }
            else if(mm.getVendorExtensions() !=  null && mm.getVendorExtensions().containsKey("x-codegen-import-mapping")) {
                String codegenImport = mm.getVendorExtensions().get("x-codegen-import-mapping").toString();
                config.importMapping().put(key, codegenImport);
                allImports.add(codegenImport);
            }
            GeneratedModel cm = config.fromModel(key, mm, allDefinitions);
            Map<String, Object> mo = new HashMap<String, Object>();
            mo.put("model", cm);
            mo.put("importPath", config.toModelImport(cm.classname));
            models.add(mo);

            allImports.addAll(cm.imports);
        }
        objs.put("models", models);
        Set<String> importSet = new TreeSet<String>();
        for (String nextImport : allImports) {
            String mapping = config.importMapping().get(nextImport);
            if (mapping == null) {
                mapping = config.toModelImport(nextImport);
            }
            if (mapping != null && !config.defaultIncludes().contains(mapping)) {
                importSet.add(mapping);
            }
            // add instantiation types
            mapping = config.instantiationTypes().get(nextImport);
            if (mapping != null && !config.defaultIncludes().contains(mapping)) {
                importSet.add(mapping);
            }
        }
        List<Map<String, String>> imports = new ArrayList<Map<String, String>>();
        for (String s : importSet) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("import", s);
            imports.add(item);
        }
        objs.put("imports", imports);
        //config.postProcessModels(objs);
        return objs;
    }
}
