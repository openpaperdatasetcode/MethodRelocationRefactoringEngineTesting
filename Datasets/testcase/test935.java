package refactoring.test;

// Source class: normal, private modifier, same package as target, features: two local inner classes
private class SourceClass {
    // Per condition: source contains target class field
    TargetClass targetField = new TargetClass();
    // Instance field for access_instance_field feature
    private int instanceField = 3;

    // Method to be refactored: varargs, return Object, private access, position source
    // method types parameter is:none (no typed parameters beyond varargs)
    private Object moveMethod(Object... args) {
        // Variable call feature
        int localVar = 3;
        // Access instance field feature
        localVar += this.instanceField;

        // First local inner class (source_class feature)
        class FirstLocalInner {
            String process(Object obj) {
                return obj.toString() + "-processed-1";
            }
        }

        // Second local inner class (source_class feature)
        class SecondLocalInner {
            int count = 0;

            public SecondLocalInner() {
                super(); // Implicit super constructor invocation
                super(); // Implicit super constructor invocation
            }

            void increment() {
                count++;
            }
        }

        // Constructor invocation feature
        FirstLocalInner firstInner = new FirstLocalInner();
        SecondLocalInner secondInner = new SecondLocalInner();

        // Enhanced for statement feature
        for (Object arg : args) {
            secondInner.increment();
            // ThrowStatement feature (type: ThrowStatement, modifier: public, target_feature: super.field, 3, pos: source)
            if (arg == null) {
                throw new IllegalArgumentException("Argument cannot be null: " + SourceClass.this.instanceField);
            }
            localVar += secondInner.count;
        }

        // No new exception thrown (no_new_exception feature - only standard exception)
        Object result = firstInner.process(targetField.innerClass().getData(localVar));
        return result;
    }
}

// Target class: normal, default modifier, same package, target_feature: member inner class
class TargetClass {
    // Member inner class (target_feature)
    class TargetMemberInner {
        Object getData(int value) {
            return "target-data-" + value;
        }
    }

    public TargetMemberInner innerClass() {
        return new TargetMemberInner();
    }
}