package com.refactoring.test;

protected abstract class SourceClass {
    // Member inner class (source_class feature)
    class InnerSourceClass {
        /**
         * Instance method to be refactored
         * @param param TargetClass's inner static nested class parameter (per_condition)
         * @return base type (int) result
         */
        private int targetMethod(TargetClass.StaticNestedTargetClass param) { // instance, base type return, private access, source_inner
            super(); // super constructor invocation
            
            // Variable call
            String targetValue = param.getValue();
            
            // Access instance method
            int methodResult = param.calculateValue(targetValue.length());
            
            // Switch statement with call_method (pos: switch)
            switch (methodResult) {
                case 1:
                    // Call method (others_class, protected modifier, overloading, this.methodName(arguments), return int)
                    int result1 = OtherClass.overloadedMethod(1, targetValue);
                    break;
                case 2:
                    int result2 = OtherClass.overloadedMethod(2); // overloading feature
                    break;
                default:
                    int result3 = OtherClass.overloadedMethod(0);
                    break;
            }
            
            // No new exception (no_new_exception)
            return methodResult;
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerSourceClass inner = new InnerSourceClass();
            inner.targetMethod(new TargetClass.StaticNestedTargetClass());
        }
    };
}

protected abstract class TargetClass {
    // Static nested class (target_feature) - target_inner
    static class StaticNestedTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }

        public int calculateValue(int len) {
            return len * 2;
        }
    }
}

// Others_class (call_method type)
class OtherClass {
    // Overloading method 1 (protected modifier, return int, this.methodName(arguments))
    protected static int overloadedMethod(int num) {
        return OtherClass.this.helperMethod(num); // this.methodName(arguments)
    }

    // Overloading method 2 (overloading feature)
    protected static int overloadedMethod(int num, String str) {
        return num + str.length();
    }

    // Helper method for this.methodName(arguments)
    private int helperMethod(int num) {
        return num * 3;
    }
}