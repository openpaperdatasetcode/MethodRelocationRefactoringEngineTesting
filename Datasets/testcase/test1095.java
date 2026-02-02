package com.example;

import java.util.List;
import java.util.ArrayList;

// Source class (default modifier, normal class, same package, empty feature list)
class SourceClass {
    // Method to be refactored (varargs, TargetClass return, protected access, position: source)
    protected TargetClass<String> targetMethod(TargetClass<String> param, String... args) { // per_condition: target parameter
        // ConstructorInvocation (private modifier, this.field, 1, pos: diff_package_others)
        private void constructorInvocBlock() {
            this.field = "sourceField"; // this.field
            TargetClass<String> temp = new TargetClass<>(1); // 1 in target_feature, ConstructorInvocation
        }
        constructorInvocBlock();

        // With_bounds (generic bounds)
        List<? extends CharSequence> boundedList = new ArrayList<>();

        // While loop with static method call (pos: while)
        int count = 0;
        while (count < 1) { // 1 in method_feature
            staticMethod(); // source, static, new ClassName().method()
            count++;
            break; // break statement
        }

        // Constructor invocation
        TargetClass<String> newTarget = new TargetClass<>("initValue");

        // Variable call
        String targetValue = param.getValue();
        boundedList.add(targetValue);

        // Depends on inner class (anonymous inner class in target)
        param.anonymousInner.run();

        // No new exception
        return newTarget;
    }

    // Static method (default modifier, void return, 1, source, static)
    static void staticMethod() {
        // new ClassName().method() (method_feature)
        new HelperClass().helperMethod();
    }

    // this.field for ConstructorInvocation
    private String field;

    // Helper class for new ClassName().method()
    static class HelperClass {
        void helperMethod() {}
    }
}

// Target class (sealed modifier, normal class, type parameter, anonymous inner class target_feature)
sealed class TargetClass<T extends CharSequence> permits TargetSubClass {
    private T value;
    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target");
        }
    };

    // Constructor for ConstructorInvocation (1 in target_feature)
    public TargetClass(int num) {
        this.value = (T) ("value_" + num);
    }

    // Constructor for constructor invocation
    public TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

// Permitted subclass for sealed target class
final class TargetSubClass extends TargetClass<String> {
    public TargetSubClass() {
        super("subValue");
    }
}