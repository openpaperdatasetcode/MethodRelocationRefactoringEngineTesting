```java
public class TestCase84969 {

    protected static class Source {
        private Target targetField;

        static class Nested1 {
        }

        static class Nested2 {
        }

        private Object methodToMove(int param) {
            if (param == 0) {
                throw new NullPointerException();
            }
            targetField.accessProtected();
            new Nested1();
            return param;
        }
    }

    protected static class Target {
        class Inner {
        }

        protected void accessProtected() {
        }
    }
}
```