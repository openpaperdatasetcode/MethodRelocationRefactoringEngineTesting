```java
package pkg57459;

interface StringReturner {
    String getValue(int arg);
}

class Target {
    protected static class StaticNested implements StringReturner {
        private String getValue(int arg) {
            return arg == 0 ? "42" : "0";
        }
    }

    static {
        new StaticNested().getValue(0);
    }

    private StaticNested nested = new StaticNested();

    StaticNested getNested() {
        return nested;
    }
}

class Source {
    public class InnerMember {}
    public static class StaticNested {}

    private Target targetField = new Target();

    final int methodToMove() {
        int result = Integer.parseInt(targetField.getNested().getValue(0));
        return result;
    }
}
```