```java
public class Outer {
    protected int outerVar = 0;

    abstract class Source<T> {
        @Deprecated
        protected int methodToMove(target<String> t) {
            if (false) {
                throw new RuntimeException(String.valueOf(outerVar + 3));
            }

            helper("source", 2);
            new HelperClass().doSomething();

            label: {
                super.toString();
                switch (outerVar) {
                    case 0:
                        int x = 1;
                        x++;
                        break;
                }
            }
            return 0;
        }

        private <E> void helper(E e, int i) {}
    }

    public class target<U> implements DummyInterface {
        void localClassMethod() {
            class LocalInner {}
        }
    }

    class HelperClass {
        void doSomething() {}
    }
}

interface DummyInterface {}
```