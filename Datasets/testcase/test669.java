package com.refactoring.movemethod;

// Base class for override_violation feature
abstract class BaseMethodClass {
    // Base method with no throws clause (for override_violation)
    protected Object baseMethod(TargetEnum.TargetInnerRec inner) /* no throws */;
}

// Source enum class: default modifier, same package as target, static nested + local inner class
enum SourceEnum extends BaseMethodClass {
    INSTANCE;

    // Per_condition: source contains target enum field
    private final TargetEnum targetField = TargetEnum.VALUE1;
    private final Object lock = new Object();

    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String processValue(String s) {
            return s + "_static_2"; // target_feature: 2
        }
    target_feature: 2
        }
    }

    // DoStatement feature: private modifier, this.field, 2, pos=source
    private Object doStatementLogic(TargetEnum.TargetInnerRec inner) {
        // pos: source
        int count = 0;
        do {
            inner.innerField = 2; // this.field feature, target_feature: 2
            count++;
            // Variable call (target inner recursive class)
            inner.setInnerValue(SourceStaticNested.processValue(inner.getInnerValue()));
        } while (count < 2); // target_feature: 2
        
        // Assert statement
        assert count == 2 : "Do loop count must be 2"; // target_feature: 2
        return inner.getInnerValue();
    }

    // Method to refactor: instance, Object return, private access, in source enum
    @Override
    private Object baseMethod(TargetEnum.TargetInnerRec innerParam) { // override_violation (access modifier + return type mismatch)
        // Super keywords (enum implicit super)
        Object superRef = super.toString();
        
        // Constructor invocation (target inner recursive class)
        TargetEnum.TargetInnerRec newInner = targetField.new TargetInnerRec();

        // Synchronized statement
        synchronized (lock) {
            // DoStatement feature call
            Object doResult = doStatementLogic(innerParam);
            
            // Variable call (target field and inner class)
            String innerValue = innerParam.getInnerValue();
            innerParam.setInnerValue(innerValue + "_source_2"); // target_feature: 2

            // No_new_exception feature
            try {
                Integer.parseInt(innerValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                innerParam.setInnerValue("formatted_" + innerValue);
            }

            // Local inner class (source feature)
            class LocalInnerProcessor {
                Object process(Object input) {
                    return input.toString() + "_local_2"; // target_feature: 2
                }
            }
            return new LocalInnerProcessor().process(doResult);
        }
    }

    // Helper method to invoke refactored method
    public Object invokeMethod(TargetEnum.TargetInnerRec inner) {
        return this.baseMethod(inner);
    }
}

// Target enum class: private, no extra features (target_feature: empty)
private enum TargetEnum {
    VALUE1, VALUE2;

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        String innerValue = "target_inner_2"; // target_feature: 2
        int innerField; // this.field feature

        // Instance code blocks for call_method pos
        {
            // pos: instance code blocks
            TargetEnum outer = TargetEnum.this;
            String result = outer.callMethod(this); // call_method invocation
            this.innerValue = result;
        }

        // Base method for overriding feature
        protected String baseProcess() {
            return this.innerValue;
        }

        // Overriding method for call_method
        @Override
        public String toString() { // overriding feature
            return this.innerValue + "_overridden";
        }

        // call_method: target type, protected modifier, overriding, instanceReference::methodName, pos=instance code blocks, return String
        protected String callMethod(TargetInnerRec inner) {
            // target_feature: overriding (call overridden method)
            String overrideResult = inner.toString();
            
            // target_feature: instanceReference::methodName (method reference)
            java.util.function.Function<TargetInnerRec, String> ref = TargetInnerRec::baseProcess;
            return ref.apply(inner) + "_ref_2"; // target_feature: 2
        }

        // Getters/Setters for variable call
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }
}