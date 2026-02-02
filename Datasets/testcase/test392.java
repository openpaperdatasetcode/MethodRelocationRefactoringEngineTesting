package refactoring.test;

import diffpkg.OthersClass;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for generic method's parent_class feature
class TargetParentClass<T> {
    protected T parentField = (T) "parent_value";
    public T getParentField() { return parentField; }
}

// Different package class for diff_package_others pos
package diffpkg;
import refactoring.test.TargetClass;

public class OthersClass {
    // For SynchronizedStatement diff_package_others pos
    public static <T> void syncLogic(TargetClass<T> target) {
        // SynchronizedStatement: private modifier, super.field + 1
        private Object lock = new Object();
        synchronized (lock) {
            target.super.field = (T) Integer.valueOf(1); // super.field + 1 (target_feature)
            target.counter = 1;
        }
    }
}

// Back to test package
package refactoring.test;

// Source generic class: public modifier, same package, empty features
public class SourceClass<S extends CharSequence> {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("source_init");

    // Varargs method to be refactored (all specified features)
    public final TargetClass<String> refactorMethod(String... args) { // varargs type + final access
        // Constructor invocation (target class)
        TargetClass<Integer> intTarget = new TargetClass<>(1);

        // Variable call (target class field)
        targetField.value = "refactor_value";
        targetField.counter = 1;

        // SynchronizedStatement: diff_package_others pos (call others class method)
        OthersClass.syncLogic(targetField);

        // Try statement
        try {
            // Generic method: default modifier, functional interfaces pos, 1/parent_class/generic/obj.m1().m2().m3()
            Function<TargetClass<String>, List<String>> func = this::genericMethod;
            List<String> genericResult = func.apply(targetField);
            targetField.value = genericResult.get(0);
        } catch (Exception e) {
            // No new exception
            targetField.value = "error_" + e.getMessage();
        }

        // Override violation (invalid attempt to override final method)
        class InvalidOverride extends TargetClass<String> {
            public InvalidOverride(String value) { super(value); }
            @Override
            public final void printValue() { // Compile error: final method override
                // No new exception
            }
        }

        // No new exception, return TargetClass type
        return targetField;
    }

    // Generic method (type:generic, default modifier, method_feature:1/parent_class/generic/obj.m1().m2().m3())
    default <T> List<String> genericMethod(TargetClass<T> target) { // generic feature
        List<String> result = new ArrayList<>();
        // obj.m1().m2().m3() + parent_class + 1
        String chainResult = target.getParentClass() // m1()
                                   .getParentField() // m2()
                                   .toString() + "_1"; // m3() + 1 (method_feature)
        result.add(chainResult);
        return result;
    }
}

// Target generic class: private modifier, anonymous inner class feature
private class TargetClass<T> extends TargetParentClass<T> { // extends parent for super.field
    T value;
    int counter;

    // Constructor for constructor invocation feature
    public TargetClass(T value) {
        this.value = value;
    }

    // For generic method's obj.m1().m2().m3() chain
    public TargetParentClass<T> getParentClass() {
        return (TargetParentClass<T>) this;
    }

    // Final method for override_violation feature
    public final void printValue() {
        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(value); // No new exception
            }
        };
        targetAnonymous.run();
    }
}