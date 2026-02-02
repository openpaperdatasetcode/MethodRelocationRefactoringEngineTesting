package refactoring.test;

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * Protected normal class with static nested class and inner recursive structure
 */
// Target class: normal, protected modifier, javadoc + static nested class features
protected class TargetClass {
    String value;
    int counter = 1;
    TargetInnerRecursive innerRecursive;

    // Constructor for constructor invocation feature
    public TargetClass(String value) {
        this.value = value;
        this.innerRecursive = new TargetInnerRecursive();
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 1;

        // For super.methodName() feature in constructor logic
        protected int getNestedValue() {
            return nestedValue;
        }
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        String recursiveValue;
        TargetRecursiveInner recursiveInner = new TargetRecursiveInner();

        // Recursive inner structure
        public class TargetRecursiveInner {
            int innerCounter = 1;
        }
    }

    // For overload_exist feature
    public void updateValue(String value) {
        this.value = value;
    }

    // Overloaded method (overload_exist feature)
    public void updateValue(String value, int counter) {
        this.value = value;
        this.counter = counter;
    }
}

// Subclass for method_feature: sub_class
class TargetSubClass extends TargetClass {
    public TargetSubClass(String value) {
        super(value);
    }

    // For super.methodName() feature
    @Override
    public void updateValue(String value) {
        super.updateValue(value);
    }

    // Constructor helper method (returns base type)
    public int initCounter() {
        return 1; // 1 (method_feature)
    }
}

// Source abstract class: normal (abstract), same package, double static nested classes
abstract class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass("source_init");

    // First static nested class (source feature)
    static class SourceStaticNestedOne {
        int value = 1;
    }

    // Second static nested class (source feature - duplicate)
    static class SourceStaticNestedTwo {
        String data;
    }

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        /**
         * Varargs method to be refactored (final access, TargetClass return, all specified features)
         * @param args varargs parameters
         * @return TargetClass instance
         */
        @SuppressWarnings("unused") // has_annotation
        public final TargetClass refactorMethod(String... args) { // varargs type + final access
            // Constructor invocation (target class)
            TargetClass newTarget = new TargetClass("constructor_invoked");

            // Variable call (target inner recursive class field)
            targetField.innerRecursive.recursiveValue = "refactor_value";
            targetField.innerRecursive.recursiveInner.innerCounter = 1;

            // Assert statement
            assert targetField.counter == 1 : "Counter must be 1";

            // Exception throwing statements with constructor logic call (pos: exception throwing statements)
            try {
                if (args.length == 0) throw new IllegalArgumentException("No args provided");
                int ctorResult = constructorHelper(args); // constructor type method call
                targetField.counter = ctorResult;
            } catch (IllegalArgumentException e) {
                // No new exception
                targetField.value = "error_" + e.getMessage();
            }

            // overload_exist feature (call overloaded method)
            targetField.updateValue("overload_updated");
            targetField.updateValue("overload_updated_with_counter", 1);

            // No new exception, return TargetClass type
            return targetField;
        }

        /**
         * Constructor type helper method (method_feature: 1/sub_class/constructor/super.methodName())
         * @param args varargs parameters
         * @return base type (int)
         */
        default int constructorHelper(String... args) { // default modifier + constructor type
            // sub_class feature: instantiate target subclass
            TargetSubClass subClass = new TargetSubClass(args[0]);
            // super.methodName() feature: call super class method
            subClass.updateValue(super.toString() + "_1"); // 1 (method_feature)
            // constructor feature: use static nested class constructor
            TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
            // return base type
            return subClass.initCounter() + staticNested.getNestedValue();
        }

        // Overloaded method for overload_exist feature (source side)
        public final TargetClass refactorMethod(int param, String... args) {
            return refactorMethod(args);
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}