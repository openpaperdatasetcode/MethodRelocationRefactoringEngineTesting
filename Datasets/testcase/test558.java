package com.refactor;

import java.util.List;

// Source class: normal, default modifier, same package as target, implements interface
class SourceClass implements SampleInterface {
    // Static field for depends_on_static_field feature
    private static int staticField = 1;

    // Overriding method (implements interface method), Object return, default modifier, contains target parameter (per_condition)
    @Override
    public Object methodToMove(TargetClass<String> targetParam) {
        // Instance feature (protected modifier, 1, sub_class, superTypeReference.methodName(arguments) in ternary operators)
        protected Object ternaryResult = (staticField == 1) ? SubTargetClass.superTypeMethod(1) : null;

        // Constructor invocation
        TargetClass<String> targetInstance = new TargetClass<>("test");

        // Synchronized statement
        synchronized (this) {
            variableCall(targetParam);
        }

        // Variable call (target parameter access)
        String targetVar = targetParam != null ? targetParam.getValue() : "";

        // Raw type usage
        TargetClass rawTarget = new TargetClass("raw");

        // Access instance method
        if (targetParam != null) {
            targetParam.innerClassInstance.innerMethod();
        }

        // Depends on static field
        int dependentValue = staticField + targetVar.length();

        // Call method: inner_class, public modifier, constructor, lambda in if/else conditions
        TargetClass<String> callResult;
        if (dependentValue > 0) {
            callResult = new TargetClass.TargetInner(() -> targetInstance.getValue()).getTargetInstance();
        } else {
            callResult = new TargetClass.TargetInner(() -> "default").getTargetInstance();
        }

        // No new exception thrown
        return callResult;
    }

    // Variable call helper method
    private void variableCall(TargetClass<String> param) {}
}

// Interface for source class implements feature
interface SampleInterface {
    Object methodToMove(TargetClass<String> targetParam);
}

// Target class: normal, abstract modifier, type parameter + member inner class (target_feature)
abstract class TargetClass<T> {
    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    // Member inner class (target_feature)
    public class TargetInner {
        private final java.util.function.Supplier<T> supplier;

        // Call method: public modifier, constructor, lambda (parameters) -> methodBody
        public TargetInner(java.util.function.Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public TargetClass<T> getTargetInstance() {
            return new SubTargetClass<>(supplier.get());
        }

        public void innerMethod() {}
    }

    // Inner class instance for access_instance_method
    public TargetInner innerClassInstance = new TargetInner(() -> value);
}

// Sub class for instance feature (sub_class)
class SubTargetClass<T> extends TargetClass<T> {
    public SubTargetClass(T value) {
        super(value);
    }

    // SuperTypeReference method for instance feature
    public static void superTypeMethod(int param) {}
}