```java
package pkg;

class SourceClass {
    private target_inner_rec targetField = new target_inner_rec();

    static class StaticNested {
    }

    private class target_inner_rec {
        public class TargetInner {
        }
    }

    public class SourceInner {
        public int methodToMove() {
            int[] arr = new int[1];
            int value = arr[0];
            switch (value) {
                case 0:
                    return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        new Object() {
            void anonymousMethod() {
            }
        }.anonymousMethod();
    }
}
```