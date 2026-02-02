```java
package pkg;

import java.util.function.Function;

public class TestContainer {
    public static class Target<T> {
        public Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
    }

    private static class Source {
        public Target<Integer> targetField = new Target<>();

        class Inner {
            private strictfp void methodToMove() {
                Runnable r = Source.this::protectedMethod;
                String s = "test";
                int len = s.length();
                Function<String, Integer> lenFunc = String::length;
            }
        }

        protected void protectedMethod() {
        }

        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
            }
        };
    }
}
```