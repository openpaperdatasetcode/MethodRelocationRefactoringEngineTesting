```java
package pkg;

class SourceClass {
    private TargetClass targetField;

    public class MemberInnerClass {}
    public static class StaticNestedClass {}

    @Deprecated
    public synchronized void methodToMove(int... values) {
        synchronized (targetField) {
            targetField.doSomething(values.length > 0 ? values[0] : 0);
        }
        super.toString();
        Object obj = new Object();
        if (values.length > 100) {
            throw new RuntimeException("Too many arguments");
        }
        String s = "example";
        int length = s.length();
    }
}

abstract class TargetClass {
    public static class NestedClassInTarget {}
    public abstract void doSomething(int x);
}

strictfp class TargetSubClass extends TargetClass {
    @Override
    public void doSomething(int x) {}

    public void callMethod() {
        Object[] array = new Object[] {
            ((Runnable) () -> super.methodToMove(1, 2, 3)).run()
        };
    }
}
```