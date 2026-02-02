```java
package p1;

import p2.TargetClass;

abstract class SourceClass {
    protected Object protectedField;

    public static class StaticNested {
        void methodInStaticNested() {
            new Object() {
                @Deprecated
                Object moveMethod(TargetClass target) {
                    class LocalClass {}
                    LocalClass lc = new LocalClass();
                    Object result = protectedField;
                    return result;
                }
            };
        }
    }
}
```

```java
package p2;

public abstract class TargetClass {
    public static class TargetInner {}
}
```