package com.refactoring.movemethod;

// Source class: abstract normal class, same package as target, no extra features
abstract class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_6563";
    // Per_condition: source contains target class field
    private final TargetClass<String> targetField = new TargetClass<>("initial_target_6563");

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec {
        // Method to refactor: overloading, void return, private access, in source_inner_rec
        private <T> void methodToRefactor(TargetClass<T>.TargetInner innerParam) {
            // Early return for return statement feature
            if (innerParam == null) {
                return; // return statement
            }

            int count = 0;
            // Do statement
            do {
                // Constructor invocation (target inner class)
                TargetClass<T>.TargetInner newInner = targetField.new TargetInner();
                
                // Variable call (target inner class)
                T innerValue = innerParam.getInnerValue();
                newInner.setInnerValue(innerValue);

                // Access_outer_protected feature (access outer class protected field)
                String protectedValue = SourceClass.this.outerProtectedField;
                innerParam.setInnerValue((T) (innerValue + "_" + protectedValue));

                // No_new_exception feature
                try {
                    Integer.parseInt(innerValue.toString());
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    innerParam.setInnerValue((T) "safe_value_6563");
                }

                // Call call_method (target type)
                Object callResult = innerParam.callMethod();
                
                count++;
            } while (count < 1);
        }

        // Overloading method 2 (overload_exist feature)
        private <T> void methodToRefactor(TargetClass<T>.TargetInner innerParam, T extraValue) {
            methodToRefactor(innerParam); // Call base overloaded method
            innerParam.setInnerValue((T) (innerParam.getInnerValue() + "_overload_" + extraValue));
        }
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractMethod();
}

// Target class: private normal class, type parameter, static nested class feature
private class TargetClass<T> {
    private T value;

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public TargetStaticNested(U value) {
            this.nestedValue = value; // constructor feature
        }

        public U getNestedValue() {
            return nestedValue;
        }
    }

    // Target_inner (target inner class)
    public class TargetInner {
        private T innerValue;

        // call_method: target type, default modifier, constructor, instanceReference.methodName(), pos=property assignment, return Object
        Object callMethod() {
            // Constructor feature (static nested class constructor)
            TargetStaticNested<T> nested = new TargetStaticNested<>(this.innerValue);
            
            // pos: property assignment
            T processedValue = nested.getNestedValue(); // instanceReference.methodName()
            this.innerValue = processedValue; // property assignment
            
            return this.innerValue;
        }

        // Constructor invocation helper
        public TargetInner() {}

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Target class constructor
    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Getter for target field (per_condition variable call)
    public TargetInner new TargetInner() {
        return new TargetInner();
    }
}