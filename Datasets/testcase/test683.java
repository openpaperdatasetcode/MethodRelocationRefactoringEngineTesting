package com.refactoring.movemethod;

import java.util.function.Consumer;

// Abstract base class for abstract method feature
abstract class AbstractRecordHelper<T> {
    // Abstract method: type=abstract, return=void, access=protected, in source
    protected abstract void abstractMethod(TargetRecord<T> target);

    // Generic feature method: public modifier, 3, target, generic, super.methodName(), pos=functional interfaces, return void
    public <T> void genericFeatureMethod(TargetRecord<T> target) {
        // pos: functional interfaces (Consumer)
        Consumer<TargetRecord<T>> consumer = (param) -> {
            // super.methodName(arguments) + target feature
            super.abstractMethod(param); // method_feature: 3
        };
        consumer.accept(target); // generic feature
    }
}

// Source record class: default modifier, same package as target, no extra features
record SourceRecord<T>(T value) extends AbstractRecordHelper<T> {
    // Per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_3"); // numbers=3

    // Override_violation feature (return type/access mismatch)
    @Override
    public void abstractMethod(TargetRecord<T> target) { // override_violation (base: protected, override: public)
        // Per_condition: method contains target parameter
        if (target == null) {
            return;
        }

        // Constructor invocation (target record + inner class)
        TargetRecord<T> newTarget = new TargetRecord<>((T) "new_target_3"); // numbers=3
        TargetRecord<T>.TargetInner inner = newTarget.new TargetInner();

        // Type declaration statement
        class ProcessedType<U> {
            private U value;
            ProcessedType(U val) { this.value = val; }
            U getVal() { return value; }
        }

        // PrefixExpression feature: numbers=3, public modifier, exp=PrefixExpression
        public int prefixExpr = ++3; // numbers=3, PrefixExpression (++), public modifier

        // Variable call (target record + inner class)
        T targetValue = target.value();
        inner.setInnerValue((T) (targetValue + "_inner_3")); // numbers=3

        // Raw type feature (use raw TargetRecord)
        TargetRecord rawTarget = new TargetRecord("raw_type_3"); // numbers=3, raw_type

        // Access_instance_method feature (call target inner instance method)
        String innerResult = inner.innerMethod((T) "instance_method_3"); // numbers=3, access_instance_method

        // Generic feature call (functional interfaces)
        genericFeatureMethod(target);

        // No_new_exception feature
        try {
            // PrefixExpression operation
            int num = 3;
            prefixExpr = ++num; // PrefixExpression (++), numbers=3
            Integer.parseInt(innerResult);
        } catch (NumberFormatException e) {
            // No throw new exception, handle only
            inner.setInnerValue((T) "safe_value_3"); // numbers=3
        }

        // Variable call for targetField (per_condition)
        targetField.new TargetInner().setInnerValue(targetField.value() + "_field_3"); // numbers=3
    }
}

// Target record class: public modifier, member inner class feature
public record TargetRecord<T>(T value) {
    // Member inner class (target_feature)
    public class TargetInner {
        private T innerValue;

        // Instance method for access_instance_method feature
        public String innerMethod(T input) {
            this.innerValue = input;
            return innerValue.toString() + "_method_3"; // numbers=3
        }

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Constructor invocation helper
    public TargetRecord {
        // Default canonical constructor
    }
}