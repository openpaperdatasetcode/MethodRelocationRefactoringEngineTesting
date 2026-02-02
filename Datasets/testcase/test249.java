```java
import java.util.function.Supplier;

public class SourceClass {
    private TargetClass targetField = new TargetClass() {};

    static {
        // Static block
    }

    /**
     * Javadoc for moved method.
     */
    private void methodToMove() {
        targetField.doSomething();
        Object o = new Object() {
            @Override
            public String toString() {
                return "test";
            }
        };
        String s = "hello";
        int len = s.length();
    }
}

class SuperClass {
    public void methodToMove() {}
}

class TargetClass extends SuperClass {
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {}
    };

    private TargetClass() {}

    public static void staticMethod() {
        Supplier<TargetClass> supplier = TargetClass::new;
    }

    public void doSomething() {}
}
```