```java
public class Source<T> {
    protected int protectedField = 1;
    private Target targetField;

    public class Inner {
        private static int methodToMove(Target param) {
            int x = Target.staticField;

            int[] arr = {1, 2, 3};
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }

            Source<String> outer = new Source<>();
            int y = outer.protectedField;

            java.util.function.Function<Integer, Integer> square = 
                a -> a * a;
            int z = square.apply(5);

            return x + sum + y + z;
        }
    }
}

public class Target {
    public static int staticField = 10;

    public void methodWithAnonymous() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
    }
}

class SuperClass {
    int methodName() {
        return 42;
    }
}

class OthersClass extends SuperClass {
    strictfp int callMethod() {
        int[] values = { super.methodName() };

        try {
            java.lang.reflect.Method method = Source.Inner.class
                .getDeclaredMethod("methodToMove", Target.class);
            method.setAccessible(true);
            method.invoke(null, new Target());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values[0];
    }
}
```