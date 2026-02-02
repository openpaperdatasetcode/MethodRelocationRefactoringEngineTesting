import java.util.ArrayList;
import java.util.List;

// Source class: public normal class, same package, two member inner classes
public class SourceClass {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // First member inner class (fulfills source_class feature)
    class FirstMemberInner {}
    // Second member inner class (fulfills source_class feature twice)
    class SecondMemberInner {}

    // Base class for overriding
    public class SourceInnerClass {
        // Base method for overriding
        public void processData(TargetClass.TargetInnerClass param) {
            System.out.println("Base method: " + param.getInnerValue());
        }
    }

    // Overriding inner class (source_inner)
    public class OverridingInnerClass extends SourceInnerClass {
        // Target method: overriding, void return, protected access, in source_inner
        @Override
        protected void processData(TargetClass.TargetInnerClass param) {
            // Uses_outer_this (access outer class instance)
            SourceClass outerThis = SourceClass.this; // uses_outer_this
            outerThis.targetField.setTargetValue("updated_by_outer_this");

            // Variable call to target parameter
            String targetVar = param.getInnerValue();

            // Raw_type feature (use generic list without type parameter)
            List rawList = new ArrayList(); // raw_type
            rawList.add(targetVar);

            // Do statement
            int count = 0;
            do {
                // Switch case
                switch (count) {
                    case 0:
                        rawList.add(targetVar + "_case0");
                        break;
                    case 1:
                        rawList.add(targetVar + "_case1");
                        break;
                    default:
                        rawList.add(targetVar + "_default");
                        break;
                }
                count++;
            } while (count < 3);

            // Override_violation: inner class with incompatible method signature
            class OverrideViolation extends SourceInnerClass {
                @Override
                public Integer processData(TargetClass.TargetInnerClass param) { // Incompatible return type (void vs Integer)
                    return rawList.size(); // override_violation
                }
            }

            // No new exception thrown (no_new_exception)
            System.out.println("Processed values: " + rawList);
        }
    }
}

// Target class: default modifier, local inner class (target_feature)
class TargetClass {
    private String targetValue = "target_base_value";

    // Target inner class (target_inner)
    public class TargetInnerClass {
        private String innerValue = targetValue + "_inner";

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String val) {
            this.innerValue = val;
        }
    }

    // Call method: public modifier, normal, super.methodName(arguments) in instance code blocks
    public int invokeSourceMethod() {
        // Instance code blocks position (pos: instance code blocks)
        {
            SourceClass source = new SourceClass();
            SourceClass.OverridingInnerClass overridingInner = source.new OverridingInnerClass();
            TargetInnerClass targetInner = this.new TargetInnerClass();

            // super.methodName(arguments) (target_feature: normal + super call)
            overridingInner.super.processData(targetInner); // super.methodName(arguments)
            
            // Call overriding method
            overridingInner.processData(targetInner);
        }

        // Return int type
        return this.targetValue.length();
    }

    // Getter/Setter for uses_outer_this feature
    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    // Local inner class (fulfills target_feature)
    public void outerMethod() {
        class TargetLocalInner {
            String processValue(String val) {
                return val.toUpperCase();
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.targetValue = localInner.processValue(this.targetValue);
    }
}