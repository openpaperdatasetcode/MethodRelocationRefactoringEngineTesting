package com.refactoring.movemethod;

import diffpackage.DiffPackageHelper;
import java.lang.reflect.Method;

// Different package class for VariableDeclarationStatement pos=diff_package_others
package diffpackage;
public class DiffPackageHelper {
    public static int helperValue = 3;
}

package com.refactoring.movemethod;

// Source class: generic class, private modifier, same package, anonymous inner class, static nested class
private class SourceClass<T extends CharSequence> {
    // per_condition: source contains target class field
    private TargetClass<Integer> targetField = new TargetClass<>();
    private String sourceField = "sourceValue";

    // Static nested class (source feature)
    static class StaticNestedSource<U> {
        U nestedField;
    }

    // Method: overloading, return Object, public access, source position
    public Object moveableMethod() {
        // Variable call feature
        String localVar = sourceField;
        localVar = targetField.targetField;

        // VariableDeclarationStatement: protected modifier, this.field, 3, pos=diff_package_others
        protected void declareVariable() {
            int num = diffpackage.DiffPackageHelper.helperValue; // target_feature "3", pos=diff_package_others
            localVar = SourceClass.this.sourceField; // target_feature "this.field"
        }
        declareVariable();

        // Break statement feature
        outerLoop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 3) break outerLoop;
                localVar += j;
            }
        }

        // Synchronized statement feature
        synchronized (targetField) {
            localVar = "synchronizedValue";
        }

        // While statement feature
        int counter = 0;
        while (counter < 3) {
            localVar += counter++;
        }

        // Expression statement feature
        sourceField = localVar.toUpperCase();
        targetField.targetField = "updatedTargetValue";

        // Super keywords feature
        class SuperClass extends SourceClass<String> {
            void useSuper() {
                super.sourceField = "superAssignedValue";
            }
        }
        new SuperClass().useSuper();

        // Override_violation feature (attempt to override final method)
        class OverrideViolation extends TargetClass<Integer> {
            @Override
            public final void finalMethod() {} // Compile error (override violation)
        }

        // Uses_outer_this feature
        String outerThisVal = SourceClass.this.sourceField;

        // Used_by_reflection feature
        try {
            Method method = this.getClass().getDeclaredMethod("moveableMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // no_new_exception feature (no custom exceptions instantiated)
            return localVar;
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                localVar = "anonymousValue";
            }
        };
        anonymousRunnable.run();

        // no_new_exception feature (no custom exceptions thrown)
        return targetField;
    }

    // Overloading method (overloading type feature)
    public Object moveableMethod(String param) {
        // Variable call feature
        String localVar = param + targetField.targetField;
        
        // no_new_exception feature
        return localVar;
    }
}

// Target class: generic class, public modifier, target_feature: javadoc, local inner class
/**
 * Javadoc feature for TargetClass
 * Generic class with local inner class feature
 * @param <V> generic type parameter
 */
public class TargetClass<V> {
    String targetField = "targetValue";

    // Final method for override_violation feature
    public final void finalMethod() {}

    // Local inner class (target_feature)
    void methodWithLocalInner() {
        class LocalInnerTarget {
            V innerField;
            int value = 3;
        }
        LocalInnerTarget localInner = new LocalInnerTarget();
    }
}