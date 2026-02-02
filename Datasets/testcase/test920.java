package com.refactoring.test;

// Parent class for source_class extends feature and overriding method_feature "parent_class"
class SourceParentClass<T> {
    public Object processTarget(T target) {
        return target;
    }
}

// Source class (normal class, private modifier, same package, extends + two member inner classes)
private class SourceClass<T extends CharSequence> extends SourceParentClass<TargetClass> { // with_bounds (T extends CharSequence)
    // First member inner class (source_feature)
    class SourceMemberInnerOne {}
    
    // Second member inner class (source_feature)
    class SourceMemberInnerTwo {}

    // DoStatement (private modifier, this.field, 2, pos=source)
    private void doStatement(TargetClass target) {
        int count = 0;
        do {
            target.targetField = "value_" + 2; // this.field (target.field) + 2 from target_feature
            count++;
        } while (count < 2);
    }

    // Overriding method (public modifier, method_feature:1/parent_class/overriding/this.methodName(arguments), pos=exception throwing statements, return Object)
    @Override
    public Object processTarget(TargetClass target) {
        try {
            if (target == null) {
                throw new IllegalArgumentException("Target is null"); // exception throwing statements pos
            }
            return this.moveMethod(target); // this.methodName(arguments) + 1 (method count/value)
        } catch (IllegalArgumentException e) {
            return "default_" + 1; // 1 from method_feature
        }
    }

    // Method to be refactored (overriding, TargetClass return, final access, source position)
    @Override
    public final TargetClass moveMethod(TargetClass targetParam) { // overriding from parent, final access
        // Per_condition: contains target parameter
        if (targetParam == null) {
            // ClassInstanceCreation (numbers:2, modifier:default, exp:ClassInstanceCreation)
            TargetClass defaultTarget = new TargetClass(); // ClassInstanceCreation
            defaultTarget.targetField = "default_" + 2; // 2 from numbers
            return defaultTarget;
        }

        // DoStatement invocation
        doStatement(targetParam);

        // Variable call
        String varCall = targetParam.targetField; // Access target field (per_condition)
        targetParam.targetField = varCall + "_modified";

        // With_bounds usage (T extends CharSequence)
        T boundVal = (T) targetParam.targetField;
        targetParam.targetField = boundVal.toString() + "_bounded";

        // No new exception
        return targetParam;
    }
}

// Target class (normal class, protected modifier, empty target_feature)
protected class TargetClass {
    String targetField; // For variable call/per_condition
}