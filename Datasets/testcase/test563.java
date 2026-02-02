package com.refactor;

import java.util.List;
import java.util.ArrayList;

// Source class: normal, protected modifier, same package as target, no additional features
protected class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();

    // Method to refactor: instance, List<String> return, final access modifier, in source class
    public final List<String> methodToMove() {
        // Switch case
        int switchVar = 1;
        switch (switchVar) {
            case 1:
                variableCall();
                break;
            default:
                break;
        }

        // Throw statement (predefined exception, no new exception instantiated)
        try {
            if (targetField == null) {
                throw new IllegalArgumentException("Target field cannot be null");
            }
        } catch (IllegalArgumentException e) {
            // No new exception thrown
            return new ArrayList<>();
        }

        // Variable call (target field access)
        String targetVar = targetField.innerClassInstance.innerField;

        // Uses outer this
        SourceClass outerThis = SourceClass.this;
        outerThis.targetField = new TargetClass();

        // Call method: inner_class type, final modifier, generic + this.methodName(arguments) in property assignment
        TargetClass.TargetInner innerInstance = targetField.new TargetInner();
        innerInstance.genericProp = innerInstance.<String>genericMethod("arg");

        // No new exception thrown
        List<String> result = new ArrayList<>();
        result.add(targetVar);
        return result;
    }

    // Variable call helper method
    private void variableCall() {}
}

// Target class: normal, public modifier, local inner class (target_feature)
public class TargetClass {
    // Member inner class (target_inner - target class for method)
    class TargetInner {
        String innerField = "targetInnerFieldValue";
        // Property for call_method pos: property assignment
        Runnable genericProp;

        // Call method: final modifier, generic, this.methodName(arguments)
        public final <T> void genericMethod(T param) {
            this.innerMethod(param);
        }

        private <T> void innerMethod(T param) {}
    }

    // Inner class instance for variable call
    TargetInner innerClassInstance = new TargetInner();

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            void localMethod() {}
        }
        new TargetLocalInner().localMethod();
    }
}