package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Diff package target class (simulated same package for minimal structure)
class DiffPackageTargetHelper {
    static int checkFieldValue(TargetClass<?>.TargetInner inner) {
        return inner.targetField;
    }
}

final class SourceClass<T extends CharSequence> {
    // Source inner class for method_position: source_inner
    class SourceInnerClass {
        // Normal method with required features
        List<String> processTarget(TargetClass<T>.TargetInner innerParam) {
            List<String> result = new ArrayList<>();
            // Variable call (per_condition: source contains target field)
            int targetFieldVal = innerParam.targetField;
            
            // IfStatement (duplicate feature)
            if (targetFieldVal > 0) {
                result.add("positive");
            }
            
            // IfStatement with private modifier, obj.field, 2, pos: diff_package_target
            privateIfStatement(innerParam);
            
            // Expression statement
            innerParam.setTargetField(innerParam.getTargetField() + 1);
            
            // OuterClass.this.x (no outer field, simulate with dummy reference)
            SourceClass.this.toString();
            
            // Access instance method
            innerParam.instanceMethod();
            
            // Overload exist (call overloaded method)
            innerParam.overloadedMethod();
            innerParam.overloadedMethod(1);
            
            // Constructor with private modifier, ternary operators pos, super.methodName
            int ctorResult = new PrivateConstructorClass(innerParam).getValue();
            int ternary = (ctorResult > 0) ? 1 : 0;
            
            // No new exception (empty try-catch to comply)
            try {
                // No exception thrown
            } catch (Exception e) {
                // Do nothing
            }
            
            return result;
        }

        // Private IfStatement with required features
        private void privateIfStatement(TargetClass<T>.TargetInner inner) {
            // Diff package target position
            int fieldVal = DiffPackageTargetHelper.checkFieldValue(inner);
            // obj.field and 2
            if (fieldVal == 2) {
                inner.targetField = 2;
            }
        }

        // Private constructor class with required features
        private class PrivateConstructorClass {
            private int value;

            // Private constructor
            private PrivateConstructorClass(TargetClass<T>.TargetInner inner) {
                // Super.methodName(arguments) (via anonymous subclass)
                TargetClass<T>.TargetInner subInner = new TargetClass<T>().new TargetInner() {
                    @Override
                    public int getTargetField() {
                        return super.getTargetField() + 1;
                    }
                };
                // 1, inner_class, constructor features
                this.value = subInner.getTargetField() + 1;
            }

            // Return base type
            public int getValue() {
                return this.value;
            }
        }
    }

    // Dummy method to satisfy OuterClass.this.x reference
    @Override
    public String toString() {
        return super.toString();
    }
}

final class TargetClass<T extends Number> {
    // Anonymous inner class in target class
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetInner inner = new TargetInner();
            inner.instanceMethod();
        }
    };

    // Target inner class (target_inner)
    class TargetInner {
        // Target field (per_condition: source contains target field)
        int targetField = 0;

        // Instance method
        public void instanceMethod() {}

        // Overloaded methods (overload_exist)
        public void overloadedMethod() {}
        public void overloadedMethod(int val) {}

        // Getter/setter for target field
        public int getTargetField() {
            return targetField;
        }

        public void setTargetField(int targetField) {
            this.targetField = targetField;
        }
    }
}