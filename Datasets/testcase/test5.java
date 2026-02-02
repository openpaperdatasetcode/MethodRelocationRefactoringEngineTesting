```java
package pkg;

abstract class SourceClass extends ParentClass {
    public static int staticFlag = 0;
    private TargetClass<Integer> targetField;

    public class Inner1 {}
    public class Inner2 {
        public void performAction() {}
    }

    @Override
    public synchronized Object recursiveMethod(int count) {
        ; // Empty statement
        if (count == 0) return "base";
        SourceClass outerRef = this;
        Object result = (staticFlag > 0) ? 
                         outerRef.new Inner2().performAction() : 
                         recursiveMethod(count-1);
        return result;
    }
}

class ParentClass {
    public Object recursiveMethod(int count) {
        return null;
    }
}

public class TargetClass<T> {
    // Target class content
}
```