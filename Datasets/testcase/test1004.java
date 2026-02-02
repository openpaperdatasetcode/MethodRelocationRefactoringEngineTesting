import java.io.IOException;

private class SourceClass {
    // Method to be refactored: static, private, return Object, contains target parameter
    private static Object moveCandidateMethod(TargetClass targetParam) {
        // Requires try-catch block
        try {
            // Super constructor invocation (Object super)
            Object obj = new Object() {};
            // Super keywords
            obj.super.toString();
            
            // Constructor invocation
            TargetClass.StaticNestedClass nested = new TargetClass.StaticNestedClass();
            // Raw type usage
            TargetClass.StaticNestedClass rawNested = new TargetClass.StaticNestedClass();
            
            // Variable call
            int val = nested.calculate(5);
            // Break statement
            for (int i = 0; i < 10; i++) {
                if (i == val) {
                    break;
                }
            }
            
            // Overload exist (call overloaded method)
            nested.calculate(5, 10);
            return targetParam;
        } catch (IOException e) {
            // Required try-catch handling, no new exception thrown
            return null;
        }
    }

    // Call method: final, source type, return int, pos: property assignment
    final int callMethod() {
        TargetClass target = new TargetClass();
        // Property assignment position + OuterClass.InnerClass.methodName()
        int result = target.StaticNestedClass.staticMethod();
        // Normal target feature
        result += TargetClass.StaticNestedClass.calculateStatic(3);
        return result;
    }
}

/**
 * Javadoc for TargetClass
 * Protected class with static nested class
 */
protected class TargetClass {
    // Static nested class (target_feature)
    static class StaticNestedClass {
        int calculate(int num) {
            return num * 2;
        }

        // Overload method (overload_exist)
        int calculate(int num1, int num2) {
            return num1 + num2;
        }

        static int staticMethod() {
            return 10;
        }

        static int calculateStatic(int num) {
            return num * 3;
        }
    }

    // Constructor with super invocation
    public TargetClass() {
        super();
    }
}