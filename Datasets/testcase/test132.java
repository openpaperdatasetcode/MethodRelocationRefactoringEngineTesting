```java
import java.util.*;

public class SourceClass implements SourceInterface {
    private static Object helperMethod(Object... args) {
        return null;
    }

    public class SourceInner {
        @Deprecated
        public List<String> methodToMove(TargetClass target) {
            if (target != null) {
                String s = target.toString();
            }

            List<Object[]> data = new ArrayList<>();
            data.add(new Object[]{"a", 1});
            data.add(new Object[]{"b", 2});

            Function<Object[], Object> func = SourceClass::helperMethod;
            for (Object[] args : data) {
                Object result = func.apply(args);
            }

            return Arrays.asList("value1", "value2");
        }
    }

    public static class StaticNested {}
}

interface SourceInterface {}

class TargetClass implements TargetInterface {
    public class TargetInnerRec {}
}

interface TargetInterface {}
```