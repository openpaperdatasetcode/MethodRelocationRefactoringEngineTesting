import java.util.ArrayList;
import java.util.List;

// Super class for target_class extends feature
class SuperTargetClass<U> {
    protected String superField = "super_target_field_5807";
}

// Source generic class (private modifier, same package, empty feature list)
private class SourceClass<T extends CharSequence> {
    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Constructor with this(arguments)
        public InnerSourceRecord() {
            this("default_data"); // this(arguments) feature
        }

        // Method to be refactored (varargs, base type return, private access)
        private int targetMethod(PrivateTargetClass<String> param, String... args) { // per_condition: target parameter
            // Expression statement
            String targetValue = param.getValue();
            targetValue = targetValue + "_processed_" + String.join(",", args); // expression statement

            // numbers:1, modifier:volatile, exp:NullLiteral
            volatile int num = 1; // numbers:1, modifier:volatile
            if (param == null) { // exp:NullLiteral
                num = -1;
            }

            // Variable call
            param.setValue(targetValue);
            String updatedVal = param.getValue();

            // Call method in exception handling statements (pos: exception handling statements)
            List<String> callResult = null;
            try {
                callResult = recursiveMethod(num, 3); // source, protected, recursion, this.methodName(arguments)
            } catch (Exception e) {
                callResult = new ArrayList<>();
                callResult.add(e.getMessage());
            }

            // No new exception
            return updatedVal.length() + callResult.size();
        }

        // Call method (source type, protected modifier, recursion, this.methodName(arguments), return List<String>)
        protected List<String> recursiveMethod(int count, int max) {
            List<String> result = new ArrayList<>();
            if (count >= max) {
                result.add("end_recursion_" + count);
                return result;
            }
            // this.methodName(arguments) - recursion
            result.addAll(this.recursiveMethod(count + 1, max));
            result.add("recursion_step_" + count);
            return result;
        }
    }
}

// Target generic class (private modifier, extends, anonymous inner class target_feature)
private class PrivateTargetClass<T> extends SuperTargetClass<T> { // target_feature: extends
    private T value;

    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in TargetClass: " + value);
        }
    };

    // Constructor
    public PrivateTargetClass(T value) {
        this.value = value;
        this.anonymousInner.run(); // Trigger anonymous inner class
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}