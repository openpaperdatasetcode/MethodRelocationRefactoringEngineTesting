package com.refactoring.movemethod;

import java.io.IOException;

// Source class: normal, default modifier, same package, two local inner classes
class SourceClass {
    // per_condition: source contains field of target
    private AbstractTargetClass targetField = new TargetClassImpl();

    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outer_private_6115";

    // Member inner class (method_position: source_inner)
    protected class SourceInnerClass {
        private String innerField = "inner_field_1"; // For VariableDeclarationStatement target_feature "this.field"

        // Method to be refactored: instance, void return, protected access, source_inner
        protected void methodToMove() throws IOException { // requires_throws (IOException)
            // super constructor invocation
            super();

            // First local inner class (source feature)
            class FirstLocalInner {
                private int localVal = 1; // For VariableDeclarationStatement target_feature "1"
                void process(SourceInnerClass inner) {
                    innerField = "processed_by_first_local";
                }
            }

            // Second local inner class (source feature)
            class SecondLocalInner {
                void validate(String val) throws IOException {
                    if (val == null) throw new IOException("Null value");
                }
            }

            // VariableDeclarationStatement feature (type: VariableDeclarationStatement, modifier: protected, pos: same_package_target)
            protected void varDeclStatementLogic() {
                // target_feature: this.field
                this.innerField = "updated_inner_field";
                // target_feature: "1"
                int count = 1;
                // VariableDeclarationStatement
                String processedVal = this.innerField + "_" + count;
                targetField.getStaticNested().nestedField = processedVal;
            }
            varDeclStatementLogic();

            // variable call feature
            String varCall = this.innerField;

            // access_outer_private feature
            String outerPrivateVal = SourceClass.this.outerPrivateField;

            // switch statement
            switch (outerPrivateVal.length()) {
                case 1:
                    varCall += "_case1";
                    break; // break statement
                case 2:
                    varCall += "_case2";
                    break;
                default:
                    varCall += "_default";
                    break;
            }

            // otherObject.process(this); feature
            FirstLocalInner firstLocal = new FirstLocalInner();
            firstLocal.process(this);

            // requires_throws: call method that throws checked exception
            SecondLocalInner secondLocal = new SecondLocalInner();
            secondLocal.validate(varCall);
        }
    }
}

// Target class: abstract, static nested class (target_feature)
abstract class AbstractTargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        String nestedField;
    }

    public abstract TargetStaticNested getStaticNested();
}

// Concrete implementation of abstract target class (same_package_target for VariableDeclarationStatement pos)
class TargetClassImpl extends AbstractTargetClass {
    private TargetStaticNested staticNested = new TargetStaticNested();

    @Override
    public TargetStaticNested getStaticNested() {
        return staticNested;
    }
}