```java
package test;

import java.io.*;

@interface SourceAnnotation {
    class SourceInner {
        private TargetContainer.TargetClass methodToMove() {
            try {
                FileInputStream fis = new FileInputStream("test.txt");
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = new String("example");
            return new TargetContainer().new TargetClass();
        }
    }

    static class SourceStaticNested {}
}

class TargetContainer {
    private @interface TargetAnnotation {
        default void methodWithLocalClass() {
            class LocalInner {
                void movedMethod() {}
            }
        }
    }
}
```