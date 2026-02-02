package com.refactor;

// Source class: normal, default modifier, same package as target, implements interface feature
class SourceClass implements SampleInterface {
    // Target class field reference (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("targetValue");

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Varargs helper method (private modifier, 2, target, varargs, super.methodName() in if/else)
        private <T> TargetClass<T> varargsHelperMethod(T... params) {
            if (params.length == 2) {
                return superVarargsMethod(params); // super.methodName() usage
            } else {
                return new TargetClass<>();
            }
        }

        // Super varargs method for super.methodName() feature
        private <T> TargetClass<T> superVarargsMethod(T... params) {
            return new TargetClass<>();
        }

        // Method to refactor: normal type, Object return, private access, in source_inner_rec
        private Object methodToMove() {
            // Varargs feature (in if/else conditions)
            TargetClass<String> varargsResult;
            if (targetField != null) {
                varargsResult = varargsHelperMethod("arg1", "arg2"); // 2 parameters
            } else {
                varargsResult = varargsHelperMethod("default");
            }

            // Do statement with continue statement
            int count = 0;
            do {
                if (count == 1) {
                    continue; // Continue statement
                }
                count++;
                // Expression statement
                String exprVar = targetField.getNestedValue();
                exprVar += "_modified";
            } while (count < 3);

            // Variable call (target field access)
            String targetVar = targetField.getValue();
            TargetClass.StaticNested staticNested = new TargetClass.StaticNested();

            // No new exception thrown
            return new Object[]{varargsResult, targetVar, staticNested};
        }
    }

    @Override
    public void interfaceMethod() {
        // Implements feature - interface method implementation
    }
}

// Interface for source class implements feature
interface SampleInterface {
    void interfaceMethod();
}

// Target class: normal, public modifier, type parameter + static nested class (target_feature)
public class TargetClass<T> {
    private T value;

    public TargetClass() {}
    public TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public String getNestedValue() {
        return String.valueOf(this.value);
    }

    // Static nested class (target_feature)
    public static class StaticNested {}
}