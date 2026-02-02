package com.example;

import java.util.List;
import java.util.ArrayList;

// Source class (strictfp modifier, normal class, same package, type parameter, static nested class, local inner class)
strictfp class SourceClass<T extends CharSequence> {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // Type parameter usage (source_class feature)
    private T typeParamValue;

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {
        // Static method for ClassName.methodName(arguments)
        static int staticMethod(int num) { // return_type: base type
            return num * 2;
        }
    }

    // Overloading method 1 (overloading type, List<String> return, default access)
    List<String> targetMethod() {
        return targetMethod("default");
    }

    // Overloading method 2 (overloading feature)
    List<String> targetMethod(String suffix) {
        // Super constructor invocation
        super();

        // Static method call in constructor parameter list (pos: the parameter list of constructors)
        TargetClass tempTarget = new TargetClass(StaticNestedSourceClass.staticMethod(1)); // 1, source, static, ClassName.methodName(arguments)

        // Try statement
        try {
            // Variable call
            String targetValue = targetField.instanceField; // access_instance_field
            // Local inner class (source_class feature)
            class LocalInnerClass {
                String process(String val) {
                    return val + suffix;
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            List<String> result = new ArrayList<>();
            result.add(local.process(targetValue));
            return result;
        } catch (Exception e) {
            // No new exception
            return new ArrayList<>();
        }
    }
}

// Target class (final modifier, normal class, empty target_feature)
final class TargetClass {
    // Instance field for access_instance_field
    String instanceField = "targetInstanceField";

    // Constructor for static method call in parameter list
    public TargetClass(int num) {
        this.instanceField += "_" + num;
    }

    // Default constructor
    public TargetClass() {}
}