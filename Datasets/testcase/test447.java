package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: normal class, private modifier, same package as target
// Features: static nested class (duplicate as specified)
private class SourceClass extends ParentClass {
    // Per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature - first occurrence)
    public static class FirstStaticNestedClass<T extends Number & Comparable<T>> {
        // With bounds feature support
        public static <T extends Number & Comparable<T>> T getBoundedValue(T input) {
            return input;
        }
    }

    // Static nested class (source_class feature - duplicate occurrence)
    public static class SecondStaticNestedClass {
        public static String processString(String input) {
            return input.toUpperCase();
        }
    }

    // Recursive inner class for method_position: source_inner_rec
    public class RecursiveInnerClass {
        private String innerVar = "recursiveInnerValue";

        // Custom annotation for has_annotation feature
        @Retention(RetentionPolicy.RUNTIME)
        private @interface MethodAnnotation {}

        // Instance method (type: instance), return List<String>, final access, position: source_inner_rec
        @MethodAnnotation // has_annotation feature
        final List<String> moveCandidateMethod() {
            List<String> result = new ArrayList<>();

            // Variable call (method feature)
            String varCall = this.innerVar;
            result.add(varCall);

            // With bounds (method feature)
            Integer boundedValue = FirstStaticNestedClass.getBoundedValue(100);
            result.add(boundedValue.toString());

            // Call parent_class method in switch (call_method pos: switch)
            switch (boundedValue % 3) {
                case 0:
                    ParentClass.staticMethod(targetField, "case0");
                    break;
                case 1:
                    ParentClass.staticMethod(targetField, "case1");
                    break;
                default:
                    ParentClass.staticMethod(targetField, "default");
                    break;
            }

            // No new exception (method feature - no throw new Exception)
            try {
                // Access target class and its local inner class
                String targetInnerValue = targetField.callLocalInnerClass();
                result.add(targetInnerValue);
                result.add(SecondStaticNestedClass.processString(varCall));
            } catch (Exception e) {
                // No new exception instantiation
                result.add(e.getMessage());
            }

            return result;
        }

        // Recursive inner class layer to satisfy source_inner_rec
        public class NestedRecursiveInnerClass {
            public void callTargetMethod() {
                RecursiveInnerClass.this.moveCandidateMethod();
            }
        }
    }
}

// Parent class for call_method (type: parent_class)
class ParentClass {
    // call_method: modifier=private, target_feature: static, super.methodName(arguments)
    // pos: switch, return_type: void
    private static void staticMethod(TargetClass target, String param) {
        // super.methodName(arguments) feature (call super in parent class context)
        super.toString();
        target.processInput(param);
    }
}

// Target class: normal class, public modifier, target_feature: local inner class
public class TargetClass {
    public String callLocalInnerClass() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String getValue() {
                return "targetLocalInnerValue";
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        return localInner.getValue();
    }

    public void processInput(String input) {
        // Dummy method for parent class call
    }
}