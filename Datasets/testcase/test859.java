package refactoring.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Source class: normal, private modifier, same package as target, features: local inner class, member inner class
private class SourceClass {
    // Per condition: source contains target class field
    TargetClass<String> targetField = new TargetClass<>("target-data");

    // Member inner class (source_class feature)
    class SourceInnerRecClass {
        /**
         * Javadoc for overloading method (method javadoc feature)
         * @param input input string
         * @return List<String> result
         */
        // Method to be refactored: overloading, return List<String>, public access, position source_inner_rec
        public List<String> moveMethod(String input) {
            // Variable call feature
            int counter = 0;
            List<String> resultList = new ArrayList<>();

            // While statement feature
            while (counter < 5) {
                resultList.add(input + "-" + counter);
                counter++;
            }

            // Type declaration statement feature
            SourceLocalInner localInner;
            localInner = new SourceLocalInner();
            // Depends on inner class feature
            resultList = localInner.processList(resultList);

            // Overloading method call (collection operations pos)
            moveMethod(resultList);

            // Used_by_reflection feature
            try {
                Method method = TargetInnerClass.class.getMethod("addData", String.class);
                TargetInnerClass innerInstance = targetField.new TargetInnerClass();
                method.invoke(innerInstance, "reflection-data");
            } catch (Exception e) {
                // No_new_exception feature (no new custom exception thrown)
                e.printStackTrace();
            }

            return resultList;
        }

        // Overloading method (type: overloading, modifier: public, return_type: void, pos: collection operations)
        public void moveMethod(List<String> collection) {
            // method_feature: 1
            int num = 1;
            // method_feature: source (access source class field)
            String targetData = SourceClass.this.targetField.getData();
            // method_feature: overloading (confirm overloading)
            // method_feature: super.methodName(arguments)
            super.toString();
            
            collection.add(targetData + "-" + num);
        }

        // Local inner class (source_class feature)
        class SourceLocalInner {
            List<String> processList(List<String> list) {
                List<String> processed = new ArrayList<>();
                for (String s : list) {
                    processed.add(s.toUpperCase());
                }
                return processed;
            }
        }
    }
}

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * @param <T> type parameter (target_feature: type parameter)
 */
// Target class: normal, protected modifier, same package, target_feature: javadoc, type parameter, member inner class
protected class TargetClass<T> {
    private T data;

    public TargetClass(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    // Member inner class (target_feature: member inner class)
    class TargetInnerClass {
        private List<String> innerList = new ArrayList<>();

        public void addData(String data) {
            innerList.add(data);
        }

        public List<String> getInnerList() {
            return innerList;
        }
    }
}