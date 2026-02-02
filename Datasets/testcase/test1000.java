import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface CallAnnotation {
    int value() default OtherClass.callMethod(new TargetClass<>());
}

private class SourceClass {
    // Per condition: source contains the field of the target
    private TargetClass<String>.MemberInnerClass targetInnerField;

    // Member inner class (source feature)
    class SourceMemberInner {
        void helperMethod() {
            // Super constructor invocation
            super();
            // Super keywords
            super.toString();
            // Variable call
            targetInnerField.setValue(10);
            // Expression statement
            int expr = targetInnerField.getValue() * 2;
            // While statement
            int count = 0;
            while (count < expr) {
                count++;
                // this.methodName(arguments)
                this.loopHelper(count);
            }
            // Used by reflection
            try {
                Method method = TargetClass.MemberInnerClass.class.getDeclaredMethod("getValue");
                method.setAccessible(true);
                method.invoke(targetInnerField);
            } catch (Exception e) {
                // No new exception thrown
            }
        }

        void loopHelper(int num) {}
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceClass.this.targetInnerField = new TargetClass<String>().new MemberInnerClass();
            new SourceMemberInner().helperMethod();
        }
    };

    // Abstract method (simulated via inner abstract class for private access)
    abstract static class AbstractMethodHolder {
        private abstract void moveCandidateMethod(TargetClass<String>.MemberInnerClass targetParam);
    }
}

public class TargetClass<T> {
    // Member inner class (target_feature) + type parameter (target_feature)
    class MemberInnerClass {
        private T value;

        void setValue(T val) {
            this.value = val;
        }

        int getValue() {
            return Integer.parseInt(value.toString());
        }
    }
}

// Others class for call_method
class OtherClass {
    // Public, others_class type, return int, pos: annotation attribute values
    public static int callMethod(TargetClass<String> target) {
        // Constructor target_feature + OuterClass.InnerClass.methodName()
        TargetClass<String>.MemberInnerClass inner = target.new MemberInnerClass();
        inner.setValue("5");
        return TargetClass<String>.MemberInnerClass.class.hashCode() + inner.getValue();
    }
}