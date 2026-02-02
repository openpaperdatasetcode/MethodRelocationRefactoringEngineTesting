// Target enum package (different from source)
package com.target;

// Super class for super.field and super.methodName()
class EnumSuperClass {
    protected int superField = 1; // super.field + 1 for ContinueStatement
    
    protected Object superMethod(int value) {
        return "super_" + value;
    }
}

// Target abstract enum class: abstract modifier, anonymous inner class (target_feature)
abstract enum TargetEnum extends EnumSuperClass {
    TARGET_1, TARGET_2;

    // Target inner class (target_inner - target class of method)
    public class TargetInner {
        public int innerValue = 1;

        public int getInnerValue() {
            return innerValue;
        }
    }

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + superField);
        }
    };

    public abstract int process(int... args);

    public TargetInner createInner() {
        return new TargetInner();
    }
}

// Subclass of TargetEnum for call_method (sub_class type)
strictfp class TargetEnumSubClass extends EnumSuperClass {
    @Override
    public Object superMethod(int value) {
        return super.superMethod(value + 1); // super.methodName(arguments)
    }

    // strictfp modifier, instance feature, return Object
    public strictfp Object instanceMethod(TargetEnum.TargetInner inner, int... args) {
        int sum = 0;
        for (int arg : args) sum += arg;
        return sum + inner.getInnerValue();
    }
}

// Source enum package (different from target)
package com.source;

import com.target.TargetEnum;
import com.target.TargetEnumSubClass;

// Different package class for diff_package_others pos
package com.others;

public class OtherPackageClass {
    public static int processContinue(int val) {
        return val;
    }
}

// Back to source package
package com.source;

import com.target.TargetEnum;
import com.target.TargetEnumSubClass;
import com.others.OtherPackageClass;

// Source enum class: default modifier, member inner + static nested class (source_feature)
enum SourceEnum {
    SOURCE_1, SOURCE_2;

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static int staticHelper(int value) {
            return value * 1;
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public int getOuterThisValue() {
            return SourceEnum.this.ordinal(); // uses_outer_this feature
        }
    }

    // ContinueStatement feature: public modifier, super.field, 1, pos=diff_package_others
    public void continueStatementFeature(TargetEnum target) {
        for (int i = 0; i < 5; i++) {
            // diff_package_others pos
            int val = OtherPackageClass.processContinue(target.superField);
            if (val == 1) { // super.field + 1
                continue; // ContinueStatement
            }
            System.out.println("Continue skipped: " + i);
        }
    }

    // Varargs method: private access, base return type (int), target parameter (per_condition)
    private int refactorMethod(TargetEnum targetParam, int... args) {
        // uses_outer_this feature
        SourceMemberInner inner = new SourceMemberInner();
        int outerThisVal = inner.getOuterThisValue();

        // Variable call feature
        TargetEnum.TargetInner targetInner = targetParam.createInner();
        int varCall = targetInner.getInnerValue();

        // Expression statement feature
        int expr = varCall + args.length + outerThisVal;

        // Execute ContinueStatement feature
        continueStatementFeature(targetParam);

        // call_method: sub_class type, strictfp, instance + super.methodName(), pos=if/else, return Object
        TargetEnumSubClass subClass = new TargetEnumSubClass();
        Object callResult;
        if (expr > 0) { // if/else conditions pos
            callResult = subClass.instanceMethod(targetInner, args); // instance feature
        } else {
            callResult = subClass.superMethod(varCall); // super.methodName(arguments)
        }

        // No new exception thrown feature
        return expr + (int) callResult;
    }
}