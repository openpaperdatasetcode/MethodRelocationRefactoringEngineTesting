import java.util.ArrayList;

sealed record SourceClass(String privateField) permits SubSourceRecord {
    // Member inner class in record
    class MemberInnerSourceClass {
        /**
         * Overloading method for target field processing
         * Handles target record instance with raw type usage
         */
        void sourceInnerMethod() {
            // Access outer private field from record component
            String varCall = SourceClass.this.privateField;
            // Type declaration statement with raw type
            ArrayList rawList = new ArrayList();
            
            int count = 0;
            while (count < 5) {
                try {
                    // Local inner class
                    class LocalInnerSourceClass {
                        void processTarget() {
                            // Use outer this to access target field
                            SourceClass.this.targetField.toString();
                        }
                    }
                    new LocalInnerSourceClass().processTarget();
                    rawList.add(varCall);
                    count++;
                } catch (Exception e) {
                    // No new exception thrown
                    count++;
                }
            }
        }

        // Overloading method (matches overloading type)
        void sourceInnerMethod(TargetClass targetParam) {
            sourceInnerMethod(); // Call overloaded variant
            varCall = targetParam.innerClass().toString();
        }
    }

    // Target field in source record (per_condition: source contains target field)
    private final TargetClass targetField = new TargetClass("test", 1);

    // Record constructor
    public SourceClass {
        new MemberInnerSourceClass().sourceInnerMethod(targetField);
    }
}

// Permitted subclass for sealed record
non-sealed record SubSourceRecord(String privateField) extends SourceClass(privateField) {}

/**
 * Public record class with javadoc and member inner class
 * @param data1 String component
 * @param data2 int component
 */
public record TargetClass(String data1, int data2) {
    // Member inner class in target record
    class InnerClass {
        public String getCombined() {
            return data1 + data2;
        }

        @Override
        public String toString() {
            return getCombined();
        }
    }

    // Accessor for inner class
    public InnerClass innerClass() {
        return new InnerClass();
    }
}