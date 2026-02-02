```java
public class TestCase_22677 {
    static class BaseClass {}

    protected static enum TargetEnum {
        CONSTANT;

        public class TargetInner extends BaseClass {
            private int counter = 3;

            // Method will be moved here by refactoring
        }
    }

    private static enum SourceEnum {
        SOURCE_CONSTANT;

        private TargetEnum targetField = TargetEnum.CONSTANT;

        public static class StaticNested {}

        public void createLocalClass() {
            class LocalInner {}
        }

        public class RecursiveInner {
            private void methodToMove() {
                TargetEnum val = SourceEnum.this.targetField;
                
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add("item");
                list.remove(0);
                
                try {
                    Runnable r = () -> {};
                    r.run();
                } catch (Exception e) {}
                
                switch (RecursiveInner.this.hashCode() % 2) {
                    case 1:
                        break;
                }
                
                if (val != null) {
                    methodToMove();
                }
                
                new Helper().m1().m2().m3();
            }
        }
    }

    static class Helper {
        Helper m1() { return this; }
        Helper m2() { return this; }
        Helper m3() { return this; }
    }
}
```