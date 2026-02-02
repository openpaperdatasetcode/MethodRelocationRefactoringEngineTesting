package refactoring.test;

import java.util.function.Function;

// Source normal class with strictfp modifier, same package as target
strictfp class SourceClass {
    // Member inner class feature
    class MemberInnerClass {
        int innerField = 5;
    }

    // Target instance method (protected access, base type return, in source class)
    // Precondition: method contains target class parameter
    protected int targetMethod(TargetClass targetParam) {
        // Variable call feature
        MemberInnerClass innerObj = new MemberInnerClass();
        int varCall = innerObj.innerField;

        // ReturnStatement (private modifier context, source pos, obj.field + 3)
        private int returnVar = targetParam.targetField + 3;
        if (returnVar > 0) {
            return returnVar; // ReturnStatement feature
        }

        // Static nested method (protected modifier, functional interfaces pos, base type return)
        protected static int staticNestedMethod(int num) {
            // Method features: 1 (literal), inner_class, static, superTypeReference.methodName(arguments)
            int literalOne = 1;
            LocalInnerClass staticInner = new LocalInnerClass();
            
            // SuperTypeReference.methodName(arguments) - Object (super type) method call
            String arg = Object.class.getSimpleName();
            int result = staticInner.innerMethod(arg);

            // Functional interfaces pos requirement
            Function<Integer, Integer> func = (x) -> x * literalOne;
            func.apply(num);

            try {
                // No new exception (reuse existing exception, no explicit new)
                if (num < 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                return -1;
            }
            return literalOne; // Base type return
        }

        // Local inner class feature (within target method)
        class LocalInnerClass {
            int innerMethod(String arg) {
                return arg.length();
            }
        }

        staticNestedMethod(varCall);
        // No new exception (implicit NPE if targetParam is null, no new exception instance)
        return targetParam.targetField; // Base type return
    }

    // Local inner class feature (within source class method)
    public void sourceLocalInnerClass() {
        class LocalInnerInSource {
            void localMethod() {}
        }
        new LocalInnerInSource().localMethod();
    }
}

// Target normal class (default modifier, anonymous inner class target_feature)
class TargetClass {
    int targetField = 10;

    public TargetClass() {
        // Anonymous inner class target_feature
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetField);
            }
        };
        anonymousRunnable.run();
    }
}