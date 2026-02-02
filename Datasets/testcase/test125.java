```java
package pkg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Target extends BaseClass {
    static {
        Target t = new Target();
        t.call_method();
    }

    void call_method() {
        Inner inner = new Inner();
        inner.innerMethod();
    }

    void call_method(int x) {
    }

    public class Inner {
        public void innerMethod() {
        }
    }
}

class BaseClass {
    protected int baseField = 1;
}

class Helper {
}

class Source {
    Target targetObj = new Target();

    private class MemberInner {
    }

    List<String> moveMethod() {
        Helper helper = new Helper();
        int i = 0;
        do {
            i++;
            int value = targetObj.baseField;
            assert value == 1;
            List<String> list = new ArrayList<>();
            list.add("test");
            for (String s : list) {
                System.out.println(s);
            }
        } while (i < 1);
        return new ArrayList<>();
    }

    public Source() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
            }
        };
        r.run();
    }

    public void reflectionCaller() {
        try {
            Method m = Source.class.getDeclaredMethod("moveMethod");
            m.setAccessible(true);
            m.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```