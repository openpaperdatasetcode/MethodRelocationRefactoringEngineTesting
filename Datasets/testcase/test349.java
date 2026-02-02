package com.refactoring.movemethod;

import java.lang.reflect.Field;
import java.sql.SQLException;

// Public normal source class (extends, two anonymous inner classes; same package as target)
public class SourceClass extends BaseClass {
    // Source contains target class field (satisfies per_condition)
    private TargetClass targetField = new TargetClass();
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "protected_value";

    // First anonymous inner class (source feature)
    private Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class");
        }
    };

    // Second anonymous inner class (source feature)
    private Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class");
        }
    };

    // Recursive inner class (source_inner_rec position)
    public class SourceInner {
        // Recursive inner class containing the target method
        public class RecursiveInner {
            // Instance method (protected access, base type return, target parameter)
            protected int instanceMethod(TargetClass targetParam) {
                // Type declaration statement
                int localVar;
                String strVar;

                // Variable call
                localVar = 0;
                strVar = outerProtectedField;

                // Access outer protected field
                String protectedVal = SourceClass.this.outerProtectedField;

                // Access target instance field
                localVar = targetParam.targetInstanceField;

                // ClassInstanceCreation with number 2, default modifier
                TargetClass[] targetInstances = new TargetClass[2];
                for (int i = 0; i < 2; i++) {
                    targetInstances[i] = new TargetClass(); // ClassInstanceCreation
                }

                // Continue statement
                loop:
                for (int i = 0; i < 5; i++) {
                    if (i == 2) {
                        continue loop;
                    }
                    localVar += i;
                }

                // SQLException (requires try-catch, no_new_exception)
                try {
                    if (targetParam == null) {
                        throw new SQLException("Target parameter is null");
                    }
                    // Used by reflection (access target instance field)
                    Field field = TargetClass.class.getDeclaredField("targetInstanceField");
                    field.setAccessible(true);
                    int reflectedVal = (int) field.get(targetField);
                    localVar += reflectedVal;
                } catch (SQLException | ReflectiveOperationException e) {
                    // No new exception (reuse existing, no new instantiation)
                    localVar = -1;
                }

                return localVar; // Base type return (int)
            }
        }
    }
}

// Base class for SourceClass extension
class BaseClass {}

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * This is the target class with instance field for access_instance_field feature
 */
public class TargetClass {
    // Instance field for access_instance_field feature
    public int targetInstanceField = 2;
}