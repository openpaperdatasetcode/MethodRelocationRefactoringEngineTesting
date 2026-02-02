```java
package p;

public class TargetClass<U> {
    public void targetClassMethod() {
        class TargetLocalInner {}
    }
}

abstract class SourceClass<T> {
    protected TargetClass<T> targetField;

    @Deprecated
    protected TargetClass<T> methodToMove() {
        targetField.targetClassMethod();
        return targetField;
    }

    public void containerMethod() {
        class LocalInner {}
        
        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
    }
}
```