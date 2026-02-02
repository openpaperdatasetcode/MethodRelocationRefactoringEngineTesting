```java
package p1;

import p2.TargetEnum;
import java.util.*;

public class Outer1 {
    protected enum SourceEnum implements MyInterface {
        CONST;

        private static List<String> field = Arrays.asList("a", "b");

        public static class StaticNested {}

        public class MemberInner {}

        protected List<String> methodToMove(TargetEnum target) {
            return new ArrayList<String>(field) {{
                add(target.name());
            }};
        }
    }
}

interface MyInterface {}
```

```java
package p2;

import java.util.*;

public class Outer2 {
    protected enum TargetEnum {
        TARGET_CONST;

        protected class TargetInner {}
    }
}
```