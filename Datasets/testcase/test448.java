package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: normal class, protected modifier, same package as target
// Feature: type parameter
protected class SourceClass<T> {
    // Custom annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    private @interface MethodAnnotation {}

    // Normal method (type: normal), void return, private access, position: source
    // Per_condition: contains parameter of the target (TargetClass param)
    @MethodAnnotation // has_annotation feature
    private void moveCandidateMethod(TargetClass param) {
        // Variable call (method feature)
        String varCall = param.toString();
        int value = varCall.length();

        // Expression statement (method feature)
        value *= 2;

        // ConditionalExpression (numbers:2, modifier:protected, exp:ConditionalExpression)
        protected int conditionalResult = (value > 10) ? 10 : 5;

        // Exception throwing statements (pos for nested normal method)
        try {
            if (param == null) {
                // Nested normal method call (method_feature: ["2", "sub_class", "normal", "outerInstance.new InnerClass().methodName()"])
                int subClassResult = new SourceSubClass<>().nestedNormalMethod(new SourceClass<>().new InnerClass().innerMethod());
                value += subClassResult;
            }
        } catch (IllegalArgumentException e) {
            // No new exception (method feature - no throw new Exception)
            value -= e.getMessage().length();
            return; // return statement (method feature)
        }

        // Exception handling statements (pos for call_method)
        try {
            // Call parent_class method (call_method)
            int callResult = ParentClass.callMethod(param, value);
            value += callResult;
        } catch (Exception e) {
            value = 0;
        }

        // Return statement (method feature - void return)
        return;
    }

    // Inner class for outerInstance.new InnerClass().methodName()
    public class InnerClass {
        public int innerMethod() {
            return 2; // method_feature: "2"
        }
    }

    // Nested normal method (type:normal, modifier:public, return base type)
    public int nestedNormalMethod(int input) {
        return input * 2;
    }
}

// Sub class for method_feature: "sub_class"
class SourceSubClass<T> extends SourceClass<T> {
    @Override
    public int nestedNormalMethod(int input) {
        return super.nestedNormalMethod(input) + 2;
    }
}

// Parent class for call_method (type:parent_class)
class ParentClass {
    // call_method: modifier=private, target_feature: constructor, superTypeReference.methodName(arguments)
    // pos: exception handling statements, return_type:int
    private static int callMethod(TargetClass target, int value) {
        // Constructor (target_feature)
        TargetClass newTarget = new TargetClass();
        
        // superTypeReference.methodName(arguments) (target_feature)
        Object superRef = newTarget.getClass().getSuperclass();
        int result = ((Number) superRef.getClass().getMethod("hashCode").invoke(superRef)).intValue();
        
        return result + value;
    }
}

// Target class: normal class, public modifier, target_feature: empty
public class TargetClass {}