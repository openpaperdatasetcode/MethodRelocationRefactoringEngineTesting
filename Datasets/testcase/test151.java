```java
package pkg;

import java.util.ArrayList;

interface SomeInterface997 {
}

class SourceClass997 implements SomeInterface997 {

    class MemberInner {
    }

    /**
     * Method Javadoc
     */
    @Deprecated
    public final void methodToMove(TargetClass997 targetObj) {
        targetObj.field = 1;
        int localVar = 5;
        System.out.println(localVar);
        new ArrayList<>();
        class LocalInner {
        }
    }
}

public class TargetClass997 {
    int field;

    public class TargetInner {
    }
}
```