package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for target class implements feature
interface TargetInterface {
    void interfaceMethod();
}

// Target class (normal class, default modifier, implements + static nested class)
class TargetClass implements TargetInterface {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        // Final method for override_violation
        public final void finalMethod() {}
    }

    // Target inner class (target_inner)
    public class TargetInner {
        String innerField; // For per_condition and variable call

        @Override
        public void interfaceMethod() {} // Implements interface method
    }

    @Override
    public void interfaceMethod() {}
}

// Source class (normal class, private modifier, same package, two member inner classes)
private class SourceClass {
    // First member inner class (source_feature)
    class SourceMemberInnerOne {}

    // Second member inner class (source_feature)
    class SourceMemberInnerTwo {}

    // Method to be refactored (instance, Object return, default access, source position)
    @RefactorAnnotation // has_annotation feature
    Object moveMethod(TargetClass.TargetInner targetParam) throws IllegalAccessException { // requires_throws
        // Per_condition: contains target parameter
        if (targetParam == null) {
            throw new IllegalAccessException("Target parameter cannot be null"); // requires_throws
        }

        // Type declaration statement
        TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
        SourceMemberInnerOne innerOne = new SourceMemberInnerOne();

        // Super keywords
        Object superObj = new Object();
        String superStr = superObj.toString();

        // Variable call
        String varCall = targetParam.innerField; // Access target field (per_condition)
        targetParam.innerField = varCall == null ? "default_value" : varCall + "_modified";

        // Override violation (attempt to override final method)
        class OverrideViolationClass extends TargetClass.TargetStaticNested {
            @Override
            public void finalMethod() {} // Compile error (override_violation)
        }

        // No new exception (only declared IllegalAccessException)
        return targetParam.innerField + "_" + superStr;
    }
}