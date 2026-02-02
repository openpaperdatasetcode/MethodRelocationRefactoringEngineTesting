package com.refactoring.movemethod;

import java.io.IOException;

// Source class: strictfp, type parameter, anonymous inner class, member inner class
strictfp class SourceClass<T extends CharSequence> {
    // Field referencing target class (per_condition: source contains target's field)
    private TargetClass<String>.StaticNestedClass targetField;

    // Member inner class
    class SourceInnerClass {
        private int innerField = 42;
    }

    // Method to refactor: instance, base type return, protected, in source class
    protected int methodToRefactor() {
        // Constructor invocation
        SourceInnerClass innerInstance = new SourceInnerClass();
        // Variable call (target class field)
        if (targetField != null) {
            targetField.setValue("test");
        }
        // Requires try-catch
        try {
            // Anonymous inner class (source class feature)
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    throw new IOException("Test exception");
                }
            };
            runnable.run();
        } catch (IOException e) {
            return innerInstance.innerField;
        }
        return innerInstance.innerField;
    }
}

// Target class: public, type parameter, static nested class
public class TargetClass<U extends Number> {
    // Static nested class (target_inner_rec - target inner recursive)
    public static class StaticNestedClass {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

// Others class for call_method
class CallerClass {
    // call_method: default modifier, others_class type, normal, ClassName.methodName(), in for loop, returns TargetClass type
    TargetClass<Double> callMethod() {
        TargetClass<Double> targetInstance = new TargetClass<>();
        SourceClass<String> sourceInstance = new SourceClass<>();
        
        // Position: for loop
        for (int i = 0; i < 5; i++) {
            // ClassName.methodName(arguments) feature
            sourceInstance.methodToRefactor();
        }
        
        return targetInstance;
    }
}