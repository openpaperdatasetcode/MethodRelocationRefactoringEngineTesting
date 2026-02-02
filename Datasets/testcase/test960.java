package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Super class for overriding method
abstract class SuperClass<T> {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "PROTECTED_VALUE_1";

    public abstract Object processTarget(T target) throws Exception;
}

// Source normal class (default modifier, same package, type parameter + two member inner classes)
class SourceClass<T extends TargetClass> extends SuperClass<T> {
    // Target field reference (per_condition: source contains target field)
    private TargetClass targetFieldRef;

    // First member inner class (source feature)
    class SourceMemberInner1 {
        // IfStatement (public modifier, obj.field, 2, pos: inner class)
        public void checkTargetField(TargetClass target) {
            // obj.field access + 2 feature
            if (target.targetField != null && target.targetField.contains("2")) {
                target.targetField = target.targetField + "_updated_2";
            } else {
                target.targetField = "default_2";
            }
        }
    }

    // Second member inner class (source feature)
    class SourceMemberInner2 {
        // Accessor method helper
        public String getTargetValue(TargetClass target) {
            return target.getTargetField(); // Accessor call
        }
    }

    @RefactorAnnotation // has_annotation feature
    // Overriding method (public access, Object return, source position)
    @Override
    public Object processTarget(T targetParam) throws Exception {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            throw new Exception("Target parameter cannot be null"); // requires_throws
        }
        targetFieldRef = targetParam;

        // Raw type usage (raw_type feature)
        TargetClass.TargetMemberInner rawInner = new TargetClass().new TargetMemberInner(); // Raw type

        // Access_outer_protected: access super class's protected field
        String protectedVal = super.outerProtectedField;
        targetFieldRef.setTargetField(targetFieldRef.getTargetField() + "_" + protectedVal);

        // Object initialization (pos for accessor method)
        TargetClass initTarget = new TargetClass();
        List<String> accessorResult = protectedAccessorMethod(initTarget, 1);

        // Use first member inner class (IfStatement pos: inner class)
        new SourceMemberInner1().checkTargetField(targetFieldRef);

        // Variable call: access target's member inner class
        targetFieldRef.new TargetMemberInner().updateInnerField(accessorResult.get(0));

        return targetFieldRef;
    }

    // Accessor method (protected modifier, List<String> return, object initialization pos)
    protected List<String> protectedAccessorMethod(TargetClass target, int num) {
        List<String> result = new ArrayList<>();
        // 1 feature (literal value)
        if (num == 1) {
            // instanceReference.methodName(arguments) + accessor feature
            String val = new SourceMemberInner2().getTargetValue(target); // Accessor call
            result.add(val + "_1");
            // Target accessor call (instanceReference.methodName)
            result.add(target.getTargetField()); // Accessor method
        }
        return result;
    }
}

// Target normal class (default modifier, member inner class feature)
class TargetClass {
    // Target field (direct access for obj.field feature)
    String targetField = "default_target";

    // Member inner class (target feature)
    public class TargetMemberInner {
        private String innerField;

        public void updateInnerField(String val) {
            this.innerField = val;
        }

        public String getInnerField() {
            return innerField;
        }
    }

    // Accessor methods (accessor feature)
    public String getTargetField() { // Getter (accessor)
        return targetField;
    }

    public void setTargetField(String targetField) { // Setter (accessor)
        this.targetField = targetField;
    }
}