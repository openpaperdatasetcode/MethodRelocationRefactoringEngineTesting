import java.util.ArrayList;
import java.util.List;

// Super class for target_class extends feature
class SuperTargetClass {
    protected String superField = "super_target_field_5824";
}

// Source abstract normal class (abstract modifier, same package, static nested class, local inner class)
abstract class SourceClass {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "source_static_5824";

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        public static String format(String s) {
            return s.toUpperCase() + "_" + STATIC_FIELD;
        }
    }

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (normal, List<String> return, protected access)
        protected List<String> targetMethod(TargetClass.InnerRecord param) { // per_condition: target parameter (target_inner_rec)
            // Super constructor invocation (for inner class)
            super();

            List<String> result = new ArrayList<>();

            // Variable call (target inner record + target class)
            String innerRecVal = param.value();
            targetField.setValue(innerRecVal + "_" + STATIC_FIELD); // depends_on_static_field
            String targetVal = targetField.getValue();

            // Local inner class (source_class feature)
            class LocalInnerClass {
                void processList(List<String> list) {
                    // Variable call (target static nested class)
                    String staticNestedVal = TargetClass.StaticNestedTargetClass.CONST;
                    list.add(targetVal + "_" + staticNestedVal);
                    list.add(StaticNestedSourceClass.format(innerRecVal)); // depends_on_static_field
                }
            }

            new LocalInnerClass().processList(result);

            // No new exception
            return result;
        }
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

// Target normal class (default modifier, extends, static nested class target_feature)
class TargetClass extends SuperTargetClass { // target_feature: extends
    private String value = "target_value_5824";

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static final String CONST = "TARGET_STATIC_CONST_5824";
    }

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {} // target_inner_rec

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}