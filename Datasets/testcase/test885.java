// Different package for ConstructorInvocation pos=diff_package_target
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageClass {
    // ConstructorInvocation (private modifier, obj.field, 2, pos=diff_package_target)
    private DiffPackageClass(TargetClass.TargetInner obj) {
        obj.innerField = 2; // obj.field + 2 from target_feature
    }

    // call_method (type:others_class, modifier:private, target_feature:instance + super.methodName())
    private TargetClass.TargetInner callMethod(TargetClass.TargetInner param) {
        super.toString(); // super.methodName()
        param.innerField += 2;
        return param; // instance feature
    }
}

// Main package
package com.refactoring.test;
import com.refactoring.others.DiffPackageClass;

// Parent class for target class extends feature
class TargetParentClass {
    protected int parentField = 2;
}

// Source class (protected modifier, same package, anonymous inner + member inner class)
protected abstract class SourceClass { // Abstract for abstract method type
    // Member inner class (source feature)
    class SourceMemberInner {
        // Inner class for method_position:source_inner
        abstract class SourceInnerClass {
            // Method to be refactored (abstract, base type return, strictfp access)
            public strictfp abstract int moveMethod(TargetClass.TargetInner targetParam);

            // Concrete implementation (for abstract method context)
            static class SourceInnerConcrete extends SourceInnerClass {
                @Override
                public strictfp int moveMethod(TargetClass.TargetInner targetParam) {
                public strictfp int moveMethod(TargetClass.TargetInner targetParam) {
                    // Per_condition: contains target parameter
                    if (targetParam == null) {
                        return 0;
                    }

                    // ConstructorInvocation (pos:diff_package_target)
                    new DiffPackageClass(targetParam); // obj.field = 2

                    // Variable call
                    int varCall = targetParam.innerField; // obj.field from ConstructorInvocation

                    // Expression with call_method (pos:expression)
                    TargetClass.TargetInner processedTarget = new DiffPackageClass().callMethod(targetParam);

                    // Return statement
                    return processedTarget.innerField + varCall; // base type return

                    // No new exception
                }
            }
        }
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };
}

// Target class (protected modifier, extends + member inner class)
protected class TargetClass extends TargetParentClass {
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For variable call/ConstructorInvocation obj.field

        // Member inner class (target_feature)
        class TargetMemberInner {
            void updateField(int value) {
                innerField = value;
            }
        }
    }
}