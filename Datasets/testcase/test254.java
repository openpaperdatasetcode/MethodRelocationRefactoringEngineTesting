```java
abstract class Source<T> {
    static class StaticNested {}
    class MemberInner extends BaseForInner {
        @Override
        protected TargetClass<String> methodToMove(TargetClass<String> target) {
            super.methodToMove(target);
            return target;
        }
        protected TargetClass<String> methodToMove(TargetClass<String> target, String s) {
            return target;
        }
    }
}

class BaseForInner {
    protected TargetClass<String> methodToMove(TargetClass<String> target) {
        return null;
    }
}

class BaseClass {}

class TargetClass<U> extends BaseClass {
    class Inner {}
}
```