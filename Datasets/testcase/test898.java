package refactoring.test;

import java.io.IOException;

// Same package others class for VariableDeclarationStatement pos
class SamePackageOthers {
    public static int helperField = 1; // obj.field + 1 for VariableDeclarationStatement
}

// Target class: final modifier, empty target_feature, same package as source
final class TargetClass {
    public int targetField = 1; // obj.field + 1 for VariableDeclarationStatement

    public TargetClass() {}

    public int getTargetField() {
        return targetField;
    }
}

// Super class for super constructor invocation
class SourceSuperClass {
    protected SourceSuperClass() {}
}

// Source class: protected modifier, two member inner classes (source_feature), same package as target
protected class SourceClass extends SourceSuperClass {
    // First member inner class (source_feature)
    class FirstMemberInner {
        public String innerData = "firstInner";
    }

    // Second member inner class (source_feature)
    class SecondMemberInner {
        // Inner recursive class (source_inner_rec - method_position)
        class SourceInnerRecursive {
            // VariableDeclarationStatement feature: public modifier, obj.field, 1, pos=same_package_others
            public void variableDeclarationFeature(TargetClass obj) {
                int localVar = obj.targetField + SamePackageOthers.helperField; // same_package_others pos, obj.field + 1
            }

            // Instance method: default access, void return type, target parameter (per_condition)
            void refactorMethod(TargetClass targetParam) {
                // Super constructor invocation (method feature)
                SourceClass source = new SourceClass();

                // Variable call feature
                int varCall = targetParam.getTargetField();

                // Synchronized statement (method feature)
                synchronized (this) {
                    FirstMemberInner firstInner = new FirstMemberInner();
                    System.out.println(firstInner.innerData);
                }

                // Switch statement (method feature)
                switch (varCall) {
                    case 1:
                        try {
                            // IOException feature + requires_try_catch
                            throw new IOException("Test IO exception");
                        } catch (IOException e) {
                            // Throw statement feature
                            throw new RuntimeException(e);
                        }
                    default:
                        break;
                }

                // Execute VariableDeclarationStatement feature
                variableDeclarationFeature(targetParam);

                // Requires try-catch handling for throw statement
                try {
                    if (varCall != 1) {
                        throw new IllegalArgumentException("Invalid target field value");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}