package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for "the attribute values of annotations" pos
@Retention(RetentionPolicy.RUNTIME)
@interface AccessorAnnotation {
    String value() default "accessor_3"; // 3 for method_feature
}

// Source abstract class: default modifier, same package, static nested + anonymous inner classes
abstract class SourceAbstractClass {
    // Source contains target field (per_condition)
    private TargetAbstractClass targetField = new TargetAbstractClass() {
        @Override
        protected String getTargetData() {
            return "source_init";
        }
    };

    // Static nested class (source feature)
    public static class SourceStaticNested {
        int nestedValue = 3; // For accessor method's 3 (method_feature)
    }

    // Member inner class (source_inner_rec - recursive inner structure)
    public class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceInnerRecursive {
            /**
             * Varargs method to be refactored (all specified features)
             * @param args varargs parameters
             */
            @AccessorAnnotation("accessor_3") // has_annotation + annotation attribute pos
            protected void refactorMethod(Object... args) { // varargs type + protected access
                // Raw type usage
                TargetAbstractClass rawTarget = targetField;
                rawTarget.value = "raw_type_value";

                // Variable call (target class field)
                targetField.value = "refactor_value";
                // Access target static nested class
                TargetAbstractClass.TargetStaticNested staticNested = new TargetAbstractClass.TargetStaticNested();
                targetField.counter = staticNested.nestedValue;

                // Labeled statement
                processLabel: {
                    // Super constructor invocation (in anonymous inner class)
                    Runnable anonymous = new Runnable() {
                        {
                            super(); // super constructor invocation
                        }
                        @Override
                        public void run() {
                            targetField.counter += 3;
                        }
                    };
                    anonymous.run();

                    // Accessor method call in annotation attribute values pos
                    @AccessorAnnotation(AccessorAnnotation.value())
                    class AnnotationHelper {}
                    Object accessorResult = accessorMethod(targetField); // 3/source/accessor/obj.m1().m2().m3()

                    break processLabel;
                }

                // Anonymous inner class (source feature)
                TargetAbstractClass anonymousTarget = new TargetAbstractClass() {
                    @Override
                    protected String getTargetData() {
                        return "anonymous_inner_" + targetField.value;
                    }

                    // return this; feature
                    @Override
                    public TargetAbstractClass getThis() {
                        return this;
                    }
                };
                anonymousTarget.getThis(); // Trigger return this;

                // No new exception, void return
            }

            /**
             * Accessor method (method_feature: 3/source/accessor/obj.m1().m2().m3())
             * @param target target abstract class instance
             * @return Object result
             */
            @AccessorAnnotation // has_annotation
            protected Object accessorMethod(TargetAbstractClass target) { // protected modifier + accessor type
                // obj.m1().m2().m3() chain + source + 3
                return target.getStaticNested() // m1()
                             .getNestedValue() // m2()
                             .toString() + "_" + SourceStaticNested.nestedValue; // m3() + 3 (method_feature)
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Target abstract class: private modifier, static nested class feature
private abstract class TargetAbstractClass {
    Object value;
    int counter;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 3; // For accessor method's 3 (method_feature)

        public Integer getNestedValue() {
            return nestedValue;
        }
    }

    // Accessor for static nested class (obj.m1() in accessor method)
    public TargetStaticNested getStaticNested() {
        return new TargetStaticNested();
    }

    // Abstract method (required for abstract class)
    protected abstract String getTargetData();

    // For return this; feature
    public TargetAbstractClass getThis() {
        return this;
    }
}