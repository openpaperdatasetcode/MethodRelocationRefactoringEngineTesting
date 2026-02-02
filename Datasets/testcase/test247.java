```java
import java.util.ArrayList;
import java.util.List;

public class SourceClass {

    private TargetClass<Integer> targetField;

    public static class StaticNested {
        // Static nested class required in source
    }

    public void methodContainingLocalClass() {
        class LocalInner {
            // Local inner class required in source
        }
    }

    protected static class TargetClass<T> {
        protected static String staticField = "fieldValue";
        private int instanceField = 1;

        public static class NestedInTarget {
            // Static nested class in target
        }
    }

    protected final List<String> recursiveMethod(int count) {
        if (count <= 0) {
            String localVar = "base";
            return List.of(TargetClass.staticField, localVar);
        } else {
            List<String> result = new ArrayList<>(recursiveMethod(count - 1));
            result.add(String.valueOf(TargetClass.staticField.length()));
            return result;
        }
    }
}
```