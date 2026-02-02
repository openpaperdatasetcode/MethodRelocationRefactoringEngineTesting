package sourcepkg;

import targetpkg.TargetClass;
import targetpkg.SubClass;

class SourceClass {
    private int outerPrivateField = 42;

    // Member inner class
    class MemberInnerClass {
        // Nested inner class (method_position: source_inner_rec)
        class RecursiveInnerClass {
            @Deprecated
            RecursiveInnerClass refactorMethod(TargetClass targetParam) {
                // ThisExpression
                private final ThisExpression thisExp = new ThisExpression(this);
                // Variable call
                targetParam.innerField = 10;
                // Access outer private member
                int accessOuterPrivate = SourceClass.this.outerPrivateField;
                // Empty statement
                ;
                // Enhanced for statement
                for (String s : targetParam.stringList) {
                    // Switch case
                    switch (s.length()) {
                        case 1:
                            // Static private method in ternary operator
                            Object staticResult = (s.isEmpty()) ? staticPrivateMethod() : new Object();
                            break;
                        default:
                            break;
                    }
                }
                // NullPointerException handling
                try {
                    // Call private source method in exception handling
                    Object callResult = callPrivateMethod();
                } catch (NullPointerException e) {
                    // No new exception instantiation
                    e.printStackTrace();
                }
                // Return this
                return this;
            }

            // Static private method with specified features
            private static Object staticPrivateMethod() {
                SubClass subClass = new SubClass();
                // instanceReference.methodName(arguments)
                return subClass.instanceMethod("arg1");
            }

            // Private call method with super.methodName
            private Object callPrivateMethod() {
                try {
                    return super.toString();
                } catch (Exception e) {
                    return new Object();
                }
            }
        }
    }

    // Static nested class
    static class StaticNestedClass {
        String staticField;
    }

    // ThisExpression helper class
    private static class ThisExpression {
        private final Object instance;

        ThisExpression(Object instance) {
            this.instance = instance;
        }
    }
}

package targetpkg;

public interface TargetInterface {
    void interfaceMethod();
}

public class TargetClass implements TargetInterface {
    int innerField;
    String[] stringList = {"a", "bb", "ccc"};

    // Member inner class
    class TargetInnerClass {
        int innerValue;
    }

    @Override
    public void interfaceMethod() {}
}

public class SubClass extends TargetClass {
    public Object instanceMethod(String arg) {
        return arg;
    }
}