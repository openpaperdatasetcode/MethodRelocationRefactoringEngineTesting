```java
package p1;

import p2.TargetRecord;
import java.util.ArrayList;
import java.util.List;

record SourceRecord() {
    private List<String> privateData = new ArrayList<>();

    /**
     * Method javadoc description.
     */
    public List<String> moveMethod(TargetRecord target, String... items) {
        privateData.add("accessed");
        
        List<String> result = new ArrayList<>();
        for (String item : items) {
            result.add(item);
        }
        
        new Helper().execute();
        return result;
    }

    private static class Helper {
        void execute() {
            System.out.println("Helper executed");
        }
    }
}
```

```java
package p2;

public strictfp record TargetRecord(int value) {
    public class InnerClass {
    }
}
```