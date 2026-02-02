```java
package pkg;

public class TestCase79432 {

    /**
     * Target class for move method refactoring test.
     */
    public static class PublicTarget79432 {
        /**
         * Static nested class inside target.
         */
        public static class NestedClass {
        }

        public int getValue() {
            return 1;
        }

        public void action() {
        }

        public void alternateAction() {
        }
    }

    private static class PrivateSource79432 {
        private PublicTarget79432 targetField = new PublicTarget79432();

        public void methodToMove() throws NullPointerException {
            class LocalType {
                void execute() {
                    System.out.println("Local type executed");
                }
            }
            LocalType local = new LocalType();
            local.execute();

            int val = targetField.getValue();

            if (val > 0) {
                switch (val) {
                    case 1:
                        targetField.action();
                        break;
                    case 2:
                        throw new NullPointerException("Case 2 error");
                    default:
                        targetField.alternateAction();
                }
            }
        }
    }

    public static class CallerClass79432 {
        public <T> int callMethod(java.util.function.Function<T, Integer> func, T param) {
            int counter = 0;
            do {
                counter++;
                PrivateSource79432 src = new PrivateSource79432();
                src.methodToMove();
            } while (counter < 3 && func.apply(param) > 0);
            return counter;
        }
    }
}
```