```java
package pkg13986;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceClass {

    public static class StaticNested {}

    static int staticField = 5;

    public void methodWithLocal() {
        class LocalInner extends Base {
            @Override
            Target methodToMove(Target... args) throws IOException {
                // Labeled statement
                outer: for (int i = 0; i < 3; i++) {
                    if (i == 1) break outer;
                }

                // Type declaration statement
                class LocalType {
                    void display() {}
                }
                new LocalType().display();

                // Access instance field of Target
                int val = args[0].field;

                // Depends on static field
                int x = staticField;

                // Raw type
                List list = new ArrayList();
                list.add("test");
                int size = list.size(); // Variable call

                // Throw statement
                IOException ex = new IOException();
                throw ex;
            }

            void recursive(int n) {
                if (n > 0) recursive(n - 1);
            }
        }
        new LocalInner().recursive(2);
    }

    class Base {
        Target methodToMove(Target... args) throws IOException {
            return null;
        }
    }
}

abstract class Target {
    int field;
    public Target m2() { return this; }
    public int m3() { return 42; }
    public Target methodToMove(Target arg) { return this; } // Overloaded method
}
```