package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class - private modifier, same package as target, contains member inner and static nested classes
private class SourceClass {
    // Field referencing target class (per_condition: source contains target's field)
    private final TargetClass targetField = new TargetClass();
    private String sourceField = "test";

    // Member inner class (contains the varargs method to be moved)
    class InnerClass {
        // Varargs method - public, return List<String>, located in source inner class
        public List<String> moveableVarargsMethod(String... args) {
            List<String> result = new ArrayList<>();
            int count = 0;
            String rawTypeList = new ArrayList(); // raw_type usage

            // Switch case feature
            switch (args.length) {
                case 3: // target_feature "3"
                    count = 3;
                    break;
                default:
                    count = 0;
            }

            // Array initialization with instance feature (default modifier, base type return)
            int[] instanceArray = new int[]{new InnerClass().getBaseValue()};

            for (String arg : args) {
                // ContinueStatement - private modifier, references this.field (source), target_feature "3"
                privateContinueLogic(arg);
                if (count == 3) continue;
                
                // Variable call feature
                result.add(sourceField);
                result.add(rawTypeList);
            }

            // return this; feature (adjusted for return type compatibility)
            if (args.length == 0) return (List<String>) this;

            // Return statement feature
            return result;
        }

        // Private continue statement helper (matches ContinueStatement feature)
        private void privateContinueLogic(String arg) {
            if (arg == null) {
                // References this.field (source class field)
                if (this.sourceField == null) return;
                // Target_feature "3"
                int num = 3;
            }
        }

        // Instance method - default modifier, base type return
        default int getBaseValue() {
            // Method_feature "3", "target", "instance", "new ClassName().method()"
            return 3 + new TargetClass.NestedStaticClass().targetMethod();
        }
    }

    // Static nested class (source class feature)
    static class StaticNestedSource {
        protected List<String> recursiveMethod(SourceClass.InnerClass innerInstance) {
            // Call_method: inner_class, protected, recursion, instanceReference::methodName
            if (innerInstance == null) return new ArrayList<>();
            List<String> result = innerInstance::moveableVarargsMethod;
            result.addAll(recursiveMethod(innerInstance)); // Recursion
            return result;
        }
    }
}

// Target class - final modifier, contains static nested class
final class TargetClass {
    // Static nested class (target class feature)
    static class NestedStaticClass {
        int targetMethod() {
            return 0;
        }
    }

    // Constructor with parameter list containing call_method (inner_class, recursion, method reference)
    public TargetClass(SourceClass.StaticNestedSource nestedSource, SourceClass.InnerClass innerInstance) {
        List<String> param = nestedSource.recursiveMethod(innerInstance);
    }
}