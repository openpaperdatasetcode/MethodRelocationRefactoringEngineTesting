```java
package pkg;

public record Target() {
    public void targetMethod() {}
}

record Source(Target target) {
    static class StaticNested {}

    static class BaseClass {
        void baseMethod() {}
    }

    class Inner extends BaseClass {
        Inner() {
            methodToMove();
        }

        void methodToMove() {
            super.baseMethod();
            Source.this.target.targetMethod();
            Source.this.outerProtectedMethod();
            Object o = new Object();
            
            class BaseWithArg {
                BaseWithArg(int i) {}
            }
            
            class Sub extends BaseWithArg {
                Sub() {
                    super(1);
                }
            }
            new Sub();
        }
    }

    Inner inner = new Inner();
    BaseClass anonymous = new BaseClass() {};

    protected void outerProtectedMethod() {}

    void callViaReflection() {
        try {
            java.lang.reflect.Method m = Inner.class.getDeclaredMethod("methodToMove");
            m.setAccessible(true);
            m.invoke(inner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```