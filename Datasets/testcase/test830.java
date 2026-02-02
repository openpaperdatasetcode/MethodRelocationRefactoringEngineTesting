package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Target generic class: public modifier, empty target_feature, same package as source
public class TargetClass<T> {
    public static int staticField = 1; // ClassName.field + 1 for ConstructorInvocation feature
    private T value;

    public TargetClass() {}

    // Protected constructor for ConstructorInvocation feature
    protected TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

// Others class for call_method feature
class OthersClass {
    // strictfp modifier, static, returns TargetClass Type, this.methodName(arguments)
    public strictfp static <T> TargetClass<T> strictfpStaticMethod(T data) {
        OthersClass instance = new OthersClass();
        return instance.instanceHelperMethod(data);
    }

    private <T> TargetClass<T> instanceHelperMethod(T data) {
        return new TargetClass<>(data);
    }

    // Static code block for call_method pos
    static {
        TargetClass<String> staticTarget = strictfpStaticMethod("staticInit");
        System.out.println(staticTarget.getValue());
    }
}

// Source generic class: non-sealed modifier, two member inner classes, same package as target
non-sealed class SourceClass<S> {
    // First member inner class (source_feature)
    class FirstInnerClass {
        private S innerData;

        public FirstInnerClass(S data) {
            this.innerData = data;
        }

        public S getInnerData() {
            return innerData;
        }
    }

    // Second member inner class (source_feature)
    class SecondInnerClass {
        // Override violation target (static method can't be overridden)
        public static void nonOverridableMethod() {}
    }

    // Instance method: private access, base return type (int), target parameter (per_condition)
    private int refactorMethod(TargetClass<String> targetParam) {
        int result = 0;

        // ConstructorInvocation feature: protected modifier, ClassName.field, 1, pos=inner class
        class InnerConstructorInvocation {
            TargetClass<String> createTarget() {
                return new TargetClass<>(TargetClass.staticField + "_" + 1);
            }
        }
        TargetClass<String> innerCreatedTarget = new InnerConstructorInvocation().createTarget();

        // Try statement feature
        try {
            // Variable call feature
            String varCall = targetParam.getValue();
            result = varCall.length();

            // Raw type feature
            List rawList = new ArrayList();
            rawList.add(varCall);

            // Override violation feature
            try {
                SecondInnerClass invalidOverride = new SecondInnerClass() {
                    // Compile-time override violation (static method override attempt)
                    public void nonOverridableMethod() {}
                };
            } catch (Exception e) {
                // No new exception thrown
                result += 1;
            }

            // Depends on inner class feature
            FirstInnerClass firstInner = new FirstInnerClass(varCall);
            result += firstInner.getInnerData().length();

            // Call_method feature: others_class, strictfp, static, this.methodName, pos=static code blocks
            TargetClass<String> othersClassResult = OthersClass.strictfpStaticMethod("callMethodData");
            result += othersClassResult.getValue().length();
        } catch (Exception e) {
            // No new exception thrown (no custom exception instantiation)
            result = 0;
        }

        // No new exception thrown feature
        return result;
    }
}