// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Source class: normal, public, different package, local inner + static nested class
public class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("test");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        String nestedField = "nestedValue";
    }

    // Target method: normal, List<String> return, public access, source position
    public List<String> sourceMethod() {
        List<String> result = new ArrayList<>();
        // Variable call (access target field's instance field)
        String varCall = targetField.instanceField;

        // Super constructor invocation (SourceClass extends Object, call super())
        super();

        // Type declaration statement
        StaticNestedSourceClass nestedObj = new StaticNestedSourceClass();
        // Raw type feature (use ArrayList without generic)
        ArrayList rawList = new ArrayList();
        rawList.add(varCall);

        // Constructor invocation (static nested class + target class constructor)
        StaticNestedSourceClass newNestedObj = new StaticNestedSourceClass();
        TargetClass<String> newTargetObj = new TargetClass<>("newInstance");

        // Access instance field of target class
        targetField.instanceField = "updatedValue";
        varCall = targetField.instanceField;

        // Try statement
        try {
            // Local inner class (source_class feature)
            class LocalInnerSourceClass {
                void processTarget() {
                    result.add(varCall);
                    result.add(newNestedObj.nestedField);
                    result.addAll((Collection<? extends String>) rawList);
                }
            }
            new LocalInnerSourceClass().processTarget();
        } catch (Exception e) {
            // No new exception thrown
            result.add("fallback: " + e.getMessage());
        }

        return result;
    }

    // Main method for test (optional)
    public static void main(String[] args) {
        new SourceClass().sourceMethod();
    }
}

// Target class package (different from source)
package com.target;

import java.io.Serializable;

// Target class: normal, final, type parameter + implements + anonymous inner class features
public final class TargetClass<T> implements Serializable {
    // Instance field for access_instance_field feature
    public String instanceField;

    // Constructor
    public TargetClass(T initialValue) {
        this.instanceField = initialValue.toString();
        // Anonymous inner class (target_feature)
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + instanceField);
            }
        }.run();
    }

    // Implements Serializable (target_feature: implements)
    @Override
    public String toString() {
        return instanceField;
    }
}