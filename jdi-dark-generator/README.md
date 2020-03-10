[![Codacy Badge](https://api.codacy.com/project/badge/Grade/33945d791ef14f41ae05740328d7bdb9)](https://www.codacy.com/app/jdi-testing/jdi-dark?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jdi-testing/jdi-dark&amp;utm_campaign=Badge_Grade)

# JDI Dark Generator

## Building
After cloning the project, you can build it from source with this command:

mvn clean package
If you don't have maven installed, you may directly use the included maven wrapper, and build with the command:

./mvnw clean package

## Generating

To generate jdi-dark project for http://petstore.swagger.io/v2/swagger.json, run following:

java -jar target/DarkGenerator.jar generate -i http://petstore.swagger.io/v2/swagger.json -o ../sample -p com.petshop

## Options

                [(-a <authorization> | --auth <authorization>)]
                [--api-package <api package>] 
                [--artifact-id <artifact id>] 
                [--artifact-version <artifact version>]
                [--group-id <group id>] 
                (-i <spec file> | --input-spec <spec file>)
                [--invoker-package <invoker package>]
                [--model-package <model package>]
                [(-o <output directory> | --output <output directory>)]

