import java.io.IOException;

// Sealed interface for source_class permits feature
sealed interface SourceSealedInterface permits SourceClass.MemberInnerClass {}

// Super class for super.field feature
class TargetSuperClass {
    protected String superField = "super_target_field_5826";
}

// Source normal class (default modifier, same package, permits, member inner class, anonymous inner class)
class SourceClass {
    // per_condition: source contains the field of the target
    private final PublicTargetClass targetField = new PublicTargetClass();

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in SourceClass");
        }
    };

    // Member inner class (source_class feature, method_position: source_inner, permits feature)
    class MemberInnerClass implements SourceSealedInterface {
        // Instance code block for instance method position
        {
            // Instance method call in instance code blocks (pos: instance code blocks, 1, inner_class, instance, super.methodName())
            instanceMethod(1);
        }

        /**
         * Method Javadoc: Generic method with TargetClass return, private access
         * method types parameter is:none
         * @param param Target inner class parameter
         * @return PublicTargetClass instance
         * @throws IOException Required throws feature
         */
        private <T> PublicTargetClass targetMethod(PublicTargetClass.InnerClass param) throws IOException { // generic type, TargetClass return
            // Super constructor invocation (for inner class)
            super();

            // Try statement
            try {
                // ConstructorInvocation (public modifier, super.field, 1, pos: diff_package_others)
                public void constructorBlock() {
                    TargetSuperClass superObj = new TargetSuperClass();
                    String superFieldVal = superObj.superField; // super.field
                    int num = 1; // target_feature: 1
                    PublicTargetClass newTarget = new PublicTargetClass(superFieldVal + "_" + num); // ConstructorInvocation
                    param.setOuterTarget(newTarget);
                }
                constructorBlock();

                // While statement
                int count = 0;
                while (count < 3) {
                    // Variable call
                    String innerVal = param.getValue();
                    param.setValue(innerVal + "_loop_" + count);

                    // Continue statement
                    if (count == 1) {
                        count++;
                        continue;
                    }

                    // Break statement
                    if (count == 2) {
                        break;
                    }
                    count++;
                }

                // Constructor invocation
                PublicTargetClass.StaticNestedClass staticObj = new PublicTargetClass.StaticNestedClass();
                param.setValue(param.getValue() + "_" + staticObj.getStaticVal());

                // Depends on inner class
                HelperInnerClass helper = new HelperInnerClass();
                helper.updateTarget(param);

            } catch (Exception e) {
                throw new IOException("Processing error", e); // requires_throws
            }

            // Anonymous inner class trigger
            anonymousInner.run();

            // Return target class instance
            return param.getOuterTarget();
        }

        // Instance method (public modifier, 1, inner_class, instance, super.methodName(), return void)
        public void instanceMethod(int num) {
            super.toString(); // super.methodName()
            if (num == 1) { // method_feature: 1
                System.out.println("Instance method called with num: " + num);
            }
        }

        // Helper inner class for depends_on_inner_class feature
        class HelperInnerClass {
            void updateTarget(PublicTargetClass.InnerClass inner) {
                inner.setValue(inner.getValue() + "_helper_inner");
            }
        }
    }
}

// Target normal class (public modifier, static nested class target_feature)
public class PublicTargetClass extends TargetSuperClass {
    private String value = "target_value_5826";

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private String staticVal = "static_nested_val_5826";

        public String getStaticVal() {
            return staticVal;
        }
    }

    // Inner class (target_inner)
    public class InnerClass {
        private String value;
        private PublicTargetClass outerTarget;

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
        public PublicTargetClass getOuterTarget() { return outerTarget; }
        public void setOuterTarget(PublicTargetClass outerTarget) { this.outerTarget = outerTarget; }
    }

    // Constructor for invocation
    public PublicTargetClass() {
        this.value = super.superField + "_default";
    }

    public PublicTargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}