```java
public class SourceClass {
    private TargetClass targetField = new TargetClass();

    protected static int staticMethod() {
        return 0;
    }

    public static class Nested1 {
        public void nestedMethod() {
        }
    }

    public static class Nested2 {
    }

    protected Object methodToMove() {
        int x = staticMethod();
        TargetClass.Inner inner = targetField.new Inner();
        inner.innerMethod();
        Nested1 nested = new Nested1();
        nested.nestedMethod();
        return inner;
    }
}

public class TargetClass {
    private int privateField = 10;

    public class Inner {
        public void innerMethod() {
            int value = privateField;
        }
    }

    protected void callMethod(SourceClass source) {
        int condition = 1;
        switch (condition) {
            case 1:
                source.methodToMove();
                break;
            default:
                break;
        }
    }
}
```