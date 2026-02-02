package com.source;

import com.target.TargetClass;
import com.target.TargetClass.InnerRecord; // target_inner_rec

/**
 * Source Normal Class (public modifier, different package from target)
 * Features: local inner class, static nested class
 */
public class SourceClass {
    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        public static String format(String s) {
            return s + "_formatted";
        }
    }

    /**
     * Method Javadoc: Refactored instance method for 5668
     * @param innerParam Target class inner record parameter (target_inner_rec)
     * @return TargetClass instance (TargetClas Type)
     * @note Contains override violation (intentional compile hint)
     */
    // Method to be refactored (instance, TargetClass return, default access, position: source)
    TargetClass targetMethod(InnerRecord innerParam) { // per_condition: contains target parameter
        // Type declaration statement (feature)
        String targetFieldVal;
        int loopCount = 0;
        TargetClass newTarget;

        // Constructor invocation (feature: target class constructor)
        newTarget = new TargetClass("init_value_5668");

        // Variable call (feature: access target inner record field)
        targetFieldVal = innerParam.value();

        // WhileStatement (private modifier, obj.field, 1, pos: source)
        private void whileBlock() {
            String objField = innerParam.value(); // obj.field (target inner record field)
            int num = 1; // target_feature: 1
            while (loopCount < num) { // WhileStatement
                targetFieldVal = StaticNestedSourceClass.format(targetFieldVal);
                loopCount++;
            }
        }
        whileBlock();

        // Local inner class (source_class feature)
        class LocalInnerClass {
            void updateTarget(TargetClass target) {
                target.setValue(targetFieldVal);
            }
        }
        new LocalInnerClass().updateTarget(newTarget);

        // Override violation (feature: attempt to override final method)
        @Override // Compile error: Cannot override final method (override_violation)
        public final String toString() {
            return newTarget.getValue();
        }

        // No new exception (feature: no custom exception instantiation)
        return newTarget;
    }

    // Final method to trigger override violation
    public final String toString() {
        return "SourceClass";
    }
}
package com.target;

/**
 * Target Normal Class (default modifier, extends SuperTarget, static nested class)
 * Features: extends, static nested class
 */
class TargetClass extends SuperTargetClass { // target_feature: extends
    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static final String CONST = "TARGET_CONST";
    }

    // Inner record (target_inner_rec: target class inner record)
    public record InnerRecord(String value) {} // target_inner_rec

    private String value;

    // Constructor (for constructor invocation)
    public TargetClass(String value) {
        super(); // super constructor (from extends)
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

/**
 * Super class for TargetClass extends feature
 */
class SuperTargetClass {
    protected String superField = "super_field";
}