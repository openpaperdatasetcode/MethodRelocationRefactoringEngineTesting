```java
package pkg;

class Helper {
    public static RuntimeException exceptionField = new RuntimeException();
}

public class SuperTarget {
    public void methodToMove(TargetClass<Integer> target, int... args) {
    }
}

public class TargetClass<T> extends SuperTarget {
    private Runnable anonymousInner = new Runnable() {
        public void run() {}
    };
}

public class Outer {
    private int privateField = 0;

    public class SourceClass {
        public class MemberInnerClass {}

        private Runnable anonymousInner = new Runnable() {
            public void run() {}
        };

        TargetClass<Integer> methodToMove(TargetClass<Integer> target, int... args) {
            int value = Outer.this.privateField;

            java.util.List list = new java.util.ArrayList();
            list.add("raw");

            helperMethod();
            helperMethod();
            helperMethod();

            if (args.length > 0) {
                Helper.exceptionField.printStackTrace();
                Helper.exceptionField.printStackTrace();
                throw Helper.exceptionField;
            }

            return target;
        }

        private void helperMethod() {}
    }
}
```