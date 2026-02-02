```java
package pkg;

import java.util.ArrayList;
import java.util.List;

class SourceClass {
    protected class InnerSource {
        List<String> methodToMove(TargetClass target, String... items) {
            List<String> result = new ArrayList<>();
            for (String item : items) {
                if (item.isEmpty()) {
                    continue;
                }
                String modified = target.transform(item);
                result.add(modified);
            }
            return result;
        }
    }

    void useAnonymous() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
        r.run();
    }
}

class TargetClass {
    String transform(String input) {
        return input.trim();
    }
}
```