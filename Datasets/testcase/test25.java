```java
package pkg;

public final class Source<T> {
    private Target<String> targetField;

    public abstract class Inner1 {
        private synchronized void methodToMove() {
            ;
            try {
                targetField.toString();
            } catch (Exception e) {
            }
        }
    }

    public class Inner2 {
    }
}

public class Target<U> {
    public void methodToMove() {
    }

    public class TargetInner {
        public String callMethod() {
            do {
                methodToMove();
            } while (false);
            return "";
        }

        public String callMethod(int x) {
            return "";
        }
    }
}
```