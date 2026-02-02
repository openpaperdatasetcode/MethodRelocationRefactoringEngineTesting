import java.util.List;
import java.util.ArrayList;

// Source generic class (public modifier, same package as target)
public class SourceClass<T> {
    // Member inner class (source_class feature)
    public class FirstInnerClass {
        // Recursive inner class (source_inner_rec position)
        public class SecondInnerClass {
            // Overloading method 1 (private, base type return, target parameter)
            private int methodToMove(TargetClass<Integer> targetParam) {
                int count = 0;
                // Raw type usage
                List rawList = new ArrayList();
                // Do statement
                do {
                    // Variable call
                    rawList.add(targetParam.getValue());
                    count++;
                    // Variable call
                    targetParam.updateValue(count);
                } while (count < 5);
                return count;
            }

            // Overloading method 2 (overloading feature, private, base type return, target parameter)
            private long methodToMove(TargetClass<String> targetParam) {
                long sum = 0;
                // Raw type usage
                ArrayList rawArrayList = new ArrayList();
                // Do statement
                do {
                    // Variable call
                    rawArrayList.add(targetParam.getValue());
                    sum += targetParam.getValue().length();
                    // Variable call
                    targetParam.updateValue(String.valueOf(sum));
                } while (sum < 10);
                return sum;
            }
        }

        // Anonymous inner class (source_class feature)
        Runnable sourceAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous inner class");
            }
        };
    }

    // Target generic class (private modifier, same package as source)
    private class TargetClass<U> {
        private U value;

        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class");
            }
        };

        public U getValue() {
            return value;
        }

        public void updateValue(U value) {
            this.value = value;
        }
    }
}