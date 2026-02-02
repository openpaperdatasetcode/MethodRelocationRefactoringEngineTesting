import java.util.ArrayList;
import java.util.List;

// Super interface for target_class implements feature
interface TargetProcessable {
    String superField = "TARGET_SUPER_FIELD_5839"; // super.field for ContinueStatement
    void process(String value);
}

// Source record class (public modifier, same package, two member inner classes)
public record SourceRecord(String data) {
    // per_condition: source contains the field of the target
    private final ProtectedTargetRecord targetField = new ProtectedTargetRecord("init_value_5839");

    // First member inner class (source_class feature)
    class MemberInnerClass1 {
        String enhance(String val) {
            return val.toUpperCase() + "_inner1";
        }
    }

    // Second member inner class (source_class feature)
    class MemberInnerClass2 {
        String enhance(String val) {
            return val.toLowerCase() + "_inner2";
        }
    }

    // Overloading method 1 (base overload)
    ProtectedTargetRecord targetMethod(ProtectedTargetRecord param) { // per_condition: target parameter
        return targetMethod(param, 1); // overload_exist (call overloaded method)
    }

    // Method to be refactored (overloading, TargetClass return, default access)
    ProtectedTargetRecord targetMethod(ProtectedTargetRecord param, int num) { // overload_exist
        // ContinueStatement (private modifier, super.field, 1, pos: same_package_others)
        private void continueBlock() {
            String superFieldVal = TargetProcessable.superField; // super.field
            int count = 1; // target_feature: 1

            // Labeled statement
            outerLabel:
            // Do statement
            do {
                if (count == 2) {
                    count++;
                    continue outerLabel; // ContinueStatement
                }

                // super keywords (access target's super interface member)
                String processed = param.value() + "_" + superFieldVal + "_" + count + "_" + super.toString();
                param.getInnerClass().updateValue(processed);
                count++;
            } while (count < 5);
        }

        continueBlock();

        // Variable call (target member inner class)
        String innerVal = param.getInnerClass().getValue();
        // Use both member inner classes
        innerVal = new MemberInnerClass1().enhance(innerVal);
        innerVal = new MemberInnerClass2().enhance(innerVal);
        
        // Update target value
        ProtectedTargetRecord newTarget = new ProtectedTargetRecord(innerVal);
        newTarget.getInnerClass().updateValue(innerVal);

        // No new exception
        return newTarget;
    }
}

// Target record class (protected modifier, implements, member inner class target_feature)
protected record ProtectedTargetRecord(String value) implements TargetProcessable {
    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue;

        public void updateValue(String value) {
            this.innerValue = value;
        }

        public String getValue() {
            return innerValue;
        }
    }

    // Inner class instance for variable call
    private final InnerClass innerClass = new InnerClass();

    // Implement super interface method
    @Override
    public void process(String value) {
        this.innerClass.updateValue(value + "_processed");
    }

    // Constructor to initialize inner class
    public ProtectedTargetRecord {
        innerClass.updateValue(value + "_record_init");
    }

    // Variable call: getter for inner class
    public InnerClass getInnerClass() {
        return innerClass;
    }
}