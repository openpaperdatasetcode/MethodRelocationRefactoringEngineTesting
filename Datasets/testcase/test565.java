package com.refactor;

// Source class: normal, private modifier, same package as target, member inner + static nested class
private class SourceClass {
    // Target class field reference (per_condition)
    private static TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Method to refactor: static, Object return, private, in source_inner_rec
        private static Object methodToMove() throws Exception {
            // Accessor feature (public modifier, 2, target, accessor, new ClassName().method() in object initialization)
            public int baseTypeValue = new TargetClass().getIntValue(); // return base type (int)

            // Expression statement
            baseTypeValue += 2;

            // ArrayCreation (numbers:3, default modifier)
            default String[] strArray = new String[3];
            default int[] intArray = new int[3];
            default TargetClass[] targetArray = new TargetClass[3];

            // Variable call (target field access)
            String targetVar = targetField.getName();

            // Raw type usage
            TargetClass rawTarget = new TargetClass();

            // Requires throws (declared exception)
            if (targetArray == null) {
                throw new Exception("Array creation failed");
            }

            // No new exception thrown (only declared exception)
            return new Object[]{baseTypeValue, targetVar, strArray};
        }
    }

    // Member inner class (source feature)
    class SourceMemberInner {}
}

// Target class: normal, public modifier, no additional features (target_feature: [])
public class TargetClass {
    // Target field (referenced in source)
    private String name = "targetFieldValue";
    private int value = 2;

    // Accessor method for accessor feature (returns base type int)
    public int getIntValue() {
        return this.value;
    }

    // Accessor method for variable call
    public String getName() {
        return this.name;
    }
}