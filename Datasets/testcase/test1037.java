package com.refactoring.test;

import java.util.function.Function;

// Parent class for target_class extends feature
class TargetParentClass<T> {
    protected T parentField;

    public TargetParentClass(T value) {
        this.parentField = value;
    }
}

// Target generic class (protected modifier, type parameter + extends + static nested class)
protected class TargetClass<T extends CharSequence> extends TargetParentClass<T> {
    T targetField; // For per_condition (source contains this field)

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        U nestedField;

        public TargetStaticNested(U value) {
            this.nestedField = value;
        }

        public U getNestedField() {
            return nestedField;
        }
    }

    // Instance method for access_instance_method feature
    public T processField(T suffix) {
        return (T) (this.targetField + "_processed_" + suffix);
    }

    // Inner class for overloading method_feature "inner_class"
    public class TargetInnerClass {
        public TargetClass<T> createTarget(T value) {
            return new TargetClass<>(value);
        }
    }

    public TargetClass(T value) {
        super(value); // Super constructor invocation (TargetParentClass)
        this.targetField = value;
    }
}

// Source generic class (protected modifier, same package, two anonymous inner classes)
protected class SourceClass<S extends CharSequence> {

    // Overloading method 1 (protected modifier, method_feature:1/inner_class/overloading/(parameters)->methodBody, pos=if/else, return TargetClass Type)
    protected <T extends CharSequence> TargetClass<T> overloadMethod(TargetClass<T> target, T... args) {
        return overloadMethod(target, true, args);
    }

    // Overloading method 2 (overloading feature)
    protected <T extends CharSequence> TargetClass<T> overloadMethod(TargetClass<T> target, boolean flag, T... args) {
        // (parameters) -> methodBody (lambda) + pos=if/else conditions + 1 from method_feature
        Function<T[], T> combineArgs = (params) -> {
            StringBuilder sb = new StringBuilder();
            for (T param : params) {
                sb.append(param).append("_1");
            }
            return (T) sb.toString();
        };

        if (flag) { // if/else conditions position
            target.targetField = combineArgs.apply(args);
        } else {
            target.targetField = (T) (combineArgs.apply(args) + "_else_1");
        }
        // inner_class feature
        TargetClass<T>.TargetInnerClass inner = target.new TargetInnerClass();
        return inner.createTarget(target.targetField);
    }

    /**
     * Method javadoc for refactored varargs method
     * Processes target generic class instance with various control flow statements
     * @param targetParams TargetClass instances (per_condition: contains target parameter)
     * @return Processed Object result
     */
    // Method to be refactored (varargs, Object return, private access, source position)
    private <T extends CharSequence> Object moveMethod(TargetClass<T>... targetParams) {
        // Per_condition: contains target parameter (target_class)
        if (targetParams == null || targetParams.length == 0) {
            return "no_target_params";
        }

        // Super constructor invocation (implicit for SourceClass's Object superclass)
        super.toString();

        // Constructor invocation
        TargetClass<T> newTarget = new TargetClass<>((T) "init_value_3"); // numbers:3
        TargetClass.TargetStaticNested<T> staticNested = new TargetClass.TargetStaticNested<>((T) "static_nested_3"); // numbers:3

        // If statement
        if (targetParams[0].targetField == null) {
            targetParams[0].targetField = (T) "default_if_3";
        }

        // While statement + numbers:3
        int count = 0;
        while (count < 3) {
            targetParams[0].targetField = (T) (targetParams[0].targetField + "_while_" + count);
            count++;
        }

        // Do statement + numbers:3
        count = 0;
        do {
            newTarget.targetField = (T) (newTarget.targetField + "_do_" + count);
            count++;
        } while (count < 3);

        // Variable call (access target field - per_condition)
        T varCall = targetParams[0].targetField;

        // access_instance_method feature + numbers:3 (MethodReference)
        targetParams[0].processField((T) "instance_method_3"); // MethodReference via instance call

        // Overloading method call
        TargetClass<T> overloadedTarget = overloadMethod(targetParams[0], (T) "arg1_3", (T) "arg2_3");

        // First anonymous inner class (source_feature)
        Runnable anonymous1 = new Runnable() {
            @Override
            public void run() {
                overloadedTarget.targetField = (T) (overloadedTarget.targetField + "_anon1_3");
            }
        };
        anonymous1.run();

        // Second anonymous inner class (source_feature)
        Runnable anonymous2 = new Runnable() {
            @Override
            public void run() {
                staticNested.nestedField = (T) (staticNested.nestedField + "_anon2_3");
            }
        };
        anonymous2.run();

        // Collect result data
        StringBuilder result = new StringBuilder();
        result.append("Original target field: ").append(varCall)
              .append("\nOverloaded target field: ").append(overloadedTarget.targetField)
              .append("\nStatic nested field: ").append(staticNested.getNestedField());

        // No new exception
        return result.toString();
    }
}