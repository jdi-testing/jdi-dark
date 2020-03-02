package com.epam.generator;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * User: lanwen Date: 24.03.15 Time: 20:22
 */

@Command(name = "generate", description = "Generate code with chosen settings")
public class Generate implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(Generate.class);

    @Option(name = {"-v", "--verbose"}, description = "verbose mode")
    private Boolean verbose;

    @Option(name = {"-o", "--output"}, title = "output directory",
            description = "where to write the generated files (current dir by default)")
    private String output = "";

    @Option(name = {"-i", "--input-spec"}, title = "spec file", required = true,
            description = "location of the swagger spec, as URL or file (required)")
    private String spec;

    @Option(name = {"-p", "--invoker-package"}, title = "invoker package",
            description = GeneratorConstants.INVOKER_PACKAGE_DESC)
    private String invokerPackage;

    @Option(name = {"--api-package"}, title = "api package",
            description = GeneratorConstants.API_PACKAGE_DESC)
    private String apiPackage;

    @Option(name = {"--model-package"}, title = "model package",
            description = GeneratorConstants.MODEL_PACKAGE_DESC)
    private String modelPackage;

    @Option(name = {"--group-id"}, title = "group id", description = GeneratorConstants.GROUP_ID_DESC)
    private String groupId;

    @Option(name = {"--artifact-id"}, title = "artifact id",
            description = GeneratorConstants.ARTIFACT_ID_DESC)
    private String artifactId;

    @Option(name = {"--artifact-version"}, title = "artifact version",
            description = GeneratorConstants.ARTIFACT_VERSION_DESC)
    private String artifactVersion;

    @Option(
            name = {"-a", "--auth"},
            title = "authorization",
            description = "adds authorization headers when fetching the swagger definitions remotely. "
                    + "Pass in a URL-encoded string of name:header with a comma separating multiple values")
    private String auth;

    @Option(name = {"-s", "--skip-overwrite"}, title = "skip overwrite",
            description = "specifies if the existing files should be "
                    + "overwritten during the generation.")
    private Boolean skipOverwrite;

    public void run() {

        // create new options
        GeneratorOptions opts = new GeneratorOptions();

        if (verbose != null) {
            opts.setVerbose(verbose);
        }

        if (skipOverwrite != null) {
            opts.setSkipOverwrite(skipOverwrite);
        }

        if (isNotEmpty(spec)) {
            opts.setInputSpec(spec);
        }

        if (isNotEmpty(apiPackage)) {
            opts.setApiPackage(apiPackage);
        }

        if (isNotEmpty(modelPackage)) {
            opts.setModelPackage(modelPackage);
        }

        if (isNotEmpty(invokerPackage)) {
            opts.setInvokerPackage(invokerPackage);
            if (!isNotEmpty(apiPackage)) {
                opts.setApiPackage(invokerPackage + ".api");
            }
            if (!isNotEmpty(modelPackage)) {
                opts.setModelPackage(invokerPackage + ".model");
            }
        }

        if (isNotEmpty(groupId)) {
            opts.setGroupId(groupId);
        }

        if (isNotEmpty(artifactId)) {
            opts.setArtifactId(artifactId);
        }

        if (isNotEmpty(artifactVersion)) {
            opts.setArtifactVersion(artifactVersion);
        }

        if (isNotEmpty(output)) {
            opts.setOutputDir(output);
        }

        if (isNotEmpty(auth)) {
            opts.setAuth(auth);
        }

        new DefaultProcessor().generate(opts.build());
    }
}
