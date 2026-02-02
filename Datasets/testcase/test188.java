```java
package pkg;

public class Target {
    public int field;

    protected Object callMethod() {
        class LocalClass {}
        Object result = true ? new LocalClass() : String::valueOf;
        return result;
    }
}

class Base {}
class Source<T extends Base> {
    class Inner1 {}
    class Inner2 {}

    /**
     * Method javadoc.
     */
    @Deprecated
    public int methodToMove(Target obj) {
        class LocalClass {}
        int x = 0;
        do {
            x = obj.field + 3;
        } while (x < 0);
        return x;
    }
}
```