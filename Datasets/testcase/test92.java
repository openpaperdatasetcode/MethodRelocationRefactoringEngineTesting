```java
package pkg1;

import pkg2.TargetClass;
import java.util.*;
import java.lang.reflect.Method;

public class SourceClass extends SuperSource {
    public class MemberInner {}

    public void methodWithLocalClass() {
        class LocalInner {}
        new LocalInner();
    }

    public synchronized List<String> recursiveMethod(int count, String base, Object ctx) {
        if (count <= 0) return new ArrayList<>();
        return recursiveMethod(count - 1, base + "!", ctx);
    }

    public void methodToMove(TargetClass target, String... args) {
        int val = target.getFieldValue();
        switch (val) {
            case 1:
                System.out.println("Case 1");
                break;
            case 2:
                System.out.println("Case 2");
                break;
        }

        List<String> result = recursiveMethod(3, "init", this);
        target.performAction();

        try {
            Method privateMethod = TargetClass.class.getDeclaredMethod("privateMethod", int.class);
            privateMethod.setAccessible(true);
            int num = (int) privateMethod.invoke(target, 5);
            List<Integer> nums = new ArrayList<>();
            nums.add(num);
            nums.forEach(n -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SourceClass src = new SourceClass();
        Method mtd = SourceClass.class.getMethod("methodToMove", TargetClass.class, String[].class);
        mtd.invoke(src, new TargetClass(), new String[]{"a", "b"});
    }
}

class SuperSource {}
```

```java
package pkg2;

import java.util.*;

public strictfp class TargetClass extends SuperTarget {
    private int field = 1;

    public int getFieldValue() {
        return field;
    }

    public void performAction() {
        System.out.println("Action performed");
    }

    private int privateMethod(int input) {
        List<Integer> list = new ArrayList<>();
        list.add(input);
        return list.stream().mapToInt(n -> super.adjustValue(n)).sum();
    }
}

class SuperTarget {
    protected int adjustValue(int x) {
        return x * 2;
    }
}
```