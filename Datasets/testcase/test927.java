package com.refactoring.test;

// Parent class for method_feature "parent_class"
class TargetParentClass {
    protected void parentMethod(TargetClass target) {
        target.targetField = "parent_" + 3;
    }
}

// Interface for source class implements feature
interface SourceInterface {
    void interfaceMethod();
}

// Target class (normal class, abstract modifier, member inner class feature)
abstract class TargetClass extends TargetParentClass {
    String targetField; // For per_condition and variable call

    // Member inner class (target_feature)
    public class TargetMemberInner {
        void updateField(String value) {
            TargetClass.this.targetField = value;
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractTargetMethod();
}

// Source class (normal class, abstract modifier, same package, implements + two static nested classes)
abstract class SourceClass implements SourceInterface {
    // First static nested class (source_feature)
    static class SourceStaticNestedOne {}
    // Second static nested class (source_feature)
    static class SourceStaticNestedTwo {}

    // Instance method (final modifier, method_feature:3/parent_class/instance/instanceReference::methodName, pos:if/else conditions, return void)
    final void instanceMethod(TargetClass target) {
        // if/else conditions position
        if (target.targetField == null) {
            // instanceReference::methodName (method reference)
            Runnable methodRef = target::parentMethod; // parent_class feature
            methodRef.run();
            target.targetField = "value_" + 3; // 3 from method_feature
        } else {
            target.targetField += "_modified_" + 3;
        }
    }

    // Method to be refactored (varargs, TargetClass return, default access, source position)
    TargetClass moveMethod(TargetClass... targetParams) {
        // Per_condition: contains target parameter (varargs)
        if (targetParams == null || targetParams.length == 0) {
            return new TargetClass() {
                @Override
                public void abstractTargetMethod() {}
            };
        }

        // Type declaration statement
        SourceStaticNestedOne nestedOne = new SourceStaticNestedOne();
        TargetClass.TargetMemberInner targetInner = targetParams[0].new TargetMemberInner();

        // Instance method call (pos:if/else conditions)
        for (TargetClass target : targetParams) {
            if (target.targetField == null) {
                instanceMethod(target);
            } else {
                instanceMethod(target);
            }
        }

        // Variable call
        TargetClass targetParam = targetParams[0];
        String varCall = targetParam.targetField; // Access target field (per_condition)
        targetParam.targetField = varCall + "_var_call";

        // No new exception
        return targetParam;
    }

    // Implements interface method (required for implements feature)
    @Override
    public void interfaceMethod() {}

    // Abstract method (required for abstract class)
    public abstract void abstractSourceMethod();
}