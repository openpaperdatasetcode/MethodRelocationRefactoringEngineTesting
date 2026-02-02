```java
package pkg84021;

public class Source {
    public Target targetField;

    public class MemberInner {
        Object methodToMove(Object... args) {
            if (Source.this.targetField != null) {
                // Access instance field
            }

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    // Anonymous inner class
                }
            };
            r.run(); // Variable call

            return args.length > 0 ? args[0] : null;
        }
    }
}

public class Target {
    public static class StaticNested {
    }
}
```