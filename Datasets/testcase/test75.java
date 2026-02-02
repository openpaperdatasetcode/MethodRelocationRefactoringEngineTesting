package com.refactoring.movemethod;

import diffpackage.DiffPackageHelper;
import java.util.function.Function;

// Different package class for TryStatement pos=diff_package_others
package diffpackage;
public class DiffPackageHelper {
    public static int helperValue = 1;
}

package com.refactoring.movemethod;

// Super class for super.field and super constructor invocation features
class SuperSourceClass<T> {
    protected String superField = "superFieldValue";
    public SuperSourceClass() {}
}

// Others class for static method feature
class OthersClass {
    static <U> TargetClass<U> staticHelperMethod(TargetClass<U> target) {
        target.targetField = String.valueOf(1); // method_feature "1"
        return target;
    }
}

// Source class: generic class, protected modifier, same package, member inner class, local inner class
protected class SourceClass<T extends Number & Comparable<T>> extends SuperSourceClass<T> { // with_bounds feature
    // per_condition: source contains target class field
    private TargetClass<T> targetField = new TargetClass<>();

    // Member inner class (source feature)
    class MemberInnerSource {
        T innerField;
    }

    // Method: instance, return TargetClass Type, protected access, source position
    protected TargetClass<T> moveableInstanceMethod() {
        // Variable call feature
        String localVar = targetField.targetField;
        localVar = super.superField; // super.field reference

        // TryStatement: private modifier, super.field, 1, pos=diff_package_others
        private void tryStatementLogic() {
            try {
                if (diffpackage.DiffPackageHelper.helperValue == 1) { // target_feature "1", pos=diff_package_others
                    localVar = super.superField; // target_feature "super.field"
                }
            } catch (Exception e) {
                // no_new_exception feature (no custom exceptions instantiated)
                localVar = "default";
            }
        }
        tryStatementLogic();

        // Static feature: private modifier, method_feature [1, others_class, static, method ref], pos=if/else, return TargetClass Type
        private static <U extends Number> TargetClass<U> staticMethodLogic(TargetClass<U> target) {
            Function<TargetClass<U>, TargetClass<U>> func = OthersClass::staticHelperMethod; // instanceReference::methodName
            if (target != null) { // if/else conditions pos
                return func.apply(target); // method_feature "others_class", "static", "1"
            } else {
                return new TargetClass<>();
            }
        }
        TargetClass<T> staticResult = staticMethodLogic(targetField);

        // Type declaration statement feature
        class LocalTypeDeclaration<U extends T> { // with_bounds usage
            U typeField;
        }
        LocalTypeDeclaration<T> localType = new LocalTypeDeclaration<>();

        // Constructor invocation feature
        TargetClass<T> newTargetInstance = new TargetClass<>();
        newTargetInstance.targetField = localVar;

        // Super constructor invocation feature
        new SuperSourceClass<T>() {};

        // Local inner class (source feature)
        class LocalInnerSource {
            void localMethod() {
                newTargetInstance.innerClassMethod();
            }
        }
        new LocalInnerSource().localMethod();

        // no_new_exception feature (no custom exceptions thrown)
        return newTargetInstance;
    }
}

// Target class: generic class, default modifier, same package, target_feature: javadoc, member inner class
/**
 * Javadoc feature for TargetClass
 * Generic class with member inner class and type parameter bounds
 * @param <V> Generic type parameter (extends Number for with_bounds compatibility)
 */
class TargetClass<V extends Number> {
    V targetField;

    // Member inner class (target_feature)
    class MemberInnerTarget {
        V innerField;
        void innerMethod() {}
    }

    // Helper method for variable call
    void innerClassMethod() {
        new MemberInnerTarget().innerMethod();
    }
}