package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface EnumMethodAnnotation {}

// Source class: enum, public, same package as target, two member inner classes
public enum SourceEnum {
    INSTANCE;

    // Per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.VALUE1;

    // First member inner class (source class feature)
    class FirstInnerClass {
        String innerField1 = "inner1_value";
    }

    // Second member inner class (source_inner - method position)
    class SecondInnerClass {
        // Abstract method to refactor: default access, TargetEnum return, in source_inner
        @EnumMethodAnnotation // has_annotation feature
        abstract TargetEnum methodToRefactor();

        // Concrete implementation of abstract method (enum requires concrete impl for abstract methods)
        TargetEnum methodToRefactorImpl() {
            // Variable call (target enum field and its inner class)
            String targetInnerValue = targetField.innerClass.innerValue;
            
            // No new exception feature
            try {
                // Attempt parse to trigger exception handling (no new exception thrown)
                Integer.parseInt(targetInnerValue);
            } catch (NumberFormatException e) {
                // Handle exception without throwing new
                targetInnerValue = "formatted_" + targetInnerValue;
            }

            // Return target enum instance (TargetClass Type return)
            return TargetEnum.VALUE2;
        }
    }

    // Demonstrate inner class usage
    public void useInnerClasses() {
        new FirstInnerClass();
        SecondInnerClass inner = new SecondInnerClass();
        // Call abstract method impl
        TargetEnum result = inner.methodToRefactorImpl();
        System.out.println("Result: " + result);
    }
}

// Target class: enum, protected, same package as source, member inner class feature
protected enum TargetEnum {
    VALUE1, VALUE2;

    // Member inner class (target class feature)
    public class InnerClass {
        String innerValue = "target_inner_value";
    }

    // Instance of inner class (used in source class variable call)
    InnerClass innerClass = new InnerClass();
}