package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Parent class for overriding feature
class SourceParentClass {
    public Object processTarget(List<TargetClass.TargetInnerRec> targetList) {
        return new ArrayList<>();
    }
}

// Target class (normal class, empty modifier, local inner class feature)
class TargetClass {
    int targetField = 1; // For SuperConstructorInvocation this.field + 1

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        String innerField;
        TargetInnerRec nestedInner; // Recursive feature

        public TargetInnerRec() {
            // Local inner class (target_feature)
            class TargetLocalInner {
                void updateField(TargetInnerRec inner) {
                    inner.innerField = "local_inner_" + 1;
                }
            }
            new TargetLocalInner().updateField(this);
        }
    }
}

// Source class (normal class, public modifier, same package, member inner + anonymous inner class)
public class SourceClass extends SourceParentClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        protected void superConstructorHelper(TargetClass.TargetInnerRec target) { // protected modifier
            target.innerField = TargetClass.this.targetField + "_super_" + 1; // this.field + 1
        }
    }

    // Overriding method (Object return, default access, source position)
    @Override
    public Object processTarget(List<TargetClass.TargetInnerRec> targetList) { // method types parameter is:List
        // Per_condition: contains target parameter (target_inner_rec)
        if (targetList == null || targetList.isEmpty()) {
            return new ArrayList<>();
        }

        // Type declaration statement
        TargetClass.TargetInnerRec targetParam = targetList.get(0);
        SourceMemberInner inner = new SourceMemberInner();

        // SuperConstructorInvocation (protected modifier, this.field, 1, pos=same_package_target)
        TargetClass targetOuter = new TargetClass() {
            {
                inner.superConstructorHelper(targetParam); // same_package_target position
            }
        };

        // Variable call (access target inner field - per_condition)
        String varCall = targetParam.innerField;
        targetParam.innerField = (varCall == null ? "" : varCall) + "_var_modified";

        // Assert statement
        assert targetParam.innerField != null : "Inner field cannot be null";

        // Anonymous inner class (source_feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                if (targetParam.nestedInner == null) {
                    targetParam.nestedInner = new TargetClass().new TargetInnerRec();
                }
                targetParam.nestedInner.innerField = targetParam.innerField + "_nested";
            }
        };
        anonymousRunnable.run();

        // No new exception
        return targetList;
    }
}