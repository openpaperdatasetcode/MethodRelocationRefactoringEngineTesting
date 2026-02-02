package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Target abstract enum with type parameter (target_feature: type parameter)
abstract enum TargetEnum<T extends CharSequence> { // with_bounds (T extends CharSequence)
    INSTANCE;

    // Static field for depends_on_static_field
    public static final String STATIC_FIELD = "target_static_1";
    private T innerField;

    // Target inner recursive class (target_inner_rec)
    public abstract class TargetInnerRec {
        T recursiveField;
        TargetInnerRec nestedRecursive;

        // Accessor method for call_method feature
        public void setRecursiveField(T value) {
            this.recursiveField = value;
        }

        // Accessor method (getter)
        public T getRecursiveField() {
            return this.recursiveField;
        }

        public TargetInnerRec(T value) {
            this.recursiveField = value;
            this.nestedRecursive = new TargetInnerRec(null) {}; // Recursive constructor
        }
    }

    // Abstract method for enum
    public abstract TargetInnerRec createInnerRec(T value);
}

// Source enum (default modifier, same package, local inner + anonymous inner class)
enum SourceEnum {
    INSTANCE;

    // call_method (source type, default modifier, accessor + superTypeReference.methodName(), pos=exception handling, return String)
    private <T extends CharSequence> String callMethod(TargetEnum<T>.TargetInnerRec target) throws IllegalArgumentException {
        try { // pos=exception handling statements
            // superTypeReference.methodName(arguments) + accessor
            T value = (T) CharSequence.class.cast(target.getRecursiveField());
            target.setRecursiveField((T) (value + "_accessor_call"));
            return target.getRecursiveField().toString() + "_" + TargetEnum.STATIC_FIELD;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid type: " + e.getMessage());
        }
    }

    // Method to be refactored (normal, List<String> return, public access, source position)
    public <T extends CharSequence> List<String> moveMethod(TargetEnum<T>.TargetInnerRec targetParam) throws IllegalArgumentException { // requires_throws
        // Per_condition: contains target parameter (target_inner_rec)
        List<String> result = new ArrayList<>();
        if (targetParam == null) {
            throw new IllegalArgumentException("TargetInnerRec cannot be null"); // requires_throws
        }

        // Constructor invocation
        TargetEnum<T> targetEnum = TargetEnum.INSTANCE;
        TargetEnum<T>.TargetInnerRec newInner = targetEnum.createInnerRec((T) "init_value");

        // Variable call (access target field - per_condition)
        T varCall = targetParam.getRecursiveField();
        if (varCall == null) {
            varCall = (T) ("default_" + TargetEnum.STATIC_FIELD);
        }
        targetParam.setRecursiveField(varCall);

        // with_bounds usage (T extends CharSequence)
        String boundValue = varCall.toString().toUpperCase();
        result.add(boundValue);

        // depends_on_static_field
        result.add(TargetEnum.STATIC_FIELD + "_depends");

        // Anonymous inner class (source_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                targetParam.setRecursiveField((T) (targetParam.getRecursiveField() + "_anonymous"));
            }
        };
        anonymous.run();

        // Local inner class (source_feature)
        class SourceLocalInner {
            void processRecursive(TargetEnum<T>.TargetInnerRec inner) {
                inner.setRecursiveField((T) (inner.getRecursiveField() + "_local_inner"));
                result.add(inner.getRecursiveField().toString());
            }
        }
        new SourceLocalInner().processRecursive(targetParam);

        // call_method invocation (pos=exception handling)
        result.add(callMethod(targetParam));

        // Add new inner instance data
        result.add(newInner.getRecursiveField().toString());

        // No new exception thrown (only declared requires_throws)
        return result;
    }
}