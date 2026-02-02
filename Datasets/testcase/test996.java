import java.io.IOException;
import java.util.function.Function;

// Source class: normal, private modifier, same package, extends + static nested + anonymous inner
private class SourceClass extends BaseClass {
    // Static nested class (source feature)
    static class StaticNestedClass {
        static int field = 3; // Matches "ClassName.field" and "3" in ContinueStatement feature
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };

    // Method: normal, return TargetClass Type, public access, contains target parameter
    public TargetClass moveCandidateMethod(TargetClass targetParam) throws IOException {
        // Requires throws declaration
        if (targetParam == null) throw new IOException("Target parameter cannot be null");

        // ContinueStatement (private modifier, ClassName.field, 3, pos: same_package_others)
        privateContinueBlock: {
            for (int i = 0; i < 10; i++) {
                if (i != StaticNestedClass.field) continue; // 3, ClassName.field
                targetParam.innerHelper(i);
            }
        }

        // Instance method feature (strictfp, 1, source, instanceReference::methodName, constructor param list pos)
        strictfp int instanceHelper(int num) {
            return num * 1; // Matches "1" in method_feature
        }
        TargetClass.MemberInnerClass inner = new TargetClass.MemberInnerClass(this::instanceHelper); // Constructor param list pos

        // Type declaration statement
        TargetClass processedTarget;
        // Variable call + depends_on_inner_class
        processedTarget = targetParam;
        processedTarget.memberInner = inner;
        int val = processedTarget.memberInner.calculate(5); // Depends on inner class

        return processedTarget;
    }
}

// Base class for "extends" source feature (same package others)
class BaseClass {
    protected String baseField = "base_value";
}

// Target class: normal, default modifier, member inner class feature
class TargetClass {
    MemberInnerClass memberInner;

    // Member inner class (target feature)
    class MemberInnerClass {
        private Function<Integer, Integer> func;

        // Constructor with method reference parameter (matches instanceReference::methodName)
        MemberInnerClass(Function<Integer, Integer> func) {
            this.func = func;
        }

        int calculate(int num) {
            return func.apply(num);
        }
    }

    void innerHelper(int val) {
        this.memberInner = new MemberInnerClass(n -> n * val);
    }
}