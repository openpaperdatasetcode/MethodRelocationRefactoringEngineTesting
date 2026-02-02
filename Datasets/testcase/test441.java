package com.refactoring.movemethod;

import java.io.IOException;

// Source class: generic class, public modifier, same package as target
// Features: extends, implements, member inner class, local inner class
public class SourceClass<T extends Number & CharSequence> extends ParentClass implements SampleInterface {
    // Per_condition: source contains the field of the target
    private TargetClass<String> targetField = new TargetClass<>();

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        // Recursive inner class for method_position: source_inner_rec
        public class RecursiveInnerClass {
            String innerVar = "recursiveInnerVar";

            // Normal method (method type: normal), return base type (int), default access, position: source_inner_rec
            int moveCandidateMethod() throws IOException {
                // Variable call (method feature)
                String varCall = this.innerVar;
                int value = varCall.length();

                // Super keywords (method feature)
                super.toString();
                ParentClass parent = super.getClass().asSubclass(ParentClass.class);

                // Requires throws (method feature - declares throws IOException)
                if (targetField == null) {
                    throw new IOException("Target field is null");
                }

                // Object initialization with recursion (recursion pos: object initialization)
                RecursiveHelper helper = new RecursiveHelper() {
                    @Override
                    public Object recursiveCall(int count) {
                        // OuterClass.InnerClass.methodName() (method_feature)
                        Object result = SourceClass.this.new MemberInnerClass().new RecursiveInnerClass().recursiveMethod(count);
                        return result;
                    }
                };
                value += (int) helper.recursiveCall(3);

                // Call inner class method (call_method) in while loop (pos: while)
                int count = 5;
                while (count > 0) {
                    InnerCaller caller = new InnerCaller();
                    value += caller.callMethod(count);
                    count--;
                }

                return value;
            }

            // Recursion method (type: recursion, public modifier, return Object)
            // method_feature: ["3", "parent_class", "recursion", "OuterClass.InnerClass.methodName()"]
            public Object recursiveMethod(int count) {
                if (count <= 0) {
                    return 0;
                }
                // Recursive call (parent_class association)
                return ParentClass.parentHelper() + recursiveMethod(count - 1);
            }
        }

        // call_method: type=inner_class, modifier=private, target_feature: overriding, (parameters) -> methodBody
        // pos: while, return_type: int
        private class InnerCaller extends CallerBase {
            @Override
            public int callMethod(int param) {
                // (parameters) -> methodBody (target_feature)
                return targetField.StaticNestedClass.process(param, p -> p * 2);
            }
        }

        abstract class CallerBase {
            public abstract int callMethod(int param);
        }
    }

    // Local inner class (source_class feature)
    public void localInnerClassHolder() {
        class LocalInnerClass {
            void callTargetMethod() {
                MemberInnerClass.RecursiveInnerClass inner = new MemberInnerClass().new RecursiveInnerClass();
                try {
                    inner.moveCandidateMethod();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        new LocalInnerClass().callTargetMethod();
    }

    interface RecursiveHelper {
        Object recursiveCall(int count);
    }
}

// Parent class for source_class extends feature
class ParentClass {
    public static int parentHelper() {
        return 1;
    }
}

// Interface for source_class implements feature
interface SampleInterface {}

// Target class: generic class, public modifier, target_feature: type parameter, static nested class
public class TargetClass<T> {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static int process(int value, java.util.function.Function<Integer, Integer> mapper) {
            return mapper.apply(value);
        }
    }
}