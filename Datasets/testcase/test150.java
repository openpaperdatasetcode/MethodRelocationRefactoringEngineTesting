```java
public class MoveMethodTest {

    static class HelperOuter {
        public static class HelperInner {
            public static String getString() {
                return "";
            }
        }
    }

    public record Source(Target<String> target) {
        protected int protectedField;

        public class SourceInner {
            private void methodToMove() throws java.io.IOException {
                ;
                protectedField = 5;
                String s = "test";
                s.length();
                int count = 0;
                while (count < 1) {
                    String result = HelperOuter.HelperInner.getString();
                    count++;
                }
            }
        }

        public Source {
            class LocalInner {}
        }
    }

    private record Target<T>() {
        public class TargetInner {}
    }
}
```