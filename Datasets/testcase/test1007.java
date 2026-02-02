import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    // Source contains target's field (per_condition)
    private TargetClass.MemberInnerClass targetInnerField;

    public void outerMethod() {
        // Inner class with recursive structure (source_inner_rec)
        class SourceInnerClass {
            // strictfp access modifier, instance method, return List<String>
            strictfp List<String> moveCandidateMethod() {
                // Depends on static field
                int staticVal = OtherClass.staticField;
                // Variable call
                targetInnerField = new TargetClass().new MemberInnerClass(staticVal);
                // Expression statement
                String processed = targetInnerField.getValue() + "_processed";
                List<String> result = new ArrayList<>();
                result.add(processed);
                
                // No new exception thrown
                try {
                    result.add(targetInnerField.process(staticVal));
                } catch (NullPointerException e) {
                    // Silent handling, no new exception instantiation
                }
                return result;
            }
        }

        SourceInnerClass inner = new SourceInnerClass();
        inner.moveCandidateMethod();
    }
}

class TargetClass {
    // Member inner class (target_feature)
    class MemberInnerClass {
        private int value;

        public MemberInnerClass(int val) {
            this.value = val;
        }

        public String getValue() {
            return String.valueOf(this.value);
        }

        public String process(int num) {
            return String.valueOf(this.value * num);
        }
    }
}

// Others class for call_method
class OtherClass {
    public static int staticField = 10;

    // Public, static, return String, pos: Lambda expressions, target_feature: (parameters) -> methodBody
    public static String callMethod(SourceClass source) {
        return ((param) -> {
            source.outerMethod();
            return new TargetClass().new MemberInnerClass(param).getValue();
        }).apply(staticField);
    }
}