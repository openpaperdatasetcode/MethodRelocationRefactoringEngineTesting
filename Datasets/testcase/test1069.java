package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

public class SourceClass<T extends CharSequence> implements CustomInterface<T> {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerValue";

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // Local inner class (source_class feature)
            class LocalInnerClass {
                T processValue(T value) {
                    return value;
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            local.processValue((T) "localInner");
        }
    };

    // Accessor method to be refactored (accessor, TargetClass Type return, protected access, position: source)
    protected TargetClass<T> getTarget(TargetClass<T>.InnerTargetRecord param) { // per_condition: target parameter
        // Constructor invocation
        TargetClass<T> targetInstance = new TargetClass<>();
        TargetClass<T>.InnerTargetRecord innerRecord = new TargetClass.InnerTargetRecord<>("init");

        // Expression statement
        param.value = (T) "updatedValue";
        // Access outer protected field
        String outerValue = this.outerProtectedField;

        // With_bounds (generic bounds)
        List<? extends T> boundedList = new ArrayList<>();
        boundedList.add(param.value);

        // Variable call
        T targetValue = param.value;
        if (targetValue == null) {
            throw new NullPointerException(); // NullPointerException feature
        }

        // Do-while with overloading method (pos: do-while)
        int count = 1; // method_feature: 1
        do {
            // Overloading method call (final modifier, source, super.methodName(), base type return)
            int result = overloadedMethod(count); // 1 in method_feature
            count++;
        } while (count <= 3);

        // Duplicate NullPointerException feature
        if (boundedList.isEmpty()) {
            throw new NullPointerException();
        }

        return targetInstance; // no_new_exception
    }

    // Overloading method 1 (final modifier, base type return, super.methodName())
    final int overloadedMethod(int num) { // 1 in method_feature, base type return
        super.toString(); // super.methodName()
        return num * 2;
    }

    // Overloading method 2 (overloading feature)
    final int overloadedMethod(int num, String str) {
        return num + str.length();
    }
}

// Interface for implements feature (source_class)
interface CustomInterface<T> {
    TargetClass<T> getTarget(TargetClass<T>.InnerTargetRecord param);
}

protected class TargetClass<T extends CharSequence> extends SuperTargetClass<T> { // type parameter, extends (target_feature)
    // Member inner class (target_feature) + inner record (target_inner_rec)
    record InnerTargetRecord<T>(T value) {}

    // Inner target record instance
    InnerTargetRecord<T> innerRecord = new InnerTargetRecord<>("targetValue");
}

// Super class for target_class extends feature
class SuperTargetClass<T> {}