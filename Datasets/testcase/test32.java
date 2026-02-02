```java
package pkg;

import java.io.IOException;
import java.util.function.Function;

class SuperClass {
    Object methodToMove(target_inner param) throws IOException {
        return null;
    }
}

public class Outer {
    protected static class SourceClass extends SuperClass {
        static class NestedStaticClass {}

        private static Object staticField = "data";

        public SourceClass() {
            new Runnable() {
                @Override
                public void run() {}
            };
        }

        @Override
        final Object methodToMove(target_inner t) throws IOException {
            class LocalClass extends SuperClass {
                LocalClass() {
                    super();
                }
            }

            do {
                for (int i = 0; i < 1; i++) {
                    Function<String, Integer> parser = Integer::parseInt;
                    String val = staticField.toString();
                    this.toString();
                }
            } while (false);
            
            return 1;
        }
    }
}

class target_inner {
    void containerMethod() {
        class LocalInnerClass {}
    }
}
```