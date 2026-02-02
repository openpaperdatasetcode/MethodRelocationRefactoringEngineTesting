```java
package pkg27649;

public class Wrapper {
    protected static class BaseClass {}

    protected static class Helper {}

    protected static class SourceClass extends BaseClass {
        private TargetClass targetField;

        protected static class StaticNested {}

        public SourceClass(TargetClass target) {
            this.targetField = target;
        }

        final void recursiveMethod(int n) {
            if (n <= 0) {
                return;
            }
            targetField.methodInTarget();
            Helper helper = new Helper();
            recursiveMethod(n - 1);
        }

        public void anonymousExample() {
            Runnable r = new Runnable() {
                @Override
                public void run() {}
            };
        }
    }

    protected static class TargetClass {
        protected class MemberInner {}

        protected void methodInTarget() {}
    }
}
```