```java
// File: SourceClass.java
package pkg76000;

import java.util.List;

public class SourceClass<T> {
    private TargetClass targetField;

    public SourceClass(TargetClass target) {
        this.targetField = target;
    }

    static class NestedStatic {
        public void staticNestedMethod() {}
    }

    public final TargetClass recursiveMethod(int count) {
        if (count <= 0) {
            return targetField;
        }

        new Runnable() {
            public void run() {
                System.out.println("Anonymous class executed");
            }
        }.run();

        List<String> result = targetField.callInner("arg1", "arg2");
        return recursiveMethod(count - 1);
    }
}

// File: TargetClass.java
package pkg76000;

import java.util.Arrays;
import java.util.List;

class TargetClass {
    public class target_inner_rec {
        private List<String> processVarargs(String... items) {
            return Arrays.asList(items);
        }
    }

    public List<String> callInner(String... args) {
        target_inner_rec inner = new target_inner_rec();
        return inner.processVarargs(args);
    }
}
```