package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;

// Functional interface for source_class implements feature
interface SourceFunctionalInterface {
    void processTarget(Object target);
}

/**
 * Target abstract class (empty modifier, javadoc + static nested class features)
 */
abstract class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static void helperMethod(TargetInner inner) {
            inner.innerField = "static_nested_processed";
        }
    }

    // Target inner class (target_inner)
    public abstract class TargetInner {
        String innerField; // For per_condition and variable call

        public abstract void abstractInnerMethod();
    }
}

// Source abstract class (public modifier, same package, implements + anonymous inner + static nested class)
public abstract class SourceClass implements SourceFunctionalInterface {
    // Outer protected field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_value";

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        static void validateTarget(TargetClass.TargetInner target) {
            if (target == null) {
                throw new NullPointerException("TargetInner cannot be null");
            }
        }
    }

    // Method to be refactored (instance, void return, public access, source position)
    public void processTarget(Object targetObj) {
        // Cast to target_inner and check per_condition (contains target parameter)
        if (!(targetObj instanceof TargetClass.TargetInner targetParam)) {
            return;
        }

        // Type declaration statement
        TargetClass.TargetStaticNested staticNestedInstance = new TargetClass.TargetStaticNested();
        Method reflectMethod = null;

        // Super keywords (call Object superclass method)
        super.toString();

        // NullPointerException handling
        try {
            SourceStaticNested.validateTarget(targetParam);
        } catch (NullPointerException e) {
            // No new exception thrown
            return;
        }

        // Used by reflection
        try {
            reflectMethod = TargetClass.TargetInner.class.getMethod("abstractInnerMethod");
            Method getInnerFieldMethod = TargetClass.TargetInner.class.getMethod("getClass");
            getInnerFieldMethod.invoke(targetParam);
        } catch (Exception e) {
            // No new exception thrown
        }

        // Variable call (access target inner field - per_condition)
        String varCall = targetParam.innerField;
        targetParam.innerField = (varCall == null ? "" : varCall) + "_var_modified";

        // Access outer protected field
        targetParam.innerField += "_" + this.outerProtectedField;

        // Anonymous inner class (source_feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                TargetClass.TargetStaticNested.helperMethod(targetParam);
            }
        };
        anonymousRunnable.run();

        // No new exception thrown
    }

    // Abstract method required for abstract class
    public abstract void abstractSourceMethod();
}