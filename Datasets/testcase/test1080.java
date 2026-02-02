package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class (normal, abstract modifier, same package, local inner class, static nested class)
abstract class SourceClass {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Overloading method 1 (overloading type, List<String> return, default access)
    @SuppressWarnings("unused") // has_annotation feature
    List<String> targetMethod(TargetClass param) { // per_condition: target parameter
        return targetMethod(param, "default");
    }

    // Overloading method 2 (overloading feature)
    List<String> targetMethod(TargetClass param, String suffix) {
        List<String> result = new ArrayList<>();
        
        // Local inner class (source_class feature)
        class LocalInnerClass {
            String processValue(String value) {
                return value + suffix;
            }
        }
        LocalInnerClass local = new LocalInnerClass();

        // Access instance method
        String targetValue = param.getValue();
        // Variable call
        String processed = local.processValue(targetValue);
        // Expression statement
        result.add(processed);

        // No new exception (no_new_exception)
        return result;
    }
}

// Target class (normal, abstract modifier, extends, local inner class target_feature)
abstract class TargetClass extends SuperTargetClass {
    // Local inner class (target_feature)
    private void initLocalInner() {
        class LocalInnerClass {
            String innerValue = "targetLocalInner";
        }
        LocalInnerClass local = new LocalInnerClass();
        this.value = local.innerValue;
    }

    private String value;

    // Instance method for access_instance_method
    public String getValue() {
        initLocalInner();
        return value;
    }
}

// Super class for target_class extends feature
class SuperTargetClass {}