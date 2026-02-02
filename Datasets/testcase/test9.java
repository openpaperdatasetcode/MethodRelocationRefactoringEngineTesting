```java
package test;

interface SourceInterface {
    static TargetInterface field = null;

    class MemberInnerClass {
    }

    default TargetInterface methodToMove(TargetInterface targetParam) {
        /**
         * Method javadoc comment.
         */
        class LocalInner {
            int value;
            LocalInner(int value) {
                this.value = value;
            }
            int getValue() {
                return value;
            }
        }

        LocalInner local = new LocalInner(10);
        int x = local.getValue();

        do {
            if (SourceInterface.field == null) {
                x++;
            }
            break;
        } while (true);

        try {
            java.lang.reflect.Method method = TargetInterface.class.getDeclaredMethod("callMethod", int.class);
            method.setAccessible(true);
            int result = (Integer) method.invoke(targetParam, 42);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int y = local.getValue();
        Class<?> cls = String.class;
        return targetParam;
    }
}

interface TargetInterface {
    private int callMethod(int x) {
        return x * 2;
    }

    private int callMethod(String s) {
        return s.length();
    }

    default void anotherMethod() {
        class TargetLocalInner {
        }
        new TargetLocalInner();
    }
}
```