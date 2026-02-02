```java
// pkg1/SourceClass.java
package pkg1;

import pkg2.TargetClass;

public class SourceClass {
    private static class StaticNested {
        void nestedMethod() {
            System.out.println("StaticNested method");
        }
    }

    private class MemberInner {
        void innerMethod() {
            System.out.println("MemberInner method");
        }
    }

    final TargetClass.TargetInner methodToMove(TargetClass.TargetInner param) {
        for (int i = 0; i < 3; i++) {
            System.out.println("For loop iteration: " + i);
        }

        Object lock = new Object();
        synchronized (lock) {
            int num1 = 1;
            int num2 = 2;
            int num3 = 3;
            System.out.println("Synchronized sum: " + (num1 + num2 + num3));
        }

        try {
            throw new Exception("Test exception");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        StaticNested nested = new StaticNested();
        nested.nestedMethod();

        MemberInner inner = new MemberInner();
        inner.innerMethod();

        java.util.function.Consumer<String> ref = System.out::println;
        ref.accept("Method reference output");

        param.innerMethodCall();

        return param;
    }
}
```

```java
// pkg2/TargetClass.java
package pkg2;

public class TargetClass {
    public TargetInner createTarget() {
        class TargetInner {
            void innerMethodCall() {
                System.out.println("TargetInner method");
            }
        }
        return new TargetInner();
    }

    public interface TargetInner {
        void innerMethodCall();
    }
}
```