package com.refactoring.test;

import java.lang.reflect.Method;

// Parent class for source class extends feature
class ParentSourceClass {}

// Source class (protected modifier, same package, extends + static nested + local inner class)
protected class SourceClass extends ParentSourceClass {
    // Static nested class (source feature)
    static class StaticNestedSource {
        // Inner recursive class (source_inner_rec)
        class InnerRecursiveClass {
            private int instanceField = 10;

            // Method to be refactored (instance, TargetClass return, private access)
            private TargetClass moveMethod(TargetClass targetParam) throws Exception {
                // Source contains target's field (per_condition)
                int targetField = targetParam.targetField;
                // Variable call & access instance field
                int varCall = this.instanceField;
                targetParam.targetField = varCall + targetField;

                // Super constructor invocation
                ParentSourceClass superInstance = new ParentSourceClass();

                // Type declaration statement
                LocalInnerClass localInner = new LocalInnerClass();
                localInner.processTarget(targetParam);

                // ThrowStatement (protected modifier, obj.field, 1, pos=source)
                if (targetParam.targetField < 1) {
                    throw new Exception("Field value less than 1: " + targetParam.targetField);
                }

                // Used by reflection
                try {
                    Method method = TargetClass.class.getDeclaredMethod("getTargetField");
                    method.setAccessible(true);
                    method.invoke(targetParam);
                } catch (Exception e) {
                    // Exception handling with call_method (pos: exception handling statements)
                    int result = new InnerCallClass().callMethod(targetParam);
                    throw new Exception("Reflection error", e);
                }

                // Requires throws (method declares throws Exception)
                return targetParam;
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                void processTarget(TargetClass target) {
                    target.targetField *= 2;
                }
            }

            // Inner class for call_method (inner_class type)
            protected class InnerCallClass {
                // call_method (protected modifier, normal, superTypeReference.methodName(arguments))
                protected int callMethod(TargetClass param) {
                    // SuperTypeReference.methodName(arguments)
                    return ParentSourceClass.super.toString().length() + param.targetField;
                }
            }
        }
    }
}

// Target class (strictfp modifier, static nested class feature)
strictfp class TargetClass {
    int targetField;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        public static void updateField(TargetClass target, int value) {
            target.targetField = value;
        }
    }

    public int getTargetField() {
        return this.targetField;
    }
}