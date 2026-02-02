package com.example;

import java.util.function.Supplier;

// Source enum class (default modifier, enum, same package, type parameter, local inner class, static nested class)
enum SourceEnum<T extends CharSequence> {
    VALUE1, VALUE2;

    // per_condition: source contains the field of the target
    private final TargetEnum targetField = TargetEnum.TARGET1;
    // this.field for ThrowStatement
    private String field = "sourceFieldValue";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass<T> {}

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord<U>(U data) {
        // Method to be refactored (instance, base type return, default access)
        int targetMethod() {
            // Local inner class (source_class feature)
            class LocalInnerClass<V> {
                V process(V val) { return val; }
            }
            LocalInnerClass<String> local = new LocalInnerClass<>();

            // ThrowStatement (static modifier, this.field, 3, pos: source)
            static void throwBlock() {
                SourceEnum<?> enumInst = SourceEnum.VALUE1;
                String thisField = enumInst.field; // this.field
                int num = 3; // target_feature: 3
                if (num == 3) {
                    throw new IllegalArgumentException(thisField + num); // ThrowStatement
                }
            }

            // Constructor invocation
            TargetEnum.InnerClass innerObj = targetField.new InnerClass();

            // Variable call
            String targetValue = innerObj.getValue();
            local.process(targetValue);

            // Requires try-catch
            int result = 0;
            try {
                throwBlock();
                // Call method in Lambda expressions (pos: Lambda expressions)
                Supplier<Object> supplier = () -> SourceEnum.callMethod(3); // ClassName.methodName(arguments)
                supplier.get();
                result = targetValue.length();
            } catch (IllegalArgumentException e) {
                result = -1;
            }

            return result; // base type return
        }
    }

    // Call method (source, public modifier, normal, ClassName.methodName(arguments), return Object)
    public static Object callMethod(int arg) {
        return "callResult_" + arg;
    }
}

// Target enum class (public modifier, enum, member inner class target_feature)
public enum TargetEnum {
    TARGET1, TARGET2;

    // Member inner class (target_feature)
    class InnerClass {
        private String value = "targetInnerValue";
        public String getValue() { return value; }
    }
}