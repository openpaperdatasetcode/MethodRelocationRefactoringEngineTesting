```java
public class TestCase62843 {
    static record SourceRecord(TargetRecord target) {
        public static class StaticNestedInSource {}

        protected final TargetRecord methodToMove(TargetRecord param) {
            class LocalInnerInMethod {
                LocalInnerInMethod() {
                    super();
                }
            }
            new LocalInnerInMethod();

            int[] values = {1, 2, 3};
            for (int val : values) {
                String s = "test";
                int length = s.length();
            }

            TargetRecord.target_inner inner = new TargetRecord.target_inner() {
                @Override
                public void abstractMethod() {
                    System.out.println("Implemented");
                }
            };
            inner.abstractMethod();

            this.methodToMove(42);
            return param;
        }

        protected final void methodToMove(int num) {
            System.out.println("Overloaded: " + num);
        }
    }

    static final record TargetRecord() {
        public static abstract class target_inner {
            public abstract void abstractMethod();
        }
    }
}
```