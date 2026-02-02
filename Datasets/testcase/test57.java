```java
public class TestCase95263 {

    private static class BaseClass {
        BaseClass() {}
    }

    private static class SourceClass {
        public class SourceInner {
            private void recursiveMethod(TargetClass target, int depth) {
                if (depth <= 0) return;
                if (target == null) throw new NullPointerException();

                class LocalClass extends BaseClass {
                    LocalClass() {
                        super();
                    }
                }
                new LocalClass();

                int value = depth * 10;
                String res;
                do {
                    res = target.performAction();
                } while (res == null);

                recursiveMethod(target, depth - 1);
            }

            private void recursiveMethod(int depth) {
                recursiveMethod(null, depth);
            }
        }

        public static class StaticNested {}
    }

    private static class TargetClass {
        public TargetClass() {
            Runnable r = new Runnable() {
                @Override public void run() {}
            };
        }

        public synchronized String performAction() {
            return "Done";
        }
    }
}
```