package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source normal class (public modifier, same package, type parameter, two static nested classes)
public class SourceClass<T extends CharSequence> {
    // First static nested class (source feature)
    static class SourceStaticNested1 {}
    // Second static nested class (source feature)
    static class SourceStaticNested2 {}

    // Target field reference (per_condition: source contains target field)
    private TargetClass targetFieldRef;

    // Source inner class (method_position: source_inner)
    class SourceInnerClass extends SuperClass {
        // Overriding method (default access, List<String> return)
        @Override
        List<String> processTarget(TargetClass targetParam) {
            List<String> result = new ArrayList<>();
            
            // Variable call (target parameter/field access)
            targetFieldRef = targetParam;
            String targetVal = targetFieldRef.getTargetField();
            result.add(targetVal);

            // No_new_exception (empty try-catch)
            try {
                // No exception thrown
                result.add(targetParam.getTargetField().toUpperCase());
            } catch (Exception e) {
                // Do not throw new exception
                result.add("error");
            }

            return result;
        }
    }
}

// Super class for overriding method
abstract class SuperClass {
    abstract List<String> processTarget(TargetClass targetParam);
}

// Target normal class (protected modifier, no target features)
protected class TargetClass {
    // Target field for per_condition
    private String targetField = "default_target_value";

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}