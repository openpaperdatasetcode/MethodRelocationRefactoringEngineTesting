```java
public final class TestCase91570 {

    static class Source extends SuperClass {
        private static class NestedStatic {
        }

        private record InnerRec() {
            private void methodToMove() {
                new Object() {
                    void anonymousMethod() {
                    }
                };
            }
        }

        @Override
        protected Object methodToMove(target.Target param) throws Exception {
            Object x = super.field;
            Object y = super.field;
            java.util.List<Object> list = java.util.List.of();
            for (Object o : list) {
                System.out.println(o);
            }
            int i = 0;
            while (i < 3) {
                i++;
            }
            Object obj = new Object();
            obj.toString();
            if (param == null) throw new Exception();
            return null;
        }
    }

    static class SuperClass {
        protected Object field;
    }
}

class TargetContainer {
    private record Target() {
        public static class NestedInTarget {
        }
    }
}
```