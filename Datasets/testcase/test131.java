```java
package pkg;

import java.sql.SQLException;

public class TargetTopLevel<T> {
    public class TargetInner<U> implements MyInterface {
        public void methodWithLocalClass() {
            class LocalInTarget {}
        }

        strictfp TargetInner(Void v) throws SQLException {
            super();
            int y = 0;
        }
    }
}

interface MyInterface {}

class Container {
    private static class SourceClass<S> {
        TargetTopLevel<?>.TargetInner<?> targetField;

        void sourceMethod() {
            class LocalInSource {}
            Runnable r = new Runnable() {
                public void run() {}
            };
        }

        private class InnerSource {
            InnerSource() throws SQLException {
                this(0);
            }

            strictfp InnerSource(int x) throws SQLException {
                super();
                int z = x;
                targetField.toString();
            }
        }
    }
}
```