package refactoring.test;

import java.io.IOException;

// Source class: normal, protected modifier, same package as target, member inner + local inner classes
protected class SourceClass {
    // Member inner class (source feature)
    protected class SourceMemberInner {
        int innerValue;
    }

    /**
     * First overloading method (method javadoc feature)
     * @param targetParam target class parameter (per_condition)
     * @return Object result
     * @throws IOException required throws feature
     */
    public Object refactorMethod(TargetClass targetParam) throws IOException {
        // Variable call (target class field)
        targetParam.data = "first_overload";
        // Local inner class (source feature)
        class LocalInnerClass {
            void updateTarget(TargetClass target) {
                target.counter++;
            }
        }
        new LocalInnerClass().updateTarget(targetParam);
        return targetParam;
    }

    /**
     * Second overloading method (overloading type feature, method javadoc)
     * @param targetParam target class parameter (per_condition)
     * @param additional additional parameter for overloading
     * @return Object result
     * @throws IOException required throws feature
     */
    public Object refactorMethod(TargetClass targetParam, String additional) throws IOException {
        // Variable call (target class field)
        targetParam.data = additional;
        return targetParam;
    }
}

// Target class: normal, public modifier, empty target_feature
public class TargetClass {
    String data;
    int counter;
}