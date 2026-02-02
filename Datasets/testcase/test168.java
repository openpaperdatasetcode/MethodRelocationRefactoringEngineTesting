```java
package p;

public @interface SourceAnnotation {
    public default TargetAnnotation methodToMove(TargetAnnotation param) {
        class LocalInner<T extends Base> extends Base {
            private int field;
            private LocalInner() {
                super();
                field = 1;
                int x = protectedField;
            }
            void example() {
                overloaded();
                overloaded(1);
            }
        }

        LocalInner<Base> obj = new LocalInner<>();
        int value = obj.field;
        ;
        switch (value) {
            case 1: break;
            default: break;
        }
        this.helper();
        if (param != null) {
            this.methodToMove(null);
        }
        return param;
    }
    public default void helper() {}
}

strictfp @interface TargetAnnotation {}

class Base {
    protected int protectedField;
    public Base() {
        protectedField = 42;
    }
    public void overloaded() {}
    public void overloaded(int x) {}
}
```