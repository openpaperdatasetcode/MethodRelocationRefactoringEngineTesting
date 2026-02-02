package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodRefactorAnnotation {}

// Super class for source class extends feature
class SuperSourceClass {
    protected String superField = "superValue6430";
}

// Abstract helper class for abstract method feature
abstract class AbstractHelper {
    // Abstract method: protected modifier, method_feature matches abstract type
    protected abstract Object abstractMethod(TargetClass targetParam);
}

// Source class: private, same package as target, extends SuperSourceClass
private class SourceClass extends SuperSourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Inner class to demonstrate uses_outer_this feature
    class InnerClass {
        void useOuterThis() {
            // uses_outer_this: access outer class instance via SourceClass.this
            String outerValue = SourceClass.this.superField;
            System.out.println("Outer this value: " + outerValue);
        }
    }

    // Method to refactor: instance, base type (int) return, public access, in source class
    @MethodRefactorAnnotation // has_annotation feature
    public int methodToRefactor() {
        // Variable call: access target field's inner data
        String targetValue = targetField.getInnerData();
        int result = 0;

        // Constructor invocation: create new target class instance
        TargetClass newTargetInstance = new TargetClass();
        
        // Abstract method feature implementation
        AbstractHelper helper = new AbstractHelper() {
            @Override
            protected Object abstractMethod(TargetClass targetParam) {
                // method_feature: 1 (numeric literal)
                int count = 1;
                // instanceReference.methodName(arguments): call target instance method
                return targetParam.getInnerData() + count;
            }
        };

        // pos: for loop (abstract method execution in for loop)
        for (int i = 0; i < 1; i++) { // method_feature: 1 (loop count)
            // method_feature: target (pass target instance to abstract method)
            Object abstractResult = helper.abstractMethod(newTargetInstance);
            result += abstractResult.toString().length();
        }

        // uses_outer_this: invoke inner class method that uses outer this
        new InnerClass().useOuterThis();

        // no_new_exception: try-catch without throwing new exception
        try {
            result += Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // Handle exception without throwing new
            result = targetValue.length();
        }

        return result; // Base type (int) return
    }
}

// Target class: private, same package as source, anonymous inner class feature
private class TargetClass {
    private String innerData = "targetData123";

    public String getInnerData() {
        // anonymous inner class: target_feature
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous class: " + innerData);
            }
        };
        anonymousRunnable.run();
        return innerData;
    }
}