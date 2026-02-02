package refactoring.test;

// Source normal class (default modifier, same package as target, implements interface)
class SourceClass implements Runnable {
    // Implements feature (Runnable interface)
    @Override
    public void run() {}

    // Target varargs method (private access, void return, in source class)
    // Precondition: method contains target class parameter
    private void targetMethod(TargetClass targetParam, String... varargs) {
        // Variable call feature
        String varCall = String.join(";", varargs);
        int varLength = varCall.length();

        // Expression statement feature
        varLength += 5;

        // NullPointerException (no_new_exception - no explicit new exception instance)
        Object nullObj = null;
        nullObj.toString(); // Triggers NPE without new

        // Override_violation feature (attempt to override non-overridable method)
        // Local inner class with override violation
        class LocalInnerClass extends TargetClass.MemberInnerClass {
            // Violation: trying to override private method (not allowed)
            @Override
            void innerMethod() { // Override violation (method is private in super)
                super.innerMethod();
            }
        }

        // Local inner class feature
        class LocalInnerInMethod {
            void useTargetParam() {
                targetParam.printInner();
            }
        }
        new LocalInnerInMethod().useTargetParam();

        // Anonymous inner class feature
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                targetMethod(targetParam, "anonymous");
            }
        };
        anonymousRunnable.run();
    }
}

// Target normal class (default modifier, member inner class target_feature)
class TargetClass {
    // Member inner class (target_inner_rec context)
    class MemberInnerClass {
        private void innerMethod() {} // Private method for override violation
    }

    public void printInner() {
        new MemberInnerClass().innerMethod();
    }
}