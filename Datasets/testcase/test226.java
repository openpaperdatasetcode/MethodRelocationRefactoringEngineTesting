```java
package pkg;

import java.util.ArrayList;
import java.util.List;

public class Test_90250 {

    protected static class SourceClass {
        protected static List<String> protectedData = new ArrayList<>();

        public static class Nested1 {
            // Empty nested class
        }

        public static class Nested2 {
            public List<String> methodToMove(TargetClass.InnerClass target) {
                List<String> result = new ArrayList<>(protectedData);
                result.add(target.targetField);
                return result;
            }
        }
    }

    private static class TargetClass {
        private Runnable anonymousField = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };

        public class InnerClass {
            public String targetField = "InnerClassField";
        }
    }
}
```