package com.epam.generator;

import io.airlift.airline.Cli;

/**
 * User: lanwen Date: 24.03.15 Time: 17:56
 * <p>
 * Command line interface for swagger codegen use `swagger-codegen-cli.jar help` for more info
 *
 * @since 2.1.3-M1
 */
public class DarkGeneratorRunner {

    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        Cli.CliBuilder<Runnable> builder =
                Cli.<Runnable>builder("dark-generator")
                        .withDescription(
                                String.format(
                                        "Swagger code generator CLI (version 1.0.0). More info on swagger.io"))
                        .withCommands(Generate.class);

        builder.build().parse(args).run();
    }
}
