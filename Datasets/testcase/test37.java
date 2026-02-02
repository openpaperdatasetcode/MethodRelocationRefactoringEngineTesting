```java
// File: pkg44868/SourceClass.java
package pkg44868;

public sealed class SourceClass permits TargetClass {
    private TargetClass targetField;

    private Object methodToMove() {
        ;
        int[] numbers = {1, 2, 3};
        for (int num : numbers) {
            int x = num;
        }
        int count = 0;
        while (count < 3) {
            count++;
        }
        Object obj = targetField;
        return obj;
    }

    public void demoFeatures() {
        class LocalInner {
            void display() {
                System.out.println("Local inner class");
            }
        }
        new Object() {
            void anonymousMethod() {
                System.out.println("Anonymous inner class");
            }
        };
    }
}

// File: pkg44868/TargetClass.java
package pkg44868;

public final class TargetClass extends SourceClass {
    public static class NestedStatic {
        public void nestedMethod() {}
    }
}
```