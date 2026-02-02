```java
import java.util.ArrayList;
import java.util.List;

class BaseSource {}

public class Source extends BaseSource {
    protected static class target_inner_rec {}
    private target_inner_rec targetField;

    public class Inner {
        List<String> methodToMove(String... args) {
            Base b = new Base(Source.this.targetField, 1) {};
            List<String> list = new ArrayList<>();
            for (String arg : args) {
                list.add(arg);
            }
            return list;
        }
    }

    private static class Base {
        private Base(target_inner_rec t, int x) {}
    }

    public static class NestedStatic {}
}
```