package test;

import java.io.IOException;

private class SourceClass extends ParentSource {
    class SourceInner {
        class SourceInnerRec {
            /**
             * Varargs method to process target inner classes
             * @param innerParams target inner class instances
             * @return TargetClass instance
             */
            private TargetClass methodToMove(TargetClass.TargetInner... innerParams) {
                // Super constructor invocation
                class SubSource extends SourceInner {
                    SubSource() {
                        super();
                    }
                }
                SubSource sub = new SubSource();

                // Variable call
                TargetClass target = innerParams[0].outer;
                String var = innerParams[0].value;
                int count = innerParams.length;

                // Raw type
                TargetClass.TargetInner rawInner = new TargetClass().new TargetInner();

                // Requires try-catch
                try {
                    if (var == null) {
                        throw new NullPointerException();
                    }
                    target.process(var);
                } catch (NullPointerException e) {
                    throw new IOException("Processing failed", e);
                }

                return target;
            }
        }
    }

    {
        new Runnable() {}; // Anonymous inner class
        new Object() {};   // Another anonymous inner class
    }
}

class ParentSource {}

strictfp class TargetClass implements MyInterface {
    class TargetInner {
        String value;
        TargetClass outer = TargetClass.this;
    }

    void process(String data) {}
}

interface MyInterface {}
package test;

private abstract class SourceClass {
    private int outerPrivate = 10;

    class SourceMemberInner {}

    private int methodToMove(TargetClass target) {
        // VariableDeclarationStatement in inner class
        class LocalInner {
            private int innerVar = target.field + 1;
        }
        LocalInner local = new LocalInner();

        // Varargs method in array initialization
        Object[] results = {
            target.varargsMethod(3, "a", "b", "c")
        };

        // Assert statement
        assert target.field > 0 : "Field must be positive";

        // Empty statement
        ;

        // Synchronized statement
        synchronized (target) {
            target.field++;
        }

        // Expression statement
        target.process();

        // Variable call
        int var = target.field;
        TargetClass anotherTarget = target;

        // Access outer private
        int sum = var + outerPrivate;

        return sum;
    }

    void localInnerMethod() {
        class LocalInnerInMethod {} // Local inner class
    }
}

abstract class TargetClass {
    int field;

    default Object varargsMethod(int num, String... args) {
        return args[0].length();
    }

    synchronized static int staticMethod() {
        return 5;
    }

    void process() {}

    void callInDoWhile() {
        int i = 0;
        do {
            // Call method in do-while
            int val = TargetClass::staticMethod;
            i++;
        } while (i < 3);
    }
}