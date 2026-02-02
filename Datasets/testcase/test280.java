package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source enum class (public modifier, same package, local inner + anonymous inner class)
public enum SourceEnum {
    INSTANCE;

    /**
     * Static method to be moved (public, returns List<String>, source position)
     * @param targetParam Target enum parameter (satisfies pre-condition)
     * @return List<String> result
     */
    public static List<String> moveableMethod(TargetEnum targetParam) {
        List<String> result = new ArrayList<>();

        // Local inner class (source feature)
        class SourceLocalInner {
            String localField = "source_local";
        }
        SourceLocalInner sourceLocal = new SourceLocalInner();

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetParam.innerRec.field());
            }
        };
        anonymousInner.run();

        // Constructor invocation feature
        ArrayList rawList = new ArrayList(); // Raw type feature

        // If statement feature
        if (targetParam.innerRec.field().length() > 0) {
            // Variable call feature
            String varCall = sourceLocal.localField + targetParam.innerRec.field();
            rawList.add(varCall);
        }

        // No new exception instantiation (no_new_exception feature)
        result.addAll(rawList);
        return result;
    }
}

// Target enum class (protected modifier, local inner class target feature)
protected enum TargetEnum {
    TARGET_INSTANCE;

    // Inner record class for target_inner_rec reference
    protected record InnerRec(String field) {}

    // InnerRec instance
    InnerRec innerRec = new InnerRec("target_rec_field");

    // Local inner class (target feature)
    public void targetMethod() {
        class TargetLocalInner {
            int localInt = 10;
        }
        TargetLocalInner targetLocal = new TargetLocalInner();
        System.out.println(targetLocal.localInt);
    }
}