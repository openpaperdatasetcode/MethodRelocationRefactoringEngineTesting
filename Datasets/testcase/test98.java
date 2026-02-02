```java
// SourceClass.java
package pkg;

import java.util.ArrayList;
import java.util.List;

public class SourceClass extends ArrayList<String> {
    public TargetClass targetField;

    public static class StaticNested {
    }

    public class MemberInner {
        public MemberInner next;

        private List<String> methodToMove() {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (SourceClass.this.targetField.flag == 1) {
                    break;
                }
                list.add("item");
            }
            return list;
        }
    }
}

// TargetClass.java
package pkg;

import java.io.Serializable;

/**
 * Target class Javadoc.
 */
public class TargetClass implements Serializable {
    public int flag;

    public static class TargetStaticNested {
    }

    public class TargetInner {
    }
}
```