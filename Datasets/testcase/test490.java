package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;

// Sealed interface for source_class permits feature
sealed interface SealedInterface<T> permits SourceClass {}

// Source normal class (private modifier, same package, type parameter + permits + two anonymous inner classes)
private class SourceClass<T extends Comparable<T>> implements SealedInterface<T> {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // First anonymous inner class (source_class feature)
    private final Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class");
        }
    };

    // Second anonymous inner class (source_class feature - duplicate as specified)
    private final Runnable secondAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class");
        }
    };

    // Instance method to refactor (default access, returns Object, source position)
    Object refactorMethod() {
        // Expression statement feature
        int count = 0;
        List<String> collection = new ArrayList<>();

        // Variable call feature
        firstAnonymous.run();
        secondAnonymous.run();
        String varCall = targetField.toString();

        // Access instance method feature
        varCall += sourceInstanceMethod();

        // While statement feature
        while (count < 3) { // Number 3 for overriding method_feature
            collection.add(varCall + count);
            count++;
        }

        // Overriding method (synchronized modifier, collection operations pos, returns TargetClass Type)
        synchronized TargetClass.TargetInnerRec overridingMethod() {
            // this.methodName(arguments) + target + 3 + overriding features
            this.processCollection(collection, 3); // collection operations position
            return targetField.new TargetInnerRec("overridden-" + 3); // TargetClass Type return
        }

        // No_new_exception feature (no explicit throw new Exception)
        if (collection.isEmpty()) {
            return new Object();
        }

        return overridingMethod();
    }

    // Instance method for access_instance_method feature
    private String sourceInstanceMethod() {
        return "instance_method_value";
    }

    // Helper method for collection operations
    private void processCollection(List<String> coll, int num) {
        coll.add("processed-" + num);
    }

    // Override parent interface method (overriding feature fulfillment)
    @Override
    public int compareTo(SourceClass<T> o) {
        return 0;
    }
}

// Target normal class (public modifier, anonymous inner class target_feature)
public class TargetClass {
    // Target_inner_rec (inner record for target class)
    public record TargetInnerRec(String data) {
        // Anonymous inner class (target_feature)
        private final Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + data);
            }
        };

        public TargetInnerRec {
            targetAnonymous.run();
        }
    }
}