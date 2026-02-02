package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;

// Parent interface for target_class extends feature
interface TargetParentInterface {
    default String parentMethod(int num) {
        return "parent_" + num; // Number 1 for method_feature
    }
}

// Source interface (default modifier, same package, empty feature list)
interface SourceInterface {
    // Target class field (per_condition: source contains the field of the target)
    TargetInterface<String> targetField = TargetInterface.create("target_1");

    // Overloading method 1 (protected access, returns TargetClass Type, source position)
    TargetInterface<String> refactorMethod();

    // Overloading method 2 (overloading feature)
    TargetInterface<Integer> refactorMethod(int param);

    // Default instance method (fulfills method_feature: 1 + target + instance + superTypeReference, while pos)
    default List<String> instanceMethod() {
        List<String> result = new ArrayList<>();
        int count = 0;

        // While statement position (pos: while)
        while (count < 1) { // Number 1 for method_feature
            // SuperTypeReference.methodName(arguments) target_feature
            TargetParentInterface superType = () -> {};
            result.add(superType.parentMethod(1)); // Number 1 + target feature

            // Variable call feature
            result.add(targetField.getData());

            // Access instance method feature
            result.add(processInstance(targetField));

            count++;
        }

        return result;
    }

    // Instance method for access_instance_method feature
    default String processInstance(TargetInterface<?> target) {
        // Uses_outer_this feature (outer interface reference)
        return SourceInterface.this.targetField.getData() + "_processed_1"; // Number 1
    }

    // Default method implementation (fulfills all method features)
    default TargetInterface<String> refactorMethod() {
        // Depends_on_inner_class feature (uses target's inner class)
        TargetInterface.InnerClass inner = new TargetInterface.InnerClass();
        String innerData = inner.process("inner_1"); // Number 1

        // Super keywords feature (via parent interface)
        String superData = ((TargetParentInterface) () -> {}).parentMethod(1);

        // Variable call feature
        String varCall = innerData + "_" + superData;

        // Return statement feature (returns TargetClass Type)
        return TargetInterface.create(varCall);
    }

    // Overload method implementation
    default TargetInterface<Integer> refactorMethod(int param) {
        // No_new_exception feature (no explicit throw new Exception)
        return TargetInterface.create(1); // Number 1
    }
}

// Target interface (public modifier, type parameter + extends + member inner class target_feature)
public interface TargetInterface<T> extends TargetParentInterface {
    // Type parameter target_feature (core feature)
    T getData();

    // Member inner class target_feature
    class InnerClass {
        public String process(String val) {
            return val + "_inner_processed_1"; // Number 1 for method_feature
        }
    }

    // Static factory method (for instance creation)
    static <T> TargetInterface<T> create(T data) {
        return () -> data;
    }
}