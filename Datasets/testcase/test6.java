```java
package pkg;

import java.io.IOException;
import java.util.List;

public abstract class TargetClass {
    public class InnerClass {
    }
}

abstract class SourceContainer {
    private abstract static class SourceClass {
        private TargetClass targetField;

        public static class Nested1 {
        }

        static class Nested2 {
        }

        int recursiveMethod(List<Integer> list) throws IOException {
            if (list.size() > 10) {
                throw new IOException("List too long");
            }
            if (list.isEmpty()) {
                return 0;
            }
            interface MyInterface {
                void