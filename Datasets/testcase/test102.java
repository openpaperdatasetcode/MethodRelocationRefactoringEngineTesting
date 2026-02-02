```java
package p;

public class SuperClass {
    protected int overriddenMethod(Target target) {
        return 0;
    }
}

class Source extends SuperClass {
    static class NestedStaticClass {}
    
    private int value = 10;
    
    void anonymousClassExample() {
        new Runnable() {
            @Override
            public void run() {}
        };
    }

    @Override
    protected int overriddenMethod(Target target) {
        int local = this.value;
        try {
            Target newTarget = getNewTarget();
            if (newTarget == null) throw new NullPointerException();
        } catch (NullPointerException e) {
            local = 0;
        }
        return local;
    }

    private Target getNewTarget() {
        return new Target();
    }
}

public class Target {
    static class NestedStaticClass {}
}
```