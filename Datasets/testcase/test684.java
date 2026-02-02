// Source package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetRecord;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 3;
}

// Source record class: protected modifier, different package from target, no extra features
protected record SourceRecord<T>(T value) {
    // Protected field for access_outer_protected feature
    protected T outerProtectedField = (T) "outer_protected_3";
    // Per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_3");

    // this(arguments) feature (canonical constructor with this call)
    public SourceRecord {
        this((T) (value + "_this_args_3")); // this(arguments)
    }

    // Auxiliary constructor for this(arguments)
    private SourceRecord(T value, boolean dummy) {
        this(value); // super keywords (implicit record super constructor)
    }

    // Method to refactor: instance, TargetClass Type return, default access, in source
    @ProcessAnnotation(3) // has_annotation feature
    TargetRecord<T> methodToRefactor(TargetRecord<T>.TargetInner innerParam) {
        // Per_condition: method contains target parameter
        if (innerParam == null) {
            innerParam = targetField.new TargetInner(); // constructor invocation
        }

        // If statement
        if (innerParam.getInnerValue() == null) {
            innerParam.setInnerValue((T) "default_3"); // variable call
        }

        // Super keywords (record implicit super)
        Object superRef = super.toString();

        // Access_outer_protected feature (access outer protected field)
        T protectedValue = this.outerProtectedField;
        innerParam.setInnerValue((T) (innerParam.getInnerValue() + "_" + protectedValue));

        // Depends_on_inner_class (use target's static nested class)
        TargetRecord.TargetStaticNested<T> nested = new TargetRecord.TargetStaticNested<>((T) "nested_3");
        innerParam.setInnerValue((T) (innerParam.getInnerValue() + "_" + nested.getNestedValue()));

        // No_new_exception feature
        try {
            // Variable call (target inner class)
            T innerValue = innerParam.getInnerValue();
            Integer.parseInt(innerValue.toString());
        } catch (NumberFormatException e) {
            // No throw new exception, handle only
            innerParam.setInnerValue((T) "safe_value_3");
        }

        // Variable call for targetField (per_condition)
        TargetRecord<T> result = new TargetRecord<>((T) (targetField.value() + "_" + innerParam.getInnerValue())); // constructor invocation

        return result; // Return TargetClass Type
    }
}

// Target package (different from source)
package com.refactoring.target;

// Target record class: private modifier, static nested class feature
private record TargetRecord<T>(T value) {
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

    // Target_inner (target inner class)
    public class TargetInner {
        private T innerValue;

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