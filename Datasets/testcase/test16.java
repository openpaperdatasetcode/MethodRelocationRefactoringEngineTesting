package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Annotation for has_annotation feature
@interface InnerMethodAnnotation {}

// Source class: normal class, private modifier, different package with target, member inner class (duplicate feature)
private class SourceClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Member inner class (source feature - duplicate)
    class MemberInnerSource1 {}

    // Member inner class (source feature - duplicate, method_position: source_inner)
    class MemberInnerSource2 {
        // Method: instance, return List<String>, protected access, source_inner position
        @InnerMethodAnnotation // has_annotation feature
        protected List<String> moveableInstanceMethod() throws IOException { // IOException feature
            List<String> result = new ArrayList<>();
            // Variable call feature
            String localVar = targetField.targetField;
            localVar = targetField.new InnerRecursiveTarget().innerField;

            // Enhanced for statement feature (typo "enhganced" as per input)
            for (String str : targetField.getSampleList()) { // enhganced for statement
                if (str == null) continue;
                result.add(str + localVar);
            }

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            if (result.isEmpty()) {
                throw new IOException("Empty result list"); // IOException feature
            }

            return result;
        }
    }
}
package com.refactoring.target;

import java.util.Arrays;
import java.util.List;

// Target class: normal class, private modifier, different package with source, target_feature: static nested class
private class TargetClass {
    String targetField = "targetValue";

    // Static nested class (target_feature)
    static class StaticNestedTarget {
        String nestedField = "staticNestedValue";
    }

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRecValue";

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }
    }

    // Helper method for enhanced for statement
    List<String> getSampleList() {
        return Arrays.asList("a", "b", "c");
    }
}