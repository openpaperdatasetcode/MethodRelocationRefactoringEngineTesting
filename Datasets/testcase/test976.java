package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

// Diff package target helper (simulated same package for minimal structure)
class DiffPackageTargetHelper {
    public static void processTypeDeclaration(TargetRecord.TargetInner inner) {
        inner.targetField = 1;
    }
}

// Super class for source record extends (records can extend only java.lang.Record implicitly, use interface + abstract class workaround)
abstract class RecordSuperClass {
    protected String outerProtectedField = "PROTECTED_DATA"; // For access_outer_protected

    public synchronized void synchronizedMethod(TargetRecord.TargetInner inner) {
        // obj.m1().m2().m3() chain
        inner.setValue(inner.getValue().concat("_processed").toUpperCase());
    }
}

// Source record class (public modifier, same package)
public record SourceRecord(String sourceField) extends RecordSuperClass {
    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            try {
                new SourceInnerClass().processTarget(new TargetRecord("target").new TargetInner());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Varargs method (default access, void return)
        void processTarget(TargetRecord.TargetInner... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            // Enhanced for statement
            for (TargetRecord.TargetInner param : params) {
                // Variable call (per_condition: source contains target field)
                String targetVal = param.targetField;
                
                // TypeDeclarationStatement (public modifier, this.field, 1, pos: diff_package_target)
                publicTypeDeclarationStatement(param);

                // numbers=1, protected modifier, exp=NullLiteral
                protectedNullLiteralCheck(param, 1);

                // Access_outer_protected
                param.targetField = SourceRecord.this.outerProtectedField;

                // Uses_outer_this
                SourceRecord.this.synchronizedMethod(param);

                // Used_by_reflection
                Method method = TargetRecord.TargetInner.class.getMethod("getValue");
                String reflectedVal = (String) method.invoke(param);
            }
        }

        // Public TypeDeclarationStatement method
        public void publicTypeDeclarationStatement(TargetRecord.TargetInner inner) {
            // diff_package_target position
            DiffPackageTargetHelper.processTypeDeclaration(inner);
            // this.field (inner class's outer this)
            this.toString();
        }

        // Protected NullLiteral check method
        protected void protectedNullLiteralCheck(TargetRecord.TargetInner inner, int num) {
            // numbers=1
            if (num == 1 && inner.targetField == null) { // NullLiteral
                inner.targetField = "default";
            }
        }

        // call_method: type=source, modifier=synchronized, overriding, obj.m1().m2().m3(), pos=instance code blocks
        @Override
        public synchronized void synchronizedMethod(TargetRecord.TargetInner inner) {
            super.synchronizedMethod(inner); // Overriding feature
            // Instance code blocks (pos)
            {
                inner.targetField = inner.targetField + "_overridden";
            }
        }
    }

    // Local inner class (source feature)
    public void sourceMethodWithLocalInner() {
        class LocalInnerClass {
            void invokeProcessTarget(TargetRecord.TargetInner inner) throws Exception {
                new SourceInnerClass().processTarget(inner);
            }
        }
        new LocalInnerClass().invokeProcessTarget(new TargetRecord("local").new TargetInner());
    }
}

// Target record class (public modifier)
public record TargetRecord(String targetField) {
    // Target inner class (target_inner)
    class TargetInner {
        String targetField;

        public String getValue() {
            return targetField;
        }

        public void setValue(String value) {
            this.targetField = value;
        }
    }

    // Local inner class (target feature)
    public void targetMethod() {
        class TargetLocalInner {
            void updateInner(TargetInner inner) {
                inner.targetField = targetField;
            }
        }
        new TargetLocalInner().updateInner(new TargetInner());
    }
}