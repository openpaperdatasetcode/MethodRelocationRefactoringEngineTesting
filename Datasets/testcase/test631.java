package com.refactoring.movemethod;

// Super class for super constructor invocation and superTypeReference feature
class SuperClass<T> {
    protected T superField;

    public SuperClass(T value) {
        this.superField = value;
    }

    protected T superMethod(T param) {
        return (T) (param.toString() + "_super_2"); // numbers=2
    }
}

// Source class: private normal class, type parameter, member inner, anonymous inner class
private class SourceClass<T extends CharSequence> extends SuperClass<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_1"); // target_feature: 1

    // ConstructorInvocation feature: static modifier, ClassName.field, 1, pos=inner class
    private static class CtorInvocationInner { // pos: inner class
        public static <U extends CharSequence> TargetClass<U> createTarget() {
            TargetClass<U> target = new TargetClass<>((U) "ctor_1"); // target_feature: 1
            target.classField = 1; // ClassName.field feature, target_feature: 1
            return target;
        }
    }

    // Member inner class (source feature)
    class SourceInnerClass {
        // call_method: inner_class type, default modifier, instance, superTypeReference.methodName(), pos=ternary operators, return TargetClass Type
        TargetClass<T> callMethod(TargetClass<T> target, boolean flag) {
            // pos: ternary operators
            T processed = flag ? 
                SourceClass.super.superMethod(target.getValue()) : // superTypeReference.methodName()
                (T) (target.getValue() + "_ternary_2"); // numbers=2
            
            // target_feature: instance (call instance method)
            target.setValue(processed);
            return target;
        }
    }

    // Method to refactor: varargs, TargetClass return, final access, in source class
    public final TargetClass<T> methodToRefactor(TargetClass<T>... targetParams) {
        // Super constructor invocation
        super SuperClass((T) "super_ctor_1"); // target_feature: 1

        TargetClass<T> result = CtorInvocationInner.createTarget(); // ConstructorInvocation feature

        // Variable call (target varargs parameters)
        for (TargetClass<T> param : targetParams) {
            T value = param.getValue();
            
            // SwitchExpression feature: numbers=2, private modifier, exp=SwitchExpression
            private T switchExpr(T input) { // numbers=2, private modifier
                return switch (input.length()) {
                    case 2 -> (T) (input + "_switch_2"); // numbers=2
                    default -> (T) (input + "_switch_default_1"); // target_feature: 1
                };
            }

            // Expression statement
            value = switchExpr(value); // expression statement
            param.setValue(value); // expression statement

            // No_new_exception feature
            try {
                Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                param.setValue((T) ("formatted_" + value));
            }
        }

        // Variable call for targetField (per_condition)
        result.setValue((T) (targetField.getValue() + "_result_1")); // target_feature: 1

        // Anonymous inner class (source feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner: " + result.getValue());
            }
        };
        anonymous.run();

        return result;
    }
}

// Target class: private normal class, anonymous inner class feature
private class TargetClass<T> {
    // ClassName.field feature (static field)
    public static int classField;
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
        
        // Anonymous inner class (target_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous: " + value);
            }
        };
        anonymous.run();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}