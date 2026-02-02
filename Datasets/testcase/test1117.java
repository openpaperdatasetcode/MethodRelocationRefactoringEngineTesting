package com.example;

// Super class for target_class extends feature
class SuperTargetClass<U> {
    protected String superField = "superTargetField";
}

// Source generic class (public modifier, generic, same package, member inner class, anonymous inner class)
public class SourceClass<T extends CharSequence> {
    // per_condition: source contains the field of the target
    private final TargetClass<String> targetField = new TargetClass<>("initValue");

    // Member inner class (source_class feature)
    class MemberInnerClass<V> {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source class");
        }
    };

    // Parent method for overriding
    public TargetClass<String> parentMethod() throws Exception {
        return targetField;
    }

    // Method to be refactored (overriding type, TargetClass return, default access)
    @Override
    public TargetClass<String> parentMethod() throws Exception { // requires_throws
        // WhileStatement (static modifier, obj.field, 3, pos: diff_package_others)
        static void whileStatementBlock() {
            TargetClass.StaticNestedClass staticObj = new TargetClass.StaticNestedClass();
            String objField = staticObj.field; // obj.field
            int num = 3; // target_feature: 3
            int count = 0;
            while (count < num) { // WhileStatement
                System.out.println(objField + count);
                count++;
            }
        }
        whileStatementBlock();

        // Constructor invocation
        TargetClass<String> newTarget = new TargetClass<>("newValue");
        TargetClass.InnerRecord innerRec = newTarget.new InnerRecord("innerData");

        // Super keywords usage
        super.toString();

        // Variable call (target_inner_rec)
        String targetValue = innerRec.value();
        innerRec.updateValue(targetValue + "_updated");

        // Access instance method
        String methodResult = newTarget.instanceMethod(); // access_instance_method

        // Requires throws (simulate checked exception)
        if (methodResult.isEmpty()) {
            throw new Exception("Instance method returned empty value");
        }

        return newTarget;
    }
}

// Target generic class (public modifier, generic, extends, static nested class target_feature)
public class TargetClass<T> extends SuperTargetClass<T> {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        String field = "targetStaticField";
    }

    // Inner record (target_inner_rec)
    public class InnerRecord {
        private T value;

        public InnerRecord(T value) {
            this.value = value;
        }

        public T value() { return value; }
        public void updateValue(T value) { this.value = value; }
    }

    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    // Instance method for access_instance_method
    public String instanceMethod() {
        return "processed_" + this.value + "_" + super.superField;
    }

    public T getValue() {
        return value;
    }
}