package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super class for super constructor invocation
class SuperGenericClass<T> {
    protected T superField;

    public SuperGenericClass(T value) {
        this.superField = value;
    }
}

// Source class: generic, final, same package as target, static nested + anonymous inner class
final class SourceClass<T extends CharSequence> extends SuperGenericClass<T> {
    // Instance field for access_instance_field feature
    private String instanceField = "source_instance_field";
    // Per_condition: method contains target parameter (enforced in signature)

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) (val.toString() + "_static_processed");
        }
    }

    // Method to refactor: instance, List<String> return, private access, in source class
    private List<String> methodToRefactor(TargetClass<T> targetParam) {
        // Super constructor invocation
        super SourceGenericClass((T) "super_constructor_value");

        List<String> result = new ArrayList<>();
        
        // Variable call (target class and its inner class)
        T targetValue = targetParam.getValue();
        T innerValue = targetParam.getInnerClass().getInnerValue();
        result.add(targetValue.toString());
        result.add(innerValue.toString());

        // Raw_type feature (use raw TargetClass)
        TargetClass rawTarget = new TargetClass();
        rawTarget.setValue((T) "raw_type_value");
        result.add(rawTarget.getValue().toString());

        // Access_instance_field feature
        result.add(this.instanceField);

        // No_new_exception feature
        try {
            Integer.parseInt(targetValue.toString());
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            result.add("formatted_" + targetValue);
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner: " + result);
            }
        };
        anonymousRunnable.run();

        return result;
    }

    // Inner class for call_method (inner_class type)
    class SourceInnerClass {
        // call_method: strictfp modifier, static, instanceReference::methodName, pos=exception throwing, return String
        strictfp String callMethod(SourceClass<T> sourceInstance, TargetClass<T> targetParam) {
            try {
                // pos: exception throwing statements
                if (targetParam == null) {
                    throw new IllegalArgumentException("Target param is null");
                }
                // target_feature: static (call static nested class method)
                T staticProcessed = SourceStaticNested.processValue(targetParam.getValue());
                // target_feature: instanceReference::methodName (method reference)
                Function<TargetClass<T>, List<String>> methodRef = sourceInstance::methodToRefactor;
                List<String> methodResult = methodRef.apply(targetParam);
                return methodResult.toString();
            } catch (IllegalArgumentException e) {
                // Handle exception, no new exception thrown
                return "error_" + e.getMessage();
            }
        }
    }
}

// Target class: generic, default modifier, member inner class feature
class TargetClass<T> {
    private T value;
    private TargetInner innerClass = new TargetInner();

    // For raw_type feature (no-arg constructor)
    public TargetClass() {}

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Member inner class (target_feature)
    public class TargetInner {
        private T innerValue;

        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T value) {
            this.innerValue = value;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TargetInner getInnerClass() {
        return innerClass;
    }
}