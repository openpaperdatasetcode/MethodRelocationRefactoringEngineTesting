```java
package pkg;

import java.lang.reflect.Method;

class SuperSource {}

class Source extends SuperSource {
    private String value = "test";
    private Target<String> targetField;

    static class StaticNested {}

    class MemberInner {}

    Target<String> recursiveMethod(int n) {
        int localVar = 10;
        MemberInner inner = new MemberInner();

        switch (n) {
            case 0:
                break;
            case 1:
                Runnable r = () -> System.out.println(this.value);
                r.run();
                break;
            case 2:
                java.util.Date date = new java.util.Date();
                System.out.println(date);
                break;
        }

        if (n <= 0) {
            return targetField;
        } else {
            return recursiveMethod(n - 1);
        }
    }

    public static void main(String[] args) {
        try {
            Source src = new Source();
            Method method = Source.class.getDeclaredMethod("recursiveMethod", int.class);
            method.setAccessible(true);
            Target<String> result = (Target<String>) method.invoke(src, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Target<T> {
    public static class TargetNested {}
}
```