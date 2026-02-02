package com.refactoring.movemethod;

import java.util.function.Consumer;

// Source record class: strictfp modifier, same package, member inner + static nested classes
strictfp record SourceRecord(String sourceField, int sourceInt) {
    // per_condition: source contains field of target
    private final TargetRecord targetField = new TargetRecord("target_6106", 6106);

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private int nestedStaticField = 1; // For ForStatement target_feature "1"
    }

    // Member inner class (source feature) - method_position: source_inner
    private class SourceInnerClass {
        private String innerField = "sourceInnerField"; // For ForStatement target_feature "this.field"

        // Custom annotation for has_annotation feature
        @CustomAnnotation
        @Override
        private Integer methodToMove() { // overriding, base type return (Integer), private access
            // ForStatement feature (type: ForStatement, modifier: private, pos: inner class)
            private void forStatementLogic() {
                SourceStaticNested staticNested = new SourceStaticNested();
                // ForStatement with this.field and "1" target features
                for (int i = 0; i < staticNested.nestedStaticField; i++) { // target_feature "1"
                    this.innerField = "updated_" + i; // target_feature "this.field"
                }
            }
            forStatementLogic();

            // variable call feature
            String varCall = this.innerField;
            int processedVal = varCall.length() + targetField.targetInt();

            // overload_exist feature (overloaded method exists)
            methodToMove(processedVal);

            // no_new_exception (no explicit new Exception instantiation)
            if (processedVal < 0) {
                return 0;
            }

            return processedVal;
        }

        // Overloaded method (overload_exist feature)
        private Integer methodToMove(int param) {
            return param * 2;
        }
    }

    // Override parent method (for overriding type of the target method)
    @Override
    public String toString() {
        return new SourceInnerClass().methodToMove().toString();
    }
}

// Custom annotation for has_annotation feature
@interface CustomAnnotation {}

// Target record class: public modifier, anonymous inner class (target feature)
public record TargetRecord(String targetField, int targetInt) {
    // Anonymous inner class (target feature)
    private final Consumer<String> anonymousProcessor = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Processed: " + s + "_" + targetInt);
        }
    };

    public void process() {
        anonymousProcessor.accept(targetField);
    }
}