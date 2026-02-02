package com.refactoring.movemethod;

import java.io.IOException;

// Super type for superTypeReference feature
class SuperTypeClass {
    protected Object processInnerClass(Object innerObj) {
        return innerObj.toString() + "_super_processed";
    }
}

// Source class: private, same package as target, no extra features
private class SourceClass extends SuperTypeClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Overload method (overload_exist feature)
    private TargetClass methodToRefactor(String extraParam) {
        TargetClass baseResult = methodToRefactor();
        baseResult.getInnerClass().setInnerValue(baseResult.getInnerClass().getInnerValue() + extraParam);
        return baseResult;
    }

    // Method to refactor: instance, TargetClass return, private access, in source class
    private TargetClass methodToRefactor() {
        // Variable call (target field and its inner class)
        String targetInnerValue = targetField.getInnerClass().getInnerValue();
        
        // Recursion method feature: protected modifier, 1, inner_class, recursion, superTypeReference.methodName(), pos=exception handling, return Object
        protected Object recursiveMethod(int num) {
            try {
                // pos: exception handling statements
                if (num < 0) throw new IOException("Negative number"); // IOException feature
                
                // method_feature: 1 (base case for recursion)
                if (num == 1) return targetInnerValue + "_1";
                
                // method_feature: recursion (recursive call)
                Object recursiveResult = recursiveMethod(num - 1);
                
                // method_feature: inner_class (process target inner class)
                TargetClass.InnerClass inner = targetField.getInnerClass();
                
                // superTypeReference.methodName(arguments) (call super class method)
                return super.processInnerClass(inner);
            } catch (IOException e) {
                // No new exception (handle without throwing new)
                return "error_" + e.getMessage();
            }
        }

        // Switch case feature
        int switchKey = 1; // align with method_feature: 1
        switch (switchKey) {
            case 1:
                targetInnerValue += "_case_1";
                break;
            case 2:
                targetInnerValue += "_case_2";
                break;
            default:
                targetInnerValue += "_default";
        }

        // Execute recursion method
        Object recursionResult = recursiveMethod(3);
        targetField.getInnerClass().setInnerValue(targetInnerValue + "_recursion_" + recursionResult);

        // No new exception feature (additional catch block)
        try {
            Integer.parseInt(targetInnerValue);
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetField.getInnerClass().setInnerValue("formatted_" + targetInnerValue);
        }

        return targetField; // Return TargetClass Type
    }
}

class TargetClass {
    private String value = "target_base_value";
    public InnerClass getInnerClass() {
        return new InnerClass();
    }

    public class InnerClass {
        private String innerValue = "target_inner_value_1"; // align with method_feature: 1
        public String getInnerValue() {
            return innerValue;
        }
        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }
}