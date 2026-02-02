```java
class OtherClass {
    void helperMethod() {}
}

abstract sealed class SourceClass<T> permits SourceClass.PermittedSubclass {
    TargetClass targetField;

    static final class PermittedSubclass extends SourceClass<String> {
    }

    class MemberInner {
        private Object methodToMove(Object... args) {
            class LocalType {}
            this.helperMethod();
            String var = "test";
            return args.length > 0 ? targetField : TargetClass.getInstance();
        }
    }
}

abstract class TargetClass {
    public void containerMethod() {
        class LocalInner {}
    }

    public static TargetClass getInstance() {
        return new ConcreteTarget();
    }

    private static class ConcreteTarget extends TargetClass {
    }
}
```