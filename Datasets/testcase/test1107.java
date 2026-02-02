package com.example;

// Source interface (no modifier, same package, local inner class, anonymous inner class)
interface SourceInterface {
    // Method to be refactored (varargs, Object return, default access, position: source)
    default Object targetMethod(TargetInterface.InnerRecord param, String... args) { // per_condition: target parameter
        // Static method call in for loop (pos: for)
        for (int i = 0; i < 1; i++) { // 1 in method_feature
            int staticResult = staticMethod(); // target, static, super.methodName()
        }

        // Expression statement
        String targetValue = param.value();
        targetValue = targetValue + "_processed";

        // Variable call
        Object[] arr = {
            // Call method in array initialization (pos: array initialization)
            new InnerHelper().callMethod(param) // inner_class, private, accessor, outerInstance.new InnerClass().methodName()
        };

        // No new exception
        return arr[0];
    }

    // Static method (default modifier, base type return, 1, target, static)
    static int staticMethod() {
        // super.methodName() simulation (interface static method call)
        return TargetInterface.superInterfaceMethod();
    }

    // Local inner class (source_class feature)
    default void localInnerClassDemo() {
        class LocalInnerClass {
            String process(String val) { return val; }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.process("test");
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source interface");
        }
    };

    // Inner class for call_method (inner_class, private modifier)
    class InnerHelper {
        private void callMethod(TargetInterface.InnerRecord outerInstance) { // accessor feature
            // outerInstance.new InnerClass().methodName()
            TargetInterface.InnerClass inner = outerInstance.new InnerClass();
            inner.setValue(outerInstance.value());
        }
    }
}

// Target interface (public modifier, implements target_feature)
public interface TargetInterface extends SuperInterface {
    // Inner record (target_inner_rec)
    record InnerRecord(String value) {
        // Inner class for outerInstance.new InnerClass().methodName()
        class InnerClass {
            private String val;
            // Accessor method
            public void setValue(String val) { this.val = val; }
            // Accessor method
            public String getValue() { return val; }
        }
    }

    // Implements feature (extends SuperInterface)
    @Override
    default int superInterfaceMethod() {
        return 1;
    }
}

// Super interface for TargetInterface implements feature
interface SuperInterface {
    default int superInterfaceMethod() {
        return 0;
    }
}