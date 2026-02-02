```java
package pkg;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    String value();
}

interface TestInterface {
    void interfaceMethod();
}

public class Target implements TestInterface {
    @Override
    public void interfaceMethod() {}

    public static class NestedClass {
        public static String staticMethod() {
            return "Called";
        }
    }
}

class Outer {
    private class Source {
        private int field = 10;
        
        class MemberInner {}
        
        void outerMethod() {
            class LocalInner {}
            LocalInner local = new LocalInner();
        }

        Object methodToMove(Target target, Object... args) {
            String s = new String("Test");
            int val = this.field;
            return s;
        }

        @TestAnnotation(value = "attribute")
        protected String callMethod() {
            return Target.NestedClass.staticMethod();
        }
    }
}
```