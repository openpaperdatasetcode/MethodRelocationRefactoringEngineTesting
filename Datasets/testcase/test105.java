```java
package p1;

import p2.Outer;
import java.util.*;

public class Source extends Outer {
    private int field;
    private static int staticField = 5;

    public static class Nested1 {}
    public static class Nested2 {}

    private List<String> recursiveMethod(Target target, int depth, String... messages) {
        if (depth <= 0) {
            return Arrays.asList(messages);
        }

        List<String> result = recursiveMethod(target, depth-1, messages);

        int i = 0;
        while (i < 10) {
            if (i++ == 5) continue;
            this.field = i;
        }

        Object objVar = "test";
        String str = (String) objVar;
        int len = str.length();

        HelperClass helper = new HelperClass();
        Object ret = helper.process("arg1", "arg2");

        int val = staticField;
        return result;
    }

    static class HelperClass {
        protected Object process(String... args) {
            return Arrays.toString(args);
        }
    }

    static {
        final Source src = new Source();
        Target tgt = src.new Target();
        List<String> res = src.recursiveMethod(tgt, 2, "a", "b");
    }
}
---
package p2;

public class Outer {
    protected class Target {
        public class Inner {}
    }
}
```