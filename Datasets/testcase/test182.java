```java
package pkg;

import java.util.*;

interface SomeInterface {}

public enum SourceEnum implements SomeInterface {
    CONSTANT;

    private TargetEnum targetField;

    public static class NestedClass {
        public List<String> recursiveMethod(TargetEnum target, int depth) throws Exception {
            if (depth <= 0) {
                return new ArrayList<>();
            }
            try {
                while (true) {
                    class LocalClass {
                        String value = "test";
                    }
                    LocalClass lc = new LocalClass();
                    if (lc.value.length() > 0) break;
                }
                List<String> res = recursiveMethod(target, depth - 1);
                res.add(target.str);
                return res;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void methodWithLocalClass() {
        class LocalInner {}
        new LocalInner();
    }

    private enum TargetEnum {
        TARGET_CONSTANT("data");

        private String str;

        TargetEnum(String s) {
            str = s;
        }

        public void targetMethod() {
            class InnerLocal {}
            new InnerLocal();
        }
    }
}
```