import java.util.ArrayList;
import java.util.List;

// Source sealed enum class (protected modifier, same package as target, permits static nested classes)
sealed enum SourceEnum permits SourceEnum.FirstStaticNested, SourceEnum.SecondStaticNested {
    INSTANCE;

    // First static nested class (source_class feature)
    static final class FirstStaticNested extends SourceEnum {}

    // Second static nested class (source_class feature)
    static final class SecondStaticNested extends SourceEnum {}

    // Member inner class (for source_inner_rec)
    public class FirstInnerClass {
        // Recursive inner class (source_inner_rec position)
        public class SecondInnerClass {
            // Varargs method to be moved (public access, List<String> return, source_inner_rec)
            public List<String> processTarget(AbstractTargetEnum.InnerClass targetParam, String... values) {
                List<String> result = new ArrayList<>();
                
                // Requires try-catch block
                try {
                    // Enhanced for statement
                    for (String val : values) {
                        // Access instance field of target inner class
                        targetParam.data = val; // access_instance_field
                        // Variable call to target inner class method
                        result.add(targetParam.processData());
                    }

                    // Override violation (attempt to override final method)
                    targetParam.overrideViolation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return result;
            }

            // Call method inner class (inner_class type, default modifier)
            class CallMethodInner {
                // Overriding method (call_method target_feature: overriding)
                @Override
                public String toString() {
                    return "Overridden toString";
                }

                // Method with switch position for call_method
                public String processEnum(AbstractTargetEnum.InnerClass target) {
                    String result = "";
                    // pos: switch
                    switch (target.getCounter()) {
                        case 1:
                            // call_method target_feature: ClassName.methodName(arguments)
                            result = SourceEnum.SecondStaticNested.valueOf("INSTANCE").name();
                            break;
                        case 2:
                            result = AbstractTargetEnum.processInnerData(target);
                            break;
                        default:
                            result = "Default";
                    }
                    return result; // return_type: String
                }
            }
        }
    }
}

// Target abstract enum class (abstract modifier, same package as source)
abstract enum AbstractTargetEnum {
    VALUE1, VALUE2;

    // Inner class (target_inner for method's target class)
    public class InnerClass {
        // Instance field for access_instance_field feature
        public String data;
        private int counter = 0;

        // Final method for override_violation feature
        public final void overrideViolation() {
            throw new UnsupportedOperationException("Final method cannot be overridden");
        }

        // Variable call methods
        public String processData() {
            return "Processed: " + data;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        // Local inner class (target_feature)
        public void methodWithLocalInner() {
            class LocalInnerClass {
                void printTargetData() {
                    System.out.println(InnerClass.this.data);
                }
            }
            new LocalInnerClass().printTargetData();
        }
    }

    // Static method for ClassName.methodName(arguments) feature
    public static String processInnerData(InnerClass inner) {
        return inner.data + "_" + inner.getCounter();
    }
}