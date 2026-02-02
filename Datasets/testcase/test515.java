// Source record class (private modifier, same package as target)
private record SourceRecord(String sourceField, AbstractTargetRecord targetField) { // target field satisfies per_condition
    // Member inner class (source_class feature)
    public class MemberInnerClass {
        // Helper method for overload existence
        public void processTarget(AbstractTargetRecord target, String... args) {}
    }

    // Method to be moved (varargs, void return, public, source position)
    /**
     * Method Javadoc (method feature)
     * @param targetParam target class parameter
     * @param values varargs parameters
     */
    public void processTarget(AbstractTargetRecord targetParam, String... values) {
        int index = 0;
        for (String val : values) {
            index++;
            if (val.isEmpty()) {
                continue; // Continue statement (method feature)
            }
            // Variable call (method feature)
            targetParam.nestedStatic.printValue(val);
            // Lambda expression: () -> System.out.println(this.value) (method feature)
            Runnable lambda = () -> System.out.println(this.targetField.value());
            lambda.run();
        }
    }

    // Local inner class (source_class feature) - defined in a method
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printSourceField() {
                System.out.println(sourceField);
            }
        }
        new LocalInnerClass().printSourceField();
    }
}

// Target abstract record class (abstract modifier, same package as source)
abstract record AbstractTargetRecord(String value) {
    // Static nested class (target_feature)
    public static class NestedStaticClass {
        public void printValue(String val) {
            System.out.println(val);
        }
    }

    // Static nested class instance for variable call
    public static final NestedStaticClass nestedStatic = new NestedStaticClass();
}