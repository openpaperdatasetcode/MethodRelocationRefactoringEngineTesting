```java
package pkg;

public record SourceClass() {
    public static class NestedStatic {}
    public class InnerClass {}

    public final void methodToMove(target_inner_rec... args) {
        Runnable r = () -> {
            synchronized (this) {
                NestedStatic obj = new NestedStatic();
                obj.m1().m2().m3();
            }
        };
        r.run();
    }

    public final void methodToMove(String s) {}
}

class Host {
    protected record target_inner_rec() extends BaseClass {
        Object obj = new Object() {};
    }
}

class BaseClass {}
```