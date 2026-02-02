```java
package p;

interface I {
    void m();
}

class Outer {
    protected static class Target<T> {
        public Object targetMethod() {
            return "test";
        }
    }
}

final class Source<K> implements I {
    private Outer.Target<String> targetField = new Outer.Target<>();

    @Override
    public void m() {}

    final Object methodToMove() {
        synchronized (this) {
            return targetField.targetMethod();
        }
    }
}

class SubSource extends Source<String> {
    public int lambdaCaller() {
        java.util.function.IntSupplier s = () -> {
            Object r = super.methodToMove();
            return r.hashCode();
        };
        return s.getAsInt();
    }
}

public class TestCase81474 {
    public static void main(String[] args) throws Exception {
        Source<?> src = new Source<>();
        java.lang.reflect.Method method = Source.class.getDeclaredMethod("methodToMove");
        Object result = method.invoke(src);
        System.out.println(result);
    }
}
```