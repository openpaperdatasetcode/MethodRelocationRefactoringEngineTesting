// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import java.io.IOException;
import java.lang.reflect.Method;

// Source class (public modifier, normal class, different package, two local inner classes)
public class SourceClass {
    // Private outer field for access_outer_private
    private String outerPrivateField = "outerPrivateValue";

    // Member inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (instance, base type return, final access)
        final int targetMethod(TargetClass param) throws IOException { // per_condition: target parameter, IOException feature
            // Super constructor invocation
            super();

            // Constructor invocation
            TargetClass.InnerTargetClass innerTarget = param.new InnerTargetClass();
            TargetClass targetInstance = new TargetClass() {
                @Override
                public void abstractMethod() {}
            };

            // Type declaration statement
            int count = 0;
            String targetValue;

            // While loop with instance method call (pos: while)
            while (count < 3) { // 3 in method_feature
                // outerInstance.new InnerClass().methodName() (method_feature)
                TargetClass processedTarget = instanceMethod(targetInstance); // source, instance type
                // Variable call
                targetValue = processedTarget.getInnerValue();
                count++;
            }

            // Access outer private field
            String privateValue = SourceClass.this.outerPrivateField;

            // First local inner class (source_class feature)
            class LocalInnerClass1 {
                int process(String val) {
                    return val.length() + privateValue.length();
                }
            }

            // Second local inner class (source_class feature)
            class LocalInnerClass2 {
                int calculate(int num) {
                    return num * 2;
                }
            }

            LocalInnerClass1 local1 = new LocalInnerClass1();
            LocalInnerClass2 local2 = new LocalInnerClass2();

            // Used by reflection
            try {
                Method method = InnerSourceClass.class.getDeclaredMethod("targetMethod", TargetClass.class);
                method.setAccessible(true);
                method.invoke(this, param);
            } catch (Exception e) {
                // No new exception (no_new_exception) - wrap without throwing new
                throw new IOException("Reflection error", e);
            }

            // No new exception (no_new_exception)
            return local2.calculate(local1.process(targetValue));
        }

        // Instance method (protected modifier, TargetClass return, 3 in method_feature)
        protected TargetClass instanceMethod(TargetClass outerInstance) { // source, instance type
            // outerInstance.new InnerClass().methodName() implementation
            TargetClass.InnerTargetClass inner = outerInstance.new InnerTargetClass();
            inner.updateValue(outerInstance.getInnerValue() + "_processed");
            return outerInstance;
        }
    }
}

// Target class package (different from source)
package com.target;

// Target class (abstract modifier, normal class, member inner class target_feature)
public abstract class TargetClass {
    // Member inner class (target_feature)
    public class InnerTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }

        public void updateValue(String newValue) {
            this.value = newValue;
        }
    }

    public String getInnerValue() {
        return new InnerTargetClass().getValue();
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}