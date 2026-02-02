```java
package p1;

import p2.OuterTarget;

class SourceClass {
    OuterTarget.TargetClass targetField;

    class MemberInnerClass {}

    SourceClass(OuterTarget.TargetClass target) {
        this.targetField = target;
    }

    strictfp OuterTarget.TargetClass recursiveMethod(int n) {
        if (n <= 0) {
            return targetField;
        }

        class LocalInner {
            LocalInner() {
                super();
            }
        }

        try {
            LocalInner local = new LocalInner();
            int x = n;
            if (targetField != null) {
                x++;
            }
        } catch (Exception e) {}

        return recursiveMethod(n - 1);
    }
}
```

```java
package p2;

public class OuterTarget {
    /**
     * Javadoc for TargetClass.
     */
    protected static class TargetClass {
        public static class NestedInTarget {}

        public class TargetInner {}
    }
}
```