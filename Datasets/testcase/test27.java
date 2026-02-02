```java
package pkg;

public record SourceClass(int field) {

    public static class NestedInSource {
    }

    public synchronized void methodToMove(TargetClass target) {
        class LocalInner {
            void innerCall() {
            }
        }
        LocalInner inner = new LocalInner();
        inner.innerCall();

        TargetClass.NestedInTarget nested = new TargetClass.NestedInTarget(this.field, 1);

        int counter = 0;
        do {
            nested.performAction();
            counter++;
        } while (counter < 1);
    }

    public static record TargetClass() {
        public static class NestedInTarget {
            public NestedInTarget(int a, int b) {
            }
            public void performAction() {
            }
        }
    }
}
```