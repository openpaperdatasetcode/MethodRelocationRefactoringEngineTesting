```java
strictfp class SourceClass {
    private int privateField = 5;
    TargetClass targetField;

    class MemberInner {}
    static class StaticNested {}

    strictfp void recursiveMethod(int n) {
        if (n <= 0) return;
        try {
            recursiveMethod(n - 1);
            new Base(SourceClass.StaticNested.class.getDeclaredFields()[0].getName().length()) {
                {
                    int value = privateField;
                }
            };
            class Local extends BaseForLocal {
                {
                    int num = super.getNumber();
                }
            }
            new Local();
        } catch (Exception e) {}
    }
}

final class TargetClass {
    static class NestedInTarget {}
}

class Base {
    Base(int x) {}
}

class BaseForLocal {
    int getNumber() {
        return 10;
    }
}
```