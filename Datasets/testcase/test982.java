package com.refactoring.test;

// Different package for ContinueStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // ContinueStatement (static modifier, super.field, 1, pos=diff_package_others)
    public static void continueStatement(TargetClass target) {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                target.parentField = 1; // super.field + 1 from target_feature
                continue;
            }
            target.targetField += "_continue_" + i;
        }
    }
}

// Main package
package com.refactoring.test;
import com.refactoring.others.DiffPackageOthers;

// Parent class for super.field feature
class TargetParentClass {
    protected int parentField = 0;
}

// Target class (normal class, default modifier, static nested class feature)
class TargetClass extends TargetParentClass {
    String targetField; // For per_condition and variable call

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        // call_method (public modifier, static + new ClassName().method(), pos=while, return TargetClass Type)
        public TargetClass staticMethod() {
            TargetClass target = new TargetClass();
            target.targetField = "static_method_result";
            return target;
        }
    }

    // Inner class for call_method type=inner_class
    public class TargetInnerClass {
        public TargetClass innerMethod() {
            return new TargetClass();
        }
    }
}

// Source class (normal class, public modifier, same package, static nested + member inner class)
public class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void helperMethod(TargetClass target) {
            target.targetField += "_static_nested";
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Method to be refactored (normal, void return, default access, source_inner_rec)
            void moveMethod(TargetClass targetParam) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    return;
                }

                // ContinueStatement (pos=diff_package_others)
                DiffPackageOthers.continueStatement(targetParam);

                // Break statement
                for (int i = 0; i < 5; i++) {
                    if (i == 1) { // 1 from target_feature
                        break;
                    }
                    targetParam.targetField += "_break_" + i;
                }

                // Switch case
                switch (targetParam.parentField) {
                    case 1: // 1 from target_feature
                        targetParam.targetField += "_switch_case_1";
                        break;
                    default:
                        targetParam.targetField += "_switch_default";
                        break;
                }

                // Expression statement
                targetParam.targetField = targetParam.targetField.toUpperCase();

                // Variable call (access target field - per_condition)
                String varCall = targetParam.targetField;
                targetParam.targetField = varCall + "_var_modified";

                // call_method (pos=while, static + new ClassName().method(), return TargetClass Type)
                int count = 0;
                while (count < 2) {
                    TargetClass calledTarget = new TargetClass.TargetStaticNested().staticMethod(); // new ClassName().method()
                    targetParam.targetField += "_" + calledTarget.targetField;
                    count++;
                }

                // Static nested class usage
                SourceStaticNested.helperMethod(targetParam);

                // No new exception
            }
        }
    }
}