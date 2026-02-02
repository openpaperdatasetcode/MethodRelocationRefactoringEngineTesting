// Different package for IfStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // IfStatement (public modifier, ClassName.field, 3, pos=diff_package_others)
    public void ifStatement(TargetClass.TargetInner target) {
        if (TargetClass.staticField != 3) { // ClassName.field + 3 from target_feature
            TargetClass.staticField = 3;
            target.innerField = 3;
        }
    }
}

// Main package
package com.refactoring.test;
import com.refactoring.others.DiffPackageOthers;

// Target class (abstract class, private modifier, static nested class feature)
private abstract class TargetClass {
    static int staticField; // For IfStatement ClassName.field
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For variable call/per_condition
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Source class (abstract class, abstract modifier, same package, member inner + local inner class)
abstract class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Overload exist feature (overload_exist)
            private int moveMethod(TargetClass.TargetInner targetParam) {
                return moveMethod(targetParam, 3);
            }

            // Method to be refactored (instance, base type return, private access, source_inner_rec)
            private int moveMethod(TargetClass.TargetInner targetParam, int num) {
                // Per_condition: contains target parameter (targetParam)
                if (targetParam == null) {
                    return 0;
                }

                // IfStatement (pos=diff_package_others)
                new DiffPackageOthers().ifStatement(targetParam);

                // Super keywords
                Object superObj = new Object();
                String superStr = superObj.toString();

                // Variable call
                int varCall = targetParam.innerField; // Access target field (per_condition)
                targetParam.innerField = varCall + TargetClass.staticField;

                // Local inner class (source_feature)
                class LocalInnerClass {
                    int processValue(int val) {
                        return val + 3;
                    }
                }
                int processedVal = new LocalInnerClass().processValue(targetParam.innerField);

                // No new exception
                return processedVal; // Base type return (int)
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractSourceMethod();
}