```java
package pkg;

record SourceRecord() {
    public class Inner1 {
        private int methodToMove(TargetRecord target, String... args) {
            class LocalClass {
                void process() {
                    java.util.List list = new java.util.ArrayList();
                    list.add("test");
                }
            }

            try {
                LocalClass lc = new LocalClass();
                lc.process();
                TargetRecord.TargetInner inner = target.new TargetInner();
                inner.value = 1;
                return inner.value;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public class Inner2 {}
}

public record TargetRecord() {
    public class TargetInner {
        public int value;
    }
}
```