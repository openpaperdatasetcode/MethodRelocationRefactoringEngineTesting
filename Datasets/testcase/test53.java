```java
package pkg;

import java.util.List;
import java.util.ArrayList;

public class TestCase13406 {

    public static class ParentClass {
        protected String var = "variable";
        static boolean firstCall = true;

        String simpleMethod() {
            return "super";
        }

        final Object callMethod() {
            if (this instanceof SourceEnum.InnerInSource) {
                SourceEnum outer = ((SourceEnum.InnerInSource) this).getOuter();
                if (outer.targetField == null) {
                    return null;
                } else {
                    if (firstCall) {
                        firstCall = false;
                        SourceEnum.InnerInSource inner = outer.new InnerInSource();
                        return inner.methodToMove("arg1", "arg2");
                    }
                    return null;
                }
            }
            return null;
        }
    }

    public enum SourceEnum {
        CONSTANT;

        private TargetEnum targetField = TargetEnum.TARGET_CONSTANT;

        public class InnerInSource extends ParentClass {
            final List<String> methodToMove(String... args) {
                List<String> list = new ArrayList<>();
                for (String arg : args) {
                    list.add(arg);
                }
                String v = super.var;
                list.add(v);
                String s = super.simpleMethod();
                list.add(s);
                return list;
            }

            SourceEnum getOuter() {
                return SourceEnum.this;
            }
        }
    }

    protected enum TargetEnum {
        TARGET_CONSTANT;

        public static class TargetInner {
        }
    }
}
```