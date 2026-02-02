package com.refactoring.movemethod;

import java.util.Objects;

// Source record class: public modifier, same package as target, member inner + local inner class
public record SourceRecord<T>(T value) {
    // Per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_1");

    // Member inner class (source feature)
    public class SourceInnerClass {
        public T processInner(T input) {
            return (T) (input.toString() + "_inner_1");
        }
    }

    // Method to refactor: varargs, TargetClass Type return, default access, in source
    TargetRecord<T> methodToRefactor(TargetRecord<T>... targetParams) {
        // Per_condition: method contains target parameter (varargs)
        if (targetParams == null || targetParams.length == 0) {
            return new TargetRecord<>((T) "default_target_1");
        }

        // Local inner class (source feature)
        class LocalInnerProcessor {
            T process(T input) {
                return (T) (input.toString() + "_local_1");
            }
        }

        TargetRecord<T> result = null;
        int count = 0;

        for (TargetRecord<T> target : targetParams) {
            // Continue statement
            if (Objects.isNull(target.value())) {
                count++;
                continue; // continue statement
            }

            // Variable call (target record + static nested class)
            T targetValue = target.value();
            TargetRecord.TargetStaticNested<T> nested = new TargetRecord.TargetStaticNested<>(targetValue);

            // Local inner class usage
            LocalInnerProcessor processor = new LocalInnerProcessor();
            T processedValue = processor.process(targetValue);

            // Member inner class usage
            SourceInnerClass innerHelper = new SourceInnerClass();
            processedValue = innerHelper.processInner(processedValue);

            // No_new_exception feature
            try {
                Integer.parseInt(processedValue.toString());
                target = new TargetRecord<>(processedValue);
            } catch (NumberFormatException e) {
                // No throw new exception, handle only
                target = new TargetRecord<>((T) "safe_value_1");
            }

            // Variable call for targetField (per_condition)
            T combinedValue = (T) (target.value() + "_" + targetField.value());
            result = new TargetRecord<>(combinedValue);

            count++;
        }

        return result; // Return TargetClass Type
    }
}

// Target record class: public modifier, static nested class feature
public record TargetRecord<T>(T value) {
    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public TargetStaticNested(U value) {
            this.nestedValue = value;
        }

        public U getNestedValue() {
            return nestedValue;
        }

        public void setNestedValue(U nestedValue) {
            this.nestedValue = nestedValue;
        }
    }

    // Additional variable call method
    public T getValue() {
        return this.value;
    }
}