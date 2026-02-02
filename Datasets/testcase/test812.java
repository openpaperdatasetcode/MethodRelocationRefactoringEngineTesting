package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Super class for super constructor invocation
class SuperTargetClass<T> {
    protected T superField;
    public SuperTargetClass(T value) {
        this.superField = value;
    }
}

// Target class: generic, protected modifier, local inner class (target_feature), same package as source
protected class TargetClass<T> extends SuperTargetClass<T> {
    public T targetData;

    public TargetClass(T value) {
        super(value); // Super constructor invocation in target class
    }

    public int processGenericData(List<T> data) {
        // Local inner class (target_feature)
        class LocalInnerTarget {
            int countElements(List<T> list) {
                return list.size();
            }
        }
        return new LocalInnerTarget().countElements(data);
    }

    public T getTargetData() {
        return targetData;
    }

    public void setTargetData(T data) {
        this.targetData = data;
    }
}

// Source class: generic, public modifier, anonymous inner + member inner class (source_feature), same package as target
public class SourceClass<S> {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("initialValue");

    // Member inner class (source_feature)
    class SourceInnerClass {
        /**
         * Method Javadoc (method feature)
         * @param args varargs parameter (method type: varargs)
         * @return List<String> result
         */
        private List<String> refactorMethod(List<String>... args) {
            List<String> result = new ArrayList<>();
            int index = 0;

            // Array initialization (pos for generic method feature)
            String[] dataArray = new String[]{"1", "2", "3"};
            
            // Generic method feature: public modifier, 1, target, generic, instanceReference.methodName(arguments)
            public <T> int genericHelperMethod(TargetClass<T> targetInstance, List<T> data) {
                targetInstance.setTargetData((T) ("processed_" + 1));
                return targetInstance.processGenericData(data);
            }

            loop:
            for (List<String> argList : args) {
                index++;
                if (index > 3) {
                    break loop; // Break statement (method feature)
                }
                if (argList.isEmpty()) {
                    continue loop; // Continue statement (method feature)
                }

                // Variable call (method feature)
                String varCall = targetField.getTargetData();
                result.add(varCall);

                // Super constructor invocation (method feature)
                TargetClass<Integer> newTarget = new TargetClass<>(100);

                // Call generic helper method with array initialization data
                int count = genericHelperMethod(targetField, Arrays.asList(dataArray));
                result.add("Count: " + count);
            }

            // No new exception thrown (method feature)
            return result;
        }
    }

    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceInnerClass inner = new SourceInnerClass();
            inner.refactorMethod(new ArrayList<>(), Arrays.asList("test"));
        }
    };
}