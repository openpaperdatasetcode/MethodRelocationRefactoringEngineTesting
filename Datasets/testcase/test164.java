```java
// File: p1/Source.java
package p1;

import p2.Target;

public final class Source<T> extends Base {
    private Target<Integer> targetField;

    public Source(Target<Integer> targetField) {
        this.targetField = targetField;
    }

    void methodWithLocalClass() {
        class LocalClass {}
    }

    int methodToMove(int n) {
        int value = protectedField;
        int i = 0;
        do {
            i++;
        } while (i < n);

        if (n <= 0) return 0;

        new Object() {
            {
                super.toString();
            }
        };
        
        int res = methodToMove(n - 1);
        return value + i + res + targetField.targetMethod();
    }

    void methodToMove(String s) {}
}

// File: p1/Base.java
package p1;

public class Base {
    protected int protectedField = 10;
}

// File: p2/Target.java
package p2;

public class Target<T> {
    public class InnerClass {}
    
    public int targetMethod() {
        return 5;
    }
}

// File: TestReflection.java
import p1.Source;
import p2.Target;

public class TestReflection {
    public static void main(String[] args) throws Exception {
        Target<Integer> target = new Target<>();
        Source<String> source = new Source<>(target);
        java.lang.reflect.Method method = Source.class.getDeclaredMethod("methodToMove", int.class);
        method.setAccessible(true);
        method.invoke(source, 3);
    }
}
```