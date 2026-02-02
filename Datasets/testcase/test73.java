```java
public class SomeSuperclass {}

public class SourceClass extends SomeSuperclass {
    private TargetClass<Integer> targetField;

    public class Inner1 {
        public class Inner2 {
            abstract int methodToMove() throws InnerException;
        }
    }

    public class InnerDummy {}

    private static class InnerException extends Exception {}

    private class TargetClass<T> {
        static class Nested {}
    }
}
```