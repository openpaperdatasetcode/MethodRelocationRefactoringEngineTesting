```java
package pkg;

public class TestCase61846 {
    static sealed class Base permits Source {}

    protected static class Source<T> extends Base {
        private target_inner_rec<Integer> targetField;

        protected static class Nested {}

        Runnable r1 = new Runnable() {
            @Override
            public void run() {}
        };

        private final target_inner_rec<String> methodToMove() throws Exception {
            abstract class HelperBase {
                void methodName() {
                    System.out.println("HelperBase method");
                }
            }

            HelperBase obj = new HelperBase() {
                @Override
                void methodName() {
                    super.methodName();
                }
            };

            if (targetField == null) {
                throw new Exception("Field access exception");
            }
            throw new Exception("Test exception");
        }
    }

    protected static class target_inner_rec<U> {
        Runnable r2 = new Runnable() {
            @Override
            public void run() {}
        };
    }
}
```