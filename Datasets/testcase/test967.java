import java.util.List;

// Source class: normal, private modifier, same package, implements + local inner + static nested
private class SourceClass implements SourceInterface {
    // Per condition: source contains the field of the target
    private TargetClass targetInnerField;

    // Static nested class (source feature)
    static class SourceStaticNested {
        int superField = 1; // Matches "super.field" and "1" in IfStatement feature
    }

    // Abstract method (simulated via inner abstract class for private access)
    abstract static class AbstractMethodHolder {
        private abstract void moveCandidateMethod(TargetClass targetParam);
    }

    @Override
    public void interfaceMethod() {
        // Local inner class (source feature)
        class SourceLocalInner {
            void processTarget(TargetClass target) {
                // IfStatement (private modifier, super.field, 1, pos: same_package_target)
                privateIfBlock: {
                    if (SourceStaticNested.superField == 1) {
                        target.value = 1; // same_package_target position
                    }
                }

                // Assignment feature (numbers:2, public modifier, exp:Assignment)
                publicAssignmentBlock: {
                    target.value = 2; // Number 2, assignment expression
                }

                // Raw type usage
                List rawList = java.util.Arrays.asList(target.value);
                // Variable call
                int varCall = target.value;
                // Break statement
                for (int i = 0; i < 5; i++) {
                    if (i == varCall) {
                        break;
                    }
                    rawList.add(i);
                }

                // No new exception thrown
                try {
                    target.process(rawList);
                } catch (Exception e) {
                    // Silent handling, no new exception instantiation
                }
            }
        }

        // Invoke inner class method with target field
        new SourceLocalInner().processTarget(targetInnerField);
    }
}

// Interface for "implements" source feature
interface SourceInterface {
    void interfaceMethod();
}

// Target class: normal, public modifier, empty target_feature
public class TargetClass {
    int value;

    void process(List list) {
        // Target_inner_rec (recursive inner context)
        this.value = list.size();
    }
}