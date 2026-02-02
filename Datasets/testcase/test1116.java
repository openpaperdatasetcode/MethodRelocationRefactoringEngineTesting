package com.example;

// Source abstract normal class (abstract modifier, same package, member inner class, static nested class)
abstract class SourceClass {
    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Method to be refactored (varargs, base type return, protected access, position: source)
    protected int targetMethod(AbstractTargetClass param, String... args) { // per_condition: target parameter
        // For statement with instance method call (pos: for)
        AbstractTargetClass targetInstance = null;
        for (int i = 0; i < 2; i++) { // 2 in method_feature
            targetInstance = instanceMethod(2, param); // 2, others_class, instance, ClassName.methodName(arguments)
        }

        // If statement
        int result = 0;
        if (targetInstance != null) {
            // Variable call
            String targetValue = targetInstance.getValue();
            result = targetValue.length();

            // Super keywords usage
            super.toString();
        }

        // Return this simulation (adjusted for base type return, core logic retained)
        if (result > 0) {
            return result; // Analogous to return this; for method return semantics
        } else {
            return -1;
        }

        // No new exception
    }

    // Instance method (default modifier, TargetClass return, 2, others_class, instance)
    AbstractTargetClass instanceMethod(int num, AbstractTargetClass param) {
        // ClassName.methodName(arguments)
        return OthersClass.processTarget(param, num);
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

// Others class for ClassName.methodName(arguments)
class OthersClass {
    // Instance method wrapper for ClassName.methodName semantics
    public static AbstractTargetClass processTarget(AbstractTargetClass target, int num) {
        target.setValue(target.getValue() + "_" + num);
        return target;
    }
}

// Target abstract normal class (abstract modifier, empty target_feature)
abstract class AbstractTargetClass {
    private String value = "targetValue";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}