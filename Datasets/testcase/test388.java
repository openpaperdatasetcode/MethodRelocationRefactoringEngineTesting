package refactoring.test;

import java.io.IOException;
import java.lang.reflect.Method;

// Interface for target class implements feature
interface TargetProcessable {
    void process();
}

// Source class: normal, default modifier, same package, anonymous inner + local inner classes
class SourceClass {
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 10;

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        // Instance method to be refactored (all specified features)
        public Object refactorMethod(TargetClass targetParam) throws IOException { // per_condition + requires_throws
            // Type declaration statement
            TargetClass.TargetInnerClass innerClass;
            Object resultObj;

            // Variable call (target class field)
            targetParam.data = "refactor_data";
            targetParam.counter = outerProtectedField; // access_outer_protected
            innerClass = targetParam.new TargetInnerClass();

            // Super keywords usage
            SourceInnerClass.super.getClass();

            // Used by reflection
            try {
                Method targetMethod = TargetClass.class.getMethod("process");
                targetMethod.invoke(targetParam); // Call target's implemented method
                Method innerMethod = TargetClass.TargetInnerClass.class.getMethod("getInnerData");
                resultObj = innerMethod.invoke(innerClass);
            } catch (Exception e) {
                throw new IOException("Reflection error", e); // requires_throws (wrap exception)
            }

            // Local inner class (source feature)
            class SourceLocalInner {
                void updateTarget(TargetClass target) {
                    target.data = "local_updated_" + target.counter;
                }
            }
            new SourceLocalInner().updateTarget(targetParam);

            // Anonymous inner class (source feature)
            Runnable sourceAnonymous = new Runnable() {
                @Override
                public void run() {
                    targetParam.counter++; // access_outer_protected via target
                }
            };
            sourceAnonymous.run();

            // No new exception (only declared throws), return Object
            return resultObj;
        }
    }

    // Helper method to trigger inner class method
    public Object executeRefactor(TargetClass target) throws IOException {
        return new SourceInnerClass().refactorMethod(target);
    }
}

// Target class: normal, default modifier, implements + member inner class features
class TargetClass implements TargetProcessable { // implements feature
    String data;
    int counter;

    // Member inner class (target_feature)
    public class TargetInnerClass {
        String innerData = "inner_default";

        public String getInnerData() {
            return innerData + "_" + TargetClass.this.counter;
        }
    }

    // Implement interface method
    @Override
    public void process() {
        data = "processed_" + data;
    }
}