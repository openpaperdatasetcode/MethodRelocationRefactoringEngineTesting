package com.refactoring.movemethod;

import java.io.IOException;

// Super class for source class extends & super constructor invocation
class SuperSourceClass<T> {
    protected T superField;

    public SuperSourceClass(T value) {
        this.superField = value;
    }

    protected T superMethod(T param) {
        return (T) (param.toString() + "_super_processed_1"); // method_feature: 1
    }
}

// Source class: private, generic (type parameter), extends, member inner, local inner class
private class SourceClass<T extends CharSequence> extends SuperSourceClass<T> {
    // Per_condition: source contains target class field
    private TargetClass targetField = TargetClass.createInstance();

    // Instance method for access_instance_method feature
    private String instanceHelperMethod() {
        return "instance_method_result_1"; // method_feature: 1
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Overloading method 1 (protected modifier, method_feature matches)
        protected Object overloadingMethod(TargetClass targetParam) throws IOException {
            try {
                // pos: exception throwing statements
                if (targetParam == null) {
                    throw new IOException("Target param is null"); // requires_throws
                }
                // method_feature: inner_class (operate on target inner class)
                TargetClass.TargetInner inner = targetParam.getInnerClass();
                // method_feature: superTypeReference.methodName(arguments)
                T superResult = (T) SourceClass.super.superMethod((T) inner.getInnerValue());
                // method_feature: 1 (numeric literal)
                return superResult + "_1";
            } catch (IOException e) {
                throw e; // requires_throws
            }
        }

        // Overloading method 2 (overloading feature)
        protected Object overloadingMethod(TargetClass targetParam, String extra) throws IOException {
            Object baseResult = overloadingMethod(targetParam);
            return baseResult + extra + "_1"; // method_feature: 1
        }

        // Method to refactor: instance, Object return, default access, no type params, in source_inner
        Object methodToRefactor() throws IOException {
            // Super constructor invocation (source class super constructor)
            super SourceClass((T) "super_constructor_value_1"); // method_feature: 1

            // Variable call (target field and its inner class)
            String targetValue = targetField.getInnerClass().getInnerValue();
            // access_instance_method feature
            targetValue += SourceClass.this.instanceHelperMethod();

            // If statement
            if (targetValue.contains("1")) { // method_feature: 1
                targetValue += "_if_true_1";
            } else {
                targetValue += "_if_false_1";
            }

            // Call overloading methods
            Object overloadResult1 = overloadingMethod(targetField);
            Object overloadResult2 = overloadingMethod(targetField, "_extra");

            // Local inner class (source class feature)
            class LocalInnerProcessor {
                String process(Object input) {
                    return input.toString() + "_local_inner_1"; // method_feature: 1
                }
            }
            LocalInnerProcessor processor = new LocalInnerProcessor();
            targetValue = processor.process(overloadResult1);

            return targetValue;
        }
    }

    // Local inner class (source class feature)
    public void sourceLocalInnerFeature() {
        class SourceLocalInner {
            void printTarget() {
                System.out.println(targetField.getInnerClass().getInnerValue());
            }
        }
        new SourceLocalInner().printTarget();
    }
}

// Target class: abstract, member inner class feature
abstract class TargetClass {
    // Member inner class (target_feature)
    public class TargetInner {
        private String innerValue = "target_inner_value_1"; // method_feature: 1

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    // Factory method for abstract class instantiation
    public static TargetClass createInstance() {
        return new TargetClass() {
            @Override
            public TargetInner getInnerClass() {
                return new TargetInner();
            }
        };
    }

    // Abstract method (required for abstract class)
    public abstract TargetInner getInnerClass();
}