```java
package pkg;

public class TestCaseContainer {
    protected static record TargetRecord(String name) {
        class TargetInner {
            public int value = 1;
        }
    }

    private static record SourceRecord<T>(T data, TargetRecord target) {
        class SourceInner {
            public TargetRecord methodToMove(TargetRecord param) {
                TargetRecord localVar = param;
                if (localVar.name() != null) {
                    TargetRecord.TargetInner inner = param.new TargetInner();
                    switch (inner.value) {
                        case 1:
                            int fieldValue = inner.value;
                            break;
                        default:
                            break;
                    }
                }
                return localVar;
            }
        }

        public void containerMethod() {
            class LocalInner {}
            LocalInner local = new LocalInner();
        }
    }
}
```