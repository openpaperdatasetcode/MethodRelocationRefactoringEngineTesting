package com.refactor;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Source class: normal, private modifier, same package as target, permits (via sealed hierarchy) + static nested + member inner class
sealed class SourceClass permits SourceSubClass {
    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Member inner class (source_inner - method position)
    class SourceInner {
        // Field for ExpressionStatement (this.field, 2)
        private int innerField = 2;

        // Method to refactor: varargs, List<String> return, protected, contains target parameter (per_condition)
        protected List<String> methodToMove(TargetClass... targetParams) {
            // ExpressionStatement (private modifier, this.field, 2, pos: source)
            private void exprStatement() {
                this.innerField = 2;
            }
            exprStatement(); // Execute expression statement

            // Break statement
            loopLabel:
            for (int i = 0; i < 5; i++) {
                if (i == 1) break loopLabel;
            }

            // Numbers:1, final modifier, exp:Name
            final String name = "target1";

            // Variable call (target parameter access)
            List<String> result = new ArrayList<>();
            for (TargetClass target : targetParams) {
                result.add(target.innerRecInstance.innerField);
                // Access instance method
                target.innerRecInstance.innerMethod();
            }

            // Call method: target type, private modifier, constructor + lambda in ternary operators
            String callResult = (targetParams.length > 0) 
                ? new TargetClass.TargetInnerRec(() -> targetParams[0].innerRecInstance.innerField).getResult() 
                : "default";
            result.add(callResult);

            // Return statement
            return result;
        }
    }

    // Member inner class (additional source feature)
    class SourceMemberInner {}
}

// Permits subclass for source class feature
non-sealed class SourceSubClass extends SourceClass {}

/**
 * TargetClass Javadoc (target_feature: javadoc)
 * Private normal class with local inner class and inner recursive class
 */
private class TargetClass {
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        String innerField = "targetValue";
        private final Function<Void, String> lambda;

        // Call method: private modifier, constructor + lambda (parameters) -> methodBody
        private TargetInnerRec(Function<Void, String> lambda) {
            this.lambda = lambda;
        }

        public String getResult() {
            return lambda.apply(null);
        }

        public void innerMethod() {}
    }

    // Inner recursive class instance
    TargetInnerRec innerRecInstance = new TargetInnerRec((v) -> "default");

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            void localMethod() {}
        }
        new TargetLocalInner().localMethod();
    }
}