```java
public enum source {
    A, B;

    private target<?> t;
    protected int protectedField;

    class MemberInner {
        private MemberInner(Object o, int i) {}
    }

    static class StaticNested {}

    synchronized int methodToMove() {
        class LocalHelper {
            Object helperMethod() {
                MemberInner inner = new MemberInner(null, 0);
                return protectedField;
            }
        }
        new MemberInner(new LocalHelper().helperMethod(), 1);
        return 0;
    }
}

/**
 * Javadoc for target.
 */
abstract enum target<T> {
    CONSTANT;

    void methodWithLocalClass() {
        class LocalInner {}
    }
}
```