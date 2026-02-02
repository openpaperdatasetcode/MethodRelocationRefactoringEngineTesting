package com.refactoring.movemethod;

import java.io.IOException;

// Base class with abstract method for override_violation feature
abstract class BaseSourceClass {
    // Abstract method with no throws clause (for override_violation)
    public abstract int abstractMethod(TargetClass target) /* no throws */;
}

// Source class: protected normal class, same package as target, two static nested classes
protected class SourceClass extends BaseSourceClass {
    // Instance field for access_instance_field feature
    private int instanceField = 2; // target_feature: 2

    // First static nested class (source feature)
    static class FirstStaticNested {
        public static int staticField = 2; // target_feature: 2 (ClassName.field)
        public static int processInt(int val) {
            return val * 2;
        }
    }

    // Second static nested class (source feature)
    static class SecondStaticNested {
        public static int staticField = 2; // target_feature: 2 (ClassName.field)
        public static int adjustInt(int val) {
            return val + 2;
        }
    }

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec {
        // VariableDeclarationStatement feature: public modifier, ClassName.field, 2, pos=source
        public void varDeclLogic(TargetClass target) {
            // pos: source (declaration in source class context)
            int localVar = FirstStaticNested.staticField; // ClassName.field, target_feature: 2
            TargetClass.TargetInner inner = target.new TargetInner();
            inner.innerField = localVar; // target_feature: 2
        }

        // Abstract method (method type: abstract) - override_violation (adds throws clause)
        @Override
        public abstract int abstractMethod(TargetClass target) throws IOException; // requires_throws, override_violation
    }

    // Concrete implementation of inner recursive abstract method
    public class SourceInnerRecImpl extends SourceInnerRec {
        // Override violation: base method has no throws, this adds IOException (override_violation)
        @Override
        public int abstractMethod(TargetClass targetParam) throws IOException {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                throw new IOException("Target parameter cannot be null"); // requires_throws
            }

            // VariableDeclarationStatement feature call (pos=source)
            varDeclLogic(targetParam);

            // Constructor invocation (target class and inner class)
            TargetClass newTarget = new TargetClass();
            TargetClass.TargetInner newInner = newTarget.new TargetInner();

            int result = 0;
            // Enhanced for statement (simulated with target inner class collection)
            TargetClass.TargetInner[] innerArray = {newInner, targetParam.new TargetInner()};
            for (TargetClass.TargetInner inner : innerArray) {
                // Variable call (target inner class)
                inner.setInnerValue("value_" + 2);
                String innerVal = inner.getInnerValue();
                
                // Access_instance_field feature
                result += instanceField + FirstStaticNested.processInt(instanceField);

                // Use static nested class fields (ClassName.field)
                result += FirstStaticNested.staticField + SecondStaticNested.staticField;
            }

            // Access_instance_field (source class instance field)
            result += SourceClass.this.instanceField;

            return result; // Base type (int) return
        }
    }

    // Helper method to invoke abstract method
    public int invokeAbstractMethod(TargetClass target) throws IOException {
        SourceInnerRecImpl innerImpl = new SourceInnerRecImpl();
        return innerImpl.abstractMethod(target);
    }
}

// Target class: default modifier, member inner class feature
class TargetClass {
    private String value;

    // Member inner class (target_feature)
    public class TargetInner {
        int innerField; // For ClassName.field/variable call feature
        private String innerValue;

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    // Constructor invocation
    public TargetClass() {}

    // Variable call helper
    public TargetInner newTargetInner() {
        return new TargetInner();
    }
}