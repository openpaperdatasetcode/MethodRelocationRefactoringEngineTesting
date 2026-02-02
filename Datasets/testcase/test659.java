import java.lang.reflect.Method;
import java.util.List;

abstract class SourceClass extends ParentClass {
    class InnerSourceClass {
        private <T extends TargetClass & List<T>> TargetClass genericMethod(T targetParam) {
            return targetParam;
        }

        public Object sourceInnerRecMethod(TargetClass targetParam) {
            String varCall = targetParam.memberInnerTargetClass.targetField;
            int counter = 2;

            private labeledBlock: {
                while (counter > 0) {
                    // Empty statement
                    ;
                    if (counter == 2) {
                        targetParam.memberInnerTargetClass.targetField = varCall + counter;
                        break labeledBlock; // Break statement
                    }
                    counter--;
                }
            }

            // Used by reflection
            try {
                Method method = TargetClass.MemberInnerTargetClass.class.getMethod("recursiveMethod", int.class);
                method.invoke(targetParam.memberInnerTargetClass, 2);
            } catch (Exception e) {
                // No new exception thrown
            }

            // Generic method call in constructor parameter list
            TargetClass genericResult = new TargetClass(this.genericMethod(targetParam)) {};
            
            // Parent class call method in property assignment
            targetParam.memberInnerTargetClass.targetField = ParentClass.InnerParentClass.synchronizedMethod(targetParam).toString();

            // Recursive call (source_inner_rec)
            if (counter < 2) {
                return sourceInnerRecMethod(targetParam);
            }
            return genericResult;
        }
    }

    // Method with keyword parameter (method types parameter is:keywords)
    public Object sourceMethod(TargetClass thisParam) {
        return new InnerSourceClass().sourceInnerRecMethod(thisParam);
    }
}

class ParentClass {
    static class InnerParentClass {
        synchronized static TargetClass synchronizedMethod(TargetClass targetParam) {
            return targetParam;
        }
    }
}

public abstract class TargetClass {
    class MemberInnerTargetClass {
        String targetField = "targetField";
        
        void recursiveMethod(int count) {
            if (count > 0) {
                recursiveMethod(count - 1); // target_inner_rec
            }
        }
    }

    MemberInnerTargetClass memberInnerTargetClass = new MemberInnerTargetClass();

    // Constructor with generic method parameter
    public TargetClass(TargetClass param) {}
}