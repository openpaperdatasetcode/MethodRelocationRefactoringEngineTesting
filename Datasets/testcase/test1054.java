package com.refactoring.test;

import java.util.Arrays;
import java.util.List;

final class SourceClass<T> {
    // Source contains target class field (per_condition)
    private TargetClass<String> targetField;

    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    // Method to be moved (instance, public, return TargetClass Type, position: source)
    public TargetClass<String> targetMethod(int param) {
        try {
            // SuperConstructorInvocation (private modifier, this.field, pos: source)
            super();
            TargetClass<String> localTarget = this.targetField;
            
            // Empty statement
            ;
            
            // Enhanced for statement
            List<Integer> nums = Arrays.asList(1, 2, 3);
            for (int num : nums) {
                // InfixExpression (numbers:3, protected modifier)
                protected int calc = num + 3;
                // Variable call
                String val = localTarget.getNestedClassValue();
            }
            
            // Overload exist (overload_exist feature)
            targetMethod(param, "overload");
            
            // Call method in exception throwing statements (call_method pos)
            try {
                String callResult = OtherClass.staticMethod();
                SuperClass.superMethod(); // super.methodName()
            } catch (Exception e) {
                throw new RuntimeException(OtherClass.staticMethod());
            }
            
            return localTarget;
        } catch (Exception e) { // requires_try_catch
            return new TargetClass<>();
        }
    }

    // Overload method (overload_exist)
    public TargetClass<String> targetMethod(int a, String b) {
        return new TargetClass<>();
    }
}

private class TargetClass<T> {
    // Static nested class (target_feature)
    static class NestedTargetClass {
        private String value = "test";
    }

    private NestedTargetClass nested = new NestedTargetClass();

    public String getNestedClassValue() {
        return nested.value;
    }
}

// Others_class (call_method type)
class OtherClass {
    // default modifier, static (call_method target_feature)
    static String staticMethod() {
        return "called";
    }
}

class SuperClass {
    public static void superMethod() {}
}