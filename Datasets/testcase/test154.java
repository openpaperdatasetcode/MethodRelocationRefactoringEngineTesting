```java
package same;

public class Source extends SomeBaseClass {
    public static class StaticNested {}

    public Target targetField;

    public void methodWithInner() {
        class LocalInner {
            public LocalInner(int... values) throws Exception {
                if (targetField.volatileField == 0) {
                    throw Source.this.ex;
                }
                outerMethod();
            }
        }
    }

    private Exception ex = new Exception();
    private void outerMethod() {}
}

public class Target<T> {
    public volatile int volatileField;
    public class MemberInner {}
}

public class SomeBaseClass {}
```