```java
package p;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@interface TestAnnotation {}

public abstract class Source extends SomeBase {
    private Target targetField;
    public class SourceMemberInner {}

    public Source() {
        super();
        targetField = new Target() {};
    }

    @TestAnnotation
    protected final Target.TargetInnerRec methodToMove(String... args) {
        switch (args.length) {
            case 0:
                System.out.println(super.baseField);
                break;
            default:
                Target.TargetInnerRec inner = targetField.new TargetInnerRec();
                inner.doSomething();
                this.privateMethod();
                if (args[0].isEmpty()) {
                    System.out.println("Empty");
                } else {
                    System.out.println("Non-empty");
                }
        }

        int[] arr = {1, 2};
        int x = arr[0];
        List list = new ArrayList();
        list.add(1);
        return null;
    }

    private void privateMethod() {
        System.out.println("Private method called");
    }

    public void methodWithLocalInner() {
        class LocalInner {}
        LocalInner local = new LocalInner();
    }

    public static void main(String[] args) throws Exception {
        Source src = new Source() {};
        Method m = Source.class.getDeclaredMethod("methodToMove", String[].class);
        m.setAccessible(true);
        m.invoke(src, (Object) new String[]{"test"});
    }
}

class SomeBase {
    protected int baseField = 42;
}

private abstract class Target {
    public class TargetInnerRec {
        public void doSomething() {
            System.out.println("TargetInnerRec operation");
        }
    }
}
```