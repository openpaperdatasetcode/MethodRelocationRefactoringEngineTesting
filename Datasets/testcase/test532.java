import java.util.ArrayList;
import java.util.List;

// Parent class for source class extends feature and super.field/super.methodName() access
class ParentClass {
    protected int superField = 3; // Matches "3" in SwitchStatement target_feature

    protected String superMethod(int val) {
        return "Super method result: " + val;
    }
}

// Source normal class (public modifier, same package as target, extends ParentClass)
public class SourceClass extends ParentClass {
    // Field of target class (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (for source_inner_rec)
    public class FirstInnerClass {
        // Recursive inner class (source_inner_rec position)
        public class SecondInnerClass {
            // Overloading method 1 (List<String> return, public access)
            public List<String> processTarget(TargetClass.InnerClass.RecursiveInnerClass targetParam) {
                return processTarget(targetParam, "default");
            }

            // Overloading method 2 (overloading feature, List<String> return, public access)
            public List<String> processTarget(TargetClass.InnerClass.RecursiveInnerClass targetParam, String... args) {
                List<String> result = new ArrayList<>();

                // NullPointerException (no_new_exception: no explicit new Exception())
                if (targetParam == null) {
                    throw new NullPointerException("Target parameter cannot be null");
                }

                // With bounds (generic type with bounds)
                class BoundedClass<T extends Number & Comparable<T>> {
                    T processValue(T value) {
                        return value;
                    }
                }
                BoundedClass<Integer> boundedInstance = new BoundedClass<>();

                // Type declaration statement
                int switchVar = super.superField; // Access super.field for SwitchStatement
                // Labeled statement
                label:
                for (String arg : args) {
                    if (arg.isBlank()) {
                        break label;
                    }
                    result.add(arg);
                }

                // SwitchStatement (private modifier, pos: same_package_others, target_feature: super.field, 3)
                private void switchProcessing() {
                    switch (switchVar) { // super.field value is 3 (target_feature: 3)
                        case 1:
                            targetParam.setData("Case 1");
                            break;
                        case 3:
                            targetParam.setData("Case 3 (super.field value)");
                            break;
                        default:
                            targetParam.setData("Default case");
                    }
                }
                switchProcessing();

                // Varargs method with specified features (final modifier, pos: expression, return TargetClass type)
                private final TargetClass.InnerClass.RecursiveInnerClass varargsHelper(Integer... values) {
                    int val = 2; // method_feature: 2
                    // method_feature: source + super.methodName()
                    String superResult = super.superMethod(val); // super.methodName()
                    // method_feature: varargs
                    for (Integer v : values) {
                        targetParam.setCounter(targetParam.getCounter() + v);
                    }
                    targetParam.setData(superResult);
                    return targetParam; // return TargetClass type
                }

                // Expression position for varargs method invocation
                TargetClass.InnerClass.RecursiveInnerClass processedTarget = varargsHelper(1, 2, 3);
                // Variable call
                result.add(processedTarget.getData());
                result.add(String.valueOf(boundedInstance.processValue(processedTarget.getCounter())));

                // Assert statement
                assert processedTarget.getCounter() > 0 : "Counter must be positive";

                return result;
            }
        }
    }
}

// Target normal class (default modifier, same package as source)
class TargetClass {
    // Inner class (for target_inner_rec)
    public class InnerClass {
        // Recursive inner class (target_inner_rec)
        public class RecursiveInnerClass {
            private String data;
            private int counter = 0;

            // Variable call methods
            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public int getCounter() {
                return counter;
            }

            public void setCounter(int counter) {
                this.counter = counter;
            }
        }
    }

    // Local inner class (target_feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printTargetInfo() {
                System.out.println(TargetClass.this.toString());
            }
        }
        new LocalInnerClass().printTargetInfo();
    }
}