import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super interface for source_class implements feature
interface RecordProcessable<T> {
    default List<String> defaultMethod(T param) {
        return new ArrayList<>();
    }
}

// Source record class (strictfp modifier, same package, implements, member inner class, local inner class)
strictfp record SourceRecord(String data) implements RecordProcessable<SourceRecord> {
    // per_condition: source contains the field of the target
    private final ProtectedTargetRecord targetField = new ProtectedTargetRecord("init_value_5837");

    // Member inner class (source_class feature)
    class MemberInnerClass {
        // Instance method (default modifier, 3, inner_class, instance, super.methodName())
        List<String> instanceMethod(String val, int num) {
            // super.methodName() (inherit from RecordProcessable default method)
            List<String> result = SourceRecord.this.defaultMethod(SourceRecord.this);
            for (int i = 0; i < num; i++) {
                result.add(val + "_inner_" + i);
            }
            return result;
        }
    }

    // Method to be refactored (instance, List<String> return, private access, position: source)
    private List<String> process(ProtectedTargetRecord.InnerClass param) { // per_condition: target parameter (target_inner)
        // Super constructor invocation (implicit for Record)
        super();

        // Type declaration statement
        List<String> result = new ArrayList<>();
        String targetVal;
        int count;

        // Labeled statement
        outerLabel:
        // For statement
        for (count = 0; count < 5; count++) {
            // Switch statement
            switch (count % 3) {
                case 0:
                    targetVal = param.getValue() + "_case0_" + count;
                    break;
                case 1:
                    targetVal = param.getValue() + "_case1_" + count;
                    // Continue statement
                    count++;
                    continue outerLabel;
                default:
                    targetVal = param.getValue() + "_default_" + count;
                    break;
            }

            // Constructor invocation (target inner class)
            ProtectedTargetRecord.InnerClass newInner = targetField.new InnerClass(targetVal);
            
            // Variable call + access instance method
            param.setValue(newInner.getValue());
            result.add(param.getValue());

            // Instance method call in Lambda expressions (pos: Lambda expressions, 3, inner_class, instance)
            Function<String, List<String>> lambda = (s) -> new MemberInnerClass().instanceMethod(s, 3); // 3 (method_feature)
            result.addAll(lambda.apply(param.getValue()));
        }

        // Local inner class (source_class feature)
        class LocalInnerClass {
            void enhanceList(List<String> list) {
                list.replaceAll(String::toUpperCase);
            }
        }
        new LocalInnerClass().enhanceList(result);

        // No new exception
        return result;
    }
}

// Target record class (protected modifier, local inner class target_feature)
protected record ProtectedTargetRecord(String value) {
    // Inner class (target_inner)
    public class InnerClass {
        private String innerValue;

        public InnerClass(String value) {
            this.innerValue = value;
            // Local inner class (target_feature)
            class TargetLocalInnerClass {
                String format(String s) {
                    return s + "_local_inner_5837";
                }
            }
            this.innerValue = new TargetLocalInnerClass().format(this.innerValue);
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
    public ProtectedTargetRecord {
        value = value + "_record_init";
    }
}