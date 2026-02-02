package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Annotation for call_method pos: attribute values of annotations
@interface CallerAnnotation {
    String value() default "com.refactoring.movemethod.SourceClass.methodToRefactor";
}

// Source class: protected, two member inner classes, same package as target
protected class SourceClass {
    // Instance field for access_instance_field feature
    private String instanceField = "sourceInstanceField";

    // First member inner class (source class feature)
    class FirstInnerClass {
        int innerField1 = 10;
    }

    // Second member inner class (source class feature)
    class SecondInnerClass {
        int innerField2 = 20;
    }

    // Method to refactor: instance, Object return, private, in source class
    // Per_condition: contains target class parameter
    private Object methodToRefactor(TargetClass targetParam) {
        // Variable call (target parameter's inner class field)
        String targetValue = targetParam.targetInnerClass.innerValue;
        
        // Expression statement
        targetValue += this.instanceField;
        
        // Access instance field
        String fieldValue = this.instanceField;
        
        // With_bounds feature (generic with bounds)
        class BoundedGeneric<T extends Number & Comparable<T>> {
            T value;
        }
        BoundedGeneric<Integer> boundedObj = new BoundedGeneric<>();
        boundedObj.value = 100;
        targetValue += boundedObj.value.toString();
        
        // Continue statement (within loop)
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        for (int num : numbers) {
            if (num == 2) {
                continue; // continue statement
            }
            targetValue += num;
        }
        
        // Assert statement
        assert targetValue.length() > 0 : "Target value is empty";
        
        // Used by reflection feature
        try {
            Method method = TargetClass.TargetInnerClass.class.getDeclaredField("innerValue");
            method.setAccessible(true);
            targetValue += method.get(targetParam.targetInnerClass).toString();
        } catch (Exception e) {
            // No new exception feature (no throw new exception)
            targetValue = "reflection_error";
        }
        
        // No new exception feature
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetValue = "parse_error";
        }
        
        return targetValue;
    }
}

// Target class: default modifier, member inner class feature, same package as source
class TargetClass {
    // Member inner class (target class feature)
    public class TargetInnerClass {
        String innerValue = "targetInnerValue";
    }

    // Instance of inner class (used in source class variable call)
    TargetInnerClass targetInnerClass = new TargetInnerClass();
}

// Others class for call_method
@CallerAnnotation(SourceClass.class.getSimpleName() + ".methodToRefactor(targetParam)") // pos: annotation attribute values
class CallerClass {
    // Base method for overriding feature
    public TargetClass callMethod() {
        SourceClass source = new SourceClass();
        TargetClass target = new TargetClass();
        // ClassName.methodName(arguments) feature (via reflection for private method)
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToRefactor", TargetClass.class);
            method.setAccessible(true);
            method.invoke(source, target);
        } catch (Exception e) {
            // No new exception
        }
        return target;
    }
}

// Overriding class for call_method's overriding feature
class OverriddenCallerClass extends CallerClass {
    @Override
    public TargetClass callMethod() { // overriding feature
        SourceClass source = new SourceClass();
        TargetClass target = new TargetClass();
        // ClassName.methodName(arguments) (direct call via reflection)
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToRefactor", TargetClass.class);
            method.setAccessible(true);
            method.invoke(source, target);
        } catch (Exception e) {
            // No new exception
        }
        return target;
    }
}