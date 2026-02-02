```java
package pkg71922;

class SourceClass<T> {
    private TargetClass targetField;

    private abstract class InnerBase {
        abstract void innerMethod();
    }

    private TargetClass.StaticNested methodToMove() {
        new Object() {
            void anonymousMethod() {
                class LocalInner {
                }
                LocalInner local = new LocalInner();
            }
        }.anonymousMethod();

        int x = 1;
        switch (x) {
            case 1:
                Object obj = new Object();
                class LocalType {
                }
                LocalType lt = new LocalType();
                this.toString();
                targetField.toString();
                break;
        }
        return new TargetClass.StaticNested();
    }
}

class TargetClass<U> {
    static class StaticNested {
    }
}

class OtherClass {
    public int callMethod() {
        java.util.function.Supplier<TargetClass.StaticNested> supplier = 
            () -> new SourceClass().methodToMove();
        return supplier.get().hashCode();
    }
}
```