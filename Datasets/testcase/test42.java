```java
package pkg;

@interface NonNull {}

final class SourceClass<T> {
    private static TargetClass<?> targetField;

    static {
        targetField = new TargetClass<>();
    }

    static class Base {
        protected int field = 1;
    }

    static class Nested1 extends Base {
        private Object methodToMove() {
            int x = super.field;
            switch (x) {
                case 1:
                    int a;
                    a = 10;
                    targetField.toString();
                    Object obj = null;
                    obj = (@NonNull String) "hello";
                    break;
                default:
                    break;
            }
            return null;
        }
    }
}

public class TargetClass<U> {
    Object anonymous = new Object() {};
}
```