package com.refactoring.movemethod;

// Same package others class for SynchronizedStatement position
class SamePackageOthers {
    static final Object lock = new Object();
    int superField = 1;
}

// Source abstract class (abstract modifier, same package, empty features)
abstract class SourceClass {
    // Inner class containing the method to be moved (source_inner position)
    public class SourceInnerClass {
        // Varargs method to be moved (public, void return, source_inner position)
        public void moveableMethod(TargetClass targetParam, String... varargs) {
            // SynchronizedStatement (static modifier, same_package_others pos, super.field + 1)
            static SamePackageOthers syncObj = new SamePackageOthers();
            synchronized (syncObj.lock) {
                int syncVar = syncObj.superField + 1; // super.field + 1 target feature
            }

            // Instance method feature (public modifier, for pos, 3 + source + instance + outerInstance.new InnerClass().methodName())
            public TargetClass instanceMethod() {
                for (int i = 0; i < 3; i++) { // Number 3 feature
                    // outerInstance.new InnerClass().methodName() call
                    SourceClass.this.new SourceInnerClass().innerHelperMethod();
                }
                return targetParam; // Return TargetClass type
            }

            // Constructor invocation feature
            SamePackageOthers constructorObj = new SamePackageOthers();

            // Super constructor invocation feature (anonymous subclass)
            TargetClass superConstructorObj = new TargetClass() {
                {
                    super(); // Super constructor invocation
                }
            };

            // Expression statement feature
            String exprStmt = varargs[0] + constructorObj.superField;
            
            // Variable call feature
            int varCall = syncObj.superField + instanceMethod().localInnerField;
            
            // No new exception instantiation (no_new_exception feature)
            System.out.println(varCall + exprStmt);
        }

        // Helper method for instance method feature
        private void innerHelperMethod() {}
    }
}

// Target abstract class (protected modifier, local inner class target feature)
protected abstract class TargetClass {
    int localInnerField = 10;

    // Local inner class as target feature
    protected void targetMethod() {
        class TargetLocalInnerClass {
            int innerValue = 5;
        }
        TargetLocalInnerClass localInner = new TargetLocalInnerClass();
        localInnerField = localInner.innerValue;
    }

    // Abstract method (required for abstract class)
    protected abstract void abstractMethod();
}