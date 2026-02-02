import java.util.ArrayList;
import java.util.List;

// Others class for instance method_feature: others_class
class OthersClass {
    public int instanceHelperMethod(String val) {
        return val.length();
    }
}

// Source public generic class (same package, two static nested classes)
public class SourceClass<T extends CharSequence> {
    // First static nested class (source_class feature)
    public static class StaticNestedClass1<U> {
        static String format(U val) {
            return val.toString().toUpperCase() + "_static1";
        }
    }

    // Second static nested class (source_class feature)
    public static class StaticNestedClass2<U> {
        static String format(U val) {
            return val.toString().toLowerCase() + "_static2";
        }
    }

    // Inner class (method_position: source_inner)
    public class InnerSourceClass<U> {
        private final OthersClass othersObj = new OthersClass();

        // Instance method (protected modifier, 1, others_class, instance, this.methodName(arguments))
        protected int instanceMethod(String val) {
            int num = 1; // method_feature: 1
            // this.methodName(arguments)
            return this.othersObj.instanceHelperMethod(val) + num;
        }

        // Accessor method to be refactored (accessor, List<String> return, final access)
        final List<String> processTarget(ProtectedTargetClass<T>.InnerClass param) { // per_condition: target parameter
            // Super constructor invocation (implicit for inner class)
            super();

            // Instance method call in ternary operators (pos: ternary operators)
            int instanceResult = param.getValue().length() > 0 ?
                this.instanceMethod(param.getValue().toString()) : // this.methodName(arguments)
                this.instanceMethod("default_val");

            // Call method in expression (pos: expression, target type, protected, overriding, outerInstance.new InnerClass().methodName())
            ProtectedTargetClass<T> outerTarget = new ProtectedTargetClass<>((T) "outer_instance");
            Object callResult = outerTarget.new InnerClass().overriddenMethod(param.getValue()); // outerInstance.new InnerClass().methodName()

            // super keywords feature (access target's super class member)
            String superKeywordVal = outerTarget.superField; // super keywords

            // Variable call (target type parameter + static nested class)
            String staticTargetVal = ProtectedTargetClass.StaticNestedTargetClass.format(param.getValue());
            param.setValue((T) (param.getValue() + "_processed_" + staticTargetVal));

            // Use source static nested classes
            String static1Val = StaticNestedClass1.format(param.getValue());
            String static2Val = StaticNestedClass2.format(param.getValue());

            // Build result list (accessor feature)
            List<String> result = new ArrayList<>();
            result.add(String.valueOf(instanceResult));
            result.add(callResult.toString());
            result.add(superKeywordVal);
            result.add(static1Val);
            result.add(static2Val);

            // No new exception
            return result;
        }
    }

    // Helper method to create inner class instance
    public InnerSourceClass<T> createInnerClass() {
        return new InnerSourceClass<>();
    }
}

// Target protected generic class (type parameter, static nested class target_feature)
protected class ProtectedTargetClass<T> {
    // super keywords feature (super field)
    protected String superField = "TARGET_SUPER_FIELD_5873";
    private T value;

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass<U> {
        static String format(U val) {
            return val.toString() + "_static_target";
        }
    }

    // Inner class (target_inner)
    public class InnerClass {
        private T innerValue;

        // Parent method for overriding
        public Object baseMethod(T val) {
            return val + "_base_method";
        }

        // Overriding method for call_method feature
        public Object overriddenMethod(T val) {
            // overriding feature
            return super.baseMethod(val) + "_overridden";
        }

        // Variable call: getter/setter (accessor feature)
        public T getValue() {
            return innerValue != null ? innerValue : ProtectedTargetClass.this.value;
        }

        public void setValue(T value) {
            this.innerValue = value;
        }
    }

    // Type parameter feature (target_feature)
    public ProtectedTargetClass(T value) {
        this.value = value;
    }

    // Getter for super field (super keywords feature)
    public String getSuperField() {
        return superField;
    }
}