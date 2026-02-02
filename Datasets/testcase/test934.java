package refactoring.test;

// Source class: normal, default modifier, same package as target, features: anonymous inner class, local inner class
class SourceClass {
    // Static field for IfStatement target_feature: ClassName.field
    static int sourceField = 1;

    // Method to be refactored: normal, return base type (int), public access, position source
    // Per condition: method contains target class parameter
    public int moveMethod(TargetClass targetParam) {
        // Variable call feature
        int result = 0;
        // Constructor invocation feature
        LocalInner localInner = new LocalInner();
        
        // IfStatement feature (type: IfStatement, modifier: private, target_feature: ClassName.field, 1, pos: source)
        if (SourceClass.sourceField == 1) {
            result = localInner.calculate(5);
            // Depends on inner class feature
            result += targetParam.innerClass().process(result);
        }

        // Anonymous inner class (source_class feature)
        Runnable runnable = new Runnable() {
            // Super constructor invocation feature
            {
                super();
            }

            @Override
            public void run() {
                System.out.println("Anonymous inner class: " + result);
            }
        };
        runnable.run();

        // For loop with call_method (pos: for)
        for (int i = 0; i < 3; i++) {
            // call_method feature: OuterClass.InnerClass.methodName()
            String callResult = SourceClass.StaticNestedClass.staticMethod(i);
            result += callResult.length();
        }

        // No new exception thrown (no_new_exception feature)
        return result;
    }

    // Local inner class (source_class feature)
    class LocalInner {
        private int calculate(int num) {
            return num * 2;
        }
    }

    // Static nested class for call_method feature
    static class StaticNestedClass {
        // call_method feature: static
        static String staticMethod(int num) {
            return "static-" + num;
        }
    }

    // call_method: type source, modifier default, return_type String
    String defaultSourceMethod() {
        return "default-method";
    }
}

// Target class: normal, default modifier, same package, target_feature: member inner class
class TargetClass {
    // Member inner class (target_feature)
    class TargetMemberInner {
        int process(int value) {
            return value + 3;
        }
    }

    public TargetMemberInner innerClass() {
        return new TargetMemberInner();
    }
}