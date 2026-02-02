package com.refactoring.test;

/**
 * Target enum class (sealed modifier, javadoc + anonymous inner class features)
 * Sealed enum requires permits (empty for simplicity in test case)
 */
public sealed enum TargetClass permits {
    INSTANCE;

    int targetField; // For per_condition (source contains this field)

    // Anonymous inner class (target_feature)
    private final Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };

    public void invokeAnonymous() {
        targetAnonymous.run();
    }
}

/**
 * Source enum class (public modifier, same package, member inner + static nested class features)
 */
public enum SourceClass {
    INSTANCE;

    // Static nested class (source_feature)
    static class SourceStaticNested {
        static int helperVal = 3; // For ExpressionStatement target_feature "3"
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // ExpressionStatement (private modifier, this.field, 3, pos=source)
        private void expressionStatement(TargetClass target) {
            target.targetField = 3; // this.field (target.field) + 3 from target_feature
        }

        // Method to be refactored (instance, void return, default access, source_inner position)
        void moveMethod(TargetClass targetParam) throws IllegalStateException { // requires_throws
            // Per_condition: source contains the field of the target
            if (targetParam == null) {
                throw new IllegalStateException("Target parameter cannot be null"); // requires_throws
            }

            // ExpressionStatement invocation (pos=source)
            expressionStatement(targetParam);

            // Super constructor invocation (enum super is Enum)
            super.toString();

            // Synchronized statement
            synchronized (this) {
                targetParam.targetField += SourceStaticNested.helperVal;
            }

            // ThisExpression (numbers:1, modifier:protected, exp:ThisExpression)
            protected void thisExpressionMethod() {
                this.moveMethod(targetParam); // ThisExpression
                targetParam.targetField = 1; // 1 from numbers feature
            }
            thisExpressionMethod();

            // Variable call (access target field - per_condition)
            int varCall = targetParam.targetField;
            targetParam.targetField = varCall + 1;

            // Invoke target's anonymous inner class
            targetParam.invokeAnonymous();

            // Requires throws (only declared exception, no new exception thrown)
        }
    }
}