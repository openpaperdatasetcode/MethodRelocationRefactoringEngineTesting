```java
class BaseClass {
}

class Source {
    protected int outerProtectedField = 5;

    public class MemberInner {
    }

    public static class StaticNested {
    }

    private Object methodToMove(Target targetParam, int condition) {
        class LocalInner {
            @Override
            public String toString() {
                return "LocalInner";
            }
        }

        if (condition > 0) {
            LocalInner localObj = new LocalInner();
            String s = localObj.toString();
            int result = outerProtectedField + condition;
            return new Integer(result);
        } else {
            int result = outerProtectedField - condition;
            return new Integer(result);
        }
    }
}

protected class Target extends BaseClass {
    public class InnerInTarget {
    }
}
```