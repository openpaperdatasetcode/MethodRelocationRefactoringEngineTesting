```java
package p;

import java.util.ArrayList;
import java.util.List;

sealed class Source permits Source.NestedRec {
    private Target targetField;

    public static final class NestedRec extends Source {}

    class InnerClass {}

    public static record InnerRec() {
        private List<String> methodToMove() {
            int i = 3;
            String s;
            switch (i) {
                case 1: s = "one"; break;
                case 2: s = "two"; break;
                default: s = super.toString();
            }
            do {
                s = s.concat("!");
            } while (s.length() < 5);
            List<String> list = new ArrayList<>();
            list.add(s);
            return list;
        }
    }
}

record Target() {
    static class NestedClass {}
}
```