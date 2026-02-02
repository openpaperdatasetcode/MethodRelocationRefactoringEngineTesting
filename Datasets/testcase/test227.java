```java
package p;

import java.util.*;

strictfp class SourceClass {
    static class StaticNested {}

    class InnerClass extends BaseForInner {
        public int methodToMove(Target target) {
            if (target == null) {
                throw new NullPointerException();
            }
            List list = new ArrayList();
            int sum = 0;
            for (Object o : list) {
                class LocalType {}
                String result = super.protectedMethod();
                sum += result.length();
            }
            return sum;
        }
    }

    Runnable anonymous = new Runnable() {
        public void run() {}
    };
}

class BaseForInner {
    protected String protectedMethod() {
        return "base";
    }
}

public class Target {
    protected static class StaticNestedTarget extends BaseForTarget {}
}

class BaseForTarget {
    protected String protectedMethod() {
        return "targetBase";
    }
}
```