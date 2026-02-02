```java
package test;

public class MoveMethodTestCase64523 {

    public @interface BaseAnnotation {}

    protected static class OuterForTarget {
        void someMethod() {}
    }

    public @interface SourceAnnotation extends BaseAnnotation {

        class MemberInnerClass {
            void innerMethod() {}
        }

        static int methodToMove(OuterForTarget.TargetInner target) throws Exception {
            // Recursive call
            if (target != null) {
                methodToMove(null);
            }

            // Empty statement
            ;

            // Variable call
            int x = 5;
            x++;

            // Super keyword usage
            new MemberInnerClass() {
                void callSuper() {
                    super.innerMethod();
                }
            };

            // Throws requirement
            if (x > 10) {
                throw new Exception("Test exception");
            }

            return x;
        }
    }

    public static void main(String[] args) {
        class LocalContainer {
            OuterForTarget outer = new OuterForTarget() {
                class TargetInner {
                    // Target class (local inner)
                }
            };
        }
    }
}
```