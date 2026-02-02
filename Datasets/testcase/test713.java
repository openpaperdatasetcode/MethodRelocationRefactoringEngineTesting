package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: record class, protected modifier, same package as target, member inner + static nested class
protected record SourceClass(String sourceField) {
    // Per_condition: source contains target class field
    private final TargetClass targetField = new TargetClass("targetData");

    // Static nested class (source class feature)
    static class SourceStaticNestedClass {
        private int staticNestedField = 1; // For method_feature: 1
    }

    // Member inner class (source class feature)
    class SourceMemberInnerClass {
        public List<String> innerInstanceMethod() {
            return new ArrayList<>();
        }
    }

    // Method to refactor: instance, List<String> return, private, in source record class
    private List<String> methodToRefactor() {
        // Variable call (target class field)
        String targetData = targetField.data();
        List<String> result = new ArrayList<>();
        result.add(targetData);

        // Access outer private (source record's private component via accessor)
        String outerPrivate = this.sourceField(); // access_outer_private feature
        result.add(outerPrivate);

        // Nested instance method feature: public modifier, 1, inner_class, instance, obj.m1().m2().m3(), Lambda pos, List<String> return
        public List<String> nestedInstanceMethod() {
            // Lambda expressions position
            Runnable lambda = () -> {
                SourceMemberInnerClass innerObj = new SourceMemberInnerClass();
                // obj.m1().m2().m3() feature
                innerObj.innerInstanceMethod() // m1
                        .add("lambda") // m2
                        .isEmpty(); // m3
            };
            lambda.run();

            // Method_feature: 1 (use static nested field value 1)
            SourceStaticNestedClass staticNested = new SourceStaticNestedClass();
            for (int i = 0; i < staticNested.staticNestedField; i++) {
                result.add("count-" + i);
            }

            // Method_feature: inner_class (call inner class instance method)
            return new SourceMemberInnerClass().innerInstanceMethod();
        }
        result.addAll(nestedInstanceMethod());

        // Override violation (attempt to override final method from Object)
        @Override
        public final String toString() { // override_violation (final method cannot be overridden)
            return result.toString();
        }

        // No_new_exception feature (catch without throwing new exception)
        try {
            Integer.parseInt(targetData);
        } catch (NumberFormatException e) {
            // No throw new exception
            result.add("parse-error");
        }

        return result;
    }
}

// Target class: abstract record class, static nested class feature, same package as source
abstract record TargetClass(String data) {
    // Static nested class (target_inner_rec - target inner recursive)
    static class TargetStaticNestedClass {
        public List<String> targetNestedMethod() {
            return new ArrayList<>();
        }
    }
}