package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class (normal, private modifier, same package, two member inner classes)
private class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class 1 (source_class feature)
    class MemberInnerSourceClass1 {}

    // Member inner class 2 (source_class feature) - method_position: source_inner
    class MemberInnerSourceClass2 {
        // Overloading method 1 (overloading type, List<String> return, public access)
        public List<String> targetMethod() throws Exception { // requires_throws
            return targetMethod("default");
        }

        // Overloading method 2 (overload_exist feature)
        public List<String> targetMethod(String suffix) throws Exception {
            List<String> result = new ArrayList<>();
            
            // Uses outer this (uses_outer_this)
            SourceClass outerThis = SourceClass.this;
            // Variable call
            String targetValue = outerThis.targetField.innerRecord.value();
            
            // Depends on inner class
            TargetClass.InnerTargetClass innerClass = targetField.new InnerTargetClass();
            result.add(innerClass.processValue(targetValue, suffix));

            // Requires throws (simulate exception scenario)
            if (targetValue.isEmpty()) {
                throw new Exception("Empty target value");
            }
            
            return result;
        }
    }
}

// Target class (normal, default modifier, extends, member inner class target_feature)
class TargetClass extends SuperTargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        String processValue(String value, String suffix) {
            return value + "_" + suffix;
        }
    }

    // Inner record (target_inner_rec)
    record InnerTargetRecord(String value) {}
    InnerTargetRecord innerRecord = new InnerTargetRecord("targetInnerRecordValue");
}

// Super class for target_class extends feature
class SuperTargetClass {}