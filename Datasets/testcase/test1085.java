package com.refactoring.test;

import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for Annotation expression feature (numbers:3, public modifier)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
    int value() default 3; // numbers:3
}

// Source class (public modifier, normal class, same package, implements feature)
public class SourceClass implements CustomInterface {
    // Implements feature - override interface method
    @Override
    public void interfaceMethod() {}

    // Method to be refactored (normal, Object return, private access, position: source)
    private Object targetMethod(SealedTargetClass param) { // per_condition: target parameter
        // Annotation expression (numbers:3, public modifier, exp:Annotation)
        @CustomAnnotation(3) 
        String annotatedVar = "annotated";

        // Super keywords usage
        super.toString();

        // Uses outer this
        SourceClass outerThis = SourceClass.this;

        // Variable call
        String targetValue = param.getValue();

        // Exception handling statements with normal method call (pos: exception handling statements)
        try {
            // obj.m1().m2().m3() (method_feature)
            outerThis.normalMethod(2).process1().process2().process3(); // 2 in method_feature, parent_class, normal type
        } catch (Exception e) {
            // No new exception (no_new_exception)
            System.err.println("Exception: " + e.getMessage());
        }

        // Used by reflection (duplicate feature as per requirements)
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", SealedTargetClass.class);
            method.setAccessible(true);
            method.invoke(this, param);
        } catch (Exception e) {
            System.err.println("Reflection error: " + e.getMessage());
        }

        // Used by reflection (second occurrence)
        try {
            Method normalMethod = SourceClass.class.getDeclaredMethod("normalMethod", int.class);
            normalMethod.setAccessible(true);
            normalMethod.invoke(this, 2);
        } catch (Exception e) {
            System.err.println("Reflection error 2: " + e.getMessage());
        }

        // No new exception (no_new_exception)
        return targetValue;
    }

    // Normal method (public modifier, void return, parent_class, 2 in method_feature)
    public ProcessChain normalMethod(int num) { // 2 in method_feature, normal type
        super.hashCode(); // parent_class feature
        return new ProcessChain();
    }

    // Helper class for obj.m1().m2().m3() chain call
    public static class ProcessChain {
        public ProcessChain process1() { return this; }
        public ProcessChain process2() { return this; }
        public ProcessChain process3() { return this; }
    }
}

// Interface for implements feature (source_class)
interface CustomInterface {
    void interfaceMethod();
}

// Target class (sealed modifier, normal class, javadoc, local inner class target_feature)
/**
 * SealedTargetClass (javadoc target_feature)
 * This is a sealed target class with local inner class feature
 */
public sealed class SealedTargetClass permits TargetSubClass {
    private String value = "targetValue";

    public String getValue() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String formatValue(String val) {
                return val + "_formatted";
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        return local.formatValue(value);
    }
}

// Permitted subclass for sealed target class
final class TargetSubClass extends SealedTargetClass {}