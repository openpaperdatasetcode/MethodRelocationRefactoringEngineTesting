```java
package pkg;

public interface SourceInterface {
    static class StaticNested {}

    class MemberInner {
        protected TargetInterface targetField;

        protected final TargetInterface methodToMove() {
            int x = 0;
            switch (x) {
                case 0:
                    assert targetField != null;
                    break;
            }
            return targetField;
        }

        protected final TargetInterface methodToMove(int dummy) {
            return null;
        }
    }
}

public interface TargetInterface {
    default void methodWithLocal() {
        class LocalInner {}
    }
}
```