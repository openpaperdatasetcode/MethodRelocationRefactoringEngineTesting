package refactoring.test;

// Source generic class with protected modifier, same package as target
protected class SourceClass<T> {
    // Precondition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();
    private int testVar;

    // Target varargs method with final access, returns TargetClass type
    public final TargetClass<T> moveMethod(T... varargs) {
        // Raw type feature
        ArrayList rawList = new ArrayList();
        
        // Variable call feature
        rawList.add("test");
        String varCall = (String) rawList.get(0);
        
        // this.var = var feature
        this.testVar = 10;
        
        // If statement feature
        if (varargs.length > 0) {
            // NullPointerException (no_new_exception)
            Object nullObj = null;
            nullObj.toString();
        }

        // Nested varargs method (public modifier, exception throwing, returns base type)
        public int nestedVarargsMethod(Integer... nums) throws IllegalArgumentException {
            // ClassName.methodName(arguments) feature
            int result = SourceClass.staticHelperMethod(1);
            
            // Sub_class feature usage
            SubClass sub = new SubClass();
            sub.doSomething();
            
            // Exception throwing statements (pos requirement)
            if (nums == null) {
                throw new IllegalArgumentException("Varargs cannot be null");
            }
            return nums.length > 0 ? nums[0] : 0; // Returns base type (int)
        }

        // Local inner class feature
        class LocalInnerClass {
            void localMethod() {
                nestedVarargsMethod(1, 2, 3);
            }
        }

        // Anonymous inner class feature
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                localMethod();
            }
        };
        anonymousRunnable.run();

        nestedVarargsMethod(1);
        return targetField;
    }

    // Static helper method for ClassName.methodName(arguments) feature
    private static int staticHelperMethod(int num) {
        return num * 2;
    }

    // Local method for anonymous inner class access
    private void localMethod() {
        new LocalInnerClass().localMethod();
    }

    // Sub_class feature (inner subclass)
    private class SubClass extends SourceClass<T> {
        void doSomething() {}
    }
}

// Target generic class with default modifier, contains member inner class
class TargetClass<U> {
    // Member inner class target feature
    class MemberInnerClass {
        U value;
        
        MemberInnerClass(U value) {
            this.value = value;
        }
    }

    public TargetClass() {
        new MemberInnerClass(null);
    }
}