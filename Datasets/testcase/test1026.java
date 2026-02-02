package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Others class for recursion method_feature "others_class"
class OthersClass {
    public int recursiveMethod(TargetClass target, int depth) {
        if (depth <= 0) return 1; // 1 from method_feature
        return recursiveMethod(target, depth - 1) + 1; // recursion feature
    }
}

/**
 * Target class (empty modifier, javadoc + anonymous inner class features)
 * Javadoc is part of target_feature as specified
 */
class TargetClass {
    String targetField; // For per_condition (source contains this field)
    int instanceField = 2; // For access_instance_field + numbers:2

    // Anonymous inner class (target_feature)
    private final Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            targetField += "_anonymous_" + 1;
        }
    };

    public void invokeAnonymous() {
        anonymousRunnable.run();
    }
}

// Source abstract class (to host abstract method)
abstract class SourceAbstractHost {
    // Abstract method to be refactored (matches method type: abstract)
    public abstract List<String> moveMethod(TargetClass targetParam);
}

// Source class (normal class, protected modifier, same package, two static nested classes)
protected class SourceClass extends SourceAbstractHost {
    protected String outerProtectedField = "protected_val_2"; // For access_outer_protected + numbers:2

    // First static nested class (source_feature)
    static class SourceStaticNestedOne {
        static void helperMethod(TargetClass target) {
            target.targetField += "_static_nested_1";
        }
    }

    // Second static nested class (source_feature)
    static class SourceStaticNestedTwo {
        // Recursion method (default modifier, method_feature:1/others_class/recursion/instanceReference::methodName, pos=object initialization, return base type)
        int recursionMethod(TargetClass target) {
            // Object initialization position
            OthersClass othersInstance = new OthersClass();
            // instanceReference::methodName + recursion
            var recursionFunc = othersInstance::recursiveMethod;
            int result = recursionFunc.apply(target, 1); // 1 from method_feature
            return result;
        }

        // Overload exist method 1
        void processField(TargetClass target) {
            target.targetField += "_overload_1";
        }

        // Overload exist method 2 (overload_exist feature)
        void processField(TargetClass target, boolean flag) { // numbers:2 (BooleanLiteral)
            target.targetField += "_overload_2_" + flag;
        }
    }

    // Implement abstract method (method to be refactored)
    @Override
    public List<String> moveMethod(TargetClass targetParam) {
        // Per_condition: contains target parameter
        List<String> result = new ArrayList<>();
        if (targetParam == null) {
            return result;
        }

        // Requires try-catch
        try {
            // Recursion method call (pos=object initialization)
            SourceStaticNestedTwo nestedTwo = new SourceStaticNestedTwo();
            int recursionResult = nestedTwo.recursionMethod(targetParam);

            // Enhanced for statement
            List<String> items = new ArrayList<>();
            items.add("item1");
            items.add("item2");
            for (String item : items) { // enhganced for statement (typo as per input)
                targetParam.targetField += "_enhanced_" + item;
            }

            // Continue statement
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    continue; // continue statement
                }
                targetParam.targetField += "_continue_" + i;
            }

            // Numbers:2 + BooleanLiteral
            boolean flag = true; // BooleanLiteral, tied to number 2
            nestedTwo.processField(targetParam, flag); // overload_exist + numbers:2

            // Variable call (access target field - per_condition)
            String varCall = targetParam.targetField;
            result.add(varCall);

            // Access outer protected field
            targetParam.targetField += "_" + this.outerProtectedField; // access_outer_protected

            // Access instance field
            int instVal = targetParam.instanceField; // access_instance_field
            targetParam.targetField += "_instance_" + instVal;

            // Invoke target's anonymous inner class
            targetParam.invokeAnonymous();

            // Static nested class usage
            SourceStaticNestedOne.helperMethod(targetParam);

            // Update result
            result.add(targetParam.targetField);
            result.add(String.valueOf(recursionResult));
        } catch (Exception e) {
            result.add("Exception caught: " + e.getMessage());
        }

        // No new exception thrown
        return result;
    }
}