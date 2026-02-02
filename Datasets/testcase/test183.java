```java
// File: pkg/OuterForTarget.java
package pkg;

public class OuterForTarget {
    protected static class Target {
        public static class NestedInTarget {
        }
    }
}

// File: pkg/Source.java
package pkg;

public abstract class Source {
    public class MemberInner {
    }

    protected Object methodToMove(OuterForTarget.Target targetParam) throws Exception {
        class LocalInner {
        }

        ClassName obj = new ClassName();
        obj.field = 1;
        obj.field = 2;
        int x = obj.field;

        Box rawBox = new Box();
        rawBox.set("test");
        if (rawBox.get() instanceof String) {
            rawBox.toString();
        }

        new PrivateConstructorClass();

        if (x < 0) {
            throw new Exception();
        }
        return null;
    }

    private static class PrivateConstructorClass {
        private PrivateConstructorClass() {
        }
    }
}

class ClassName {
    public int field;
}

class Box<T> {
    private T t;
    public void set(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }
}
```