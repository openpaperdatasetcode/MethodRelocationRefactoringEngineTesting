package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Others class for instance method feature
class OthersClass {
    List<String> othersMethod() {
        return new ArrayList<>();
    }
}

// Source normal class (private modifier, same package, type parameter + two anonymous inner classes)
private class SourceClass<T extends CharSequence> {
    // First anonymous inner class (source feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("anonymous1");
        }
    };

    // Second anonymous inner class (source feature, duplicated)
    Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("anonymous2");
        }
    };

    // Varargs method to be moved (default access, returns TargetClass type, source position)
    TargetClass moveableMethod(TargetClass targetParam, T... varargs) {
        // VariableDeclarationExpression (public modifier, number 1 feature)
        public int varDeclExpr = 1;

        // Instance method feature (private modifier, if/else pos, 1 + others_class + instance + new ClassName().method())
        private List<String> instanceHelperMethod() {
            if (varDeclExpr == 1) { // Number 1 feature
                // new ClassName().method() call (others_class + instance)
                return new OthersClass().othersMethod();
            } else {
                return new ArrayList<>();
            }
        }

        // Uses_outer_this feature (access outer class this in inner context)
        Runnable outerThisRunnable = new Runnable() {
            @Override
            public void run() {
                SourceClass.this.anonymousInner1.run(); // uses_outer_this
            }
        };
        outerThisRunnable.run();

        // Variable call feature
        String varCall = targetParam.innerRec.field() + varDeclExpr + instanceHelperMethod().size();
        
        // No new exception instantiation (no_new_exception feature)
        System.out.println(varCall);

        // Return TargetClass type (target_inner_rec)
        return targetParam.innerRec;
    }
}

// Target normal class (protected modifier, anonymous inner class target feature)
protected class TargetClass {
    // Inner record class for target_inner_rec reference
    protected record InnerRec(String field) {}

    // InnerRec instance
    InnerRec innerRec = new InnerRec("target_rec_field");

    // Anonymous inner class (target feature)
    {
        Runnable targetAnonymousInner = new Runnable() {
            @Override
            public void run() {
                innerRec = new InnerRec("updated_field");
            }
        };
        targetAnonymousInner.run();
    }
}