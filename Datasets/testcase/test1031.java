import java.util.ArrayList;
import java.util.List;

// Functional interface for source enum implements feature
interface EnumProcessable {
    int process(String value);
}

// Super class for source enum extends feature (enum can only extend Enum implicitly, use composition)
class EnumSuperHelper {
    protected String superField = "ENUM_SUPER_FIELD_5872";
}

// Source public enum class (same package, extends (composition), implements)
public enum SourceEnum implements EnumProcessable {
    INSTANCE1("value1"),
    INSTANCE2("value2");

    // Composition for extends feature (enum cannot extend class directly)
    private final EnumSuperHelper superHelper = new EnumSuperHelper();
    private final String value;
    // per_condition: source contains the field of the target
    private final PrivateTargetEnum targetEnum = PrivateTargetEnum.TARGET_INSTANCE;

    // Super constructor invocation (enum constructor)
    SourceEnum(String value) {
        super(); // Implicit super constructor for Enum
        this.value = value;
    }

    // Inner class (method_position: source_inner)
    public class InnerEnumClass {
        // Overloading method 1 (base overload)
        public int processTarget(PrivateTargetEnum.InnerRecord param) {
            return param.getValue().length();
        }

        // Method to be refactored (overloading, base type return, public access)
        public int processTarget(PrivateTargetEnum.InnerRecord param, int multiplier) { // per_condition: target parameter
            // type declaration statement
            List<String> result = new ArrayList<>();
            String superFieldVal;
            int num;

            // ConstructorInvocation (protected modifier, super.field, 1, pos: source)
            protected void constructorBlock() {
                superFieldVal = superHelper.superField; // super.field
                num = 1; // target_feature: 1

                // Constructor invocation (target inner record)
                PrivateTargetEnum.InnerRecord newInnerRec = new PrivateTargetEnum.InnerRecord(
                    param.getValue() + "_constructor_" + superFieldVal + "_" + num
                );
                result.add(newInnerRec.getValue());
            }

            // Execute constructor block
            constructorBlock();

            // enhanced for statement (corrected typo: enhganced -> enhanced)
            for (String s : result) {
                // expression statement
                String exprVal = s + "_enhanced_for_" + num; // expression statement
                result.set(result.indexOf(s), exprVal);
            }

            // NullPointerException feature (explicit check)
            if (param == null) {
                throw new NullPointerException("Target inner record parameter is null");
            }

            // Call method in instance code blocks (pos: instance code blocks)
            { // instance code block
                List<String> callResult = SourceEnum.this.callMethod(param.getValue()); // instanceReference.methodName(arguments)
                result.addAll(callResult);
            }

            // Variable call (target enum inner record + anonymous inner class)
            param = targetEnum.processWithAnonymousInner(param); // anonymous inner class (target_feature)
            int baseResult = param.getValue().length() * multiplier;

            // No new exception
            return baseResult;
        }
    }

    // Call method (source type, default modifier, constructor, instanceReference.methodName(arguments))
    List<String> callMethod(String val) {
        // constructor feature (create new list instance)
        List<String> list = new ArrayList<>();
        list.add(val + "_call_method_" + superHelper.superField);
        return list;
    }

    // Implement functional interface method
    @Override
    public int process(String value) {
        return value.length();
    }
}

// Target private enum class (anonymous inner class target_feature)
private enum PrivateTargetEnum {
    TARGET_INSTANCE("target_value");

    private final String value;

    PrivateTargetEnum(String value) {
        this.value = value;
    }

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    // anonymous inner class target_feature
    public InnerRecord processWithAnonymousInner(InnerRecord param) {
        // Anonymous inner class processing
        EnumProcessable processor = new EnumProcessable() { // anonymous inner class
            @Override
            public int process(String value) {
                return value.length() + 1;
            }
        };
        return new InnerRecord(param.value() + "_anonymous_" + processor.process(param.value()));
    }

    // Variable call: getter
    public String getValue() {
        return value;
    }
}