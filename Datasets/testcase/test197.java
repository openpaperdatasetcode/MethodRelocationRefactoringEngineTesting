```java
class Base {
    protected int protectedField = 1;
    public Object someMethod() {
        return "Base";
    }
}

abstract class Source extends Base {
    static class Nested {}

    @Override
    public Object someMethod() {
        return "Source";
    }

    private Object methodToMove(Target<String>.TargetInner inner) {
        int a = 1, b = 2, c = 3;
        int d = (a + b) * c;

        switch (d) {
            case 9:
                break;
            default:
                break;
        }

        class LocalClass {
            void localMethod() {}
        }
        LocalClass local = new LocalClass();
        local.localMethod();

        int value = protectedField;

        Object superResult = super.someMethod();

        Object thisResult = someMethod();

        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
        r.run();

        System.out.println(inner.toString());

        return thisResult;
    }
}

abstract class Target<T> {
    class TargetInner {}
}
```