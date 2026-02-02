```java
package pkg;

interface BaseInterface {
}

interface Source extends BaseInterface {
    Target targetField = null;

    class Inner {
        final void methodToMove() {
            new Object() {
                {
                    int temp = 0;
                }
            };
            int value = 0;
            value++;
            int access = Source.privateOuterField;
        }
    }

    private static int privateOuterField = 10;
}

/**
 * Javadoc for target interface.
 */
public interface Target extends BaseInterface {
    static class Nested {
    }
}

class SubInner extends Source.Inner {
    Object callMethod() {
        new Object();
        for (int i = 0; i < 1; i++) {
            super.methodToMove();
        }
        return null;
    }
}
```