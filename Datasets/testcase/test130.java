package com.refactoring.movemethod;

// Source class: normal class, default modifier, same package, extends, anonymous inner, static nested
class SourceClass extends BaseClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    private String sourceVar;

    // Static nested class (source feature)
    static class StaticNestedSource {
        void nestedMethod() {
            System.out.println("Static nested class method");
        }
    }

    // Member inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method: instance, void return, default access, source_inner position
        void moveableInstanceMethod() {
            // this.var = var feature
            this.sourceInnerVar = "assignedValue";
            SourceClass.this.sourceVar = this.sourceInnerVar;

            // Variable call feature
            String localVar = sourceVar;
            localVar = targetField.targetInstanceField;

            // Access_instance_method feature
            targetField.targetInstanceMethod();
            StaticNestedSource.nestedMethod();

            // Depends_on_inner_class feature
            AnonymousInnerUsage anonymousUsage = new AnonymousInnerUsage();
            anonymousUsage.useAnonymousClass();

            // No_new_exception feature (no custom exceptions instantiated)
            if (localVar == null) {
                throw new NullPointerException(); // Built-in exception allowed
            }
        }

        private String sourceInnerVar;
    }

    // Anonymous inner class (source feature)
    class AnonymousInnerUsage {
        void useAnonymousClass() {
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    StaticNestedSource.nestedMethod();
                }
            };
            anonymousRunnable.run();
        }
    }
}

// Base class for source_class extends feature
class BaseClass {
    protected void baseMethod() {}
}

// Target class: normal class, public modifier, empty target_feature
public class TargetClass {
    String targetInstanceField = "targetFieldValue";

    void targetInstanceMethod() {
        // Call_method: sub_class, default modifier, normal, ClassName::methodName, pos=exception throwing
        try {
            SubClass subInstance = new SubClass();
            Runnable methodRef = SubClass::subClassMethod;
            methodRef.run();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Error", e); // exception throwing statements position
        }
    }
}

// Call_method: sub_class (subclass of TargetClass)
class SubClass extends TargetClass {
    // Call_method: default modifier, normal, return void
    default void subClassMethod() {
        System.out.println("Subclass method");
    }
}