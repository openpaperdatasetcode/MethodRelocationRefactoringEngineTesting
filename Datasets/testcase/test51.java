```java
package pkg;

import java.util.ArrayList;
import java.util.List;

class SuperSource {
    protected int field = 1;
}

protected class SourceClass extends SuperSource {

    private static class Nested1 {
        private int x = 1;
    }

    static class Nested2 {}

    @Deprecated
    synchronized int methodToMove(TargetClass target) throws NullPointerException {
        int superField = super.field;
        Nested1 n = new Nested1();
        int y = n.x;

        String s = new String("test");
        int[] arr = {1, 2, 3};
        for (int i : arr) {
            System.out.println(i);
        }

        switch (y) {
            case 1:
                break;
            default:
        }

        String trimmed = s.trim();
        Runnable r = () -> System.out.println(trimmed);

        if (target == null) {
            throw new NullPointerException();
        }
        return superField + y;
    }

    public class InnerClass {

        public interface MyInterface {
            List<String> methodToCall(TargetClass target);
        }

        public class StrictInnerClass implements MyInterface {
            private int result;

            @Override
            public strictfp List<String> methodToCall(TargetClass target) {
                this.result = SourceClass.this.methodToMove(target);
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(result));
                return list;
            }
        }
    }
}

public class TargetClass {
    public static class Nested {}
}
```