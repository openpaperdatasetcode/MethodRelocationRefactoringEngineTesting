```java
package pkg;

public class SourceClass {
    TargetInner field;

    class MemberInnerClass {}

    public void someMethod() {
        class LocalInnerClass {}
    }

    private TargetInner moveMethod() {
        SourceClass outer = SourceClass.this;
        java.util.ArrayList<Object> list = new java.util.ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            if (i == 5) continue;
            list.add(field.value);
        }
        
        class LocalInMethod extends Base {
            transient int temp;
            LocalInMethod(int a) {
                super(a);
            }
        }
        new LocalInMethod(1);
        
        return field;
    }

    Object callMoveMethod() {
        return moveMethod();
    }

    public void reflectionCall() throws Exception {
        java.lang.reflect.Method m = getClass().getDeclaredMethod("moveMethod");
        m.setAccessible(true);
        m.invoke(this);
    }
}

class Base {
    Base(int a) {}
}

class TargetInner {
    public int value;
}
```