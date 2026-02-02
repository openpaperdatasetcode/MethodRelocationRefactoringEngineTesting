```java
package pkg;

import java.util.ArrayList;
import java.util.List;

private sealed class Source permits Source.PermittedSub {
    TargetClass.TargetInner targetField;

    static class StaticNested {}

    static final class PermittedSub extends Source {}

    {
        new Runnable() {
            public void run() {}
        };
    }

    class InnerBase {
        void methodName(int a) {}
        void methodName(int a, int b) {}
        void methodName(String s) {}
    }

    class RecursiveInner extends InnerBase {
        RecursiveInner next;

        private int methodToMove() {
            label: {
                List<String> list = new ArrayList<>();
                list.add("test");
                super.methodName(1);
                super.methodName(1, 2);
                super.methodName("three");
                break label;
            }
            return 0;
        }
    }
}

class TargetClass {
    class TargetInner {}
}
```