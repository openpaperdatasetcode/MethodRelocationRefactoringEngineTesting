```java
package com.example;

class BaseClass {}
class Source extends BaseClass {
    private static class NestedHelper {}
    public class InnerClass {}
    
    private static final Outer.Target methodToMove(Outer.Target target) throws Exception {
        try {
            NestedHelper helper = new NestedHelper();
            target.doSomething();
            int value = target.someField + 2;
            if (value > 1) {
                throw new Exception("Threshold exceeded");
            }
            return target;
        } catch (Exception e) {
            throw e;
        }
    }
}

public class Outer {
    public static class Target {
        public int someField;
        public void doSomething() {}
    }
}

public class Caller {
    private int process() {
        Outer.Target param = new Outer.Target();
        param.someField = 1;
        try {
            Outer.Target result = Source.methodToMove(param);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
}
```