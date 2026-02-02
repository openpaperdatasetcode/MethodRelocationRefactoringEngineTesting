package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// Source class with public modifier, same package as target, features: permits, member inner class (duplicate as specified)
public sealed class SourceClass permits SourceSubClass {
    // Member inner class (source_class feature - duplicate as specified)
    public class FirstMemberInnerClass {
        // Recursive inner class for method_position: source_inner_rec
        public class RecursiveInnerClass {
            String innerVar = "recursiveInnerVar";

            // Instance method (method type: instance) - protected access, return Object, position: source_inner_rec
            // Per_condition: contains parameter of the target (TargetClass<T> param)
            @CustomAnnotation // has_annotation (method feature)
            protected Object moveCandidateMethod(TargetClass<String> targetParam) throws IOException {
                // Super constructor invocation (method feature)
                super();

                // Variable call (method feature)
                String varCall = this.innerVar;
                targetParam.new TargetInnerClass().innerMethod(varCall);

                // IOException (method feature)
                if (targetParam == null) {
                    throw new IOException("Target parameter is null");
                }

                // Overload exist (method feature) - overloaded method exists
                overloadMethod(1);
                overloadMethod("string");

                // Depends on inner class (method feature)
                FirstMemberInnerClass inner = new FirstMemberInnerClass();
                inner.innerHelperMethod();

                // Do-while block containing overriding method (overriding pos: do-while)
                int count = 1;
                do {
                    // Overriding method call with super.methodName() (method_feature)
                    OverridingClass overridingObj = new OverridingClass();
                    TargetClass<String> overrideResult = overridingObj.overriddenMethod(targetParam);
                    count--;
                } while (count > 0);

                // No new exception (method feature - no throw new Exception explicitly created here)
                try {
                    return targetParam.process();
                } catch (Exception e) {
                    return e.getMessage();
                }
            }

            // Overload method 1 (overload_exist feature)
            public void overloadMethod(int num) {}

            // Overload method 2 (overload_exist feature)
            public void overloadMethod(String str) {}
        }
    }

    // Member inner class (source_class feature - duplicate as specified)
    public class SecondMemberInnerClass {
        public String getValue() {
            return "secondInnerValue";
        }
    }

    // Overriding class for method feature (overriding)
    public class OverridingClass extends BaseClass {
        // Overriding method (public modifier, return TargetClass Type, uses super.methodName())
        @Override
        public TargetClass<String> overriddenMethod(TargetClass<String> param) {
            // super.methodName() (method_feature)
            super.overriddenMethod(param);
            return param;
        }
    }

    // Base class for overriding
    public class BaseClass {
        public TargetClass<String> overriddenMethod(TargetClass<String> param) {
            return new TargetClass<String>() {
                @Override
                public Object process() {
                    return "baseProcess";
                }
            };
        }
    }

    // Helper method for inner class dependency
    private void innerHelperMethod() {}
}

// Permitted subclass for SourceClass (permits feature)
final class SourceSubClass extends SourceClass {}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Target class: abstract, features: type parameter, member inner class
public abstract class TargetClass<T> {
    // Member inner class (target_class target_feature)
    public class TargetInnerClass {
        public void innerMethod(String input) {
            System.out.println("Inner method: " + input);
        }
    }

    public abstract Object process();
}