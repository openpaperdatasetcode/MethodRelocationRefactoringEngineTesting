```java
package pkg;

class SuperClass {
    public void baseMethod() {}
}

public class Source {
    private Outer.target_inner_rec targetField;
    private int value;

    public synchronized int methodToMove() {
        super.baseMethod();
        this.value = 5;
        int val = this.value;
        return val;
    }
}

class Outer {
    protected static interface target_inner_rec {}
}
```