```java
// File: Container44724.java
package pkg;

public class Container44724 {
    protected static record Target44724() {
        public void targetMethod() {
            class LocalTarget {
            }
        }
    }
}
```

```java
// File: Source44724.java
package pkg;

interface SomeInterface44724 {}

record Source44724(Container44724.Target44724 targetField) implements SomeInterface44724 {
    protected int protectedField = 42;

    public class InnerSource {
        /**
         * Method javadoc.
         * @return some object
         */
        @Deprecated
        private Object methodToMove() {
            int value = Source44724.this.protectedField;

            switch (value) {
                case 42:
                    Object o = new Object();
                    break;
                default:
                    break;
            }

            class LocalClass {}
            LocalClass lc = new LocalClass();
            return lc;
        }
    }

    public void sourceMethod() {
        class LocalInSource {}
        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
    }
}
```