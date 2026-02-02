```java
public class TestCase29838 {

    private class TargetClass {
        public class TargetInner {
            public void innerMethod() {
            }
        }
    }

    protected class SourceClass {

        public class SourceMemberInner {
        }

        public void methodWithLocalClass() {
            class LocalClass {}
        }

        private void methodToMove(TargetClass.TargetInner targetInner) {
            if (targetInner == null) {
                throw new NullPointerException();
            }
            targetInner.innerMethod();
            assert targetInner != null;
            return;
        }
    }
}
```