package refactoring.test;

// Parent class for call_method (parent_class type)
class TargetParentClass<T> {
    protected int parentField = 2;

    protected static <T> TargetClass<T>.TargetInner classNameMethod(T value) {
        TargetClass<T> target = new TargetClass<>(value);
        return target.new TargetInner(value);
    }
}

// Target class: protected modifier, type parameter + member inner class (target_feature)
protected class TargetClass<T> extends TargetParentClass<T> {
    private T thisField; // this.field for IfStatement feature

    // Member inner class (target_inner - target class of method)
    public class TargetInner {
        private T innerValue;

        public TargetInner(T innerValue) {
            this.innerValue = innerValue;
        }

        public T getInnerValue() {
            return innerValue;
        }

        public int getValueAsInt() {
            return innerValue instanceof Number ? ((Number) innerValue).intValue() : 0;
        }
    }

    public TargetClass(T thisField) {
        this.thisField = thisField;
    }

    public T getThisField() {
        return thisField;
    }
}

// Source abstract class: abstract modifier, anonymous inner + local inner class (source_feature)
public abstract class SourceClass {
    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Inner recursive class (source_inner_rec - method_position)
    public class SourceInnerRecursive {
        // IfStatement feature: private modifier, this.field, 2, pos=source
        private int ifStatementFeature(TargetClass<Integer> target) {
            int result = 0;
            if (target.getThisField() == 2) { // this.field + 2
                result = target.parentField;
            } else {
                result = 0;
            }
            return result;
        }

        // Accessor method: default access, base return type (int), target parameter (per_condition)
        int getTargetInnerValue(TargetClass<Integer> targetParam) {
            // Constructor invocation (method feature)
            TargetClass<Integer>.TargetInner targetInner = targetParam.new TargetInner(2);

            // Variable call feature
            int varCall = targetInner.getValueAsInt();

            // Execute IfStatement feature
            int ifResult = ifStatementFeature(targetParam);

            // call_method: parent_class type, protected modifier, instance + ClassName.methodName(), pos=switch, return TargetInner
            TargetClass<Integer>.TargetInner callResult;
            switch (varCall) { // pos=switch
                case 2:
                    callResult = TargetParentClass.classNameMethod(2); // ClassName.methodName(arguments)
                    break;
                default:
                    callResult = TargetParentClass.classNameMethod(0);
                    break;
            }

            // Local inner class (source_feature)
            class LocalInnerClass {
                int combineValues(int val1, int val2) {
                    return val1 + val2;
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();

            // Execute anonymous inner class
            anonymousInner.run();

            // No new exception thrown feature
            return localInner.combineValues(varCall, ifResult) + callResult.getValueAsInt();
        }
    }
}