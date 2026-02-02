package com.refactoring.test;

import java.lang.reflect.Method;

// Source class (final modifier, normal class, same package, member inner class, local inner class)
final class SourceClass {
    // Member inner class (source_class feature) - method_position: source_inner
    class InnerSourceClass {
        // Method to be refactored (instance, TargetClass return, protected access)
        protected TargetClass targetMethod(TargetClass param) { // per_condition: target parameter
            super(); // super constructor invocation
            
            // While loop with instance method (pos: while)
            int count = 1; // method_feature: 1
            while (count <= 3) {
                // Instance method call (public modifier, inner_class, super.methodName(), return void)
                innerInstanceMethod(param.innerClass); // inner_class in method_feature
                count++;
            }

            // Variable call
            String targetValue = param.innerClass.getValue();
            if (targetValue == null) {
                throw new NullPointerException(); // NullPointerException feature
            }

            // Depends on inner class
            param.innerClass.updateValue(targetValue + count);

            // Used by reflection
            try {
                Method method = InnerSourceClass.class.getDeclaredMethod("targetMethod", TargetClass.class);
                method.setAccessible(true);
                method.invoke(this, param);
            } catch (Exception e) {
                // No new exception (no_new_exception)
            }

            return param;
        }

        // Instance method (public modifier, inner_class, super.methodName(), return void)
        public void innerInstanceMethod(TargetClass.InnerTargetClass inner) { // 1 in method_feature, instance type
            super.toString(); // super.methodName()
            // Local inner class (source_class feature)
            class LocalInnerClass {
                void process(TargetClass.InnerTargetClass innerClass) {
                    innerClass.updateValue(innerClass.getValue() + "_processed");
                }
            }
            new LocalInnerClass().process(inner); // depends_on_inner_class
        }
    }
}

// Target class (protected modifier, normal class, member inner class target_feature)
protected class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }

        public void updateValue(String newValue) {
            this.value = newValue;
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
}