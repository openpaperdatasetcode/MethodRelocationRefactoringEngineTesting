package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Super class for super constructor invocation
class SuperTargetClass {
    protected String superField;
    public SuperTargetClass(String value) {
        this.superField = value;
    }
}

// Target class: protected modifier, anonymous inner class (target_feature), same package as source
protected class TargetClass extends SuperTargetClass {
    public String targetField = "targetValue";

    public TargetClass() {
        super("superInitValue"); // Super constructor invocation in target class
    }

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String value) {
        this.targetField = value;
    }
}

// Source class: protected modifier, local inner + member inner class (source_feature), same package as target
protected class SourceClass {
    // Member inner class (source_feature)
    class MemberInnerClass {
        String innerData = "memberInnerData";
        
        public String getInnerData() {
            return innerData;
        }
    }

    // Instance method: public access, List<String> return type, target parameter (per_condition)
    public List<String> refactorMethod(TargetClass targetParam) {
        List<String> result = new ArrayList<>();

        // Super constructor invocation (method feature)
        TargetClass newTarget = new TargetClass();

        // Try statement (method feature)
        try {
            // Variable call (method feature)
            String varCall = targetParam.getTargetField();
            result.add("Variable call: " + varCall);
            
            // Call anonymous inner class method from target
            targetParam.anonymousInner.run();
            
            // Use member inner class
            MemberInnerClass memberInner = new MemberInnerClass();
            result.add("Member inner data: " + memberInner.getInnerData());

            // Local inner class (source_feature)
            class LocalInnerClass {
                void addData() {
                    result.add("Local inner class data: " + newTarget.superField);
                }
            }
            new LocalInnerClass().addData();
        } catch (Exception e) {
            // No new exception thrown (method feature) - only catch existing exceptions, no new instantiation
            result.add("Caught exception: " + e.getMessage());
        }

        // No new exception thrown (method feature) - no 'new Exception()' statements
        return result;
    }
}