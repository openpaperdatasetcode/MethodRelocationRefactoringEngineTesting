package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Target class (normal class, default modifier, anonymous inner class feature)
class TargetClass {
    int targetField; // For per_condition and variable call/ConstructorInvocation this.field

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };
}

// Source class (normal class, default modifier, same package, static nested + local inner class)
class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        int nestedField = 2; // For instance method_feature "2"
    }

    // Method to be refactored (varargs, Object return, private access, source position)
    @RefactorAnnotation // has_annotation feature
    private Object moveMethod(TargetClass... targetParams) {
        // Per_condition: contains target parameter (varargs)
        if (targetParams == null || targetParams.length == 0) {
            return null;
        }

        // ConstructorInvocation (private modifier, this.field, 1, pos=source)
        private TargetClass constructorInvocation() {
            TargetClass target = new TargetClass();
            target.targetField = 1; // this.field + 1 from target_feature
            return target;
        }
        TargetClass constructedTarget = constructorInvocation();

        // Instance method (final modifier, method_feature:2/source/instance/super.methodName(), pos=array initialization, return TargetClass)
        final TargetClass instanceMethod(int num) {
            super.toString(); // super.methodName()
            TargetClass target = new TargetClass();
            target.targetField = num; // 2 from method_feature
            return target;
        }
        // Array initialization with instance method call (pos=array initialization)
        TargetClass[] targetArray = {instanceMethod(2)};

        // Empty statement feature
        ;

        // Raw type feature
        List rawList = new java.util.ArrayList(); // Raw type (no generic)

        // Variable call feature
        for (TargetClass target : targetParams) {
            int varCall = target.targetField; // Access target field (per_condition)
            target.targetField = varCall + constructedTarget.targetField + targetArray[0].targetField;
            rawList.add(String.valueOf(target.targetField));
        }

        // Local inner class (source_feature)
        class LocalInnerClass {
            Object processTargets(TargetClass[] targets) {
                return java.util.Arrays.asList(targets);
            }
        }
        Object result = new LocalInnerClass().processTargets(targetParams);

        // No new exception
        return result;
    }
}