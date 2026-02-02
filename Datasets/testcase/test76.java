```java
package test;

import java.sql.SQLException;

abstract class Source {
    private Target targetField;

    protected static <T extends CharSequence> Object methodToMove(Target t) throws SQLException {
        t.doSomething();
        throw new SQLException();
    }
}

public class Target {
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    public void doSomething() {}
}
```