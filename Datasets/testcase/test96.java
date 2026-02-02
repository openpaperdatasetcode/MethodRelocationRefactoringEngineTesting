```java
package test;

public abstract sealed class SourceClass permits Permitted1, Permitted2 {
    void outerMethod() {}

    void methodWithLocalClasses() {
        class LocalInner1 {}
        class LocalInner2 {}
    }

    class SourceInner {
        private int methodToMove(TargetClass.TargetInner param) {
            new Object();
            int value = 10;
            outerMethod();
            SourceClass.this.toString();
            param.toString();
            return value;
        }
    }
}

final class Permitted1 extends SourceClass {}
final class Permitted2 extends SourceClass {}

public abstract class TargetClass {
    class TargetInner<T> {
        protected TargetClass accessor() {
            return TargetClass.this;
        }
    }

    protected TargetClass m1() { return this; }
    protected TargetClass m2() { return this; }
    protected TargetClass m3() { return this; }
}
```