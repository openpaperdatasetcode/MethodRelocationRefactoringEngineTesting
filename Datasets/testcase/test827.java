package refactoring.test;

// Super class for super keywords and super.methodName(arguments)
class SuperAbstractTarget {
    protected int superField = 1;
    
    protected void superMethod(int value) {
        this.superField = value;
    }
}

// Target class: abstract modifier, member inner class (target_feature), same package as source
abstract class TargetClass extends SuperAbstractTarget {
    public int targetField = 1;
    
    // Member inner class (target_feature)
    protected class TargetInnerClass {
        public String innerField = "innerValue";
    }
    
    public abstract int abstractMethod();
}

// Source class: default modifier, empty features, same package as target
class SourceClass {
    // Instance method: public access, base return type (int), target parameter (per_condition)
    public int refactorMethod(TargetClass targetParam) {
        // Constructor invocation (method feature)
        TargetClass.TargetInnerClass innerObj = targetParam.new TargetInnerClass();
        
        // Expression statement (method feature)
        int localVar = targetParam.targetField + 1;
        
        // Super keywords (method feature)
        targetParam.superField = 1;
        
        // Variable call (method feature)
        String varCall = innerObj.innerField;
        
        // SwitchStatement: protected modifier, obj.field, 1, pos=same_package_others
        protected int switchFeature(TargetClass obj) {
            switch (obj.targetField) {
                case 1:
                    return obj.targetField;
                default:
                    return 0;
            }
        }
        
        // Normal method feature: public modifier, 1, target, normal, super.methodName(arguments), pos=switch, return_type=void
        public void normalHelperMethod(TargetClass target) {
            switch (target.targetField) {
                case 1:
                    target.superMethod(1); // super.methodName(arguments)
                    break;
                default:
                    break;
            }
        }

        // Execute switch feature
        int switchResult = switchFeature(targetParam);
        
        // Execute normal helper method
        normalHelperMethod(targetParam);
        
        // No new exception thrown (method feature)
        return localVar + switchResult;
    }
}