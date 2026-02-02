```java
package p;

class TargetSuper {
    public void recursiveMethod() {
    }
}

public class Source {
    Container.Target<String> targetField;

    public static class StaticNested {
    }

    public abstract class AbstractInner extends TargetSuper {
    }

    public class ConcreteInner extends AbstractInner {
        @Override
        public void recursiveMethod() {
            super.recursiveMethod();
            boolean flag = false;
            if (flag) {
                recursiveMethod();
            }
            if (flag) {
                throw new IllegalStateException();
            }
            int count = 0;
            count++;
            System.out.println(count);
        }
    }
}

public class Container {
    protected static class Target<T> extends TargetSuper {
    }
}
```