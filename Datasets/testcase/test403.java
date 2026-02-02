// Source package (different from target package)
package sourcepkg;

import targetpkg.TargetClass;
import java.lang.reflect.Method;

// Source normal class: public modifier, different package, member inner + local inner classes
public class SourceClass {
    // Member inner class (source feature)
    public class SourceInnerClass {
        String innerData = "source_inner";

        // Abstract method must be in abstract class, so create nested abstract class
        public abstract static class AbstractNestedClass {
            // Abstract method to be refactored (all specified features)
            // Type: abstract, Return: base type (int), Access: private, Position: source
            private abstract int refactorMethod(TargetClass.TargetInnerRecursive targetParam); // per_condition
        }

        // Concrete implementation of abstract method
        public class ConcreteNestedClass extends AbstractNestedClass {
            @Override
            private int refactorMethod(TargetClass.TargetInnerRecursive targetParam) {
                // Variable call (target inner recursive class field)
                targetParam.value = "refactor_value";
                targetParam.counter = 1;

                // NullPointerException feature (explicit NPE handling)
                if (targetParam == null) {
                    throw new NullPointerException("Target parameter cannot be null");
                }

                // Access instance method (target inner class instance method)
                String instanceResult = targetParam.getInstanceData();
                targetParam.counter = instanceResult.length();

                // Used by reflection (access target inner recursive class via reflection)
                try {
                    // Get inner recursive class method
                    Method setValueMethod = TargetClass.TargetInnerRecursive.class.getDeclaredMethod("setValue", String.class);
                    setValueMethod.invoke(targetParam, "reflection_updated");
                    // Get nested inner class field
                    Method getNestedValueMethod = TargetClass.TargetInnerRecursive.TargetRecursiveInner.class.getDeclaredMethod("getNestedValue");
                    Object nestedValue = getNestedValueMethod.invoke(targetParam.recursiveInner);
                    targetParam.counter += (Integer) nestedValue;
                } catch (Exception e) {
                    // No new exception (wrap without creating new exception type)
                    throw new RuntimeException(e);
                }

                // Local inner class (source feature)
                class SourceLocalInner {
                    int validateTarget(TargetClass.TargetInnerRecursive inner) {
                        if (inner.counter <= 0) {
                            throw new IllegalArgumentException("Counter must be positive");
                        }
                        return inner.counter;
                    }
                }
                int localResult = new SourceLocalInner().validateTarget(targetParam);

                // No new exception, return base type (int)
                return localResult;
            }
        }
    }

    // Helper method to instantiate inner classes and trigger abstract method
    public int executeRefactor(TargetClass.TargetInnerRecursive targetParam) {
        SourceInnerClass inner = new SourceInnerClass();
        SourceInnerClass.ConcreteNestedClass concrete = inner.new ConcreteNestedClass();
        return concrete.refactorMethod(targetParam); // Call abstract method implementation
    }
}

// Target package (different from source package)
package targetpkg;

// Target normal class: public modifier, static nested class feature
public class TargetClass {
    private String mainData;

    public TargetClass(String mainData) {
        this.mainData = mainData;
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        String value;
        int counter;
        TargetRecursiveInner recursiveInner = new TargetRecursiveInner();

        // Instance method for access_instance_method feature
        public String getInstanceData() {
            return value + "_" + mainData;
        }

        // Setter for reflection access
        public void setValue(String value) {
            this.value = value;
        }

        // Recursive inner structure
        public class TargetRecursiveInner {
            private int nestedValue = 1;

            public int getNestedValue() {
                return nestedValue;
            }
        }
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static int staticCounter = 1;

        public static int getStaticCounter() {
            return staticCounter;
        }
    }

    // Factory method for inner recursive class creation
    public TargetInnerRecursive createInnerRecursive() {
        return new TargetInnerRecursive();
    }
}