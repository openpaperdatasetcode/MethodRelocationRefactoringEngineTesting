import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: normal, public modifier, same package, extends + static nested + member inner
public class SourceClass extends BaseClass {
    // Per condition: source contains the field of the target (target_inner_rec)
    private TargetClass.MemberInnerClass targetInnerField;

    // Static nested class (source feature)
    static class SourceStaticNested {
        static int helperValue = 5;
    }

    // Member inner class (source feature)
    class SourceMemberInner {
        int process(int val) {
            return val * 2;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface RefactorAnnotation {}

    // Method: instance, base type return (int), public access, requires throws
    @RefactorAnnotation // has_annotation
    public int moveCandidateMethod() throws IOException {
        super(); // Super constructor invocation
        if (targetInnerField == null) {
            throw new IOException("Target inner field is null"); // requires_throws
        }

        int total = 0;
        // If statement
        if (SourceStaticNested.helperValue > 0) {
            // Switch statement
            switch (targetInnerField.getValue()) {
                case 1:
                    total += 10;
                    break;
                case 2:
                    total += 20;
                    break;
                default:
                    total += 5;
            }

            // Continue statement
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) continue;
                // Variable call (target inner class)
                targetInnerField.updateValue(i);
                total += new SourceMemberInner().process(i);
            }
        }
        return total; // Base type return
    }
}

// Base class for "extends" source feature
class BaseClass {
    protected String baseField = "base_value";
}

// Target class: normal, public modifier, member inner class feature (target_inner_rec)
public class TargetClass {
    // Member inner class (target_feature)
    class MemberInnerClass {
        private int value = 0;

        int getValue() {
            return value;
        }

        void updateValue(int newVal) {
            this.value = newVal;
        }
    }
}

// Others class for call_method
class OtherClass {
    // Static code block position (pos)
    static {
        callMethod();
    }

    // Call method: private, others_class type, abstract feature, instanceReference.methodName(arguments)
    private static Object callMethod() {
        SourceClass source = new SourceClass();
        // Initialize target inner field (per condition)
        TargetClass target = new TargetClass();
        source.targetInnerField = target.new MemberInnerClass();
        
        // Abstract feature (simulated via abstract inner class)
        abstract static class AbstractHelper {
            abstract Object invokeSourceMethod(SourceClass src);
        }

        AbstractHelper helper = new AbstractHelper() {
            @Override
            Object invokeSourceMethod(SourceClass src) {
                try {
                    // instanceReference.methodName(arguments)
                    return src.moveCandidateMethod();
                } catch (IOException e) {
                    return null;
                }
            }
        };
        return helper.invokeSourceMethod(source);
    }
}