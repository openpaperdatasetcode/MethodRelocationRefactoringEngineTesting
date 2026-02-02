```java
// File: Base.java
package pkg;

public class Base {
    public void baseMethod() {
    }
}
```

```java
// File: Helper.java
package pkg;

public class Helper {
    public static void helperMethod() {
    }
}
```

```java
// File: Source.java
package pkg;

public record Source(int field1) extends Base {
    private int privateField = 0;

    public class MemberInner {
    }

    public static class StaticNested {
    }

    void methodToMove(Target target) {
        ;
        int x = this.privateField;
        Helper.helperMethod();
        Object o = new Base() {
        };
        int value = 0;
        switch (value) {
            case 0:
                break;
        }
        super.baseMethod();
        try {
            Class.forName("pkg.Helper");
        } catch (ClassNotFoundException e) {
        }
        Runnable r = () -> {};
        r.run();
    }
}
```

```java
// File: Target.java
package pkg;

public record Target() {
    private Runnable r = new Runnable() {
        public void run() {
        }
    };
}
```