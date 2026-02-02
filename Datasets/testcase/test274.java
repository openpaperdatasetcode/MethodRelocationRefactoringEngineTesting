package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;

// Source enum class (public modifier, same package as target, type parameter, anonymous inner classes)
public enum SourceEnum<T extends CharSequence> {
    INSTANCE1,
    INSTANCE2 {
        // First anonymous inner class (feature requirement)
        @Override
        public <T extends CharSequence> void process(T data) {
            System.out.println("Anonymous inner 1: " + data);
        }
    },
    INSTANCE3 {
        // Second anonymous inner class (feature requirement)
        @Override
        public <T extends CharSequence> void process(T data) {
            System.out.println("Anonymous inner 2: " + data.length());
        }
    };

    // Inner class for depends_on_inner_class feature
    private static class SourceInnerClass {
        String innerField;

        public SourceInnerClass(String innerField) {
            this.innerField = innerField;
        }

        public String getProcessed() {
            return innerField.toUpperCase();
        }
    }

    // Field referencing target enum's inner class (per_condition: source contains target's field)
    private TargetEnum.target_inner_rec targetField;

    // Constructor with super constructor invocation (feature requirement)
    SourceEnum() {
        super(); // super constructor invocation
    }

    // Abstract method for super.methodName() feature
    public abstract <T extends CharSequence> void process(T data);

    // Private varargs method to refactor (void return type)
    private void moveTargetMethod(TargetEnum... targetParams) {
        // Depends_on_inner_class feature
        SourceInnerClass innerObj = new SourceInnerClass("innerData");
        
        // For loop containing public varargs method (pos: for)
        for (int i = 0; i < targetParams.length; i++) {
            // Public varargs method (method_feature: 1, source, varargs, super.methodName())
            public void varargsMethod(String... args) {
                // Feature "1" (numeric literal)
                int count = 1;
                // Variable call
                String processed = innerObj.getProcessed();
                // Source feature (reference to source enum)
                SourceEnum<?> sourceRef = SourceEnum.INSTANCE1;
                // Super.methodName() feature
                sourceRef.process(args[0]); // Call super (abstract) method
                
                // Expression statement
                System.out.println(processed + count + args[0]);
            }

            // Variable call feature
            TargetEnum currentTarget = targetParams[i];
            // Set target field (per_condition fulfillment)
            this.targetField = currentTarget.new target_inner_rec("targetData");
            
            // Call varargs method
            varargsMethod(currentTarget.name(), innerObj.innerField);
            
            // Continue statement feature
            if (i % 2 == 0) {
                continue;
            }
            
            // Expression statement feature
            System.out.println("Processed non-even index: " + i);
        }
    }

    // Generic method (type parameter feature)
    public <T extends CharSequence> T genericMethod(T input) {
        return input;
    }
}

// Target enum class (public modifier, same package as source, static nested class)
public enum TargetEnum {
    TARGET1, TARGET2, TARGET3;

    // Static nested class (target_feature: static nested class, target_inner_rec)
    public static class target_inner_rec {
        private String data;

        public target_inner_rec(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    // Protected call method (type: target, modifier: protected)
    protected TargetEnum callMethod(SourceEnum<CharSequence> sourceRef, String... args) {
        // Target_feature: normal (standard method)
        // Target_feature: superTypeReference.methodName(arguments)
        sourceRef.process(args[0]); // Call super type (SourceEnum) method
        
        // Variable call
        target_inner_rec innerRec = new target_inner_rec(args[1]);
        System.out.println(innerRec.getData());
        
        return TargetEnum.TARGET1;
    }
}