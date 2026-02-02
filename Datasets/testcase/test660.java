import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Source class: normal, private, same package, two member inner classes
private class SourceClass {
    private int thisField = 1;

    // First member inner class (source_class feature)
    class MemberInnerClass1 {}
    // Second member inner class (source_class feature)
    class MemberInnerClass2 {
        int innerMethod() {
            return 3;
        }
    }

    // Static synchronized method (matches static feature spec)
    private static synchronized int staticHelperMethod() {
        return new MemberInnerClass2().innerMethod(); // new ClassName().method()
    }

    // Call method (source type, final modifier, static, this.methodName(), if/else pos, String return)
    final static String callMethod(TargetClass targetParam) {
        return targetParam.memberInnerTargetClass.targetField;
    }

    // Target method: normal, base type return, default access, source position
    int sourceMethod(TargetClass targetParam) {
        // VariableDeclarationStatement: volatile, this.field, 1, source pos
        volatile int localVar = this.thisField;
        
        // Type declaration statement
        MemberInnerClass1 innerObj = new MemberInnerClass1();
        
        // PostfixExpression: numbers=3, public modifier
        public int postfixVar = 3;
        postfixVar++;
        
        // Constructor invocation with static method in parameter list
        TargetClass.MemberInnerTargetClass targetInner = new TargetClass.MemberInnerTargetClass(staticHelperMethod());
        
        // With bounds (generic with bounds)
        class BoundedClass<T extends Number & Comparable<T>> {
            T value;
        }
        BoundedClass<Integer> boundedObj = new BoundedClass<>();
        
        // Variable call
        String varCall = targetParam.memberInnerTargetClass.targetField;
        
        // Assert statement
        assert localVar == 1 : "Invalid field value";

        loopLabel:
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                break loopLabel; // Break statement
            }
            // If/else condition with call_method
            if (localVar > 0) {
                varCall = this.callMethod(targetParam);
            } else {
                varCall = this.callMethod(targetParam) + " fallback";
            }
        }

        try {
            // Has annotation
            @MethodAnnotation
            int annotatedVar = postfixVar;
            // No new exception thrown
            return localVar + annotatedVar + targetInner.value;
        } catch (Exception e) {
            return 0;
        }
    }
}

// Target class: normal, non-sealed, member inner class feature
non-sealed class TargetClass {
    // Member inner class (target_feature)
    class MemberInnerTargetClass {
        String targetField = "targetValue";
        int value;

        // Constructor with static method parameter
        public MemberInnerTargetClass(int val) {
            this.value = val;
        }
    }

    MemberInnerTargetClass memberInnerTargetClass = new MemberInnerTargetClass(3);
}