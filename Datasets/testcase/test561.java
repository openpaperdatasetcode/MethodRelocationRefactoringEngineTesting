package com.refactor;

import com.other.DiffPackageSuperClass;

// Source class: abstract, non-sealed modifier, same package as target, member inner + anonymous inner class
non-sealed abstract class SourceClass extends DiffPackageSuperClass {
    // Private field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // Member inner class (source feature)
    class SourceMemberInner {}

    // Method to refactor: instance, Object return, default modifier, contains target parameter (per_condition)
    Object methodToMove(TargetClass targetParam) {
        // VariableDeclarationStatement (private modifier, super.field, 1, pos: diff_package_others)
        private int superFieldAccess = super.diffPackageSuperField;
        superFieldAccess = 1;

        // Switch case
        int switchVar = 1;
        switch (switchVar) {
            case 1:
                variableCall(targetParam);
                break;
            default:
                break;
        }

        // Try statement wrapping throw statement (no new exception thrown - declared exception)
        try {
            if (targetParam == null) {
                // Throw statement (predefined exception, no new exception instantiated)
                throw new IllegalArgumentException("Target parameter cannot be null");
            }
        } catch (IllegalArgumentException e) {
            // No new exception thrown
            return e.getMessage();
        }

        // Variable call (target parameter access)
        String targetVar = targetParam.innerClassInstance.innerField;

        // Access outer private field (access_outer_private)
        String privateAccess = SourceClass.this.outerPrivateField;

        // Raw type usage
        TargetClass rawTarget = new TargetClass();

        // Uses outer this
        SourceClass outerThis = SourceClass.this;
        outerThis.outerPrivateField = privateAccess + "_modified";

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                SourceClass.this.methodToMove(targetParam);
            }
        };

        // No new exception thrown
        return new Object[] {targetVar, privateAccess, superFieldAccess};
    }

    // Variable call helper method
    private void variableCall(TargetClass param) {}
}

// Target class: abstract, public modifier, anonymous inner class (target_feature)
public abstract class TargetClass {
    // Member inner class (target_inner - target class for method)
    class TargetInner {
        String innerField = "targetInnerFieldValue";
    }

    // Inner class instance for variable call
    TargetInner innerClassInstance = new TargetInner();

    // Anonymous inner class (target_feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(innerClassInstance.innerField);
        }
    };
}

// Diff package super class for VariableDeclarationStatement pos: diff_package_others
package com.other;
public class DiffPackageSuperClass {
    public int diffPackageSuperField = 1;
}