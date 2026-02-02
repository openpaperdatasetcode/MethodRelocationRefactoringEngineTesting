package com.refactoring.movemethod;

// Annotation for has_annotation feature
@interface InnerMethodAnnotation {}

// Source class: normal class, public modifier, same package, member inner class, static nested class
public class SourceClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    private String sourceField = "sourceValue";

    // Static nested class (source feature)
    public static class StaticNestedSource {
        String nestedField = "staticNestedValue";
    }

    // Member inner class (source feature, method_position: source_inner)
    public class MemberInnerSource {
        // Method: normal, return base type (int), private access, source_inner position
        @InnerMethodAnnotation // has_annotation feature
        private int moveableMethod() {
            // Variable call feature
            String localVar = sourceField;
            localVar = targetField.targetField;
            localVar = targetField.new InnerRecursiveTarget().innerField;

            // Expression statement feature
            sourceField = localVar.toUpperCase();
            targetField.targetField = "updatedTargetValue";
            int baseVal = localVar.length(); // base type (int) assignment

            // Super keywords feature
            class SuperClass extends SourceClass {
                void useSuper() {
                    super.sourceField = "superAssignedValue"; // super keywords access
                }
            }
            new SuperClass().useSuper();

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return baseVal;
        }
    }
}

// Target class: normal class, public modifier, same package, target_feature: member inner class
public class TargetClass {
    String targetField = "targetValue";

    // Inner recursive class (target_inner_rec - method's target class)
    public class InnerRecursiveTarget {
        String innerField = "innerRecValue";

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }
    }
}