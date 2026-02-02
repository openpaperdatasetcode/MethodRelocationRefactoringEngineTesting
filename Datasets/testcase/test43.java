```java
// File: otherpkg/BaseClass.java
package otherpkg;

public class BaseClass {
    protected int field = 3;
}
```

```java
// File: pkg/SourceClass.java
package pkg;

import otherpkg.BaseClass;
import java.util.List;
import java.util.ArrayList;

class SourceClass {

    void outerMethod1() {
        class LocalInner1 extends BaseClass {
            protected int methodToMove(TargetClass target) throws Exception {
                int localVar = super.field;
                switch (localVar) {
                    case 1:
                        target.doSomething();
                        break;
                    default:
                        break;
                }
                int[] arr = new int[10];
                int value = arr[0];
                String s = new String();
                s.toString();
                List list = new ArrayList();
                list.add("test");
                return 0;
            }
        }
        new LocalInner1();
    }

    void outerMethod2() {
        class LocalInner2 {
        }
        new LocalInner2();
    }

    private static class TargetClass {
        void doSomething() {
            class LocalInTarget {
            }
            new LocalInTarget();
        }
    }
}
```