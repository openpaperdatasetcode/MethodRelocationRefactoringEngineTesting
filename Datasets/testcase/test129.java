```java
package pkg;

import java.util.ArrayList;
import java.util.List;

strictfp class Source {
    private Target<String> targetField;

    class MemberInner {
        List<String> overloadedMethod() {
            return new ArrayList<>();
        }

        List<String> overloadedMethod(int param) throws Exception {
            int value = targetField.protectedField + 1;
            String strVar = "test";
            int length = strVar.length();
            List<? extends Number> boundedList = new ArrayList<>();
            if (boundedList.isEmpty()) throw new Exception();
            List<String> result = new ArrayList<>();
            result.add(strVar);
            return result;
        }
    }

    void methodWithLocalInner() {
        class LocalInner {}
        LocalInner local = new LocalInner();
    }
}

class Target<T> {
    protected int protectedField;
    static class NestedStatic {}
}
```