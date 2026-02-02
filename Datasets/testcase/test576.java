package com.refactor;

// Source class: normal, default modifier, same package as target, anonymous inner + local inner class
class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";

    // Overloading method 1: void return, private access, contains target parameter (per_condition)
    private void methodToMove(TargetClass targetParam) {
        methodToMove(targetParam, "default");
    }

    // Overloading method 2 (core refactored method)
    private void methodToMove(TargetClass targetParam, String str) {
        // Try statement
        try {
            // Super keywords usage
            super.toString();
            super.hashCode();

            // Variable call (target parameter access)
            String targetVar = targetParam.staticNestedField;

            // Access outer protected field
            String protectedAccess = SourceClass.this.outerProtectedField;

            // Local inner class (source feature)
            class SourceLocalInner {
                void useTarget(TargetClass target) {
                    System.out.println(target.staticNestedField);
                }
            }
            new SourceLocalInner().useTarget(targetParam);

            // Anonymous inner class (source feature)
            Runnable anonymousInner = new Runnable() {
                @Override
                public void run() {
                    SourceClass.this.methodToMove(targetParam);
                }
            };
            anonymousInner.run();
        } catch (Exception e) {
            // No new exception thrown
        }
    }
}

// Target class: normal, default modifier, static nested class (target_feature)
class TargetClass {
    // Target field (used in variable call)
    public String staticNestedField = "targetValue";

    // Static nested class (target_feature)
    static class TargetStaticNested {}
}