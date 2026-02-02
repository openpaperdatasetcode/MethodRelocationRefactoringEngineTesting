package com.refactoring.movemethod;

import java.util.List;

// Annotation for method feature: has_annotation
@interface MethodAnnotation {}

// Super class for target class extension
class SuperTargetClass {}

// Source class: public, static nested class, member inner class, same package as target
public class SourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField;

    // Static nested class (source class feature)
    static class SourceStaticNestedClass {
        int staticNestedField = 10;
    }

    // Member inner class (source class feature)
    class SourceMemberInnerClass {
        int memberInnerField = 20;
    }

    // Method to refactor: instance, base type return, public, in source class
    @MethodAnnotation // has_annotation feature
    public int methodToRefactor() {
        // Variable call (target class field)
        int value = targetField != null ? targetField.getStaticNestedField() : 0;
        
        // Expression statement
        value += new SourceStaticNestedClass().staticNestedField;
        
        // SwitchStatement feature: private modifier, obj.field, 3 cases, same_package_others
        private void switchLogic() {
            SourceMemberInnerClass innerObj = new SourceMemberInnerClass();
            switch (innerObj.memberInnerField) { // obj.field
                case 1: value += 1; break;
                case 2: value += 2; break;
                case 3: value += 3; break; // 3 cases
                default: value += 0;
            }
        }
        switchLogic();

        // Accessor feature: default modifier, 2, others_class, super.methodName(), while pos, TargetClass return
        default TargetClass accessorLogic() {
            int count = 2; // 2 feature
            while (count > 0) { // while position
                SuperTargetClass superObj = new SuperTargetClass();
                // super.methodName() (simulated via others class)
                OthersClass.helperMethod(superObj); // others_class feature
                count--;
            }
            return new TargetClass(); // TargetClass Type return
        }
        accessorLogic();

        // No_new_exception feature (no throw new exception)
        if (value < 0) {
            // Only handle, no new exception
            value = 0;
        }

        // Raw_type feature
        List rawList = new java.util.ArrayList(); // raw type usage
        rawList.add(value);
        return (int) rawList.get(0); // base type return
    }
}

// Target class: private, extends SuperTargetClass, static nested class
private class TargetClass extends SuperTargetClass {
    // Static nested class (target feature)
    static class TargetStaticNestedClass {
        int staticNestedField = 15;
    }

    // Getter for static nested field (used in source class)
    public int getStaticNestedField() {
        return new TargetStaticNestedClass().staticNestedField;
    }
}

// Same package others class (for SwitchStatement pos: same_package_others)
class OthersClass {
    // Helper method for accessor's super.methodName() feature
    static void helperMethod(SuperTargetClass superObj) {
        // Dummy implementation
    }

    // call_method: others_class type, public modifier, abstract (via abstract class), obj.m1().m2().m3(), instance code blocks, int return
    public abstract static class CallerAbstractClass {
        // Instance code block position
        {
            SourceClass sourceObj = new SourceClass();
            // obj.m1().m2().m3() feature
            int result = sourceObj.methodToRefactor(); // m1
            Integer.valueOf(result).toString().length(); // m2().m3() chain
        }

        public abstract int callMethod(); // abstract feature, int return
    }

    // Concrete implementation of call_method
    public static class CallerConcreteClass extends CallerAbstractClass {
        @Override
        public int callMethod() {
            SourceClass source = new SourceClass();
            // Instance code block logic reuse
            return source.methodToRefactor();
        }
    }
}