// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;

// Source normal class (default modifier, different package, no features)
class SourceClass {
    // Inner recursive class (source_inner_rec position for refactor method)
    class SourceInnerRec {
        private int innerVar = 1;

        // Overriding method (private, return base type (int), source_inner_rec position)
        @Override
        private int hashCode() { // Overriding type (Object's hashCode)
            return refactorMethod(new TargetClass.TargetInner());
        }

        // Method to refactor (overriding, private, return int (base type), source_inner_rec)
        private int refactorMethod(TargetClass.TargetInner targetParam) {
            // Variable call feature
            int localVar = this.innerVar;

            // Expression statement feature
            localVar += 10;

            // Super constructor invocation (via anonymous inner class)
            Runnable anonRunnable = new Runnable() {
                @Override
                public void run() {
                    super.getClass(); // Super constructor invocation
                }
            };

            // TryStatement (public modifier, same_package_target pos, obj.field + 1)
            TargetClass.TryHelper tryHelper = new TargetClass.TryHelper();
            try {
                tryHelper.field = 1; // obj.field, target_feature 1
                localVar += tryHelper.field;
            } catch (NullPointerException e) {
                // No new exception feature (no throw new Exception)
                localVar = 1;
            }

            // No new exception feature (no throw new Exception)
            if (targetParam == null) {
                return 0;
            }

            anonRunnable.run();
            return localVar + targetParam.getValue();
        }
    }

    // Constructor to initialize inner recursive class
    public SourceClass() {
        SourceInnerRec innerRec = new SourceInnerRec();
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target normal class (private modifier, static nested class feature)
private class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private int value = 1;

        public int getValue() {
            return value;
        }
    }

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static int staticField = 1;
    }

    // TryHelper for TryStatement (same_package_target pos)
    public class TryHelper {
        public int field; // obj.field target_feature
    }

    // Constructor to initialize inner class
    public TargetClass() {
        TargetInner inner = new TargetInner();
    }
}