import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// Source normal class (default modifier, same package, anonymous inner class, member inner class)
class SourceClass {
    // per_condition: source contains the field of the target
    private final PublicTargetClass targetField = new PublicTargetClass("init_value_5862");

    // Member inner class (source_class feature, method_position: source_inner_rec)
    public class InnerSourceClass {
        // Method to be refactored (instance, void return, protected access)
        protected void processTarget(PublicTargetClass.InnerClass param) { // per_condition: target parameter (target_inner)
            // if statement
            if (param.getValue() == null || param.getValue().isEmpty()) {
                param.setValue("default_inner_value_5862");
                // return statement (void return)
                return;
            }

            // access_instance_method feature (call target instance method)
            String instanceMethodResult = param.enhanceValue("_instance_method_call");
            param.setValue(instanceMethodResult);

            // override_violation feature (attempt to override final method via reflection)
            try {
                Method finalMethod = PublicTargetClass.InnerClass.class.getDeclaredMethod("getValue");
                if (Modifier.isFinal(finalMethod.getModifiers())) {
                    // Simulate override violation (logical check)
                    System.out.println("Override violation detected: cannot override final method getValue()");
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            // used_by_reflection feature (invoke target method via reflection)
            try {
                Method setValueMethod = PublicTargetClass.InnerClass.class.getDeclaredMethod("setValue", String.class);
                setValueMethod.invoke(param, param.getValue() + "_reflection_modified");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Call method in while loop (pos: while, target type, public, normal, this.methodName(arguments))
            int count = 0;
            while (count < 3) {
                String callResult = param.thisMethodCall("while_loop_" + count); // this.methodName(arguments)
                param.setValue(param.getValue() + "_" + callResult);
                count++;
            }

            // Variable call (target inner class + main class)
            targetField.setValue(param.getValue() + "_outer_target_updated");
            System.out.println("Final inner value: " + param.getValue());

            // No new exception
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerSourceClass innerObj = new InnerSourceClass();
            innerObj.processTarget(targetField.new InnerClass());
        }
    };

    // Trigger method
    public void execute() {
        anonymousInner.run();
    }
}

// Target normal class (public modifier, empty target_feature list)
public class PublicTargetClass {
    private String value;

    // Inner class (target_inner)
    public class InnerClass {
        private String innerValue;

        public InnerClass() {
            this.innerValue = PublicTargetClass.this.value;
        }

        // Final method for override_violation feature
        public final String getValue() {
            return innerValue;
        }

        public void setValue(String value) {
            this.innerValue = value;
        }

        // access_instance_method feature (instance method)
        public String enhanceValue(String suffix) {
            return this.innerValue + "_enhanced" + suffix;
        }

        // Call method (target type, public, normal, this.methodName(arguments))
        public String thisMethodCall(String arg) {
            // this.methodName(arguments) (call own method)
            return this.enhanceValue("_" + arg);
        }
    }

    // Constructor
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