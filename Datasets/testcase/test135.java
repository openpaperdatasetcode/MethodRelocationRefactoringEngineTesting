```java
package a;

import b.Target;

public class Outer {
    protected int outerField = 5;

    private class Source {
        Target targetField = new Target();

        int methodToMove() {
            int value = outerField;
            switch (value) {
                case 5:
                    value *= 2;
                    break;
                default:
                    value = -1;
            }
            if (value < 0) {
                throw new RuntimeException("Negative value");
            }
            return value;
        }
    }

    static class StaticNested {}
    class MemberInner {}
}
```

```java
package b;

public non-sealed class Target {
    public void containerMethod() {
        class LocalInner extends Target {
            Target call_method(boolean flag) {
                if (flag) {
                    methodToMove();
                } else {
                    System.out.println("Alternative path");
                }
                return this;
            }
        }
        new LocalInner().call_method(true);
    }
}
```

```java
package a;

import java.lang.reflect.Method;

public class ReflectionCaller {
    public static void invokeMethod(Object obj) throws Exception {
        Method method = obj.getClass().getDeclaredMethod("methodToMove");
        method.setAccessible(true);
        method.invoke(obj);
    }
}
```