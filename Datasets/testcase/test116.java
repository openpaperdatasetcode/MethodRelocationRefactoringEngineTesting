```java
package pkg35712;

public @interface SourceAnnotation {
    class MemberInner {}
    static class StaticNested {}
}
```

```java
package pkg35712;

public class Container {
    private @interface TargetAnnotation {}
}
```

```java
package pkg35712;

class HelperClass {
    private Object field;
    private HelperClass(int num, String str) {}
}
```

```java
package pkg35712;

public class TestClass {
    public static class StaticNested {
        private Object field;
        
        protected void methodToMove(Container.TargetAnnotation param) {
            if (param != null) {
                try {
                    java.lang.reflect.Constructor<?> constructor = 
                        HelperClass.class.getDeclaredConstructor(int.class, String.class);
                    constructor.setAccessible(true);
                    HelperClass helper = (HelperClass) constructor.newInstance(3, "test");
                    this.field = helper.field;
                    return;
                } catch (Exception e) {}
            }
        }
    }
}
```