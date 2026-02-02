package com.refactoring.test;

// Target class (normal class, abstract modifier, anonymous inner class feature)
abstract class TargetClass {
    String targetField; // For per_condition and variable call/access_instance_field

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };
}

// Source class (normal class, default modifier, same package, local inner + static nested class)
class SourceClass {
    private int instanceField = 10; // For access_instance_field feature

    // Static nested class (source_feature)
    static class SourceStaticNested {
        String nestedField = "static_nested_value";
    }

    // Method to be refactored (instance, TargetClass return, final access, source position)
    final TargetClass moveMethod(TargetClass targetParam) {
        // Per_condition: contains target parameter
        if (targetParam == null) {
            return new TargetClass() {
                @Override
                public void abstractMethod() {}
            };
        }

        // If statement feature
        if (targetParam.targetField == null) {
            targetParam.targetField = "default_value";
        }

        // Expression statement feature
        targetParam.targetField = targetParam.targetField + "_modified";

        // Access instance field feature
        int instanceVal = this.instanceField;
        targetParam.targetField = targetParam.targetField + "_" + instanceVal;

        // Variable call feature
        String varCall = targetParam.targetField;
        targetParam.targetField = varCall + "_variable_call";

        // Local inner class (source_feature)
        class LocalInnerClass {
            void updateTarget(TargetClass target) {
                target.anonymousInner.run();
            }
        }
        new LocalInnerClass().updateTarget(targetParam);

        // No new exception
        return targetParam;
    }
}