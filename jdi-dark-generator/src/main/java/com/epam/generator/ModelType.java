package com.epam.generator;

public enum ModelType {

    MODEL(GeneratedModel.class),
    OPERATION(GeneratedOperation.class),
    PARAMETER(GeneratedParameter.class),
    PROPERTY(GeneratedProperty.class),
    RESPONSE(GeneratedResponse.class);

    private final Class<?> defaultImplementation;

    private ModelType(Class<?> defaultImplementation) {
        this.defaultImplementation = defaultImplementation;
    }

    public Class<?> getDefaultImplementation() {
        return defaultImplementation;
    }
}
