package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source normal class (private modifier, same package as target, type parameter + two anonymous inner classes)
private class SourceClass<T extends CharSequence> {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // First anonymous inner class (source_class feature)
    private final Consumer<String> firstAnonymous = new Consumer<>() {
        @Override
        public void accept(String s) {
            System.out.println("First anonymous: " + s);
        }
    };

    // Second anonymous inner class (source_class feature - duplicate as specified)
    private final Runnable secondAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous");
        }
    };

    // Instance method to refactor (public access, returns List<String>, source position)
    public List<String> refactorMethod() {
        List<String> result = new ArrayList<>();
        
        // Variable call feature
        firstAnonymous.accept("test");
        secondAnonymous.run();
        result.add(targetField.toString());
        
        // No_new_exception feature (no explicit throw new Exception)
        if (targetField == null) {
            result.add("null target field");
        } else {
            result.add(targetField.getStaticNestedValue());
        }
        
        return result;
    }
}

// Target normal class (private modifier, static nested class target_feature)
private class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private String value = "static_nested_value";
        
        public String getValue() {
            return value;
        }
    }

    // Method to access static nested class
    public String getStaticNestedValue() {
        TargetStaticNested nested = new TargetStaticNested();
        return nested.getValue();
    }
}

// Others_class for call_method (strictfp modifier, overloading + ClassName.methodName(arguments) target_feature)
class OthersClass {
    private Object callMethodResult;

    // Call method (strictfp modifier, others_class type, property assignment pos, returns Object)
    strictfp Object callMethod() {
        SourceClass<String> source = new SourceClass<>();
        
        // Property assignment position (call_method pos)
        callMethodResult = SourceClass.class.getName(); // ClassName.methodName(arguments) precursor
        // Overloading target_feature (call overloaded method)
        callMethodResult = callMethodOverload(source);
        
        return callMethodResult;
    }

    // Overloaded method (overloading target_feature)
    strictfp Object callMethodOverload(SourceClass<?> source) {
        // ClassName.methodName(arguments) target_feature
        return source.refactorMethod();
    }

    // Additional overload (complete overloading feature)
    strictfp Object callMethodOverload(String param) {
        return new TargetClass.TargetStaticNested().getValue();
    }
}