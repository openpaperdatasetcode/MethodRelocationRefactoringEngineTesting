```java
package pkg97487;

import java.util.ArrayList;
import java.util.List;

public class TestCase97487 {
    protected abstract static class Source<T> {
        Target targetField;

        public static class Nested1 {}
        public static class Nested2 {}

        protected int methodToMove() throws Exception {
            ;
            class Local {
                Local() {
                    super();
                }
            }
            new Local();
            String s = super.toString();
            List list = new ArrayList();
            instanceMethod();
            s.length();
            return 0;
        }

        private void instanceMethod() {}
    }

    public static class Target {
        public void methodContainingLocalClass() {
            class LocalInner {}
        }
    }
}
```