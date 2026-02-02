package com.refactor;

import com.other.DiffPackageSuperClass;

// Source class: enum, non-sealed modifier, same package as target, member inner + anonymous inner class
non-sealed enum SourceEnum {
    INSTANCE;

    // Target class field reference (per_condition)
    private final TargetEnum targetField = TargetEnum.VALUE1;

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Method to refactor: instance, returns TargetEnum type, default access, in source_inner_rec
        TargetEnum methodToMove() {
            // VariableDeclarationStatement (private modifier, super.field, 1, pos: diff_package_others)
            private DiffPackageSuperClass diffPackageObj = new DiffPackageSuperClass();
            diffPackageObj.superField = 1; // super.field = 1 (diff package super class field)

            // Break statement
            loopLabel:
            for (int i = 0; i < 5; i++) {
                if (i == 1) break loopLabel;
            }

            // Synchronized statement
            synchronized (this) {
                // Variable call (target field access)
                String targetVar = targetField.innerField;
            }

            // Type declaration statement
            class LocalTypeDeclaration {}
            LocalTypeDeclaration localType = new LocalTypeDeclaration();

            // Numbers:1, private modifier, exp:SuperMethodInvocation
            private void superMethodCall() {
                SourceInnerRec.super.toString(); // SuperMethodInvocation with param 1 context
            }
            superMethodCall();

            // Super keywords usage
            super.hashCode();

            // Override violation (invalid method override - access modifier/return type mismatch)
            class OverrideViolationClass extends TargetEnum {
                // Override violation: parent method is public, here private + return type mismatch
                @Override
                private String interfaceMethod() { // Compile error (override violation)
                    return "violation";
                }
            }

            // Call method: others_class type, protected modifier, static + ClassName.methodName() in if/else
            TargetEnum callResult;
            if (diffPackageObj.superField == 1) {
                callResult = OthersClass.staticMethod(1);
            } else {
                callResult = TargetEnum.VALUE2;
            }

            // Anonymous inner class (source feature)
            Runnable anonymousInner = new Runnable() {
                @Override
                public void run() {
                    SourceInnerRec.this.methodToMove();
                }
            };

            // No new exception thrown
            return callResult;
        }
    }

    // Member inner class (source feature)
    class SourceMemberInner {}
}

// Target class: enum, public modifier, implements interface + local inner class (target_feature)
public enum TargetEnum implements SampleInterface {
    VALUE1("value1"), VALUE2("value2");

    public final String innerField;

    TargetEnum(String innerField) {
        this.innerField = innerField;
    }

    // Implements feature - interface method
    @Override
    public String interfaceMethod() {
        return this.innerField;
    }

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            void localMethod() {
                System.out.println(innerField);
            }
        }
        new TargetLocalInner().localMethod();
    }
}

// Interface for target enum implements feature
interface SampleInterface {
    String interfaceMethod();
}

// Others class for call_method feature
class OthersClass {
    // Call method: protected modifier, static, ClassName.methodName(arguments)
    protected static TargetEnum staticMethod(int param) {
        return param == 1 ? TargetEnum.VALUE1 : TargetEnum.VALUE2;
    }
}

// Diff package super class for VariableDeclarationStatement pos: diff_package_others
package com.other;
public class DiffPackageSuperClass {
    public int superField; // super.field for VariableDeclarationStatement
}