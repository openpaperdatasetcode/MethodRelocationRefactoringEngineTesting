package com.example;

import java.util.List;
import java.util.ArrayList;

// Source abstract class (protected modifier, same package, empty feature list)
protected abstract class SourceClass {
    // Method to be refactored (normal, Object return, strictfp access, position: source)
    strictfp Object targetMethod(AbstractTargetClass param) throws Exception { // per_condition: target parameter, requires_throws
        // Normal method call in exception handling statements (pos: exception handling statements)
        try {
            normalMethod(3, param); // 3, inner_class, normal, obj.m1().m2().m3()
        } catch (IllegalStateException e) {
            // Throw statement
            throw new Exception("Normal method failed", e);
        }

        // Expression statement
        String targetValue = param.getValue();
        targetValue = targetValue.concat("_processed");

        // Variable call
        param.setValue(targetValue);

        // With_bounds (generic bounds)
        List<? extends CharSequence> boundedList = new ArrayList<>();
        boundedList.add(targetValue);

        // Access instance field
        String instanceField = param.instanceField; // access_instance_field

        // No new exception (core logic retains no custom exception creation)
        return boundedList.get(0) + "_" + instanceField;
    }

    // Normal method (public modifier, void return, 3, inner_class, normal)
    public void normalMethod(int num, AbstractTargetClass param) {
        // obj.m1().m2().m3() chain call
        InnerHelper helper = new InnerHelper();
        helper.m1().m2().m3(num, param);
    }

    // Inner class for obj.m1().m2().m3() (inner_class feature)
    private class InnerHelper {
        InnerHelper m1() { return this; }
        InnerHelper m2() { return this; }
        void m3(int num, AbstractTargetClass param) {
            if (num != 3) {
                throw new IllegalStateException("Invalid number: " + num);
            }
            param.setValue(param.getValue() + "_" + num);
        }
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

// Target abstract class (public modifier, anonymous inner class target_feature)
public abstract class AbstractTargetClass {
    // Instance field for access_instance_field
    public String instanceField = "targetInstanceField";

    private String value = "targetValue";

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target abstract class");
        }
    };

    public abstract String getValue();
    public abstract void setValue(String value);
}

// Concrete implementation of target abstract class (for compilation)
class ConcreteTargetClass extends AbstractTargetClass {
    @Override
    public String getValue() {
        return super.value;
    }

    @Override
    public void setValue(String value) {
        super.value = value;
    }
}