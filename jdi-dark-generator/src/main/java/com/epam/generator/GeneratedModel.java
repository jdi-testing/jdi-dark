package com.epam.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GeneratedModel {
    public String parent, parentSchema;
    public List<String> interfaces;

    // References to parent and interface CodegenModels. Only set when code generator supports inheritance.
    public GeneratedModel parentModel;
    public List<GeneratedModel> interfaceModels;
    public List<GeneratedModel> children;

    public String name, classname, title, description, classVarName, modelJson, dataType, xmlPrefix, xmlNamespace, xmlName;
    public String classFilename; // store the class file name, mainly used for import
    public String unescapedDescription;
    public String discriminator, discriminatorClassVarName;
    public String defaultValue;
    public String arrayModelType;
    public boolean isAlias; // Is this effectively an alias of another simple type
    public List<GeneratedProperty> vars = new ArrayList<GeneratedProperty>();
    public List<GeneratedProperty> requiredVars = new ArrayList<GeneratedProperty>(); // a list of required properties
    public List<GeneratedProperty> optionalVars = new ArrayList<GeneratedProperty>(); // a list of optional properties
    public List<GeneratedProperty> readOnlyVars = new ArrayList<GeneratedProperty>(); // a list of read-only properties
    public List<GeneratedProperty> readWriteVars = new ArrayList<GeneratedProperty>(); // a list of properties for read, write
    public List<GeneratedProperty> allVars;
    public Map<String, Object> allowableValues;

    // Sorted sets of required parameters.
    public Set<String> mandatory = new TreeSet<String>();
    public Set<String> allMandatory;

    public Set<String> imports = new TreeSet<String>();
    public boolean hasVars, emptyVars, hasMoreModels, hasEnums, isEnum, hasRequired, hasOptional, isArrayModel;
    public boolean hasOnlyReadOnly = true; // true if all properties are read-only

    public Map<String, Object> vendorExtensions;

    //The type of the value from additional properties. Used in map like objects.
    public String additionalPropertiesType;

    {
        // By default these are the same collections. Where the code generator supports inheritance, composed models
        // store the complete closure of owned and inherited properties in allVars and allMandatory.
        allVars = vars;
        allMandatory = mandatory;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name, classname);
    }
}
