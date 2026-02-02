package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.io.IOException;

// Source class: normal class, private modifier, same package as target
// Features: implements, member inner class, static nested class
private class SourceClass implements SampleInterface {
    // Per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();
    protected String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        public String innerMethod() {
            return SourceClass.this.outerProtectedField;
        }
    }

    // Static nested class (source_class feature)
    public static class StaticNestedClass {
        public static <T extends Number & Comparable<T>> T boundedMethod(T value) {
            return value;
        }
    }

    /**
     * Method Javadoc (method feature: method javadoc)
     * This is the method to be refactored for Move Method refactoring
     * @return TargetClass instance
     * @throws IOException if target field is null
     */
    // Instance method (type: instance), return TargetClass Type, protected access, position: source
    protected TargetClass moveCandidateMethod() throws IOException {
        // Variable call (method feature)
        MemberInnerClass innerObj = new MemberInnerClass();
        String varCall = innerObj.innerMethod();

        // Super constructor invocation (method feature)
        super();

        // Constructor invocation (method feature)
        TargetClass.MemberInnerClass targetInner = targetField.new MemberInnerClass();

        // Type declaration statement (method feature)
        TargetClass targetInstance;
        targetInstance = new TargetClass();

        // With bounds (method feature)
        Integer boundedValue = StaticNestedClass.boundedMethod(10);

        // Access outer protected (method feature)
        String protectedAccess = this.outerProtectedField + boundedValue.toString();

        // NullPointerException (method feature)
        if (protectedAccess == null) {
            throw new NullPointerException("Protected access value is null");
        }

        // Break statement (method feature)
        loop:
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break loop;
            }
            targetInstance.setData(i + protectedAccess);
        }

        // Used by reflection (method feature, duplicate as specified)
        try {
            Method targetMethod = TargetClass.class.getMethod("setData", String.class);
            targetMethod.invoke(targetInstance, "reflectionData");
            
            Constructor<TargetClass> targetConstructor = TargetClass.class.getConstructor();
            targetInstance = targetConstructor.newInstance();
        } catch (Exception e) {
            throw new IOException("Reflection error", e);
        }

        // Requires throws (method feature - declares throws IOException)
        if (targetField == null) {
            throw new IOException("Target field is null");
        }

        // Call others_class method in do-while (call_method pos: do-while)
        int count = 1;
        do {
            targetInstance = CallerClass.callMethod(targetInstance, count);
            count++;
        } while (count <= 3);

        return targetInstance;
    }
}

// Interface for source_class implements feature
interface SampleInterface {}

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * This is the target class for Move Method refactoring
 */
// Target class: normal class, protected modifier, target_feature: javadoc, member inner class
protected class TargetClass {
    private String data;

    // Member inner class (target_feature)
    public class MemberInnerClass {
        public String getTargetData() {
            return TargetClass.this.data;
        }
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

// Call method class: others_class, default modifier, target_feature: constructor, ClassName.methodName(arguments)
// pos: do-while, return_type: TargetClass Type
class CallerClass {
    public static TargetClass callMethod(TargetClass target, int count) {
        // Constructor (target_feature)
        TargetClass newTarget = new TargetClass();
        // ClassName.methodName(arguments) (target_feature)
        newTarget.setData(target.getData() + "_call_" + count);
        return newTarget;
    }
}