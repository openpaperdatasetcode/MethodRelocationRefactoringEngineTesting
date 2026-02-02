package com.refactoring.movemethod;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature (duplicate as specified)
@interface StrictfpMethodAnnotation {}

// Super class for super constructor invocation feature
class SuperSourceClass<T> {
    protected T superField;
    public SuperSourceClass() {}
}

// Source class: normal class, private modifier, same package, type parameter, anonymous inner class, local inner class
private class SourceClass<T extends CharSequence> extends SuperSourceClass<T> {
    // Method: instance, return base type (int), strictfp access, source position
    // per_condition: contains target class parameter (TargetClass)
    @StrictfpMethodAnnotation // has_annotation feature (duplicate)
    @StrictfpMethodAnnotation // has_annotation feature (duplicate)
    strictfp int moveableInstanceMethod(TargetClass targetParam) {
        // Variable call feature
        String localVar = targetParam.targetField;
        localVar = String.valueOf(2); // numbers "2"

        // Type declaration statement feature
        class LocalTypeDeclaration {
            T typeField;
            Pattern pattern = Pattern.compile("\\d{2}"); // exp=Pattern + numbers "2"
        }
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Numbers: 2, protected modifier, exp=Pattern
        protected Pattern getPattern() {
            return Pattern.compile(".{2}"); // numbers "2" + exp=Pattern
        }
        localType.pattern = getPattern();

        // Super constructor invocation feature
        new SuperSourceClass<T>() {};

        // Raw_type feature
        List rawList = new ArrayList(); // raw type usage
        rawList.add(localVar);

        // Override_violation feature (attempt to override final method)
        class OverrideViolationClass extends TargetClass {
            @Override
            public final void finalMethod() {} // Compile error (override violation)
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                localVar = "anonymousValue";
            }
        };
        anonymousRunnable.run();

        // Local inner class (source feature)
        class LocalInnerSource {
            void localMethod() {
                localVar += localType.pattern.pattern();
            }
        }
        new LocalInnerSource().localMethod();

        // no_new_exception feature (no custom exceptions instantiated/thrown)
        return localVar.length(); // base type (int) return
    }
}

// Target class: normal class, protected modifier, same package, target_feature: local inner class
protected class TargetClass {
    String targetField = "targetValue";

    // Final method for override_violation feature
    public final void finalMethod() {}

    // Local inner class (target_feature)
    void methodWithLocalInner() {
        class LocalInnerTarget {
            int value = 2; // numbers "2"
        }
        LocalInnerTarget localInner = new LocalInnerTarget();
    }
}