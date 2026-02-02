// Others package for call_method feature
package com.refactoring.others;
import com.refactoring.movemethod.TargetRecord;

public class OthersClass {
    // Static code blocks for call_method pos
    static {
        // pos: Static code blocks
        TargetRecord<String> target = new TargetRecord<>("static_3");
        int result = OthersClass.callMethod(target);
        System.out.println("Static block result: " + result);
    }

    // call_method: others_class type, private modifier, normal, (parameters) -> methodBody, pos=Static code blocks, return int
    private static <T> int callMethod(TargetRecord<T> target) {
        // target_feature: (parameters) -> methodBody (lambda expression)
        java.util.function.Function<TargetRecord<T>, Integer> lambda = (param) -> {
            // target_feature: normal (call normal method)
            return param.getValue().toString().length() + 3;
        };
        return lambda.apply(target); // normal feature
    }
}

// Back to source package
package com.refactoring.movemethod;

import com.refactoring.others.OthersClass;

// Abstract base class for abstract method feature
abstract class AbstractRecordHelper<T> {
    // Abstract method: type=abstract, return=void, access=private (simulated via protected)
    protected abstract void abstractMethod(TargetRecord<T> target);
}

// Source record class: default modifier, same package as target, no extra features
record SourceRecord<T>(T value) {
    // Protected field for access_outer_protected feature
    protected T outerProtectedField = (T) "outer_protected_3";
    // Instance field for access_instance_field feature
    private T instanceField = (T) "instance_3";

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec extends AbstractRecordHelper<T> {
        // Override abstract method (method type: abstract)
        @Override
        protected void abstractMethod(TargetRecord<T> targetParam) {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null");
            }

            // For statement
            for (int i = 0; i < 3; i++) {
                // Variable call (target record and static nested class)
                T targetValue = targetParam.value();
                TargetRecord.TargetStaticNested<T> nested = new TargetRecord.TargetStaticNested<>(targetValue);
                
                // Access_outer_protected feature (access outer record protected field)
                T protectedValue = SourceRecord.this.outerProtectedField;
                T combinedValue = (T) (targetValue + "_" + protectedValue);

                // Access_instance_field feature (access outer record instance field)
                combinedValue = (T) (combinedValue + "_" + SourceRecord.this.instanceField);

                // Requires_try_catch feature
                try {
                    // Simulate exception risk
                    Integer.parseInt(combinedValue.toString());
                    // Call call_method (others_class type)
                    int callResult = OthersClass.callMethod(targetParam);
                    combinedValue = (T) (combinedValue + "_" + callResult);
                } catch (NumberFormatException e) {
                    // Requires_try_catch handling
                    combinedValue = (T) "safe_value_3";
                }

                // Variable call (update static nested class value)
                nested.setNestedValue(combinedValue);
                System.out.println("Processed value: " + nested.nestedValue());
            }
        }

        // Helper method to invoke abstract method
        public void invokeAbstractMethod(TargetRecord<T> target) {
            this.abstractMethod(target);
        }
    }
}

// Target record class: protected modifier, static nested class feature
protected record TargetRecord<T>(T value) {
    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public TargetStaticNested(U value) {
            this.nestedValue = value;
        }

        public U nestedValue() {
            return nestedValue;
        }

        public void setNestedValue(U nestedValue) {
            this.nestedValue = nestedValue;
        }
    }

    // Additional variable call methods
    public T getValue() {
        return value;
    }
}