```java
package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

strictfp class Source<T> extends SomeBase {
    class InnerMember {
    }

    private List<String> recursiveMethod(TargetInnerRec target, int depth) {
        List<String> results = new ArrayList<>();
        int counter = 0;
        do {
            if (counter % 2 == 0) {
                counter++;
                continue;
            }
            String val = target.toString();
            Consumer<String> ref = target::processString;
            if (depth > 0) {
                results.addAll(recursiveMethod(target, depth - 1));
            }
            counter++;
        } while (counter < 5);
        new Runnable() {
            public void run() {
                System.out.println("Anonymous");
            }
        }.run();
        return results;
    }
}

class SomeBase {
}

class TargetInnerRec {
    public void processString(String s) {
    }

    public void existing() {
        new Object() {
            public void run() {
            }
        };
    }
}
```