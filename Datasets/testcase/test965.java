package com.refactoring.movemethod;

// Source record class (public modifier, same package, type parameter + local inner + static nested class)
public record SourceRecord<T extends CharSequence>(T sourceField) {
    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public void process(TargetRecord target) {
            // Helper method for variable call
            target.setAuxField("processed_by_static_nested");
        }
    }

    // Instance method (protected access, TargetClass Type return)
    protected TargetRecord processTarget(TargetRecord targetParam) {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            targetParam = new TargetRecord("default_target");
        }

        // Local inner class (source feature)
        class SourceLocalInner {
            void updateTarget(TargetRecord target) {
                // Expression statement
                String updatedVal = target.targetField().toUpperCase();
                target.setAuxField(updatedVal);
            }
        }

        // While statement
        int count = 0;
        while (count < 3) {
            // Variable call (target field/method access)
            String targetVal = targetParam.targetField();
            // Expression statement
            targetVal = targetVal + "_" + count;
            
            // Use local inner class
            new SourceLocalInner().updateTarget(targetParam);
            
            // Variable call (static nested class processing)
            new SourceStaticNested<>().process(targetParam);
            
            count++;
        }

        // No_new_exception (empty try-catch)
        try {
            // Additional variable call to confirm target access
            targetParam.setAuxField(targetParam.getAuxField() + "_finalized");
        } catch (Exception e) {
            // Do not throw new exception
            targetParam.setAuxField("error_fallback");
        }

        return targetParam;
    }
}

// Target record class (public modifier, anonymous inner class feature)
public record TargetRecord(String targetField) {
    // Auxiliary field for expression statement/variable call
    private String auxField;

    // Anonymous inner class (target feature)
    private final Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            // Variable call within anonymous inner class
            setAuxField(targetField() + "_anonymous_processed");
        }
    };

    // Getter/Setter for auxiliary field (variable call support)
    public String getAuxField() {
        return auxField;
    }

    public void setAuxField(String auxField) {
        this.auxField = auxField;
        // Trigger anonymous inner class logic
        targetAnonymousInner.run();
    }
}