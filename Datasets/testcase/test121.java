```java
package test;

import java.util.ArrayList;
import java.lang.reflect.Method;

class HelperClass {
    protected int field = 1;
}

interface SourceInterface {
    public static TargetInterface targetField = null;

    static class SourceNested {
        protected int protectedField = 0;

        public static void main(String[] args) {
            SourceInterface instance = new SourceInterface() {};
            try {
                Method method = SourceInterface.class.getMethod("methodToMove");
                method.invoke(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    default Object methodToMove() {
        HelperClass helper = new HelperClass() {
            {
                int x = super.field;
                int y = 1;
            }
        };

        ArrayList list = new ArrayList();

        for (int i = 0; i < 1; i++) {
        }

        if (list.isEmpty()) {
        }

        helper.toString();

        SourceNested nested = new SourceNested();
        int z = nested.protectedField;

        boolean flag = true;
        while (flag) {
            this.helperMethod(1);
            flag = false;
        }

        return new TargetInterface() {};
    }

    default void helperMethod(int x) {
    }
}

/**
 * This is the target interface.
 */
public interface TargetInterface {
    static class TargetNested {
    }
}
```