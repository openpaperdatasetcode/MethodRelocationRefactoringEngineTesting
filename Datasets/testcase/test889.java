package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for super.field feature
class TargetParentClass {
    protected int superField = 2; // For ConstructorInvocation super.field + 2
}

/**
 * Target generic class with javadoc (target_feature)
 * strictfp modifier, member inner class feature
 * @param <T> Generic type parameter
 */
strictfp class TargetClass<T> extends TargetParentClass {
    /**
     * Target inner class (target_inner)
     * Member inner class feature (target_feature)
     * @param <U> Inner generic type
     */
    public class TargetInner<U> {
        U innerField; // For per_condition and variable call

        public TargetInner(U innerField) {
            this.innerField = innerField;
        }
    }
}

/**
 * Source generic class (generic class, public modifier)
 * Same package with target, member inner + anonymous inner class features
 * @param <T> Generic type parameter
 */
public class SourceClass<T> {
    // Member inner class (source_feature)
    class SourceMemberInner<U> {
        // ConstructorInvocation inner class (pos=inner class)
        protected class ConstructorInvocationInner {
            // ConstructorInvocation (protected modifier, super.field, 2, pos=inner class)
            protected ConstructorInvocationInner(TargetClass<String>.TargetInner<Integer> target) {
                target.getClass().getSuperclass();
                superField = 2; // super.field + 2 from target_feature
                target.innerField = superField;
            }
        }
    }

    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    /**
     * Method to be refactored (instance, List<String> return, protected access)
     * Source position, target class: target_inner
     * @param targetParam Target inner class parameter (per_condition)
     * @return List<String> result
     */
    @RefactorAnnotation // has_annotation feature
    protected List<String> moveMethod(TargetClass<String>.TargetInner<Integer> targetParam) {
        // Per_condition: contains target parameter
        List<String> result = new ArrayList<>();
        if (targetParam == null) {
            return result;
        }

        // ConstructorInvocation (pos=inner class)
        new SourceMemberInner<>().new ConstructorInvocationInner(targetParam);

        // Constructor invocation (target class/inner class)
        TargetClass<String> targetInstance = new TargetClass<>();
        TargetClass<String>.TargetInner<Integer> newTargetInner = targetInstance.new TargetInner<>(2);

        // If statement feature
        if (targetParam.innerField == 2) { // 2 from target_feature
            targetParam.innerField = 100;
        }

        // Variable call feature
        int varCall = targetParam.innerField; // Access target inner field (per_condition)
        result.add(String.valueOf(varCall));
        result.add(String.valueOf(newTargetInner.innerField));

        // Anonymous inner class execution
        anonymousInner.run();

        // No new exception
        return result;
    }
}