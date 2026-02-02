```java
import java.util.*;

public final class Source implements DummyInterface {
    private target targetField;
    private int privateField = 10;

    class MemberInner { }

    protected target methodToMove(String... args) {
        class LocalBase {
            protected int field = 1;
        }
        class LocalInner extends LocalBase { }

        try {
            LocalInner inner = new LocalInner();
            int value = inner.field;
            if (value != 1) {
                throw new RuntimeException();
            }
            throw new NullPointerException();
        } catch (NullPointerException e) {
        }

        int i = 3;
        while (i > 0) {
            List<String> list = DummyUtils.staticMethod(i);
            i--;
        }

        class LocalClassWithSuper extends MemberInner {
            LocalClassWithSuper() {
                super();
            }
        }
        new LocalClassWithSuper();

        Object obj = "test";
        int hash = obj.hashCode();

        int x = privateField;

        return new target();
    }

    protected List<String> methodToMove(int a) {
        return new ArrayList<>();
    }

    private static class target {
        public void dummyMethod() {
            class LocalInTarget { }
        }
    }
}

interface DummyInterface { }

class DummyUtils {
    static List<String> staticMethod(int n) {
        return Arrays.asList("A","B","C");
    }
}
```