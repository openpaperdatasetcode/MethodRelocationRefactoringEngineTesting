```java
public class TestCase84027 {
    static class BaseForInner {
        public int baseMethod() {
            return 7;
        }
    }

    private static class SourceClass {
        private static int staticValue = 5;
        private Target targetField;

        public SourceClass(Target t) {
            this.targetField = t;
        }

        public void outerMethod() {
            class LocalInner1 {}
            class LocalInner2 extends BaseForInner {
                public int methodToMove(int x, int y, int z) {
                    int baseCall = super.baseMethod();
                    int fieldCall = targetField.targetCompute(x);
                    return baseCall + fieldCall + staticValue;
                }
            }
        }
    }

    public static class Target {
        public static class Nested {}
        public int targetCompute(int val) {
            return val * 3;
        }
    }
}
```