```java
package p;

class SuperSource {
    protected int protectedField = 10;
}

class SuperTarget {
}

final class PermittedSource extends Source {
    PermittedSource(int x) {
        super(x);
    }
}

sealed record Source(int x) extends SuperSource permits PermittedSource {
    Target targetField;

    class Inner1 {
    }

    class Inner2 {
    }

    class SourceInner {
        protected SourceInner(int a, int b) {
            this.methodToMove(a, b);
        }

        strictfp int methodToMove(int a, int b) throws NullPointerException {
            int val = Source.this.protectedField;
            if (a == 0 && b == 0) {
                throw new NullPointerException();
            }
            int sum = a + b;
            Runnable r = () -> {
                System.out.println(sum);
            };
            r.run();
            return val;
        }
    }
}

public record Target(int y) extends SuperTarget {
    class TargetInner {
    }
}
```