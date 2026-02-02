package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.reflect.Method;

// Super class for target class extends feature
class SuperTargetClass {
    protected String superTargetField = "superTargetValue";

    public SuperTargetClass() {}
}

// Source class: protected, same package as target, member inner class, anonymous inner class
protected class SourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivate6431";
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("initialTargetValue");

    // Member inner class (source class feature)
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec - method position)
        class InnerRecursiveClass {
            // Overloading method 1: private access, TargetClass return, in source_inner_rec
            private TargetClass<String> methodToRefactor() throws IOException {
                // Super constructor invocation (via target class)
                TargetClass<String> newTarget = new TargetClass<>("superInvokedValue");
                
                // Constructor invocation (target static nested class)
                TargetClass.StaticNestedClass staticNested = new TargetClass.StaticNestedClass();
                
                // Variable call (target field's inner data)
                String targetValue = targetField.getInnerValue();
                // Access outer private field (access_outer_private)
                targetValue += SourceClass.this.outerPrivateField;
                // Uses_outer_this (access outer source class instance)
                String outerThisValue = SourceClass.this.outerPrivateField;
                
                // Expression statement
                newTarget.setInnerValue(targetValue + outerThisValue);
                
                // IOException feature (throw if empty)
                if (newTarget.getInnerValue().isEmpty()) {
                    throw new IOException("Target value is empty");
                }
                
                // Used by reflection feature
                try {
                    Method setMethod = TargetClass.class.getDeclaredMethod("setInnerValue", Object.class);
                    setMethod.setAccessible(true);
                    setMethod.invoke(newTarget, targetValue + "_reflection");
                } catch (Exception e) {
                    // No new exception (wrap without throwing new)
                    throw new IOException("Reflection error", e);
                }
                
                // Override violation (attempt to override final method from Object)
                @Override
                public final String toString() { // Final method override violation
                    return newTarget.getInnerValue();
                }
                
                // Anonymous inner class (source class feature)
                Runnable anonymousRunnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(newTarget.getInnerValue());
                    }
                };
                anonymousRunnable.run();
                
                // No new exception (handle without throwing new)
                try {
                    Integer.parseInt(newTarget.getInnerValue());
                } catch (NumberFormatException e) {
                    // No throw new exception
                    newTarget.setInnerValue("formatted_" + newTarget.getInnerValue());
                }
                
                return newTarget;
            }

            // Overloading method 2 (overloading feature)
            private TargetClass<String> methodToRefactor(String extraParam) throws IOException {
                TargetClass<String> baseResult = methodToRefactor();
                baseResult.setInnerValue(baseResult.getInnerValue() + extraParam);
                return baseResult;
            }
        }
    }

    // Additional anonymous inner class (source class feature)
    public void sourceAnonymousFeature() {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous class: " + outerPrivateField);
            }
        };
        anonymous.run();
    }
}

// Target class: protected, same package as source, type parameter, extends, static nested class
protected class TargetClass<T> extends SuperTargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public String nestedValue = "staticNestedValue";
    }

    private T innerValue;

    // Super constructor invocation (target class)
    public TargetClass(T initialValue) {
        super(); // super constructor invocation
        this.innerValue = initialValue;
    }

    // Target_inner_rec (recursive inner reference)
    public T getInnerValue() {
        return innerValue;
    }

    public void setInnerValue(T value) {
        this.innerValue = value;
    }
}