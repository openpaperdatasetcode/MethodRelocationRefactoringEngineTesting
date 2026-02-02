```java
package pkg;

import java.lang.reflect.Method;

@interface SourceClass {
    class SourceInner {
        protected int outerProtectedField = 0;

        public synchronized void methodToMove(TargetClass param) {
            class LocalInner {
                LocalInner() {
                    super();
                }
            }
            new LocalInner();

            int localVar = 5;
            System.out.println(localVar);

            outerProtectedField = 10;

            try {
                Method m = getClass().getMethod("methodToMove", TargetClass.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Javadoc for abstract annotation
 */
abstract @interface TargetClass {
    static class TargetNested {}
}
```