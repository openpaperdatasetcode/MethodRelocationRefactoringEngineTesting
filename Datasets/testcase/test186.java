package com.refactoring.movemethod;

// Same package others class for SwitchStatement pos=same_package_others
class SamePackageOthers {
    public static int switchValue = 3;
}

// Super class for super.field and super keywords features
class SuperClass {
    protected String superField = "superFieldValue";
}

// Others class for overriding method_feature
class OthersClass {
    int baseMethod() {
        return 1;
    }
}

// Source class: generic class, public modifier, same package, member inner class (duplicate feature)
public class SourceClass<T extends SuperClass> {
    // Member inner class (source feature - duplicate)
    class MemberInnerSource1 {}

    // Member inner class (source feature - duplicate)
    class MemberInnerSource2 {}

    // Method: instance, return Object, default access, source position
    // per_condition: contains target class parameter (TargetClass<T>.InnerRecursiveTarget)
    Object moveableInstanceMethod(TargetClass<T>.InnerRecursiveTarget targetParam) {
        // Variable call feature
        String localVar = targetParam.innerField;
        localVar = SamePackageOthers.switchValue + "";

        // SwitchStatement: protected modifier, super.field, 3, pos=same_package_others
        protected void switchLogic(T instance) {
            switch (SamePackageOthers.switchValue) { // pos=same_package_others
                case 3: // target_feature "3"
                    localVar = instance.superField; // target_feature "super.field"
                    break;
                default:
                    localVar = "default";
            }
        }

        // Overriding feature: default modifier, method_feature [1, others_class, overriding, new ClassName().method()], pos=array initialization, return base type
        int[] intArray = new int[]{
            new OverridingClass().baseMethod() // new ClassName().method() & array initialization pos
        };

        // Constructor invocation feature
        TargetClass<T>.InnerRecursiveTarget newTargetInstance = new TargetClass<T>().new InnerRecursiveTarget();

        // Labeled statement feature
        label:
        for (int i = 0; i < 3; i++) {
            if (i == 1) break label;
            localVar += i;
        }

        // Super keywords feature
        T superInstance = (T) new SuperClass();
        localVar = superInstance.superField;

        // No_new_exception feature (no custom exceptions instantiated)
        return newTargetInstance;
    }

    // Overriding class for overriding feature
    class OverridingClass extends OthersClass {
        @Override
        default int baseMethod() { // return_type base type (int)
            return 1; // method_feature "1" (others_class, overriding)
        }
    }
}

// Target class: generic class, public modifier, target_feature: static nested class
public class TargetClass<U> {
    // Static nested class (target_feature)
    static class StaticNestedTarget<U> {
        U nestedField;
    }

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRecursiveValue";

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }
    }
}