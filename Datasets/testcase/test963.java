package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Supporting interface for source class implements feature
interface SourceInterface {
    default void normalMethod() {} // For override_violation
}

// Parent class for call_method feature
class ParentClass {
    // call_method: type=parent_class, modifier=protected, normal, instanceReference.methodName, pos=switch
    protected List<String> parentMethod(TargetClass target, int val) {
        List<String> result = new ArrayList<>();
        // pos: switch statement
        switch (val) {
            case 1:
                // instanceReference.methodName(arguments)
                result.add(target.getTargetField());
                break;
            case 3:
                result.add(target.getTargetField() + "_3");
                break;
            default:
                result.add("default");
        }
        return result;
    }
}

// Private normal source class (same package, implements + anonymous inner + member inner class)
@RefactorAnnotation
private class SourceClass extends ParentClass implements SourceInterface {
    // Target field reference (per_condition: source contains target field)
    private TargetClass targetFieldRef;

    // Member inner class (source feature)
    class SourceMemberInner {
        public void updateTarget(TargetClass target) {
            target.setTargetField("updated_by_inner");
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            try {
                processTarget(new TargetClass());
            } catch (IllegalArgumentException e) {
                // no_new_exception
                e.printStackTrace();
            }
        }
    };

    // Override violation (invalid override attempt)
    @Override
    public void normalMethod() {
        // Intentionally invalid override simulation (return type mismatch in logic)
        super.normalMethod();
    }

    // Normal method (private access, TargetClass Type return, source position)
    @RefactorAnnotation // has_annotation feature
    private TargetClass processTarget(TargetClass targetParam) {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            // throw statement
            throw new IllegalArgumentException("Target parameter cannot be null");
        }
        targetFieldRef = targetParam;

        // Enhanced for statement
        String[] values = {"val1", "val2", "val3"};
        for (String val : values) {
            // variable call
            targetFieldRef.setTargetField(targetFieldRef.getTargetField() + "_" + val);
        }

        // ExpressionStatement (static modifier, obj.field, 3, pos: same_package_target)
        staticExpressionStatement(targetFieldRef);

        // numbers=1, protected modifier, exp=ParenthesizedExpression
        protectedParenthesizedExpression(1);

        // Try statement
        try {
            // call_method invocation
            List<String> callResult = this.parentMethod(targetFieldRef, 3);
            targetFieldRef.setTargetField(targetFieldRef.getTargetField() + "_" + callResult.get(0));
        } catch (Exception e) {
            // no_new_exception
            targetFieldRef.setTargetField("fallback_value");
        }

        return targetFieldRef;
    }

    // Static ExpressionStatement method (same_package_target pos)
    private static void staticExpressionStatement(TargetClass target) {
        // obj.field + 3 feature
        target.targetField = target.getTargetField() + "_3"; // Direct field access (obj.field)
    }

    // Protected ParenthesizedExpression method (numbers=1)
    protected void protectedParenthesizedExpression(int num) {
        // ParenthesizedExpression with number 1
        int result = (num * 1) + 1;
        targetFieldRef.setTargetField(targetFieldRef.getTargetField() + "_" + result);
    }
}

// Default modifier target normal class (no target features)
class TargetClass {
    // Target field for per_condition (direct access for obj.field feature)
    String targetField = "default_target_value";

    // Variable call support methods
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}