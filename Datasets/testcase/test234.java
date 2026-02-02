```java
package samepackage;

class SourceSuper {
    int field = 10;

    public TargetClass methodName(int... args) {
        return null;
    }
}

strictfp class Source extends SourceSuper {
    private TargetClass targetField;

    @Override
    public TargetClass methodName(int... args) {
        int value = super.field * TargetClass.staticField;

        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break;
            }
        }

        Object obj = new Object();

        class LocalClass extends Object {
            LocalClass() {
                super();
            }
        }
        new LocalClass();

        synchronized (this) {
            System.out.println("synchronized block");
        }

        String s = "test";
        int len = s.length();

        return new TargetClass();
    }
}

final class TargetClass<T extends Number> {
    static class Nested {}
    static int staticField = 3;

    public TargetClass() {
    }
}
```