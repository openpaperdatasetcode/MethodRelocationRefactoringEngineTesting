package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Interface for target class implements feature
interface TargetInterface {
    int getFieldValue();
}

// Target abstract class (public modifier, implements + anonymous inner class feature)
public abstract class TargetClass implements TargetInterface {
    int targetField; // For per_condition (source contains this field)

    // Anonymous inner class (target_feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };

    @Override
    public int getFieldValue() {
        return targetField;
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Source abstract class (protected modifier, same package, two anonymous inner classes)
protected abstract class SourceClass {
    // Inner class for method_position:source_inner
    class SourceInnerClass {
        // Overloading method 1 (private modifier, method_feature:2/target/overloading/super.methodName(), pos:for, return base type)
        private int overloadingMethod(TargetClass target) {
            super.toString(); // super.methodName(arguments)
            return target.targetField + 2; // 2 from method_feature
        }

        // Overloading method 2 (overloading feature)
        private int overloadingMethod(TargetClass target, int num) {
            super.hashCode(); // super.methodName(arguments)
            return target.targetField + num + 2; // 2 from method_feature
        }

        // Method to be refactored (instance, base type return, protected access, source_inner position)
        protected int moveMethod(TargetClass targetParam) {
            // Per_condition: source contains the field of the target
            if (targetParam == null) {
                return 0;
            }

            // Overloading method call (pos:for)
            int overloadResult = 0;
            for (int i = 0; i < 3; i++) {
                overloadResult = overloadingMethod(targetParam); // overloading method call in for loop
            }

            // Empty statement feature
            ;

            // Enhanced for statement
            List<TargetClass> targetList = new ArrayList<>();
            targetList.add(targetParam);
            int enhancedForSum = 0;
            for (TargetClass t : targetList) { // enhanced for statement
                enhancedForSum += t.targetField;
            }

            // If statement
            if (targetParam.targetField < 10) { // if statement
                targetParam.targetField = 10;
            }

            // Variable call
            int varCall = targetParam.getFieldValue(); // Variable call to target field (per_condition)

            // Raw type feature
            List rawList = new ArrayList(); // Raw type (no generic)
            rawList.add(targetParam.targetField);

            // Requires try-catch feature
            int tryCatchResult = 0;
            try {
                rawList.add(varCall + overloadResult);
                tryCatchResult = (int) rawList.get(0) + enhancedForSum;
            } catch (ClassCastException e) {
                tryCatchResult = 0;
            }

            // First anonymous inner class (source_feature)
            Runnable anonymous1 = new Runnable() {
                @Override
                public void run() {
                    targetParam.targetAnonymous.run();
                }
            };
            anonymous1.run();

            // Second anonymous inner class (duplicate anonymous inner class feature)
            Runnable anonymous2 = new Runnable() {
                @Override
                public void run() {
                    tryCatchResult += 5;
                }
            };
            anonymous2.run();

            // Return statement
            return tryCatchResult + varCall; // Return base type (int)
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractSourceMethod();
}