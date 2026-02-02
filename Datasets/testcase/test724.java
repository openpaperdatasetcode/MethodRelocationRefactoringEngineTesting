package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: generic, public, implements, two member inner classes, same package as target
public class SourceClass<T extends CharSequence> implements Runnable {
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // First member inner class (source class feature)
    class FirstInnerClass {
        // Instance field for access_instance_field feature
        private int innerInstanceField = 42;
    }

    // Second member inner class (source_inner - method position)
    class SecondInnerClass {
        // Method to refactor: varargs, void return, protected, in source inner class
        protected void methodToRefactor(String... args) {
            // Variable call (target field's inner class)
            String targetInnerValue = targetField.targetInnerClass.innerValue;
            
            // Access outer private field (access_outer_private feature)
            targetInnerValue += SourceClass.this.outerPrivateField;
            
            // Access instance field (first inner class instance field)
            FirstInnerClass firstInner = new FirstInnerClass();
            int instanceFieldValue = firstInner.innerInstanceField;
            targetInnerValue += instanceFieldValue;
            
            // For statement (iterate over varargs)
            List<String> varargsList = new ArrayList<>();
            for (String arg : args) {
                varargsList.add(arg);
                targetInnerValue += arg;
            }
            
            // Switch case feature
            int caseKey = varargsList.size();
            switch (caseKey) {
                case 0:
                    targetInnerValue += "_empty";
                    break;
                case 1:
                    targetInnerValue += "_single";
                    break;
                default:
                    targetInnerValue += "_multiple";
                    break;
            }
            
            // No new exception feature
            try {
                Integer.parseInt(targetInnerValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                targetInnerValue = "parsing_error";
            }
        }
    }

    // Implement Runnable (implements feature)
    @Override
    public void run() {
        new FirstInnerClass();
        new SecondInnerClass();
    }
}

// Target class: generic, final, member inner class feature, same package as source
final class TargetClass<U> {
    // Member inner class (target_inner_rec - target inner recursive)
    public class TargetInnerClass {
        U innerValue = (U) "targetInnerValue";
    }

    // Instance of inner class (used in source class variable call)
    TargetInnerClass targetInnerClass = new TargetInnerClass();
}