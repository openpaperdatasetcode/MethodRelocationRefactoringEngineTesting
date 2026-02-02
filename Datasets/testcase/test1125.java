import java.util.function.Function;

/**
 * Source normal class with type parameter, local inner class, static nested class
 * Feature compliance: type parameter, local inner class, static nested class
 */
public class SourceClass<T extends CharSequence> {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<U> {
        public Object process(Object obj) {
            return obj.toString() + "_static_nested";
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        /**
         * Method Javadoc: Normal method with base type return, private access
         * @param param Target class instance parameter
         * @return Base type (int) result
         * @note Complies with all required features for 5670
         */
        // Method to be refactored (normal, base type return, private access)
        private int targetMethod(TargetClass param) {
            // Variable call (target class field/method access)
            String targetValue = param.value; // access_instance_field
            param.setValue(targetValue + "_updated");

            // Overload exist (call overloaded methods)
            overloadMethod(2);
            overloadMethod(targetValue);

            // Instance method call in exception handling statements (pos: exception handling statements)
            Object instanceResult = null;
            try {
                SourceClass<String> sourceInstance = new SourceClass<>();
                // instanceReference::methodName (method_feature)
                Function<TargetClass, Object> func = sourceInstance::instanceMethod;
                instanceResult = func.apply(param); // 2, source, instance
            } catch (Exception e) {
                instanceResult = e.getMessage();
            }

            // Local inner class (source_class feature)
            class LocalInnerClass {
                int calculateLength(String s) {
                    // Call method in constructor parameter list (pos: the parameter list of constructors)
                    InnerHelper helper = new InnerHelper(callMethod(param));
                    return s.length() + helper.getLength();
                }
            }

            // No new exception (no custom exception instantiation)
            return new LocalInnerClass().calculateLength(instanceResult.toString());
        }

        // Overloading method 1 (overload_exist feature)
        private void overloadMethod(int num) {
            targetField.setValue(targetField.getValue() + "_num_" + num);
        }

        // Overloading method 2 (overload_exist feature)
        private void overloadMethod(String str) {
            targetField.setValue(targetField.getValue() + "_str_" + str);
        }

        // Instance method (synchronized modifier, 2, source, instance, return Object)
        private synchronized Object instanceMethod(TargetClass param) {
            int num = 2; // method_feature: 2
            return param.getValue() + "_instance_" + num;
        }

        // Call method (inner_class type, public modifier, instance, OuterClass.InnerClass.methodName())
        public String callMethod(TargetClass param) {
            // OuterClass.InnerClass.methodName() (target_feature)
            return TargetClass.StaticNestedTargetClass.format(param.getValue());
        }

        // Inner class for call_method (inner_class type)
        class InnerHelper {
            private final int length;

            public InnerHelper(String str) {
                this.length = str.length();
            }

            public int getLength() {
                return length;
            }
        }
    }
}

/**
 * Target normal class (protected modifier)
 * Target features: javadoc, static nested class
 */
protected class TargetClass {
    // Instance field for access_instance_field
    public String value = "targetValue_5670";

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static String format(String s) {
            return s.toUpperCase();
        }
    }

    // Constructor for invocation
    public TargetClass() {}

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}