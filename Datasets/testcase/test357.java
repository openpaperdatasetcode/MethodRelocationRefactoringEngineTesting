package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Generic superclass for source class extension
class GenericSuperClass<T> {}

// Source generic class with required features
public class SourceClass<T> extends GenericSuperClass<T> permits SourceSubClass {
    // Target class field to satisfy pre-condition
    TargetClass<String> targetField;

    // Static nested class (duplicated as per feature requirement)
    public static class StaticNestedClass1 {
        static void staticMethod1() {}
    }

    // Static nested class (duplicated as per feature requirement)
    private static class StaticNestedClass2 {
        static void staticMethod2() {}
    }

    // Inner record class containing the method to be moved
    public record InnerRecClass(SourceClass<T> outerInstance) {
        /**
         * Method javadoc for the moveable instance method
         * @throws IOException as required feature
         */
        @CustomAnnotation
        protected void moveableMethod() throws IOException {
            // Private assignment expression with number 1
            private int assignedVar = 1;
            
            // Variable call feature
            String varCall = outerInstance.targetField.innerClassInstance.innerMethod();
            
            // Access outer protected feature
            outerInstance.protectedOuterMethod();
            
            // No new exception instantiation (only throws declared IOException)
            if (assignedVar == 1) {
                throw new IOException("Test IO exception");
            }
        }
    }

    // Protected outer method for access_outer_protected feature
    protected void protectedOuterMethod() {}
}

// Permitted subclass for source class
class SourceSubClass<T> extends SourceClass<T> {}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Target generic class with default modifier and member inner class
class TargetClass<U> {
    // Member inner class as target feature
    class MemberInnerClass {
        String innerMethod() {
            return "innerValue";
        }
    }

    // Instance of member inner class
    MemberInnerClass innerClassInstance = new MemberInnerClass();
}