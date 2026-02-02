package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Diff package others class (simulated same package for minimal structure)
class DiffPackageOthers {
    static void processSynchronized(TargetClass.TargetInner inner) {
        inner.targetField = 3;
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Public normal source class
public class SourceClass {
    // Member inner class (source feature)
    class SourceMemberInner {
        // Target field reference (per_condition: source contains target field)
        private TargetClass.TargetInner targetFieldRef;

        @RefactorAnnotation // has_annotation feature
        protected TargetClass.TargetInner processTarget(TargetClass.TargetInner targetParam) {
            // Type declaration statement
            String typeDecl = "processed_target";
            
            // Variable call (target parameter/field access)
            targetFieldRef = targetParam;
            targetFieldRef.targetField = 1;

            // Object initialization (pos for final instance method)
            TargetClass.TargetInner newInner = new TargetClass().new TargetInner();
            finalInstanceMethod(newInner, 1);

            // SynchronizedStatement (private modifier, obj.field, 3, pos: diff_package_others)
            privateSynchronizedStatement(targetParam);

            // No_new_exception (empty try-catch)
            try {
                // No exception thrown
                targetFieldRef.targetField += 3;
            } catch (Exception e) {
                targetFieldRef.targetField = -1;
            }

            return targetFieldRef;
        }

        // Private SynchronizedStatement method
        private void privateSynchronizedStatement(TargetClass.TargetInner inner) {
            Object lock = new Object();
            synchronized (lock) {
                // Diff_package_others position
                DiffPackageOthers.processSynchronized(inner);
                // obj.field and 3 feature
                if (inner.targetField == 3) {
                    inner.targetField = inner.targetField + 3;
                }
            }
        }

        // Final instance method (object initialization pos)
        final void finalInstanceMethod(TargetClass.TargetInner target, int val) {
            // 1: literal value usage
            if (val == 1) {
                // ClassName.methodName(arguments)
                TargetClass.StaticHelper.method(target, val);
            }
        }
    }

    // Static nested class (source feature)
    static class SourceStaticNested {}
}

// Final target class with implements and anonymous inner class
final class TargetClass implements TestInterface {
    // Anonymous inner class (target feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            TargetInner inner = new TargetInner();
            inner.targetField = 0;
        }
    };

    // Target inner class (target_inner)
    class TargetInner {
        int targetField; // Target field for per_condition

        public int getTargetField() {
            return targetField;
        }

        public void setTargetField(int targetField) {
            this.targetField = targetField;
        }
    }

    // Static helper class for ClassName.methodName call
    static class StaticHelper {
        static void method(TargetInner inner, int val) {
            inner.targetField = val;
        }
    }
}

// Supporting interface for target class implements
interface TestInterface {}