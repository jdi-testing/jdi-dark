package com.epam.generator;

import java.util.HashMap;
import java.util.Map;

public final class ModelFactory {

    private static final Map<ModelType, Class<?>> typeMapping = new HashMap<ModelType, Class<?>>();

    /**
     * Configure a different implementation class.
     *
     * @param type           the type that shall be replaced
     * @param implementation the implementation class must extend the default class and must provide a public no-arg constructor
     */
    public static void setTypeMapping(ModelType type, Class<?> implementation) {
        if (!type.getDefaultImplementation().isAssignableFrom(implementation)) {
            throw new IllegalArgumentException(implementation.getSimpleName() + " doesn't extend " + type.getDefaultImplementation().getSimpleName());
        }
        try {
            implementation.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        typeMapping.put(type, implementation);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(ModelType type) {
        Class<?> classType = typeMapping.get(type);
        try {
            return (T) (classType != null ? classType : type.getDefaultImplementation()).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
