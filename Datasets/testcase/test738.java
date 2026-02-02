package refactoring.test;

// Source class with anonymous inner class and local inner class
class SourceClass {
    // Field of target class to meet per_condition
    TargetClass targetField = new TargetClass();

    // Varargs method with required features, to be moved to target's local inner class
    public Object moveMethod(String... args) {
        // Local variable call
        int count = 0;
        // Depends on inner class
        LocalInner localInner = new LocalInner();
        
        for (String arg : args) {
            count++;
            // Break statement
            if (arg == null) {
                break;
            }
            // Constructor invocation of anonymous inner class
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(arg);
                }
            };
            runnable.run();
        }
        
        localInner.process(count);
        // No new exception thrown
        return targetField.getInnerClassInstance().calculate(count);
    }

    // Overload of moveMethod to meet overload_exist feature
    public Object moveMethod(Integer... args) {
        return args.length;
    }

    // Local inner class in source class
    class LocalInner {
        void process(int value) {
            System.out.println("Processed: " + value);
        }
    }

    // Instance code block with call to other class's protected overloaded method
    {
        OtherClass other = new OtherClass();
        // new ClassName().method() call
        int result = new OtherClass().calculate(5);
        result += other.calculate(5, 10);
    }
}

// Target class with local inner class
class TargetClass {
    // Local inner class (target_feature)
    class TargetInnerClass {
        // Method to receive the moved method
        public Object moveMethod(String... args) {
            return null; // Placeholder for moved implementation
        }

        int calculate(int value) {
            return value * 2;
        }
    }

    TargetInnerClass getInnerClassInstance() {
        return new TargetInnerClass();
    }
}

// Other class for call_method feature
class OtherClass {
    // Protected overloaded method (target_feature: overloading)
    protected int calculate(int a) {
        return a * 3;
    }

    protected int calculate(int a, int b) {
        return a + b;
    }
}