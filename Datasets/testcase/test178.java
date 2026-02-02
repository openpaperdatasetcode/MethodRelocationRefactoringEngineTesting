```java
public class SourceClass {

    public static class StaticNested {
    }

    public final void methodToMove(TargetClass target, String... args) {
        new Object() {
            {
                label:
                new HelperClass(target.field, 2, 3);
            }
        };
    }

    private static class HelperClass {
        private HelperClass(int a, int b, int c) {
        }
    }
}

public class TargetClass {
    public int field = 1;
    
    public class InnerClass {
    }
}
```