import java.util.ArrayList;
import java.util.List;

// Source record class (non-sealed modifier, same package, static nested class, member inner class)
non-sealed record SourceRecord(String data) {
    // per_condition: source contains the field of the target
    private final TargetRecord targetField = new TargetRecord("init_value_5814");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase();
        }
    }

    // Member inner class (source_class feature, method_position: source_inner)
    public class InnerSourceClass {
        // Parent method for overriding
        protected TargetRecord baseMethod(TargetRecord param, int num) {
            param = new TargetRecord(param.value() + "_base_" + num);
            return param;
        }

        // Sub class for method_feature: sub_class
        private class SubClass extends InnerSourceClass {
            // Overriding method (private modifier, 3, sub_class, overriding, this.methodName(arguments))
            @Override
            private TargetRecord baseMethod(TargetRecord param, int num) { // Compile hint: overriding violation (private cannot override protected)
                param = new TargetRecord(param.value() + "_sub_" + num);
                // this.methodName(arguments) - recursion/overriding call
                return this.baseMethod(param, num - 1);
            }
        }

        // Method to be refactored (varargs, List<String> return, public access)
        public List<String> targetMethod(TargetRecord... params) {
            // Super constructor invocation (for inner class)
            super();

            List<String> result = new ArrayList<>();

            // Overriding method call in constructor parameter list (pos: the parameter list of constructors)
            SubClass subObj = new SubClass();
            TargetRecord overriddenTarget = new TargetRecord(
                subObj.baseMethod(targetField, 3).value() // 3, sub_class, overriding
            );

            // Expression statement
            String processedVal = StaticNestedSourceClass.format(overriddenTarget.value()); // expression statement

            // Variable call & assert statement
            for (TargetRecord param : params) {
                String val = param.value() + "_" + processedVal;
                // Assert statement
                assert val.length() > 0 : "Value cannot be empty";
                // Variable call (target member inner class)
                param.getInnerClass().updateValue(val);
                result.add(param.getInnerClass().getValue());
            }

            // No new exception
            return result;
        }
    }
}

// Target record class (public modifier, member inner class target_feature)
public record TargetRecord(String value) {
    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue;

        public void updateValue(String val) {
            this.innerValue = val + "_inner";
        }

        public String getValue() {
            return this.innerValue;
        }
    }

    // Inner class instance for variable call
    private final InnerClass innerClass = new InnerClass();

    // Constructor to initialize inner class
    public TargetRecord {
        innerClass.updateValue(value);
    }

    // Variable call: getter for inner class
    public InnerClass getInnerClass() {
        return innerClass;
    }
}