```java
package p;

public class TestCase {
    public static class Enclosing {
        private static class SourceClass {
            public static class Nested1 {}
            public static class Nested2 {}

            private Object methodToMove(TargetClass t) {
                class Local1 {}
                class Local2 {}
                if (t instanceof GenericBox) {}
                return null;
            }
        }
    }

    public static class GenericBox<T> {}
    public static class SuperClassForTarget {}
    public static class TargetClass extends SuperClassForTarget {
        public class InnerClass {}
    }
}
```