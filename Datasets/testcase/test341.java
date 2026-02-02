package com.refactoring.movemethod;

import com.refactoring.others.OtherClass;

// Final normal source class (same package as target)
final class SourceClass {
    // Source contains target inner class field (satisfies per_condition)
    private TargetClass.TargetStaticNested targetField = new TargetClass.TargetStaticNested();

    // Member inner class (source feature)
    public class SourceInnerClass {
        // Varargs method (public access, returns TargetClass, source_inner position, target inner param)
        public TargetClass.TargetStaticNested targetMethod(TargetClass.TargetStaticNested targetInnerParam, String... args) throws Exception {
            // Variable call
            String localVar = "var_call";
            volatile int volatileVar = 1; // Volatile modifier for ForStatement

            // ForStatement (volatile modifier, ClassName.field + 1, diff_package_others pos)
            for (int i = 0; i < OtherClass.staticField + 1; i++) {
                localVar += args[i];
            }

            // Constructor invocation
            SourceInnerClass innerInstance = new SourceInnerClass();

            // Switch statement
            switch (volatileVar) {
                case 1:
                    localVar = "case_1";
                    break;
                default:
                    localVar = "default";
            }

            // Synchronized statement
            synchronized (targetInnerParam) {
                localVar += targetInnerParam.nestedField;
            }

            // Override violation (final class can't be extended, method can't be overridden)
            // Requires throws (declares checked exception)
            if (targetInnerParam == null) {
                throw new Exception("Target inner parameter is null");
            }

            return targetInnerParam;
        }
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in Source");
        }
    };
}

// Public normal target class (static nested class feature)
public class TargetClass {
    // Static nested class (target_inner)
    public static class TargetStaticNested {
        public String nestedField = "nested_value";
    }
}

// Different package class for ForStatement pos (diff_package_others)
package com.refactoring.others;

public class OtherClass {
    public static int staticField = 1; // ClassName.field + 1 feature
}