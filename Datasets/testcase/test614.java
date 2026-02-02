package com.refactoring.movemethod;

// Others class for method_feature: others_class
class OthersClass {
    public Object processTarget(TargetClass.TargetInner inner) {
        return inner.getInnerValue() + "_processed_by_others";
    }
}

// Super class for target class extends feature & super constructor invocation
class SuperTargetClass {
    protected String superField;

    public SuperTargetClass(String value) {
        this.superField = value;
    }
}

// Source class: public, same package as target, anonymous inner class, local inner class
public class SourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Overloading method 1 (return TargetClass Type)
        private TargetClass methodToRefactor(TargetClass.TargetInner innerParam) {
            // Type declaration statement
            class ProcessedValue {
                String value;
                ProcessedValue(String v) { this.value = v; }
            }

            // Constructor invocation (target inner class)
            TargetClass.TargetInner newInner = targetField.new TargetInner();
            
            // Super constructor invocation (via target class)
            TargetClass targetWithSuper = new TargetClass("superConstructorValue1"); // method_feature: 1

            // For statement
            String targetValues = "";
            for (int i = 0; i < 1; i++) { // method_feature: 1
                // Variable call (target inner field)
                targetValues += innerParam.getInnerValue() + "_loop_" + i;
            }

            // If statement
            if (targetValues.isEmpty()) {
                targetValues = "default_value_1"; // method_feature: 1
            }

            // Overloading method feature: public modifier, 1, others_class, overloading, (parameters) -> methodBody, pos=instance code blocks, return Object
            public Object overloadingHelper1(TargetClass.TargetInner param) {
                return new OthersClass().processTarget(param); // method_feature: others_class
            }

            public Object overloadingHelper2(TargetClass.TargetInner param, String extra) {
                // (parameters) -> methodBody (lambda expression)
                java.util.function.Function<TargetClass.TargetInner, String> lambda = (p) -> p.getInnerValue() + extra;
                return lambda.apply(param);
            }

            // pos: instance code blocks
            {
                overloadingHelper1(newInner);
                overloadingHelper2(newInner, "_1"); // method_feature: 1
            }

            // Depends on inner class (local inner class)
            class LocalInnerProcessor { // depends_on_inner_class
                String process(String input) {
                    return input + "_local_inner_processed";
                }
            }
            LocalInnerProcessor processor = new LocalInnerProcessor();
            newInner.setInnerValue(processor.process(targetValues));

            // Requires_try_catch feature
            try {
                Integer.parseInt(newInner.getInnerValue());
            } catch (NumberFormatException e) {
                newInner.setInnerValue("formatted_" + newInner.getInnerValue());
            }

            // Anonymous inner class (source class feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    System.out.println(newInner.getInnerValue());
                }
            };
            anonymous.run();

            return targetField; // Return TargetClass Type
        }

        // Overloading method 2 (overloading feature)
        private TargetClass methodToRefactor(TargetClass.TargetInner innerParam, int num) {
            TargetClass baseResult = methodToRefactor(innerParam);
            baseResult.getInner().setInnerValue(baseResult.getInner().getInnerValue() + "_" + num);
            return baseResult;
        }
    }

    // Local inner class (source class feature)
    public void sourceLocalInnerFeature() {
        class SourceLocalInner {
            void printTarget() {
                System.out.println(targetField.getInner().getInnerValue());
            }
        }
        new SourceLocalInner().printTarget();
    }
}

// Target class: protected, same package as source, extends, local inner class feature
protected class TargetClass extends SuperTargetClass {
    private TargetInner inner = new TargetInner();

    // Super constructor invocation
    public TargetClass() {
        super("default_super_value");
    }

    public TargetClass(String superValue) {
        super(superValue); // super constuctor invocation
    }

    // Target_inner (target inner class)
    public class TargetInner {
        private String innerValue = "target_inner_value_1"; // method_feature: 1

        public String getInnerValue() {
            // Local inner class (target_feature)
            class TargetLocalInner {
                String format() {
                    return innerValue + "_target_local_inner";
                }
            }
            return new TargetLocalInner().format();
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    public TargetInner getInner() {
        return inner;
    }
}