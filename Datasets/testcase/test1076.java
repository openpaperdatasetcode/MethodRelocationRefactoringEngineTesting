package com.refactoring.test;

// Source class (normal, default modifier, same package, extends, static nested class, local inner class)
class SourceClass extends SuperSourceClass {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (instance, base type return, public access)
        public int targetMethod(TargetClass.InnerTargetClass param) throws Exception { // per_condition: target parameter, requires_throws
            // Variable call
            int targetValue = param.getValue();
            
            // Overload exist feature (overloaded methods)
            int overload1 = helperMethod(targetValue);
            int overload2 = helperMethod(targetValue, "suffix");

            // Local inner class (source_class feature)
            class LocalInnerClass {
                int process(int num) {
                    return num * 2;
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            
            // Requires throws (simulate exception scenario)
            if (targetValue < 0) {
                throw new Exception("Invalid target value");
            }
            
            return local.process(overload1 + overload2);
        }

        // Overload method 1 (overload_exist feature)
        private int helperMethod(int num) {
            return num * 3;
        }

        // Overload method 2 (overload_exist feature)
        private int helperMethod(int num, String str) {
            return num + str.length();
        }
    }
}

// Super class for source_class extends feature
class SuperSourceClass {}

// Target class (normal, empty modifier, same package, extends, anonymous inner class)
class TargetClass extends SuperTargetClass {
    // Inner target class (target_inner)
    class InnerTargetClass {
        private int value = 10;

        public int getValue() {
            return value;
        }
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerTargetClass inner = new InnerTargetClass();
            System.out.println(inner.getValue());
        }
    };
}

// Super class for target_class extends feature
class SuperTargetClass {}