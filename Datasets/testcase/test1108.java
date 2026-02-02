// Source package (different from target)
package com.source;

import com.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

// Source generic class (default modifier, different package, static nested class, member inner class)
class SourceClass<T extends Number> {
    // per_condition: source contains the field of the target
    private TargetClass<String> targetField = new TargetClass<>("initValue");
    // Private outer field for access_outer_private
    private String outerPrivateField = "outerPrivateValue";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass<U> {}

    // Member inner class
    class MemberInnerClass<V> {}

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord<W>(W data) {
        // Generic method to be refactored (base type return, default access)
        <X extends CharSequence> int targetMethod(X param) {
            // Constructor invocation
            TargetClass<String> newTarget = new TargetClass<>("newValue");

            // Try statement
            try {
                // Property assignment with instance method call (pos: property assignment)
                int prop;
                prop = instanceMethod(1, newTarget); // 1, others_class, instance, outerInstance.new InnerClass().methodName()

                // InfixExpression (numbers:2, public modifier)
                public int infixExpr() {
                    int num = 2; // numbers:2
                    return num + 3; // InfixExpression
                }
                int infixResult = infixExpr();

                // This method call
                int thisResult = this.overloadMethod(prop); // this.methodName(arguments)

                // Variable call
                String targetValue = targetField.getValue();

                // Access outer private field
                String privateValue = SourceClass.this.outerPrivateField;

                // Overload exist
                overloadMethod(privateValue); // overload_exist

                // With_bounds
                List<? extends Number> boundedList = new ArrayList<>();
                boundedList.add(prop);

                // Access instance method
                String methodResult = targetField.instanceMethod(targetValue); // access_instance_method

                // Instance code block with call_method (pos: instance code blocks)
                {
                    InnerHelper helper = new InnerHelper();
                    int callResult = helper.callMethod(); // inner_class, protected, normal, new ClassName().method()
                }

                return thisResult + infixResult;
            } catch (Exception e) {
                // No new exception
                return -1;
            }
        }

        // Instance method (protected modifier, base type return, 1, others_class, instance)
        protected <Y> int instanceMethod(int num, TargetClass<Y> outerInstance) {
            // outerInstance.new InnerClass().methodName()
            TargetClass<Y>.InnerClass inner = outerInstance.new InnerClass();
            return inner.methodName(num);
        }

        // Overloading method 1 (overload_exist)
        private int overloadMethod(int num) {
            return num * 2;
        }

        // Overloading method 2 (overload_exist)
        private int overloadMethod(String str) {
            return str.length();
        }

        // Inner class for call_method (inner_class, protected modifier)
        class InnerHelper {
            protected int callMethod() {
                // new ClassName().method()
                return new HelperClass().helperMethod();
            }
        }

        // Helper class for new ClassName().method()
        static class HelperClass {
            int helperMethod() {
                return 42;
            }
        }
    }
}

// Target package (different from source)
package com.target;

// Target generic class (default modifier, anonymous inner class target_feature)
class TargetClass<T> {
    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target class");
        }
    };

    // Inner class for outerInstance.new InnerClass().methodName()
    class InnerClass {
        int methodName(int num) {
            return num * 3;
        }
    }

    // Instance method for access_instance_method
    public String instanceMethod(String input) {
        return input + "_processed";
    }

    public T getValue() {
        return value;
    }
}