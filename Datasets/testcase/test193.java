```java
package pkg;

class Container {
    protected interface Target {
        class InnerClass {
            public int field = 1;
        }
    }
}

sealed interface Source<T> permits SubSource {
    static class NestedClass {}
    class InnerClass {}
    static int staticField = 10;

    private Helper helper = new Helper();

    default Container.Target methodToMove(Container.Target param) {
        if (param == null) {
            throw new NullPointerException();
        }

        switch (staticField) {
            case 10:
                Container.Target.InnerClass inner = new Container.Target.InnerClass();
                int val = inner.field;
                break;
            default:
        }

        for (int i = 0; i < 3; i++) {
            helper.m1().m2().m3();
        }

        return param;
    }

    default Container.Target methodToMove() {
        return null;
    }
}

final class SubSource implements Source<Integer> {}

class Helper {
    Helper m1() { return this; }
    Helper m2() { return this; }
    void m3() {}
}

class Caller {
    strictfp Object invoke(Source<?> src, Container.Target tgt) {
        try {
            return src.methodToMove(tgt);
        } catch (Exception e) {
            return null;
        }
    }
}
```