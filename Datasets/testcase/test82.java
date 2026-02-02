```java
package pkg;

import java.util.ArrayList;

class SourceSuper {
    public int field;
}

class TargetSuper {
    int methodName() {
        return 0;
    }
}

public class Source<T> extends SourceSuper {
    Target<?> targetField;

    class MemberInner {
    }

    private Source() {
        super();
        for (int i = 0; i < 2; i++) {
            int x = super.field;
            int y = super.field;
            if (i == 1) {
                throw new NullPointerException();
            }
        }
        String s = "test";
        int len = s.length();
        ArrayList<String> list = new ArrayList<>();
    }

    public void methodWithLocalInner() {
        class LocalInner {
        }
    }
}

public class Target<U> extends TargetSuper {
    final int callMethod() {
        Runnable r = new Runnable() {
            public void run() {
                int val = Target.super.methodName();
            }
        };
        return 0;
    }
}
```