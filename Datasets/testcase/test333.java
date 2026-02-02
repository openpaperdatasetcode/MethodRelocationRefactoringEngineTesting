package com.refactoring.movemethod;

// Public normal source class (same package as target)
public class SourceClass {
    // Source contains target class field (satisfies per_condition)
    private PrivateTargetClass targetField = new PrivateTargetClass();

    // Member inner class (source feature)
    public class MemberInnerClass {
        // Instance method (source_inner position, public access, base type return)
        public int instanceMethod() {
            // Variable call
            int localVar = 0;
            // Access target instance field
            localVar = targetField.targetInstanceField;

            // ArrayCreation with number 2, public modifier
            String[] publicArray = new String[2];

            // Break statement
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    break;
                }
                publicArray[i] = String.valueOf(i);
            }

            // Static final method in if/else conditions (3, target, static, ClassName.methodName)
            if (localVar == 3) {
                StaticFinalMethodHolder.finalStaticMethod(targetField, 3);
            } else {
                StaticFinalMethodHolder.finalStaticMethod(targetField, 0);
            }

            // Private ThrowStatement (obj.field + 3, source position)
            try {
                if (targetField.targetInstanceField != 3) {
                    throw new IllegalStateException("Field value: " + targetField.targetInstanceField);
                }
            } catch (IllegalStateException e) {
                // No new exception (reuse existing, no new instantiation)
                localVar = -1;
            }

            return localVar; // Base type return (int)
        }

        // Static final method holder (satisfies static/final modifier, if/else pos)
        private static final class StaticFinalMethodHolder {
            public static void finalStaticMethod(PrivateTargetClass target, int num) {
                // ClassName.methodName(arguments) implementation
                if (num == 3) {
                    target.targetInstanceField = num;
                }
            }
        }
    }

    // Static nested class (source feature)
    public static class StaticNestedClass {}
}

// Private normal target class (same package as source)
private class PrivateTargetClass extends BaseClass { // Extends (target feature)
    // Instance field for access_instance_field feature
    int targetInstanceField = 3;

    // Local inner class (target feature)
    public void targetMethod() {
        class LocalInnerClass {
            private int innerVal = 2;
        }
        LocalInnerClass localInner = new LocalInnerClass();
    }
}

// Base class for target class extension
class BaseClass {}