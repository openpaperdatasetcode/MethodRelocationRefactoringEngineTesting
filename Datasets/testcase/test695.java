package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Others class for recursion method_feature
class OthersClass<T> {
    protected TargetClass<T> recursiveMethod(TargetClass<T> target, int depth) {
        // recursion feature (base case)
        if (depth <= 0) {
            return target;
        }
        // super.methodName(arguments) simulation + recursion
        return recursiveMethod(target, depth - 1); // method_feature: 2
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 2;
}

// Source class: final generic class, same package as target, static nested + anonymous inner class
final class SourceClass<T extends CharSequence> extends OthersClass<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClassImpl<>((T) "initial_target_2");

    // Static nested class (source feature)
    public static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) (val + "_static_2"); // method_feature: 2
        }
    }

    // Inner class for call_method feature
    protected class SourceInnerClass {
        // call_method: inner_class type, protected modifier, varargs, super.methodName(), pos=array initialization, return int
        protected int callMethod(TargetClass<T>... targets) { // varargs feature
            // pos: array initialization
            T[] initArray = (T[]) new CharSequence[]{"arg1_2", "arg2_2"}; // method_feature: 2
            int sum = 0;
            for (TargetClass<T> target : targets) {
                // super.methodName(arguments) feature
                TargetClass<T> result = SourceClass.super.recursiveMethod(target, 2); // method_feature: 2
                sum += result.getValue().length();
            }
            return sum;
        }
    }

    // Recursion feature: protected modifier, 2, others_class, recursion, super.methodName(), pos=array initialization, return TargetClass Type
    @Override
    protected TargetClass<T> recursiveMethod(TargetClass<T> target, int depth) {
        // pos: array initialization
        TargetClass<T>[] targetArray = (TargetClass<T>[]) new TargetClass[]{target, targetField}; // method_feature: 2
        // super.methodName(arguments) + recursion
        TargetClass<T> result = super.recursiveMethod(targetArray[0], depth); // others_class + recursion
        return result; // return TargetClass Type
    }

    // Method to refactor: normal, base type (int) return, final access, in source
    @ProcessAnnotation(2) // has_annotation feature
    public final int methodToRefactor(TargetClass<T> targetParam) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            return 0;
        }

        int result = 0;
        int count = 0;

        while (count < 5) {
            // Continue statement
            if (count % 2 == 1) {
                count++;
                continue; // continue statement
            }

            // uses_outer_this feature (access outer class this)
            T outerValue = SourceClass.this.targetField.getValue(); // uses_outer_this

            // Variable call (target class)
            T targetValue = targetParam.getValue();
            
            // Switch case
            switch (targetValue.length()) {
                case 2: // method_feature: 2
                    result += 2;
                    break;
                case 4:
                    result += 4;
                    break;
                default:
                    result += 1;
                    break;
            }

            // Depends_on_inner_class (use target's local inner class)
            targetParam.processWithLocalInner();

            // Anonymous inner class (source feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    targetParam.setValue((T) (targetValue + "_anonymous_2")); // method_feature: 2
                }
            };
            anonymous.run();

            // Recursion feature call (array initialization)
            TargetClass<T> recursiveResult = recursiveMethod(targetParam, 2); // method_feature: 2
            result += recursiveResult.getValue().length();

            // No_new_exception feature
            try {
                Integer.parseInt(targetValue.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, handle only
                targetParam.setValue((T) "safe_value_2"); // method_feature: 2
                result += 2; // method_feature: 2
            }

            count++;
        }

        // Call call_method (inner_class type)
        int callResult = new SourceInnerClass().callMethod(targetParam, targetField);
        result += callResult;

        return result; // Base type (int) return
    }
}

// Target class: abstract generic class, type parameter + local inner class feature
abstract class TargetClass<T> {
    protected T value;

    // type parameter feature (class level)
    public abstract T getValue();
    public abstract void setValue(T value);

    // Local inner class (target_feature)
    public void processWithLocalInner() {
        class TargetLocalInner {
            T process(T input) {
                return (T) (input + "_local_inner_2"); // method_feature: 2
            }
        }
        this.value = new TargetLocalInner().process(this.value);
    }
}

// Concrete implementation of abstract target class
class TargetClassImpl<T extends CharSequence> extends TargetClass<T> {
    public TargetClassImpl(T initialValue) {
        this.value = initialValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}