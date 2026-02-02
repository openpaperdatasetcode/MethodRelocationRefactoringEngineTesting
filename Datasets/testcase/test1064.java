package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

abstract class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Abstract method to be refactored (abstract, void return, private access, position: source)
    private abstract void targetMethod();

    // Concrete method implementing feature logic (backing for abstract method structure)
    private void targetMethodImpl() {
        // Constructor invocation
        TargetClass.StaticNestedTargetClass<String> nestedObj = new TargetClass.StaticNestedTargetClass<>("value");
        
        // Access instance field
        String instanceField = nestedObj.field;
        // Depends on static field
        String staticField = TargetClass.StaticNestedTargetClass.STATIC_FIELD;
        // Variable call
        String targetValue = targetField.innerClass.getValue();

        // WhileStatement (private modifier, obj.field, 1, pos: diff_package_target)
        private int count = 1; // 1
        while (count < 5) {
            String objField = targetField.innerClass.field; // obj.field
            if (objField == null) {
                throw new NullPointerException(); // NullPointerException feature
            }
            count++;
        }

        // Switch case
        switch (targetValue) {
            case "test1":
                // TypeMethodReference (numbers:2, public modifier, exp:TypeMethodReference)
                public Function<String, Integer> ref = String::length;
                int len = ref.apply(targetValue + 2); // 2
                break;
            default:
                break;
        }

        // Collection operations with recursion (pos: collection operations)
        List<Integer> list = new ArrayList<>();
        list.add(recursiveMethod(2)); // 2 in method_feature
    }

    // Recursion method (public modifier, base type return, source, super.methodName())
    public int recursiveMethod(int num) { // 2 in method_feature, return base type
        super.toString(); // super.methodName()
        if (num <= 0) {
            return 0;
        }
        // Recursion feature
        return num + recursiveMethod(num - 1);
    }
}

protected abstract class TargetClass<T> { // type parameter (target_feature)
    // Static nested class (target_feature) - target class: target_inner
    static class StaticNestedTargetClass<T> {
        public static final String STATIC_FIELD = "staticTargetField"; // depends_on_static_field
        T field;

        public StaticNestedTargetClass(T field) {
            this.field = field;
        }

        public T getValue() {
            return field;
        }
    }

    final StaticNestedTargetClass<T> innerClass = new StaticNestedTargetClass<>(null);
}