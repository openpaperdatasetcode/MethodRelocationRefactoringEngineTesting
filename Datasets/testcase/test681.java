package com.refactoring.movemethod;

// Same package target helper for ConstructorInvocation pos
class SamePackageTargetHelper {
    // ConstructorInvocation feature: private modifier, this.field, 3, pos=same_package_target
    private static <T> TargetClass.TargetInnerRec createInnerRec(TargetClass<T> outer) {
        TargetClass.TargetInnerRec inner = outer.new TargetInnerRec();
        inner.innerField = 3; // this.field feature, target_feature: 3
        return inner;
    }
}

// Source class: protected normal class, same package as target, no extra features
protected class SourceClass {
    // Outer private field for access_outer_private feature
    private final String outerPrivateField = "outer_private_3"; // target_feature: 3

    // Per_condition: source contains target class field
    private final TargetClass<String> targetField = new TargetClassImpl<>("initial_target_3");

    // call_method: source type, default modifier, generic, outerInstance.new InnerClass().methodName(), pos=while, return String
    <T> String callMethod(TargetClass<T> outer) {
        int count = 0;
        String result = "";
        // pos: while statement
        while (count < 3) { // target_feature: 3
            // outerInstance.new InnerClass().methodName() + generic feature
            TargetClass<T>.TargetInnerRec inner = outer.new TargetInnerRec();
            result = inner.innerMethod((T) ("generic_3")); // target_feature: 3
            count++;
        }
        return result;
    }

    // Method to refactor: instance, base type (int) return, protected access, in source
    protected int methodToRefactor(TargetClass<String>.TargetInnerRec innerParam) {
        // ConstructorInvocation feature call (same_package_target)
        TargetClass<String>.TargetInnerRec newInner = SamePackageTargetHelper.createInnerRec(targetField);

        // Variable call (target inner recursive class)
        String innerValue = innerParam.getInnerValue();
        int result = innerValue.length();

        // Access_outer_private feature (access outer private field)
        innerParam.setInnerValue(innerValue + "_" + this.outerPrivateField);

        // Expression statement
        innerValue = innerParam.getInnerValue() + "_expr_3"; // target_feature: 3
        innerParam.setInnerValue(innerValue); // expression statement

        // Overload_exist feature (call overloaded method)
        result += methodToRefactor(innerParam, 3); // target_feature: 3

        // No_new_exception feature
        try {
            Integer.parseInt(innerValue);
        } catch (NumberFormatException e) {
            // No throw new exception, handle only
            innerParam.setInnerValue("safe_value_3"); // target_feature: 3
            result = 3; // target_feature: 3
        }

        // Call call_method (source type)
        String callResult = callMethod(targetField);
        result += callResult.length();

        return result; // Base type (int) return
    }

    // Overloaded method (overload_exist feature)
    protected int methodToRefactor(TargetClass<String>.TargetInnerRec innerParam, int extra) {
        return innerParam.getInnerValue().length() + extra;
    }
}

// Target class: abstract normal class, static nested class feature
abstract class TargetClass<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public TargetStaticNested(U value) {
            this.nestedValue = value;
        }

        public U getNestedValue() {
            return nestedValue;
        }
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;
        int innerField; // this.field feature

        // Generic method for call_method generic feature
        public String innerMethod(T value) {
            return value.toString() + "_inner_method_3"; // target_feature: 3
        }

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }

        public int getInnerField() {
            return innerField;
        }
    }

    // Abstract method (required for abstract class)
    public abstract T getValue();
}

// Concrete implementation of target abstract class (for instantiation)
class TargetClassImpl<T> extends TargetClass<T> {
    private T value;

    public TargetClassImpl(T initialValue) {
        this.value = initialValue;
    }

    @Override
    public T getValue() {
        return value;
    }
}