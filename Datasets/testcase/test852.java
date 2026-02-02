package refactoring.test;

import java.util.Objects;

// Source record class (default modifier, same package as target, features: two anonymous inner classes)
record SourceRecord(String id, int value) {
    // Per condition: source contains target class field
    AbstractTargetRecord targetField = new ConcreteTargetRecord("target-1", 99);

    // Local inner class (method_position: source_inner)
    class SourceInnerClass {
        /**
         * Javadoc for the varargs method (method feature: method javadoc)
         * @param args variable arguments
         * @return Object result
         */
        Object moveMethod(String... args) {
            // Variable call feature
            int counter = 0;
            // Access outer protected feature (access protected member of outer record)
            String protectedData = SourceRecord.this.protectedField;

            // Do statement feature
            do {
                counter++;
                // NullPointerException (implicit via Objects.requireNonNull, no new exception thrown)
                String arg = Objects.requireNonNull(args[counter - 1], "Argument cannot be null");
                // Super keywords (call super method of anonymous inner class)
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        super.toString(); // super keyword usage
                        System.out.println(arg);
                    }
                };
                runnable1.run();

                // Second anonymous inner class (source_class feature)
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(protectedData);
                    }
                };
                runnable2.run();

                // Another NullPointerException (implicit check)
                if (args[counter - 1] == null) {
                    throw new NullPointerException(); // NPE feature (no new custom exception)
                }
            } while (counter < args.length);

            return targetField.innerClass().process(protectedData);
        }
    }

    // Protected field for access_outer_protected feature
    protected String protectedField = "source-protected-data";
}

// Abstract target record class (abstract modifier, same package, target_feature: member inner class)
abstract sealed record AbstractTargetRecord(String key, long timestamp) permits ConcreteTargetRecord {
    // Member inner class (target_feature)
    class TargetMemberInnerClass {
        Object process(String data) {
            return data + "-processed-" + key;
        }
    }

    // Abstract method to return inner class instance
    abstract TargetMemberInnerClass innerClass();
}

// Concrete implementation of abstract target record
final record ConcreteTargetRecord(String key, long timestamp) extends AbstractTargetRecord {
    public ConcreteTargetRecord(String key, int value) {
        super(key, value);
    }

    @Override
    TargetMemberInnerClass innerClass() {
        return new TargetMemberInnerClass();
    }
}