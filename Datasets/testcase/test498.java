// Source package (different from target)
package com.refactor.source;

import com.refactor.target.TargetRecord;

// Source record class (public modifier, different package, static nested + local inner class)
public record SourceRecord(String id, TargetRecord targetField) { // per_condition: contains target class field

    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        int nestedValue = 1;
        public int getNestedValue() { return nestedValue; }
    }

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Instance method to refactor (protected access, base type param, returns Object, source_inner position)
        protected Object refactorMethod(int baseTypeParam) { // method types parameter is:base type
            // Variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            int varCall = staticNested.getNestedValue();
            varCall += baseTypeParam;

            // Local inner class (source_class feature)
            class SourceLocalInner {
                // Super constructor invocation feature (extends Object implicitly)
                SourceLocalInner() {
                    super(); // super constructor invocation
                }

                Object processTarget(TargetRecord target) {
                    // ThrowStatement (private modifier, ClassName.field=1, pos: diff_package_others)
                    private void throwStatement() {
                        if (target == null) {
                            // ClassName.field target_feature (TargetRecord.STATIC_FIELD = 1)
                            throw new IllegalArgumentException("Target field: " + TargetRecord.STATIC_FIELD);
                        }
                    }

                    try {
                        throwStatement();
                        return target.data() + varCall;
                    } catch (IllegalArgumentException e) {
                        // No_new_exception feature (no explicit throw new Exception)
                        return e.getMessage();
                    }
                }
            }

            SourceLocalInner localInner = new SourceLocalInner();
            return localInner.processTarget(SourceRecord.this.targetField);
        }
    }
}

// Target package (different from source)
package com.refactor.target;

// Target record class (default modifier, static nested class target_feature)
record TargetRecord(String data) {
    // ClassName.field (value 1 for ThrowStatement target_feature)
    public static final int STATIC_FIELD = 1;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        String nestedData = "target_nested";
        public String getNestedData() { return nestedData; }
    }

    // Constructor using static nested class
    public TargetRecord {
        TargetStaticNested nested = new TargetStaticNested();
        data = data + "_" + nested.getNestedData();
    }
}