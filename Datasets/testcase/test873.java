package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Source record class (public modifier, same package, local inner + static nested class)
public record SourceRecord(String recordField) {
    // Static field for depends_on_static_field feature
    public static int staticField = 2;

    // Static nested class (source feature)
    public static class SourceStaticNested {
        // Inner recursive class (source_inner_rec)
        public class SourceInnerRecursive {
            // Base method for overriding
            public TargetRecord moveMethod(int baseParam) {
                return new TargetRecord("default");
            }

            // Method to be refactored (overriding, TargetRecord return, public access)
            @Override
            public TargetRecord moveMethod(int baseParam) { // method types parameter is:base type
                // Per_condition: source contains target's field
                TargetRecord target = new TargetRecord("source_owned");
                String targetField = target.data();

                // ExpressionStatement (private modifier, ClassName.field, 2, pos=source)
                private void expressionStatement() {
                    SourceRecord.staticField = 2; // ClassName.field + 2 from target_feature
                    target = new TargetRecord(String.valueOf(staticField));
                }
                expressionStatement();

                // Empty statement feature
                ;

                // Variable call
                int varCall = baseParam + SourceRecord.staticField;
                target = new TargetRecord(targetField + "_" + varCall);

                // Raw type feature
                List rawList = new ArrayList(); // raw type
                rawList.add(target.data());

                // Depends_on_static_field
                target = new TargetRecord(target.data() + "_static_" + SourceRecord.staticField);

                // If/else conditions with call_method (pos:if/else conditions)
                if (varCall > 0) {
                    target.callMethod(2); // this.methodName(arguments)
                } else {
                    target.callMethod("fallback"); // overloading feature
                }

                // No new exception
                return target;
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                void validateTarget(TargetRecord target) {
                    if (target.data() == null) throw new IllegalArgumentException();
                }
            }
        }
    }
}

// Target record class (public modifier, local inner class feature)
public record TargetRecord(String data) {
    // call_method (target type, private modifier, overloading + this.methodName(arguments))
    private void callMethod(int param) {
        // this.methodName(arguments)
        this.processData(String.valueOf(param));
    }

    // Overloading method for call_method
    private void callMethod(String param) {
        this.processData(param);
    }

    // Helper method for call_method
    private void processData(String value) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String modify(String input) {
                return input + "_modified";
            }
        }
        TargetLocalInner inner = new TargetLocalInner();
        new TargetRecord(inner.modify(this.data()));
    }
}