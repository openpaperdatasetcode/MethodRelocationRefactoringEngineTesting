```java
public class SourceClass {
    private TargetClass<Integer> targetField;

    private class SourceInnerBase {
        protected Object field = new Object();
    }

    public class SourceInner extends SourceInnerBase {
        public Object methodToMove(Object... args) {
            volatile int count = 0;
            if (args.length > 0) {
                for (int i = 0; i < 10; i++) {
                    if (i == 2) break;
                }
                class LocalType {
                    int id;
                }
                LocalType t = new LocalType();
                t.id = 2;
                count = 2;
                System.out.println(count);
                int val = privateData;
                if (super.field != null) {
                    return 2;
                }
            }
            return null;
        }
    }

    private int privateData = 0;

    public void outerMethod() {
        class LocalInner {}
        LocalInner li = new LocalInner();
    }
}

public final class TargetClass<T> {
    public class TargetInner {
    }
}
```