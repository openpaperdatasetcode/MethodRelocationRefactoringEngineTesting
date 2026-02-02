import java.util.ArrayList;
import java.util.List;

// Super class for super.field feature
class TargetSuperClass {
    protected String superField = "super_target_field_5833";
}

// Source record class (default modifier, same package, static nested class, local inner class)
record SourceRecord(String data) {
    // per_condition: source contains the field of the target
    private final TargetRecord targetField = new TargetRecord("init_value_5833");

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase() + "_static";
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String innerData) {
        // Method to be refactored (instance, Object return, final access)
        public final Object process(TargetRecord.InnerRecord param) { // per_condition: target parameter (target_inner_rec)
            List<String> result = new ArrayList<>();

            // VariableDeclarationStatement (public modifier, super.field, 2, pos: diff_package_others)
            public void varDeclBlock() {
                TargetSuperClass superObj = new TargetSuperClass();
                String superFieldVal = superObj.superField; // super.field
                int num = 2; // target_feature: 2
                
                // Variable declaration statement
                String targetVal = param.value() + "_" + superFieldVal + "_" + num;
                result.add(targetVal);
            }
            varDeclBlock();

            // Variable call (target inner record + member inner class)
            String innerRecVal = param.value();
            TargetRecord.MemberInnerClass innerClass = targetField.new MemberInnerClass();
            result.add(innerClass.process(innerRecVal));

            // Local inner class (source_class feature)
            class LocalInnerClass {
                String enhance(String val) {
                    return StaticNestedSourceClass.format(val) + "_local";
                }
            }
            result.add(new LocalInnerClass().enhance(innerRecVal));

            // Call method in expression (pos: expression, inner_class, protected, recursion, outerInstance.new InnerClass().method())
            String callResult = new SourceRecord("call_data").new InnerCallClass().recursiveMethod(3, innerRecVal); // outerInstance.new InnerClass().method()
            result.add(callResult);

            // No new exception
            return result;
        }

        // Inner class for call_method (inner_class type)
        protected class InnerCallClass {
            // Call method (recursion, outerInstance.new InnerClass().method(), return String)
            public String recursiveMethod(int count, String val) {
                if (count <= 0) {
                    return val + "_end_recursion";
                }
                // Recursion call
                return recursiveMethod(count - 1, val) + "_step_" + count;
            }
        }
    }
}

// Target record class (default modifier, member inner class target_feature)
record TargetRecord(String value) extends TargetSuperClass {
    // Member inner class (target_feature)
    public class MemberInnerClass {
        public String process(String val) {
            return val + "_" + super.superField; // access super.field from extends
        }
    }

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    // Variable call: helper method to get inner record
    public InnerRecord getInnerRecord() {
        return new InnerRecord(this.value + "_inner_rec");
    }
}