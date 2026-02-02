import java.util.function.Function;
import java.io.IOException;

// Source record class (public modifier, same package, type parameter, member inner class, static nested class)
public record SourceRecord<T extends CharSequence>(T data) {
    // Private outer field for access_outer_private feature
    private final String outerPrivateField = "SOURCE_PRIVATE_FIELD_5854";
    // per_condition: source contains the field of the target
    private final PrivateTargetRecord targetField = new PrivateTargetRecord("init_value_5854");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<U> {
        static String format(U val) {
            return val.toString().toUpperCase() + "_static_source";
        }
    }

    // Member inner class (source_class feature)
    public class MemberInnerClass<U> {
        void enhanceValue(PrivateTargetRecord.InnerClass targetInner) {
            // access_outer_private feature
            String enhanced = targetInner.getValue() + "_" + outerPrivateField;
            targetInner.setValue(enhanced);
        }
    }

    // Call method reference target (target type, final modifier, static, instanceReference::methodName)
    private static final class CallMethodHolder {
        final int callMethod(String val) { // final modifier
            return val.length();
        }
    }

    // Method to be refactored (instance, void return, protected access)
    protected void processTarget(PrivateTargetRecord.InnerClass param) throws IOException { // requires_throws, per_condition: target parameter (target_inner)
        // NullPointerException feature (explicit NPE handling)
        if (param == null) {
            throw new NullPointerException("Target inner class parameter is null");
        }

        // Do statement
        int count = 0;
        do {
            // Expression statement
            String exprVal = param.getValue() + "_do_loop_" + count; // expression statement
            param.setValue(exprVal);
            
            // Variable call (target local inner class)
            param.updateValue(StaticNestedSourceClass.format(exprVal));
            
            count++;
        } while (count < 3);

        // Call method in Lambda expressions (pos: Lambda expressions, static, instanceReference::methodName)
        CallMethodHolder holder = new CallMethodHolder();
        Function<String, Integer> lambda = holder::callMethod; // instanceReference::methodName
        int len = lambda.apply(param.getValue());
        param.setValue(param.getValue() + "_len_" + len);

        // access_outer_private feature (direct access)
        param.setValue(param.getValue() + "_private_field_" + outerPrivateField);

        // Member inner class usage
        new MemberInnerClass<T>().enhanceValue(param);

        // Requires throws validation
        if (param.getValue().isEmpty()) {
            throw new IOException("Target inner class value is empty");
        }
    }
}

// Target record class (private modifier, local inner class target_feature)
private record PrivateTargetRecord(String value) {
    // Inner class (target_inner)
    public class InnerClass {
        private String innerValue;

        public InnerClass() {
            this.innerValue = PrivateTargetRecord.this.value;
        }

        // Local inner class (target_feature)
        public void updateValue(String val) {
            class TargetLocalInnerClass { // local inner class feature
                String process(String s) {
                    return s + "_local_inner_processed";
                }
            }
            this.innerValue = new TargetLocalInnerClass().process(val);
        }

        // Variable call: getter/setter
        public String getValue() {
            return innerValue;
        }

        public void setValue(String value) {
            this.innerValue = value;
        }
    }

    // Constructor to initialize inner class
    public PrivateTargetRecord {
        if (value == null) {
            value = "default_target_value_5854";
        }
    }

    // Variable call: helper method to get inner class
    public InnerClass getInnerClass() {
        return new InnerClass();
    }
}