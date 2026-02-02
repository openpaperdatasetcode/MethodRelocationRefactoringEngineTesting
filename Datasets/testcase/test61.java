```java
package pkg;

public class TestCase96501 {
    private record SourceClass() {
        class InnerMemberClass {}

        static Object anonymousInner = new Object() {
            @Override
            public String toString() {
                return "Anonymous";
            }
        };

        public Object methodToMove(TargetClass target) {
            SourceClass self = this;
            assert self != null : "Assertion failed";

            int value = 5;
            switch (value) {
                case 5: value = 10; break;
                default: value = 0;
            }

            synchronized (this) {
                value++;
            }

            class LocalClass {
                int x;
                LocalClass() {
                    this(100);
                }
                LocalClass(int x) {
                    this.x = x;
                }
            }
            new LocalClass();

            return target;
        }
    }

    protected record TargetClass() {}
}
```