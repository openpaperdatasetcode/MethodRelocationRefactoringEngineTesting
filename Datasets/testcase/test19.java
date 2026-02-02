```java
package p25390;

public class Source extends SuperSource {
    private Target targetField = new Target();

    class MemberInner {}

    public Source() {
        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
    }

    protected <T> int methodToMove() {
        if (super.methodName() && targetField.someCondition()) {
            return super.field + 1;
        } else {
            return 0;
        }
    }
}

class SuperSource {
    protected int field;
    boolean methodName() {
        return true;
    }
}

strictfp class Target {
    boolean someCondition() {
        return true;
    }
}
```