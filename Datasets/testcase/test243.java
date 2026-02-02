```java
package pkg;

public class TestCase6736 {
    private static class SourceClass {
        class SourceInner {}
        static class SourceStaticNested {}

        public TargetClass moveMethod(TargetClass param) {
            try {
                int iterations = 5;
                for (int i = 0; i < iterations; i++) {
                    System.out.println(param.toString());
                }
                return new TargetClass();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

class TargetClass {
    TargetClass() {}
    class TargetInner {}
}
```