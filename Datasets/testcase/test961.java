package com.refactoring.movemethod;

// Diff package others class (simulated same package for minimal structure)
class DiffPackageOthers {
    public static void processSynchronized(TargetRecord.TargetInner inner) {
        inner.setInnerField("sync_val_2");
    }
}

// Source record class (default modifier, same package, local inner + static nested class)
record SourceRecord(String sourceField) {
    // Static nested class (source feature)
    static class SourceStaticNested {
        public void updateTarget(TargetRecord.TargetInner inner) {
            inner.setInnerField(inner.getInnerField() + "_static_nested");
        }
    }

    // Overloading method 1 (protected access, TargetClass Type return)
    protected TargetRecord.TargetInner processTarget(TargetRecord.TargetInner targetParam) {
        return processTarget(targetParam, "default");
    }

    // Overloading method 2 (overloading type, source position)
    protected TargetRecord.TargetInner processTarget(TargetRecord.TargetInner targetParam, String defaultValue) {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            // Constructor invocation (target inner class)
            TargetRecord target = new TargetRecord("default_target");
            targetParam = target.new TargetInner(defaultValue);
        }

        // Super constructor invocation (records implicitly extend Record)
        super();

        // Local inner class (source feature)
        class SourceLocalInner {
            void syncUpdate(TargetRecord.TargetInner inner) {
                // Synchronized statement
                synchronized (inner) {
                    inner.setInnerField(inner.getInnerField() + "_local_inner");
                }
            }
        }

        // Variable call (target field access)
        String targetVal = targetParam.getInnerField();
        
        // SynchronizedStatement (private modifier, this.field, 2, pos: diff_package_others)
        privateSynchronizedStatement(targetParam);

        // Use local inner class
        new SourceLocalInner().syncUpdate(targetParam);
        
        // Use static nested class (variable call)
        new SourceStaticNested().updateTarget(targetParam);

        // No_new_exception (empty try-catch)
        try {
            targetParam.setInnerField(targetVal + "_processed_2"); // 2 feature
        } catch (Exception e) {
            // Do not throw new exception
            targetParam.setInnerField(defaultValue);
        }

        return targetParam;
    }

    // Private SynchronizedStatement method (diff_package_others pos)
    private void privateSynchronizedStatement(TargetRecord.TargetInner inner) {
        Object lock = new Object();
        synchronized (lock) {
            // diff_package_others position
            DiffPackageOthers.processSynchronized(inner);
            // this.field (inner class's outer this) + 2
            this.sourceField(); // Access record's field (this.field)
            inner.setInnerField(inner.getInnerField() + "_2"); // 2 feature
        }
    }
}

// Target record class (public modifier, member inner class feature)
public record TargetRecord(String targetField) {
    // Member inner class (target_inner)
    public class TargetInner {
        private String innerField; // Target field for per_condition

        // Constructor for constructor invocation feature
        public TargetInner(String val) {
            this.innerField = val;
        }

        // Variable call support methods
        public String getInnerField() {
            return innerField;
        }

        public void setInnerField(String innerField) {
            this.innerField = innerField;
        }
    }
}