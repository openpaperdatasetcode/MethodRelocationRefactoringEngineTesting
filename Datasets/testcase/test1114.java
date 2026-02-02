package com.example;

// Source generic class (final modifier, generic, same package, two anonymous inner classes)
final class SourceClass<T extends Number> {
    // First anonymous inner class (source_class feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner 1 in source class");
        }
    };

    // Second anonymous inner class (source_class feature)
    Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner 2 in source class");
        }
    };

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord<U>(U data) {
        // Method to be refactored (normal, void return, default access)
        void targetMethod(TargetClass<String> param) { // per_condition: target parameter
            // ConstructorInvocation (public modifier, obj.field, 2, pos: diff_package_others)
            public void constructorInvocBlock() {
                TargetClass.StaticNestedClass staticObj = new TargetClass.StaticNestedClass();
                String objField = staticObj.field; // obj.field
                TargetClass<String> tempTarget = new TargetClass<>("value_" + 2); // 2 in target_feature
            }
            constructorInvocBlock();

            // Generic method call in exception handling statements (pos: exception handling statements)
            TargetClass<String> genericResult;
            try {
                genericResult = genericMethod(1, param); // 1, inner_class, generic, superTypeReference.methodName(arguments)
            } catch (IllegalArgumentException e) {
                // Throw statement
                throw new IllegalArgumentException("Generic method failed", e);
            }

            // Expression statement
            String targetValue = genericResult.getValue();
            targetValue = targetValue.toUpperCase();

            // FieldAccess (numbers:2, final modifier)
            final int num = 2; // numbers:2
            String fieldAccess = genericResult.staticNestedField; // FieldAccess

            // Variable call
            param.setValue(targetValue + fieldAccess + num);

            // Access instance method
            String methodResult = param.instanceMethod(); // access_instance_method

            // No new exception
            System.out.println(methodResult);
        }

        // Generic method (public modifier, TargetClass return, 1, inner_class, generic)
        public <V extends CharSequence> TargetClass<V> genericMethod(int num, TargetClass<V> superTypeRef) {
            // superTypeReference.methodName(arguments)
            superTypeRef.staticNestedMethod(num);
            return superTypeRef;
        }
    }
}

// Target generic class (no modifier, generic, static nested class target_feature)
class TargetClass<T> {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        String field = "staticNestedField";
    }

    // Static nested field for FieldAccess
    public String staticNestedField = "targetStaticField";

    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    // Static nested method for superTypeReference.methodName(arguments)
    public void staticNestedMethod(int num) {
        this.value = (T) (this.value + "_" + num);
    }

    // Instance method for access_instance_method
    public String instanceMethod() {
        return "processed_" + this.value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}