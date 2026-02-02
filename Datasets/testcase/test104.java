```java
package p1;

import p2.TargetClass;

class SourceClass {
    private int privateField = 1;

    static class StaticNested {}

    public void containerMethod() {
        class LocalInner {}
    }

    protected int methodToMove(TargetClass target) {
        int result;
        int i = 0;
        do {
            i++;
        } while (i < 1);

        try {
            Class<?> clazz = String.class;
            Class<?> cls = Class.forName("java.lang.Integer");
            int targetHash = target.hashCode();
            result = this.privateField + targetHash;
        } catch (ClassNotFoundException e) {
            result = -1;
        }
        return result;
    }

    private void methodToMove() {}
}
```

```java
package p1;

import p2.TargetClass;

public class CallerClass {
    public Object invokeMethod(SourceClass source, TargetClass target) {
        if (source != null) {
            return source.methodToMove(target);
        } else {
            return null;
        }
    }
}
```

```java
package p2;

/**
 * Abstract target class with member inner class
 */
public abstract class TargetClass {
    /** Inner class documentation */
    public class MemberInner {}
}
```