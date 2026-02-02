package com.refactoring.movemethod;

import java.util.Objects;

// Sealed interface with permits for source class feature
sealed interface SourceSealed permits SourceClass, SourceClass.SourceStaticNested {}

// Parent class for super constructor invocation
class ParentSourceClass {
    protected String parentField;

    public ParentSourceClass(String value) {
        this.parentField = value;
    }

    // Instance method for access_instance_method feature
    protected String parentInstanceMethod(String input) {
        return input + "_parent_6561";
    }
}

// Source class: normal default modifier, same package as target, permits, member inner, static nested class
class SourceClass extends ParentSourceClass implements SourceSealed {
    // Per_condition: source contains target class field
    private final TargetClass<String> targetField = new TargetClass<>("initial_target_6561");

    // Static nested class (source feature)
    static final class SourceStaticNested implements SourceSealed {
        // Static method for call_method static feature
        public static <T> TargetClass<T> staticProcess(TargetClass<T> target) {
            target.setValue((T) (target.getValue() + "_static_6561"));
            return target;
        }
    }

    // Member inner class (source feature)
    public class SourceInnerClass {
        public String processInner(String value) {
            return value + "_inner_6561";
        }
    }

    // Super constructor invocation (source class constructor)
    public SourceClass() {
        super("super_ctor_6561"); // super constructor invocation
    }

    // call_method: source type, protected modifier, static, superTypeReference.methodName(), pos=property assignment, return TargetClass Type
    protected <T> TargetClass<T> callMethod(TargetClass<T> target) {
        // pos: property assignment
        T processedValue = (T) ParentSourceClass.super.parentInstanceMethod(target.getValue().toString()); // superTypeReference.methodName()
        target.setValue(processedValue); // property assignment
        // target_feature: static (call static nested class method)
        return SourceStaticNested.staticProcess(target);
    }

    // Overloading method 1 (method type: overloading)
    TargetClass<String> methodToRefactor(TargetClass<String> targetParam) {
        // Type declaration statement
        class ProcessedType {
            private String value;
            ProcessedType(String val) { this.value = val; }
            String getVal() { return value; }
        }

        // Super constructor invocation verification (via parent field)
        String superValue = this.parentField;

        int count = 0;
        // Do statement
        do {
            // For statement
            for (int i = 0; i < 3; i++) {
                // Variable call (target class)
                String targetValue = targetParam.getValue();
                
                // Access_instance_method feature (call parent instance method)
                String parentResult = this.parentInstanceMethod(targetValue);
                
                // Expression statement
                targetValue = new SourceInnerClass().processInner(parentResult); // expression statement
                targetParam.setValue(targetValue); // expression statement

                // Requires_try_catch feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    targetParam.setValue("safe_value_6561");
                }

                count++;
            }
        } while (count < 1);

        // Type declaration statement usage
        ProcessedType processed = new ProcessedType(targetParam.getValue());
        targetParam.setValue(processed.getVal());

        // Variable call for targetField (per_condition)
        targetParam.setValue(targetParam.getValue() + "_" + targetField.getValue());

        // Call call_method
        targetParam = callMethod(targetParam);

        return targetParam; // Return TargetClass Type
    }

    // Overloading method 2 (overload_exist feature)
    TargetClass<String> methodToRefactor(TargetClass<String> targetParam, String extra) {
        TargetClass<String> baseResult = methodToRefactor(targetParam);
        baseResult.setValue(baseResult.getValue() + "_overload_" + extra);
        return baseResult;
    }
}

// Target class: normal public modifier, type parameter, static nested class feature
public class TargetClass<T> {
    private T value;

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public U getNestedValue() {
            return nestedValue;
        }

        public void setNestedValue(U nestedValue) {
            this.nestedValue = nestedValue;
        }
    }

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Variable call getters/setters
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}