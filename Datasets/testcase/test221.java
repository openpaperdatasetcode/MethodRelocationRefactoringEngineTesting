```java
package p;

import java.lang.reflect.Method;

public class SourceClass {
    protected int protectedField = 1;
    private TargetClass targetField = new TargetClass();

    public void methodContainingLocalClass() {
        class LocalInner {
            private void methodToMove() throws Exception {
                class LocalType {}
                LocalType var = new LocalType();
                int val = protectedField;
                if (val > 0) {
                    throw new RuntimeException("Test exception");
                }
            }
        }

        try {
            LocalInner inner = new LocalInner();
            Method m = inner.getClass().getDeclaredMethod("methodToMove");
            m.setAccessible(true);
            m.invoke(inner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void methodContainingAnonymousClass() {
        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
        r.run();
    }
}

class TargetClass {
    public class Inner {}
}
```