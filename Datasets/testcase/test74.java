```java
package pkg;

public record SourceRecord(TargetRecord targetField) {
    public class MemberInner {}

    public abstract class SourceInner extends SuperClass {
        protected Object methodToMove() {
            assert true;

            super.superMethod();

            class LocalType {}
            LocalType local = new LocalType();

            int num = (5);
            num++;
            this.toString();
            new MemberInner();

            new Object() {};
            return null;
        }
    }
}

class SuperClass {
    protected void superMethod() {}
}

record TargetRecord() extends SuperClass {
    static class NestedStatic {}
}

class CallerClass {
    static {
        new TargetRecord() {
            protected Object methodToMove() {
                super.methodToMove();
                return null;
            }
        };
    }
}
```