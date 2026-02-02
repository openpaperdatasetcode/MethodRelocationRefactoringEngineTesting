/**
 * Target enum Javadoc (target_feature: javadoc)
 */
// Target enum class (default modifier, same package as source)
enum TargetEnum {
    VALUE1, VALUE2;

    // Anonymous inner class (target_feature)
    private Runnable anonymousTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    private int counter = 0;

    // Instance methods for variable call/access
    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public void executeTask() {
        anonymousTask.run();
    }
}

// Parent class for call_method (parent_class type)
class ParentClass {
    // Final method (call_method modifier: final), normal type, superTypeReference.methodName(arguments)
    final String processEnum(TargetEnum target) {
        return "Processed: " + target.name() + "_" + target.getCounter();
    }
}

// Source enum class (public modifier, same package as target)
public enum SourceEnum {
    INSTANCE;

    // Field of target enum (satisfies per_condition)
    private TargetEnum targetField = TargetEnum.VALUE1;

    // First member inner class (source_class feature)
    class FirstInnerClass {}

    // Second member inner class (source_class feature)
    class SecondInnerClass {}

    // Constructor with call_method in parameter list (pos: constructor parameter list)
    SourceEnum() {
        // superTypeReference.methodName(arguments) - call_method target_feature
        String initValue = new ParentClass().processEnum(this.targetField);
        System.out.println(initValue);
    }

    // Instance method to be moved (private, base type return, source position)
    private int processTarget() {
        // Private instance method (method_feature: 1, target, instance, new ClassName().method(), pos: do-while, void return)
        private void helperMethod() {
            int val = 1; // method_feature: 1
            // method_feature: target + new ClassName().method()
            new TargetEnum().executeTask(); // method_feature: instance
            targetField.incrementCounter();
        }

        int count = 0;
        // Do-while statement (pos for helperMethod)
        do {
            helperMethod();
            // Access instance method of target
            targetField.incrementCounter();
            // Variable call
            count = targetField.getCounter();
        } while (count < 3);

        // Return statement
        return count; // Base type return (int)
    }
}