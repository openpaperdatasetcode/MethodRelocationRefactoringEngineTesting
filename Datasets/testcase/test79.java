```java
package p1;

public enum SourceEnum {
    ENUM_CONSTANT;

    public static class StaticNested {}

    public class MemberInner {
        public class RecursiveInner {
            protected RecursiveInner(p2.Outer.Target target, int value) {
                super();
                int result = target.field1 + target.field2 + 2;
                try {
                    java.lang.reflect.Constructor<?> ctor = target.getClass().getDeclaredConstructors()[0];
                    ctor.setAccessible(true);
                    Object obj = ctor.newInstance();
                } catch (Exception ignored) {}
            }
        }
    }
}
```

```java
package p1;

public class BaseClass {
    public BaseClass() {}
}
```

```java
package p2;

public class Outer {
    /**
     * Javadoc for private enum
     */
    private enum Target {
        ENUM_CONST {
            void innerMethod() {}
        };
        
        int field1 = 1;
        int field2 = 2;
    }

    public void testMethod() throws Exception {
        Target t = Target.ENUM_CONST;
        java.lang.reflect.Constructor<?> ctor = p1.SourceEnum.MemberInner.RecursiveInner.class
            .getDeclaredConstructor(p1.SourceEnum.MemberInner.class, p2.Outer.Target.class, int.class);
        ctor.setAccessible(true);
        Object instance = ctor.newInstance(null, t, 0);
    }
}
```