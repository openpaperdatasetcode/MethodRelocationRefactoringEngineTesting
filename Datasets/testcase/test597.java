package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethodAnnotation {}

// Source class: public, same package as target, type parameter, two static nested classes
public class SourceClass<T extends Number> {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    private static int staticField = 1; // For SuperConstructorInvocation target_feature: 1

    // First static nested class (source class feature)
    static class FirstStaticNested<U> {}

    // Second static nested class (source class feature)
    static class SecondStaticNested<V extends CharSequence> {}

    // Super class for SuperConstructorInvocation
    static class SuperSourceClass {
        protected int superField;

        public SuperSourceClass(int value) {
            this.superField = value;
        }
    }

    // Nested class to enable SuperConstructorInvocation
    private class SuperInvoker extends SuperSourceClass {
        public SuperInvoker() {
            super(SourceClass.staticField); // ClassName.field (SourceClass.staticField), target_feature: 1
        }
    }

    // Method to refactor: instance, base type (int) return, default access, in source class
    // Method types parameter is: generic
    @RefactorMethodAnnotation // has_annotation feature
    int methodToRefactor(FirstStaticNested<T> genericParam) {
        // Variable call (target field's static nested class)
        int targetValue = targetField.TargetStaticNestedClass.nestedStaticField;

        // With_bounds feature (generic with multiple bounds)
        class BoundedGeneric<W extends Number & Comparable<W>> {
            W value = (W) Integer.valueOf(3); // method_feature: 3
        }
        BoundedGeneric<Integer> boundedObj = new BoundedGeneric<>();
        targetValue += boundedObj.value;

        // Depends on static field
        targetValue += SourceClass.staticField;

        // Labeled statement
        label:
        for (int i = 0; i < 3; i++) { // method_feature: 3
            if (i == 2) break label;
            targetValue += i;
        }

        // SuperConstructorInvocation feature: private modifier, ClassName.field, 1, pos=same_package_target
        private void superConstructorLogic() {
            // pos: same_package_target (call super constructor with target-related value)
            SuperInvoker invoker = new SuperInvoker();
            targetValue += invoker.superField;
        }
        superConstructorLogic();

        // Generic method feature: public modifier, 3, target, generic, this.methodName(), pos=Static code blocks, return base type
        public <X extends TargetClass> int genericHelperMethod(X targetParam) {
            // this.methodName(arguments) feature
            return this.methodToRefactor(new FirstStaticNested<>()) + 3; // method_feature: 3
        }

        // Static code blocks pos for generic method feature
        static {
            SourceClass<Integer> sourceObj = new SourceClass<>();
            TargetClass targetObj = new TargetClass(); // method_feature: target
            sourceObj.genericHelperMethod(targetObj);
        }

        // Try statement (requires_try_catch / no_new_exception)
        try {
            // Used by reflection feature
            Method method = TargetClass.TargetStaticNestedClass.class.getDeclaredField("nestedStaticField");
            method.setAccessible(true);
            targetValue += (int) method.get(null);

            // No new exception (no throw new exception in catch)
        } catch (Exception e) {
            targetValue = 0; // Handle exception without throwing new
        }

        return targetValue; // Base type (int) return
    }
}

// Target class: protected, same package as source, static nested class feature
protected class TargetClass {
    // Static nested class (target class feature)
    public static class TargetStaticNestedClass {
        public static int nestedStaticField = 1; // For SuperConstructorInvocation target_feature: 1
    }
}