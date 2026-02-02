```java
package pkg;

import java.util.*;
import java.lang.reflect.*;

class Helper {
    protected int protectedField;
}

class HelperBase {
    protected void baseMethod() {}
}

interface SourceInterface {
    TargetInterface targetField = null;

    class Inner1 extends HelperBase {
        final List<String> methodToMove(int... args) {
            TargetInterface t = SourceInterface.targetField;

            try {
                Object obj = new Object();
                for (int i = 0; i < 10; i++) {
                    if (i == 5) continue;
                }
                Helper helper = new Helper();
                int val = helper.protectedField;
                super.baseMethod();
                Method m = Helper.class.getMethod("toString");
                m.invoke(helper);
            } catch (Exception e) {
            }

            List<String> result = new ArrayList<>();
            for (int arg : args) {
                result.add(String.valueOf(arg));
            }
            return result;
        }
    }

    class Inner2 {}
}

strictfp interface TargetInterface extends HelperBase {
    default void methodContainingLocal() {
        class LocalInner {
            // Method will be moved here
        }
    }
}
```