```java
package pkg;

public abstract sealed class Source<T> permits Source.Subclass {
    public class Inner {
        Inner next;

        private int getSomeValue() {
            return 3;
        }

        private int methodToMove(Target<String> target, String s) {
            class LocalInner {
                int value;
                LocalInner(int v) { value = v; }
                int getValue() { return value; }
            }
            LocalInner localInner = new LocalInner(10);
            int result = target.compute(s);
            int nextValue = (next != null) ? next.getSomeValue() : 0;
            return result + localInner.getValue() + nextValue;
        }

        private int methodToMove(Target<String> target) {
            return 1;
        }
    }

    static class Nested {
    }

    public static non-sealed class Subclass extends Source<Object> {
    }
}

final class Target<T> {
    public static class Nested {
    }

    public int compute(String s) {
        return s.length();
    }
}
```