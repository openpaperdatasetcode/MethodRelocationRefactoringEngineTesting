package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Superclass for enum extension (enums implicitly extend Enum, explicit extension via anonymous impl)
abstract class EnumSuperClass {}

// Source enum class (default modifier, same package, extends + two anonymous inner classes)
enum SourceEnum extends EnumSuperClass {
    INSTANCE;

    // First anonymous inner class (source feature)
    private final Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("source_anonymous_1");
        }
    };

    // Second anonymous inner class (source feature, duplicated)
    private final Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("source_anonymous_2");
        }
    };

    // Inner class for depends_on_inner_class feature
    private class SourceInnerClass {
        int innerField = 3;
    }

    /**
     * Method javadoc for overloading method 1
     * Overloading method to be moved (public, Object return, no parameter type)
     * @param targetParam Target enum parameter (satisfies pre-condition)
     * @return Object result
     */
    public Object moveableMethod(TargetEnum<?> targetParam) {
        // Instance method feature (public modifier, if/else pos, 3 + target + instance + new ClassName().method())
        public int instanceMethod(TargetEnum<?> target) {
            SourceInnerClass innerObj = new SourceInnerClass();
            if (innerObj.innerField == 3) { // Number 3 feature
                // new ClassName().method() call (target + instance)
                return new SourceInnerClass().innerField + target.instanceField;
            } else {
                return 0;
            }
        }

        // Do statement feature
        int doVar = 0;
        do {
            doVar++;
            // Access instance field feature (target enum instance field)
            doVar += targetParam.instanceField;
        } while (doVar < 10);

        // Throw statement (no_new_exception - rethrows existing, no new instantiation)
        try {
            // Used by reflection feature
            Method method = TargetEnum.class.getDeclaredMethod("enumMethod");
            method.invoke(targetParam);
        } catch (Exception e) {
            throw new RuntimeException(e); // No new custom exception, wraps existing
        }

        // Variable call feature
        Object varCall = instanceMethod(targetParam) + doVar + targetParam.getTypeParam();
        
        // Depends_on_inner_class feature
        varCall = varCall + new SourceInnerClass().innerField;

        return varCall;
    }

    /**
     * Method javadoc for overloading method 2
     * Overloading method (public, Object return, no parameter type)
     * @param targetParam Target enum parameter
     * @param extraParam Extra parameter for overloading
     * @return Object result
     */
    public Object moveableMethod(TargetEnum<?> targetParam, String extraParam) {
        return moveableMethod(targetParam) + extraParam;
    }
}

// Target enum class (no modifier, type parameter target feature)
enum TargetEnum<T> {
    TARGET_INSTANCE("test");

    // Instance field for access_instance_field feature
    public final int instanceField = 5;
    private final T typeParam;

    // Constructor with type parameter
    TargetEnum(T typeParam) {
        this.typeParam = typeParam;
    }

    // Enum method for reflection call
    public void enumMethod() {}

    // Getter for type parameter
    public T getTypeParam() {
        return typeParam;
    }
}