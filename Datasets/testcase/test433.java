import java.lang.reflect.Method;

// Source class: default modifier, same package, local inner/anonymous inner class
class SourceClass {
    // Instance field for access_instance_field feature
    private String instanceField = "source_instance_field";

    // First-level inner class
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            // Target method: instance, void return, default access, in source_inner_rec
            void processData(TargetClass param) {
                // Fulfill per_condition: method contains target class parameter
                if (param == null) {
                    System.out.println("Target parameter is null");
                    return; // no_new_exception
                }

                // Type declaration statement
                class LocalTypeDeclaration { // Local inner class (source_class feature)
                    String processValue(String val) {
                        return val + "_processed_by_local_type";
                    }
                }
                LocalTypeDeclaration localType = new LocalTypeDeclaration();

                // Variable call to target parameter
                String targetVar = param.getTargetValue();
                
                // Access_instance_field (access outer class instance field)
                String outerFieldVal = SourceClass.this.instanceField; // access_instance_field
                String combinedVal = targetVar + "_" + outerFieldVal;

                // Anonymous inner class (source_class feature)
                Runnable anonymousInner = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Anonymous inner class processing: " + combinedVal);
                    }
                };
                anonymousInner.run();

                // Used_by_reflection
                try {
                    Method method = param.getClass().getMethod("setTargetValue", String.class);
                    method.invoke(param, localType.processValue(combinedVal));
                    // Verify reflection call result
                    System.out.println("Reflected updated value: " + param.getTargetValue());
                } catch (Exception e) {
                    // No new exception thrown (no_new_exception)
                    System.out.println("Reflection warning: " + e.getMessage());
                }

                // No new exception thrown (no_new_exception)
                System.out.println("Processing completed for: " + param.getTargetValue());
            }
        }
    }
}

// Target class: public modifier, local inner class (target_feature)
public class TargetClass {
    private String targetValue = "target_default_value";

    // Local inner class (fulfills target_feature)
    public void outerMethod() {
        class TargetLocalInner {
            String getLocalProcessedValue() {
                return targetValue + "_local_inner_processed";
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.targetValue = localInner.getLocalProcessedValue();
    }

    // Getter/Setter for variable call
    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }
}