package com.refactoring.test;

// Source class (public modifier, normal class, same package, extends, static nested class, member inner class)
public class SourceClass extends SuperSourceClass {
    // Static field for depends_on_static_field feature
    private static String staticField = "staticValue";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Method to be refactored (instance, void return, private access, position: source)
    private void targetMethod(TargetClass.StaticNestedTargetClass param) { // per_condition: target parameter
        // InfixExpression (numbers:1, volatile modifier, exp:InfixExpression)
        volatile int num = 1;
        int infixResult = num + 10; // InfixExpression (1 + 10)

        // Variable call
        String targetValue = param.getValue();
        
        // Depends on static field
        String combinedValue = staticField + targetValue;

        // Override violation (attempt to override non-overridable method)
        @Override // Compile error: override_violation (Object.toString() is not final, simulate violation with final method)
        public final String toString() {
            return combinedValue;
        }

        // Instance code blocks with call_method (pos: instance code blocks)
        {
            // Call method (others_class, private modifier, constructor, instanceReference::methodName, return int)
            OtherClass other = new OtherClass(); // constructor feature
            int callResult = other.calculate(param::getValue); // instanceReference::methodName feature
        }

        // No new exception (no_new_exception)
    }
}

// Super class for source_class extends feature
class SuperSourceClass {}

// Target class (protected modifier, normal class, static nested class target_feature)
protected class TargetClass {
    // Static nested class (target_feature) - target_inner
    static class StaticNestedTargetClass {
        public String getValue() {
            return "targetStaticNestedValue";
        }
    }
}

// Others_class (call_method type)
class OtherClass {
    // Private method (call_method modifier) returning int
    private int calculate(java.util.function.Supplier<String> supplier) {
        return supplier.get().length();
    }
}