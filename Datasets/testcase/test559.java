package com.refactor;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Source class: generic, protected modifier, same package as target, extends SuperClass
protected class SourceClass<T> extends SuperClass<T> {
    // Target class field reference (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();

    // Overloading method 1: List<String> return, public, core method to refactor
    public List<String> methodToMove(int param) {
        return methodToMove(param, "default");
    }

    // Overloading method 2 (overloading type), List<String> return, public
    public List<String> methodToMove(int param, String str) {
        // Recursion feature (protected modifier, 1, inner_class, super.methodName(arguments) in while loop)
        protected int recursionCount = 1;
        while (recursionCount > 0) {
            targetField.new TargetInner().recursiveMethod(); // Inner class recursion trigger
            super.superMethod(recursionCount--); // Super method invocation
            break; // Break statement
        }

        // Synchronized statement
        synchronized (this) {
            // Variable call (target field access)
            String targetVar = targetField.getValue();
            // Access instance method
            targetField.innerClassInstance.innerMethod();
        }

        // Return statement
        List<String> result = new ArrayList<>();
        result.add(targetField.getValue());
        return result;
    }

    // Super class method for recursion feature
    @Override
    protected void superMethod(int param) {
        if (param > 0) {
            methodToMove(param); // Recursion
        }
    }
}

// Super class for source class extends feature
class SuperClass<T> {
    protected void superMethod(int param) {}
}

// Target class: generic, private modifier, member inner class (target_feature)
private class TargetClass<U> {
    private U value;

    public TargetClass() {
        this.value = (U) "targetValue";
    }

    public U getValue() {
        return value;
    }

    // Member inner class (target_inner - target class for method)
    class TargetInner {
        // Recursive method for recursion feature
        void recursiveMethod() {
            recursiveMethodHelper(1);
        }

        private void recursiveMethodHelper(int count) {
            if (count <= 0) return;
            recursiveMethodHelper(count - 1); // Recursion
        }

        public void innerMethod() {}
    }

    // Inner class instance for access_instance_method
    TargetInner innerClassInstance = new TargetInner();
}

// Sub class for call_method (type: sub_class)
public class TargetSubClass<U> extends TargetClass<U> {
    // Static code block for call_method pos: Static code blocks
    static {
        // Call method: public modifier, constructor, lambda (parameters) -> methodBody
        Function<Integer, List<String>> lambda = (param) -> new TargetSubClass<>().getList(param);
        lambda.apply(1);
    }

    // Call method return List<String>
    public List<String> getList(int param) {
        return new ArrayList<>();
    }

    // Constructor for call_method target_feature: constructor
    public TargetSubClass() {
        super();
    }
}