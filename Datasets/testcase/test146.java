```java
interface SomeInterface {
}

abstract class Target implements SomeInterface {
    void methodInTarget() {
        Runnable r = new Runnable() {
            public void run() {
            }
        };
    }
}

class Source<T> {
    private int field;

    class Inner1 {
        void safeMethod() {
        }
    }

    class Inner2 {
    }

    private Object methodToMove(T param, Target targetObj) {
        int localVar = this.field;
        Inner1 obj1 = null;
        Inner1 obj2 = null;
        Inner1 obj3 = null;
        Inner1 notNull = new Inner1();
        notNull.safeMethod();
        return null;
    }

    private Object methodToMove() {
        return null;
    }

    final int callMethod() {
        Target target = new Target() {
        };
        do {
            methodToMove(null, target);
        } while (false);
        return 0;
    }
}
```