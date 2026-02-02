```java
package pkg;

class BaseClass {
    protected int protectedField = 42;
}

class SourceClass<T> extends BaseClass {
    class InnerSource {
        private final void methodToMove(TargetClass.InnerTarget target, int... values) {
            if (values == null) {
                throw new NullPointerException();
            } else {
                try {
                    int sum = 0;
                    for (int val : values) {
                        sum += val;
                    }
                    System.out.println("Sum: " + sum);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            Util.staticMethod();

            int baseValue = 10;
            new BaseClass() {};

            class LocalClass {
                void methodA() {}
                void methodB() {}
            }
            LocalClass lc = new LocalClass();
            lc.methodA();

            int outerValue = protectedField;
            Runnable r = () -> System.out.println("Lambda: " + outerValue);
            r.run();
        }
    }

    void createAnonymous() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class");
            }
        }.run();
    }
}

class TargetClass<U> {
    /**
     * Javadoc for TargetClass
     * @author CodeGen
     * @version 1.0
     */
    class InnerTarget {
        void targetMethod() {}
    }
}

class Util {
    static void staticMethod() {}
}
```