package com.refactoring.test;

import java.util.function.Function;

// Source enum class (default modifier, implements, static nested, local inner)
enum SourceEnum implements TestInterface {
    INSTANCE;

    protected String outerProtectedField = "protectedValue";

    // Static nested class (source feature)
    static class StaticNestedSource {
        // Inner recursive method (method_position: source_inner_rec)
        protected int moveMethod(TargetEnum.TargetInnerRec... targetParams) {
            // Variable call & access outer protected field
            String outerVal = SourceEnum.this.outerProtectedField;
            int sum = 0;

            // Local inner class (source feature)
            class LocalInnerClass {
                void processParams(TargetEnum.TargetInnerRec[] params) {
                    for (TargetEnum.TargetInnerRec param : params) {
                        sum += param.field;
                    }
                }
            }

            new LocalInnerClass().processParams(targetParams);
            // No new exception

            // Functional interface with call_method (pos: functional interfaces)
            Function<TargetEnum.TargetInnerRec, Integer> func = param -> 
                OthersClass.callMethod(param);
            sum += func.apply(targetParams[0]);

            return sum; // base type return
        }
    }

    @Override
    public void interfaceMethod() {
        // Implements feature implementation
    }
}

// Target enum class (protected modifier, javadoc, anonymous inner class)
protected enum TargetEnum {
    VALUE;

    /**
     * Javadoc feature for target inner recursive class
     */
    public static class TargetInnerRec {
        int field;

        // Anonymous inner class (target feature)
        private final Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(TargetInnerRec.this.field);
            }
        };
    }
}

// Others class for call_method (others_class type, public modifier, generic, new ClassName().method())
class OthersClass {
    public static <T extends TargetEnum.TargetInnerRec> int callMethod(T param) {
        return new HelperClass().calculate(param.field);
    }

    static class HelperClass {
        int calculate(int val) {
            return val * 2;
        }
    }
}

// Implemented interface for source enum
interface TestInterface {
    void interfaceMethod();
}