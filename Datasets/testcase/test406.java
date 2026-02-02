import java.util.ArrayList;
import java.util.List;

// Source record class: public, same package as target, no extra features
public record SourceClass(String sourceField) {
    // Inner class (source_inner) containing the target method
    class SourceInnerClass {
        private int outerPrivateField = 99; // Private field for access_outer_private
        static int staticField = 55; // Static field for depends_on_static_field

        // Target method: instance, List<String> return, protected, in source_inner
        protected List<String> processData(TargetClass.TargetStaticNestedClass param) {
            // NullPointerException handling (feature: NullPointerException)
            if (param == null) {
                throw new NullPointerException("Param cannot be null");
            }

            // Super keyword usage
            super.toString();

            // Access outer private field (access_outer_private)
            int outerPrivate = outerPrivateField;

            // Access instance field (access_instance_field)
            String instanceField = sourceField();

            // Variable call to target parameter
            String targetVar = param.targetField();

            // ConstructorInvocation (private modifier, diff_package_target - simulated via static nested class field access)
            TargetClass.TargetStaticNestedClass nested = new TargetClass.TargetStaticNestedClass(TargetClass.staticTargetField);

            // Expression statement
            List<String> result = new ArrayList<>();
            result.add(instanceField);
            result.add(targetVar);
            result.add(String.valueOf(outerPrivate));
            result.add(String.valueOf(staticField)); // Depends on static field

            // No new exception thrown (no_new_exception)
            return result;
        }
    }

    // Static field for ConstructorInvocation target_feature "ClassName.field"
    public static int staticSourceField = 10;
}

// Target record class: public, with static nested class (target_feature)
public record TargetClass(String targetField) {
    // Static nested class (target_inner)
    public static class TargetStaticNestedClass {
        private final String targetField;

        // Private constructor (ConstructorInvocation modifier: private)
        private TargetStaticNestedClass(String targetField) {
            this.targetField = targetField;
        }

        public String targetField() {
            return targetField;
        }
    }

    // Static field for ConstructorInvocation target_feature "ClassName.field"
    public static String staticTargetField = "static_target_value";
}