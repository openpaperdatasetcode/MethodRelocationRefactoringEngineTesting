import java.util.ArrayList;
import java.util.List;

private class SourceClass<T> extends ParentClass {
    public static class StaticNestedSourceClass {
        public void helperMethod() {}
    }

    class MemberInnerSourceClass {
        public int innerClassMethod(TargetClass<String> param) {
            return param.staticNestedTargetClass.getValue();
        }
    }

    @Override
    List<String> sourceMethod(TargetClass<T> targetParam) {
        List<String> result = new ArrayList<>();
        String varCall = "test";
        labeledBlock: {
            // Type declaration statement
            StaticNestedSourceClass nestedObj = new StaticNestedSourceClass();
            MemberInnerSourceClass innerObj = new MemberInnerSourceClass();

            for (int i = 0; i < 1; i++) {
                switch (i) {
                    case 1:
                        // Overloading feature in switch block
                        overloadingMethod(targetParam, innerObj);
                        overloadingMethod(targetParam);
                        break labeledBlock;
                    default:
                        break;
                }
                result.add(varCall + innerObj.innerClassMethod((TargetClass<String>) targetParam));
            }
        }

        try {
            // Depends on inner class & target_inner_rec
            targetParam.staticNestedTargetClass.recursiveMethod(1);
        } catch (Exception e) {
            // No new exception thrown
            return result;
        }
        return result;
    }

    // Overloading method (protected modifier)
    protected void overloadingMethod(TargetClass<T> targetParam) {
        targetParam.staticNestedTargetClass.otherClassMethod();
    }

    // Overloaded method with instance reference call
    protected void overloadingMethod(TargetClass<T> targetParam, MemberInnerSourceClass innerInstance) {
        innerInstance.innerClassMethod((TargetClass<String>) targetParam);
    }

    // Override violation (invalid return type vs parent)
    @Override
    public String toString() {
        return super.toString();
    }
}

class ParentClass {
    public List<String> sourceMethod(Object param) {
        return new ArrayList<>();
    }
}

class TargetClass<U> {
    public static class StaticNestedTargetClass {
        public int getValue() {
            return 1;
        }

        public void otherClassMethod() {}

        public void recursiveMethod(int count) {
            if (count > 0) {
                recursiveMethod(count - 1); // target_inner_rec
            }
        }
    }
}