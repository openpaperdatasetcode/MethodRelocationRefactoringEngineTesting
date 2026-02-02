```java
// SourceClass.java
package pkg;

public class SourceClass {
    private TargetClass targetField;
    protected int protectedField = 5;
    public int instanceField = 10;

    public class MemberInner {
        public void demo() {
            System.out.println("Member inner class");
        }
    }

    public static class StaticInner {
        static int methodToMove(SourceClass source) {
            try {
                class LocalType {
                    int getValue() {
                        return 3;
                    }
                }
                LocalType var = new LocalType();
                int outerProtected = source.protectedField;
                int outerInstance = source.instanceField;
                return var.getValue() + outerProtected + outerInstance;
            } catch (RuntimeException e) {
                return -1;
            }
        }
    }

    public SourceClass() {
        new Runnable() {
            public void run() {}
        };
    }
}
```

```java
// TargetClass.java
package pkg;

/**
 * Abstract target class with Javadoc
 */
public abstract class TargetClass {
    public abstract void abstractMethod();

    public void demo() {
        new Runnable() {
            public void run() {}
        };
    }

    public static class TargetInner {}
}
```