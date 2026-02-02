package com.refactor.movemethod;

import java.util.Objects;

// Sealed interface for source_class permits feature
sealed interface GenericSealedInterface<T> permits FinalGenericSourceClass {}

// Source generic class (final modifier, same package, permits + local inner + static nested class)
final class FinalGenericSourceClass<T extends Number> implements GenericSealedInterface<T> {
    // Per_condition: source contains target class field
    private TargetGenericClass<String> targetField = new TargetGenericClass<>("target-field");
    
    // Protected member for access_outer_protected feature
    protected T outerProtectedValue;

    // Static nested class (source_class feature)
    static class SourceStaticNested<U> {
        U nestedValue;
        
        SourceStaticNested(U value) {
            this.nestedValue = value;
        }
    }

    // Member inner class (method_position: source_inner)
    class SourceInnerClass<V> {
        // Varargs method to refactor (protected access, returns TargetClass Type)
        protected TargetGenericClass<T> refactorMethod(V... varargs) {
            // Requires_try_catch feature
            try {
                // Variable call feature
                SourceStaticNested<Integer> staticNested = new SourceStaticNested<>(1); // NumberLiteral (1)
                V firstArg = varargs[0];
                String varCall = firstArg.toString() + staticNested.nestedValue;

                // Access_outer_protected feature
                outerProtectedValue = (T) Double.valueOf(1.0); // NumberLiteral (1)
                varCall += outerProtectedValue.toString();

                // Throw statement feature (NumberLiteral 1 in message)
                if (varargs.length < 1) {
                    throw new IllegalArgumentException("At least 1 argument required");
                }

                // Local inner class (source_class feature)
                class SourceLocalInner {
                    TargetGenericClass<T> createTarget() {
                        return new TargetGenericClass<>(String.valueOf(1)); // NumberLiteral (1)
                    }
                }
                SourceLocalInner localInner = new SourceLocalInner();
                
                // Return TargetClass Type (target_inner)
                return localInner.createTarget();
            } catch (ArrayIndexOutOfBoundsException | ClassCastException e) {
                // Requires_try_catch handling
                return targetField;
            }
        }
    }
}

// Target generic class (private modifier, anonymous inner class target_feature)
private class TargetGenericClass<U> {
    private U value;

    public TargetGenericClass(U value) {
        this.value = value;
        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + value);
            }
        };
        targetAnonymous.run();
    }

    // Target_inner component
    class TargetInner {
        U getInnerValue() {
            return value;
        }
    }

    public U getValue() {
        return value;
    }
}

// Others_class for call_method (protected modifier, normal + obj.m1().m2().m3(), exception handling pos)
class OthersClass {
    // Call method (protected modifier, others_class type, exception handling pos, returns int)
    protected int callMethod() {
        FinalGenericSourceClass<Double> source = new FinalGenericSourceClass<>();
        FinalGenericSourceClass.SourceInnerClass<String> inner = source.new SourceInnerClass<>();
        
        try {
            // obj.m1().m2().m3() target_feature
            TargetGenericClass<Double> target = inner.refactorMethod("arg1", "arg2")
                    .new TargetInner() // m1()
                    .getInnerValue()   // m2()
                    .toString()        // m3()
                    .length() > 0 ? inner.refactorMethod("arg") : null;
            
            return 1; // NumberLiteral (1)
        } catch (IllegalArgumentException e) {
            // Exception handling statements position
            return 0;
        }
    }
}