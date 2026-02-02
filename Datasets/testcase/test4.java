```java
package pkg;

class SuperClassForTarget {
}

class target_inner_rec extends SuperClassForTarget {
    public int publicField = 1;
    public static class StaticNestedClass {
    }
}

private class SourceClass {
    private int outerPrivateField = 10;

    public class InnerClass {
        private int innerField = 5;

        protected final int methodToMove(target_inner_rec target) {
            int outerValue = outerPrivateField;
            int innerValue = this.innerField;
            int count = 3;
            while (count > 0) {
                count--;
            }
            Object obj = new Object() {};
            return outerValue + innerValue + count + target.publicField;
        }
    }

    public void createAnonymousClass() {
        Runnable r = new Runnable() {
            public void run() {}
        };
    }
}
```