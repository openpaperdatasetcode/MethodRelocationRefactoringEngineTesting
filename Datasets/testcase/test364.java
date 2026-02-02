package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for overriding feature
interface RefactorInterface {
    TargetClass overrideMethod(TargetClass targetParam);
}

// Source abstract class (protected modifier, same package, local inner + member inner class)
protected abstract class SourceClass implements RefactorInterface {
    // Member inner class (source feature)
    protected class SourceMemberInner {
        String innerField = "source_inner";
    }

    /**
     * Overriding method to be moved (private access, returns TargetClass type, source position)
     * @param targetParam Target class parameter (satisfies pre-condition)
     * @return TargetClass instance
     */
    @RefactorAnnotation
    @Override
    private TargetClass overrideMethod(TargetClass targetParam) {
        // Local inner class (source feature)
        class SourceLocalInner {
            int localField = 1;
        }
        SourceLocalInner localInner = new SourceLocalInner();

        // Enhanced for statement feature
        List<String> items = Arrays.asList("item1", "item2", "item3");
        for (String item : items) {
            // BreakStatement (public modifier, source pos, obj.field + 1 features)
            public int breakTrigger = targetParam.targetInnerRec.field + localInner.localField;
            if (item.equals("item2")) {
                break; // BreakStatement with obj.field (targetInnerRec.field) + 1 (localField)
            }
        }

        // Try statement feature (no_new_exception - no new exception instantiation)
        try {
            // Variable call feature
            String varCall = targetParam.targetInnerRec.field + new SourceMemberInner().innerField;
            System.out.println(varCall);
        } catch (Exception e) {
            // Empty catch block (no_new_exception satisfied)
        }

        // Return TargetClass type (target_inner_rec)
        return targetParam.targetInnerRec;
    }
}

// Target abstract class (non-sealed modifier, same package, member inner class target feature)
non-sealed abstract class TargetClass {
    // Inner record class for target_inner_rec reference
    protected record TargetInnerRec(String field) {}

    // Member inner class (target feature)
    protected class TargetMemberInner {
        int innerInt = 10;
    }

    // TargetInnerRec instance (obj.field source)
    TargetInnerRec targetInnerRec = new TargetInnerRec("target_rec_field");

    // Abstract method (required for abstract class)
    protected abstract void abstractTargetMethod();
}