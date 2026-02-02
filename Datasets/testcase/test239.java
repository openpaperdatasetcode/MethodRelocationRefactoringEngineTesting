```java
package pkg1;

import pkg2.OuterTarget;
import java.lang.reflect.Method;

enum SourceEnum {
    CONST;

    private OuterTarget.Target targetField;

    // Member inner class
    class InnerClass {
        private void methodToMove() {}
        
        private void methodToMove(String s) {
            synchronized (this) {
                int val = superField;
                Accessor acc = str -> System.out.println(str);
                acc.execute(s);
            }
        }
    }

    // Static nested class
    static class StaticNested {}
    
    protected int superField = 42;
}

@FunctionalInterface
interface Accessor {
    void execute(String arg);
}
```

```java
package pkg2;

public class OuterTarget {
    private enum Target {
        T1, T2;
        
        // Member inner class in target
        class TargetInner {}
    }
    
    public static void callViaReflection() throws Exception {
        Object instance = new Object();
        Method method = instance.getClass().getDeclaredMethod("methodToMove", String.class);
        method.setAccessible(true);
        method.invoke(instance, "test");
    }
}
```