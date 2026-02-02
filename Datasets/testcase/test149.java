```java
package pkg59681;

import java.util.ArrayList;
import java.util.List;

abstract class Source {
    protected int protectedField = 42;
    Target targetField;

    public abstract class Inner {
        Inner innerRef;

        List<String> method() {
            int num = 10; // NumberLiteral
            String s = String.valueOf(num); // Variable call
            int value = protectedField; // Access_outer_protected
            return new ArrayList<>();
        }
    }

    void localClassExample() {
        class LocalInner {}
    }
}

final class ConcreteSource extends Source {}

abstract class Target {
    final class TargetInner {}
}
```