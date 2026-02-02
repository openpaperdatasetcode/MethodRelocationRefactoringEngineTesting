package com.refactoring.movemethod;

import java.util.function.Consumer;

// Source record class: protected modifier, same package, local inner + static nested classes
protected record SourceRecord(String sourceField) {
    // Static nested class (source feature)
    private static class SourceStaticNested {
        private int staticNestedField = 2; // For DoStatement target_feature "2"
    }

    // Method to be refactored: instance, TargetClass Type return, default access, source position
    TargetRecord methodToMove(TargetRecord targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // Super constructor invocation (record implicit super call)
            super();
            return new TargetRecord("default_target_6113");
        }

        // access_instance_field feature
        String instanceFieldVal = this.sourceField;

        // variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        int varCall = staticNested.staticNestedField;

        // switch case feature
        switch (varCall) {
            case 2:
                instanceFieldVal += "_case2";
                break;
            default:
                instanceFieldVal += "_default";
                break;
        }

        // DoStatement feature (type: DoStatement, modifier: private, target_feature: obj.field + "2", pos: source)
        private void doStatementLogic() {
            int count = 0;
            do {
                // target_feature: obj.field (targetParam's field)
                targetParam = targetParam.withUpdatedField(instanceFieldVal + "_" + count);
                count++;
            } while (count < staticNested.staticNestedField); // target_feature: "2"
        }
        doStatementLogic();

        // Local inner class (source feature) - declared in refactored method
        class SourceLocalInner {
            private void processTarget(TargetRecord record) {
                record.anonymousProcessor.accept(record.targetField());
            }
        }
        new SourceLocalInner().processTarget(targetParam);

        // no_new_exception (no explicit new Exception instantiation)
        return targetParam;
    }
}

// Target record class: private modifier, javadoc + anonymous inner class (target_features)
/**
 * Javadoc for TargetRecord (target_feature: javadoc)
 * ID: 6113
 */
private record TargetRecord(String targetField) {
    // Anonymous inner class (target_feature)
    final Consumer<String> anonymousProcessor = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Processed target field: " + s);
        }
    };

    // Factory method to update field (for obj.field access)
    TargetRecord withUpdatedField(String newField) {
        return new TargetRecord(newField);
    }
}