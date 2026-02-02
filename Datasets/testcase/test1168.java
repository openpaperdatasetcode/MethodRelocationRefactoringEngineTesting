import java.util.ArrayList;
import java.util.List;

// Source public normal class (same package, empty feature list)
public class SourceClass {
    // Field for uses_outer_this feature
    private String outerField = "SOURCE_OUTER_FIELD_5863";
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass("init_value_5863");

    /**
     * Processes the target inner class instance with core business logic.
     * <p>This method demonstrates:
     * <ul>
     *   <li>For loop processing with target class static field access</li>
     *   <li>Outer class 'this' reference usage</li>
     *   <li>Variable call to target inner class methods</li>
     * </ul>
     * 
     * @param param the target inner class instance to process (target_inner)
     * @return processed Object containing result list
     * @since 1.0
     */
    // Method to be refactored (instance, Object return, protected access)
    protected Object processTarget(TargetClass.InnerClass param) { // per_condition: target parameter (target_inner)
        List<String> result = new ArrayList<>();

        // ForStatement (protected modifier, ClassName.field, 1, pos: source)
        protected void forLoopProcessing() {
            String classField = TargetClass.STATIC_FIELD; // ClassName.field
            int num = 1; // target_feature: 1

            // For statement
            for (int i = 0; i < num + 2; i++) {
                // uses_outer_this feature (access outer class field via qualified this)
                String processedVal = param.getValue() + "_" + SourceClass.this.outerField + "_loop_" + i;
                param.setValue(processedVal);
                result.add(processedVal);
            }
        }

        // Execute for loop processing
        forLoopProcessing();

        // Variable call (target inner class getter/setter)
        String innerVal = param.getValue() + "_variable_call";
        param.setValue(innerVal);
        result.add(innerVal);

        // return statement
        return result; // No new exception (core logic is safe)
    }
}

// Target normal class (default modifier, empty target_feature list)
class TargetClass {
    // ClassName.field for ForStatement feature
    public static final String STATIC_FIELD = "TARGET_STATIC_FIELD_5863";
    private String value;

    // Inner class (target_inner)
    public class InnerClass {
        private String innerValue;

        public InnerClass() {
            this.innerValue = TargetClass.this.value;
        }

        // Variable call: getter/setter
        public String getValue() {
            return innerValue;
        }

        public void setValue(String value) {
            this.innerValue = value;
        }
    }

    // Constructor
    public TargetClass(String value) {
        this.value = value;
    }

    // Variable call: helper method to create inner class instance
    public InnerClass createInnerClass() {
        return new InnerClass();
    }

    // Getter for outer class value (for completeness)
    public String getValue() {
        return value;
    }
}