package com.refactor;

import java.lang.reflect.Method;

// Source class: normal, protected modifier, same package as target, type parameter + member inner + anonymous inner class
protected class SourceClass<T> {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_inner - method position)
    class SourceInner {
        // Method to refactor: varargs, returns TargetClass type, protected access, in source_inner
        protected TargetClass methodToMove(T... varargsParams) {
            // Expression statement
            int exprVar = varargsParams.length;
            exprVar += 1;

            // Used by reflection
            try {
                Method method = SourceInner.class.getDeclaredMethod("methodToMove", Object[].class);
                method.setAccessible(true);
                method.invoke(this, (Object) varargsParams);
            } catch (Exception e) {
                // No new exception thrown
            }

            // Variable call (target field access)
            String targetVar = targetField.innerRecInstance.innerField;

            // Access instance method
            targetField.innerRecInstance.innerMethod();

            // Anonymous inner class (source feature)
            Runnable anonymousInner = new Runnable() {
                @Override
                public void run() {
                    SourceInner.this.methodToMove(varargsParams);
                }
            };

            // No new exception thrown
            return targetField;
        }
    }

    // Additional member inner class (source feature extension)
    class AnotherSourceInner {}
}

// Target class: normal, strictfp modifier, local inner class (target_feature)
strictfp class TargetClass {
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        String innerField = "targetInnerRecValue";

        void innerMethod() {}
    }

    // Inner recursive class instance (for variable call/access instance method)
    TargetInnerRec innerRecInstance = new TargetInnerRec();

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            void localMethod() {}
        }
        new TargetLocalInner().localMethod();
    }
}