import java.util.ArrayList;
import java.util.List;

final class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        protected List<String> overridingMethod() {
            return new ArrayList<>();
        }
    }

    // Subclass for overriding feature (sub_class)
    class SubInnerClass extends MemberInnerSourceClass {
        @Override
        protected List<String> overridingMethod() {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(1));
            return list;
        }
    }

    public TargetClass sourceMethod() throws IllegalArgumentException {
        String varCall = targetField.toString();
        labeledBlock: {
            // Expression statement
            varCall = varCall + " - processed";
            
            // Throw statement & requires_throws
            if (targetField == null) {
                throw new IllegalArgumentException("Target field is null: " + varCall);
            }

            // For loop with overriding method (matches overriding feature spec)
            for (int i = 0; i < 1; i++) {
                MemberInnerSourceClass instanceRef = new SubInnerClass();
                List<String> overrideResult = instanceRef.overridingMethod();
                varCall = overrideResult.get(0);
                break labeledBlock;
            }
        }

        // Local inner class (source_class feature)
        class LocalInnerSourceClass {
            void processTarget() {
                // Target_inner_rec: call recursive inner method of target
                targetField.localInnerTargetClass.recursiveMethod(3);
            }
        }
        new LocalInnerSourceClass().processTarget();

        return targetField;
    }
}

public class TargetClass extends ArrayList<String> {
    // Local inner class (target_class feature)
    class LocalInnerTargetClass {
        void recursiveMethod(int count) {
            if (count > 0) {
                recursiveMethod(count - 1); // target_inner_rec (recursive)
            }
        }
    }
    
    // Target inner instance (target_inner)
    LocalInnerTargetClass localInnerTargetClass = new LocalInnerTargetClass();
}