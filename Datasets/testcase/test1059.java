package com.refactoring.test;

import java.lang.reflect.Method;

// Source annotation interface (type: @interface, modifier: empty, same package, permits, 2 member inner classes)
@interface SourceAnnotation permits TargetAnnotation {
    // Member inner class 1
    class InnerSourceClass1 {
        // Method to be refactored (instance, Object return, private access, position: source_inner_rec)
        private Object targetMethod() throws Exception { // requires_throws
            // Source contains target field (per_condition)
            TargetAnnotation targetField = new TargetAnnotation() {};
            
            // Access outer protected
            protected String outerProtected = "outerProtected";
            String accessOuter = outerProtected;
            
            // Variable call
            String targetValue = TargetAnnotation.StaticTargetMethod.staticMethod();
            
            // Raw type
            java.util.List rawList = new java.util.ArrayList();
            rawList.add(2); // method_feature: 2
            
            // Lambda expressions with instance method (pos: Lambda expressions)
            Runnable lambda = () -> {
                // inner_class, instance, new ClassName().method(), 2
                InnerSourceClass2 inner = new InnerSourceClass2();
                inner.instanceMethod(2); // 2 in method_feature
                new InnerSourceClass2().instanceMethod(2); // new ClassName().method()
            };
            lambda.run();
            
            // Used by reflection (duplicate as per feature list)
            Method method = InnerSourceClass1.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            Object reflectResult = method.invoke(this);
            
            // Override violation (attempt to override non-overridable)
            @Override // Compile error (override violation)
            public String toString() {
                return super.toString();
            }
            
            return reflectResult;
        }
    }

    // Member inner class 2
    class InnerSourceClass2 {
        // Instance method (default modifier, void return, method_feature: inner_class, instance)
        void instanceMethod(int num) {}
    }
}

// Target annotation interface (type: @interface, protected modifier, local inner class target_feature)
protected @interface TargetAnnotation {
    // Static code block with call_method (pos: Static code blocks)
    static {
        // call_method: target type, final modifier, static, super.methodName(), return String
        final String result = StaticTargetMethod.staticMethod();
        SuperClass.superMethod(); // super.methodName()
    }

    // Static nested class for call_method
    final class StaticTargetMethod {
        static String staticMethod() { // static, return String
            // Local inner class (target_feature)
            class LocalInnerClass {
                String value = "localInner";
            }
            LocalInnerClass local = new LocalInnerClass();
            return local.value;
        }
    }
}

// Super class for super.methodName()
class SuperClass {
    public static void superMethod() {}
}