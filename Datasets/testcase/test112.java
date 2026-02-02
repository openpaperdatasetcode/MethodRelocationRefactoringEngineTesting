```java
// FILE: pkg/SourceAnnotation.java
package pkg;

import java.sql.SQLException;

@interface SourceAnnotation {
    target_inner_rec field;

    abstract class NestedClass {
        protected int methodToMove(int... args) {
            class LocalType {
                void process() {
                    for (int i = 0; i < 10; i++) {
                        if (i == 5) break;
                    }
                }
            }
            new LocalType().process();

            int sum = (1 + 2);
            if (sum > 0) {
                Object obj = new Object() {
                    public String toString() {
                        return super.toString();
                    }
                };
                obj.toString();
            }

            try {
                throw new SQLException();
            } catch (SQLException e) {
                while (true) {
                    break;
                }
            }
            return StaticContainer.VALUE;
        }
    }

    class StaticContainer {
        static final int VALUE = 42;
    }
}

// FILE: pkg/target_inner_rec.java
package pkg;

public @interface target_inner_rec {
    class InnerClass {}
}
```