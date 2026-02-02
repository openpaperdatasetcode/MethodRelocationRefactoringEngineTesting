```java
package pkg;

public class TestCase_73267 {

    public enum target_inner_rec {
        TARGET_CONST;

        public int someField = 1;

        public static class StaticNestedClass {
            protected int someMethod() {
                return 1;
            }

            protected int someMethod(int x) {
                return x;
            }
        }
    }

    public enum SourceEnum {
        ENUM_CONST;

        private target_inner_rec targetField = target_inner_rec.TARGET_CONST;

        public class MemberInnerClass {
        }

        {
            new Runnable() {
                @Override
                public void run() {
                }
            };
        }

        public synchronized Object methodToMove() {
            int count = 0;
            do {
                count++;
                int fieldValue = targetField.s