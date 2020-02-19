package com.epam.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.ModelImpl;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class Generator {

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("h", "help", false, "shows this message");
        options.addOption("c", "config", true, "location of the configuration file");

        CommandLine cmd = null;
        try {
            CommandLineParser parser = new BasicParser();

            cmd = parser.parse(options, args);
            if (cmd.hasOption("d")) {
                System.out.println("Option");
                return;
            }
        }
        catch(Exception ex) {

        }
        Swagger swagger = new SwaggerParser().read("http://petstore.swagger.io/v2/swagger.json");

        if (swagger == null) {
            throw new RuntimeException("The swagger specification supplied was not valid");
        }

        List<File> files = new ArrayList<File>();
        // models
        List<Object> allModels = new ArrayList<Object>();
        new ModelGenerator(swagger).generate(files, allModels);

        System.out.println("swagger: " + swagger);
    }
}
