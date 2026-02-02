```java
package p;

class SourceClass {
    public TargetClass targetField = new TargetClass();
    public int fieldToAssign;

    public static class Nested1 {
        public static void method1() {}
    }

    public static class Nested2 {
        public static void method2() {}
    }

    public TargetClass methodToMove() {
        class LocalType {}
        new LocalType();
        if (fieldToAssign > 1000) {
            throw new RuntimeException("Test");
        }
        fieldToAssign = 42;
        int len = "test".length();
        SourceClass.Nested1.method1();
        SourceClass.Nested2.method2();
        return targetField;
    }
}

public class TargetClass {}
```