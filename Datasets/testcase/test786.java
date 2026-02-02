// Source class package (different from target)
package com.source;

import com.target.TargetClass;

// Source class: normal, protected modifier, different package, permits + local inner + static nested class
protected sealed class SourceClass permits SourceSubClass {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";
    // Static field for depends_on_static_field feature
    protected static int staticField = 42;

    // Local inner class (source_class feature)
    class LocalInnerClass {
        public void innerMethod() {}
    }

    // Static nested class (source_class feature)
    static class StaticNestedClass {
        public static void staticMethod() {}
    }

    /**
     * Method to refactor - varargs, returns TargetClass, protected access
     * @param targetParam TargetClass parameter (satisfies per_condition)
     * @param args Varargs parameters
     * @return TargetClass instance
     */
    // Method to refactor: varargs, TargetClass return, protected access, source position
    protected TargetClass methodToRefactor(TargetClass targetParam, String... args) {
        // Variable call feature
        String localVar = "localVar";
        localVar = args.length > 0 ? args[0] : localVar;

        // Access outer protected field (access_outer_protected)
        localVar += SourceClass.this.outerProtectedField;

        // Depends on static field (depends_on_static_field)
        int staticValue = SourceClass.staticField;
        localVar += staticValue;

        // Enhanced for statement (enhganced for statement)
        for (String arg : args) {
            localVar += arg;
        }

        // ConstructorInvocation: private modifier, super.field, 2, pos: same_package_target
        privateConstructorInvocation(targetParam);

        // Constructor invocation feature
        LocalInnerClass innerInstance = new LocalInnerClass();

        // No new exception feature (no 'new Exception()' statements)
        return targetParam;
    }

    // Private ConstructorInvocation implementation (same_package_target position)
    private void privateConstructorInvocation(TargetClass targetObj) {
        targetObj.superField = 2; // super.field, 2
    }
}

// Permits subclass (satisfies permits feature of source_class)
final class SourceSubClass extends SourceClass {}

// Target class package (different from source)
package com.target;

// Target class: normal, abstract modifier, anonymous inner class target_feature
public abstract class TargetClass {
    // Field for super.field feature
    int superField;

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method (matches signature)
        protected TargetClass methodToRefactor(TargetClass targetParam, String... args) {
            com.source.SourceClass source = new com.source.SourceSubClass();
            return source.methodToRefactor(targetParam, args);
        }
    }
}