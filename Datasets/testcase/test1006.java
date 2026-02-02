import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

public class SourceClass<T> {
    // Per condition: source contains the field of the target
    protected TargetClass<T>.MemberInnerClass targetInnerField;
    protected String outerProtectedField = "protectedValue";

    // Member inner class (source feature)
    class MemberInnerClass {
        int value = 2;
        TargetClass<T> getTargetInstance() {
            return new TargetClass<>();
        }
    }

    // Static nested class (source feature)
    static class StaticNestedClass {
        static <T> TargetClass<T>.MemberInnerClass createInner(TargetClass<T> target) {
            return target.new MemberInnerClass();
        }
    }

    // Method: instance, return TargetClass Type, strictfp, source position
    strictfp TargetClass<T> moveCandidateMethod() {
        // ConstructorInvocation (private modifier semantics, super.field, 2, pos: source)
        privateConstructorBlock: {
            MemberInnerClass inner = new MemberInnerClass();
            TargetClass<T> target = inner.getTargetInstance();
            targetInnerField = target.new MemberInnerClass();
            targetInnerField.superField = inner.value * 2; // super.field, value 2
        }

        // If statement
        if (targetInnerField != null && targetInnerField.superField > 0) {
            // Variable call
            String varCall = SourceClass.this.outerProtectedField;
            targetInnerField.process(varCall);
            
            // Access outer protected
            targetInnerField.data.add(SourceClass.this.outerProtectedField);
            
            // Uses outer this
            targetInnerField.outerThisRef = SourceClass.this;
        }

        // Used by reflection
        try {
            Method method = TargetClass.MemberInnerClass.class.getDeclaredMethod("process", String.class);
            method.setAccessible(true);
            method.invoke(targetInnerField, "reflectionCall");
        } catch (Exception e) {
            // No new exception thrown
        }

        // Return TargetClass type
        return targetInnerField.getOuterTarget();
    }
}

protected class TargetClass<U> {
    // Member inner class (target feature)
    class MemberInnerClass {
        int superField;
        List<String> data = new ArrayList<>();
        SourceClass<U> outerThisRef;

        void process(String input) {
            data.add(input);
        }

        TargetClass<U> getOuterTarget() {
            return TargetClass.this;
        }
    }
}