```java
package p;

sealed class SourceClass permits SourceClass.PermittedSub {
    private int value;

    public SourceClass(int value) {
        this.value = value;
    }

    private void methodToMove() {}

    private void methodToMove(TargetRecord target) {
        int localVar = 5;
        while (localVar > 0) {
            if (localVar == 2) break;
            localVar--;
        }
        this.methodToMove();
        System.out.println(value + localVar);

        class LocalInner {}
        new LocalInner();

        new Runnable() {
            @Override
            public void run() {}
        };
    }

    strictfp int calledMethod() {
        throw new RuntimeException();
    }

    static final class PermittedSub extends SourceClass {
        public PermittedSub(int value) {
            super(value);
        }
    }
}

abstract record TargetRecord() {
    static class NestedClass {
        int overrideMethod() {
            return 1;
        }
    }
}
```