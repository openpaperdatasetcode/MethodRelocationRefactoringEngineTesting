import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source record class (private modifier, same package, anonymous inner class, member inner class)
private record SourceRecord(String data) {
    // per_condition: source contains the field of the target
    private final ProtectedTargetRecord targetField = new ProtectedTargetRecord("init_value_5840");

    // Anonymous inner class (source_class feature)
    private final Consumer<String> anonymousInner = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner process: " + s);
        }
    };

    // Member inner class (source_class feature)
    class MemberInnerClass {
        // Instance method (default modifier, 1, inner_class, instance, instanceReference.methodName(arguments))
        void instanceMethod(String val, int num) {
            String processed = val + "_inner_" + num;
            anonymousInner.accept(processed);
        }
    }

    // Method to be refactored (instance, List<String> return, protected access)
    protected List<String> process(ProtectedTargetRecord param) { // per_condition: target parameter
        // Super constructor invocation (implicit for Record)
        super();

        // Type declaration statement
        List<String> result = new ArrayList<>();
        String targetVal;
        int num;
        MemberInnerClass innerObj = new MemberInnerClass();

        // numbers:2, modifier:private, exp:ParenthesizedExpression
        private String parenthesizedExpr() {
            num = 2; // numbers:2
            return (param.value() + "_parenthesized_" + num); // ParenthesizedExpression
        }
        targetVal = parenthesizedExpr();

        // Instance method call in property assignment (pos: property assignment, 1, inner_class, instance)
        String propVal = "";
        propVal = (innerObj.instanceMethod(targetVal, 1), targetVal + "_prop_assignment"); // instanceReference.methodName(arguments) + property assignment

        // If statement
        if (param.value().length() > 0) {
            // Variable call
            targetVal = param.value() + "_" + propVal + "_" + num;
            result.add(targetVal);
            // Update target value
            ProtectedTargetRecord newTarget = new ProtectedTargetRecord(targetVal);
            result.add(newTarget.value());
        } else {
            result.add(targetField.value() + "_default");
        }

        // Trigger anonymous inner class
        anonymousInner.accept(result.toString());

        // No new exception
        return result;
    }
}

// Target record class (protected modifier, empty target_feature list)
protected record ProtectedTargetRecord(String value) {
    // Constructor to validate value
    public ProtectedTargetRecord {
        if (value == null) {
            value = "default_value_5840";
        }
    }

    // Variable call: helper method (optional, for completeness)
    public String getProcessedValue() {
        return value + "_processed";
    }
}