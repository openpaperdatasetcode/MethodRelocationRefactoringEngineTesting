package com.refactoring.movemethod;

// Parent class for target class extends feature
class TargetParent {
    protected String parentField = "target_parent_6137";
}

// Source abstract class: protected modifier, same package, two member inner classes
protected abstract class SourceClass {
    // per_condition: source contains field of target
    protected TargetClass targetField = new TargetClassImpl();

    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_6137";

    // First member inner class (source feature)
    protected class FirstSourceInner {
        private String innerField1 = "inner1_6137";
        
        // Instance method for access_instance_method feature
        public String processString(String val) {
            return val + "_processed";
        }
    }

    // Second member inner class (source feature)
    public class SecondSourceInner extends FirstSourceInner {
        private String innerField2 = "inner2_6137";

        // Method to be refactored: overriding, Object return, public access, source_inner
        @Override
        public String processString(String val) {
            // super constructor invocation (inner class context)
            super();

            // access_outer_protected feature
            String outerProtectedVal = SourceClass.this.outerProtectedField;

            // variable call feature
            String varCall = this.innerField1 + this.innerField2 + outerProtectedVal;

            // switch statement
            switch (varCall.length() % 3) {
                case 0:
                    varCall += "_case0";
                    break;
                case 1:
                    varCall += "_case1";
                    break;
                default:
                    varCall += "_case2";
                    break;
            }

            // access_instance_method feature (call outer inner class method)
            FirstSourceInner firstInner = new FirstSourceInner();
            String instanceMethodResult = firstInner.processString(varCall);

            // Access target inner class (target_inner)
            TargetClass.TargetInner targetInner = targetField.new TargetInner();
            targetInner.innerField = instanceMethodResult;

            // no_new_exception (no explicit new Exception instantiation)
            return targetInner.getCombinedData();
        }
    }
}

// Target abstract class: public modifier, extends + static nested class (target_features)
public abstract class TargetClass extends TargetParent {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String staticField = "target_static_6137";
    }

    // Target inner class (target_inner)
    public class TargetInner {
        public String innerField;

        public String getCombinedData() {
            return innerField + "_" + TargetClass.this.parentField + "_" + new TargetStaticNested().staticField;
        }
    }

    public abstract TargetInner new TargetInner();
}

// Concrete implementation of abstract target class
class TargetClassImpl extends TargetClass {
    @Override
    public TargetInner new TargetInner() {
        return new TargetInner();
    }
}