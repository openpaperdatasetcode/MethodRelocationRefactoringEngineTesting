package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Target class (normal class, public modifier, static nested class feature)
public class TargetClass {
    String targetField; // For per_condition (source contains this field)
    
    // Static nested class (target_feature)
    public static class TargetStaticNested<T extends CharSequence> { // with_bounds for reference
        private T nestedField;
        
        // Final method for override_violation
        public final void finalMethod() {}
        
        public T getNestedField() {
            return nestedField;
        }
        
        public void setNestedField(T nestedField) {
            this.nestedField = nestedField;
        }
    }
}

// Source class (normal class, default modifier, same package, static nested + member inner class)
class SourceClass {
    private int instanceField = 42; // For access_instance_field feature
    
    // Static nested class (source_feature)
    static class SourceStaticNested<T extends Number> { // with_bounds feature
        T staticNestedField;
    }
    
    // Member inner class (source_feature)
    class SourceMemberInner {
        // Attempt to override final method (override_violation)
        class OverrideViolationClass extends TargetClass.TargetStaticNested<String> {
            @Override
            public void finalMethod() {} // Compile error (override_violation: final method override)
        }
    }
    
    // Method to be refactored (instance, List<String> return, protected access, source position)
    protected List<String> moveMethod(TargetClass targetParam) throws IllegalAccessException { // requires_throws
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            throw new IllegalAccessException("Target parameter cannot be null"); // requires_throws
        }
        
        // Empty statement feature
        ;
        
        // Access instance field
        int instVal = this.instanceField; // access_instance_field
        
        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField;
        if (varCall == null) {
            varCall = "default_value_" + instVal;
        }
        
        // With_bounds usage
        SourceStaticNested<Integer> sourceNested = new SourceStaticNested<>();
        sourceNested.staticNestedField = instVal;
        TargetClass.TargetStaticNested<String> targetNested = new TargetClass.TargetStaticNested<>();
        targetNested.setNestedField(varCall);
        
        // Prepare result
        List<String> result = new ArrayList<>();
        result.add(varCall);
        result.add(targetNested.getNestedField());
        result.add(String.valueOf(sourceNested.staticNestedField));
        
        // No new exception (only declared IllegalAccessException)
        return result;
    }
}