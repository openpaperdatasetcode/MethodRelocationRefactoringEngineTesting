package com.refactoring.movemethod;

// Strictfp record source class (same package as target)
strictfp record SourceRecord(String sourceField) {
    // Source contains target class field (satisfies per_condition)
    private final TargetRecord targetField = new TargetRecord("target_data");

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Inner class containing the target method (source_inner position)
    public class SourceInnerClass {
        // Private outer field for access_outer_private feature
        private String outerPrivateField = "outer_private_value";

        /**
         * Javadoc for the normal method to be refactored
         * @param targetParam parameter of target record class type
         */
        void targetMethod(TargetRecord targetParam) {
            // Type declaration statement
            String localVar;
            int numVar;

            // Variable call
            localVar = outerPrivateField;
            numVar = targetParam.data().length();

            // Access outer private field
            String privateVal = SourceInnerClass.this.outerPrivateField;

            // Super constructor invocation (for inner class)
            super();

            // NullPointerException (no new exception - check null)
            if (targetParam == null) {
                throw new NullPointerException("Target parameter cannot be null");
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                void helper() {
                    localVar += SourceRecord.this.sourceField();
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();
            localInner.helper();

            // No new exception (no instantiation of new Exception)
        }

        // Overload method (overload_exist feature)
        void targetMethod(String arg) {
            // Overload implementation
        }
    }
}

// Default modifier record target class (implements interface + member inner class)
record TargetRecord(String data) implements Runnable {
    // Member inner class (target feature)
    public class TargetMemberInner {
        private String innerData = data;
    }

    @Override
    public void run() {
        // Implements Runnable interface
    }
}