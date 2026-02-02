package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class - public, same package, with type parameter, member inner & static nested classes
public class SourceClass<T extends CharSequence> {
    // Static field for method dependency
    public static String staticField = "staticDependency";

    // Instance method for access in target method
    public void instanceMethod() {
        System.out.println("Instance method called");
    }

    // Static nested class
    public static class SourceStaticNested {
        private int nestedInt = 42;
    }

    // Member inner class
    public class SourceMemberInner {
        private String innerField = "innerValue";
    }

    // Parent class for overriding method
    public static abstract class ParentClass {
        protected abstract void methodToMove(TargetClass.TargetStaticNested targetParam) throws IOException;
    }

    // Overriding method (matches all specified features)
    public class SourceSubClass extends ParentClass {
        @CustomAnnotation // has_annotation
        @Override // overriding type
        protected void methodToMove(TargetClass.TargetStaticNested targetParam) throws IOException {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                throw new NullPointerException(); // NullPointerException feature
            }

            // variable call
            String varCall = SourceClass.staticField;
            System.out.println(varCall);

            // access_instance_method
            new SourceClass<>().instanceMethod();

            // depends_on_static_field
            if (staticField.isEmpty()) {
                return; // return statement
            }

            // IOException
            if (targetParam.nestedValue < 0) {
                throw new IOException("Invalid nested value");
            }

            // no_new_exception (no custom new Exception() creation)
            if (varCall == null) {
                throw new NullPointerException("Static field is null");
            }

            return; // return statement (void return type)
        }
    }
}

// Target class - public, with static nested class (target_feature)
public class TargetClass {
    // Static nested class
    public static class TargetStaticNested {
        public int nestedValue = 0;
    }
}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {
}