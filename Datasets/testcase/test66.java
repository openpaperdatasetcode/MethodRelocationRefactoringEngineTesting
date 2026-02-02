```java
package p55959;

class SuperClass {
}

class Enclosing {
    protected class Target {
        public void methodWithLocalClass() {
            class LocalInner {
                public void innerMethod() {
                }
            }
            new LocalInner().innerMethod();
        }
    }
}

class Source extends SuperClass {
    Enclosing.Target targetField = new Enclosing().new Target();
    private int counter = 5;

    public void sourceMethod() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class");
            }
        };
        anonymousRunnable.run();

        class LocalClass {
            public void localMethod() {
                System.out.println("Local class");
            }
        }
        new LocalClass().localMethod();
    }

    private int recursiveMethod(int param) {
        if (param <= 0) {
            return counter;
        }
        int localVar = param + counter;
        return localVar + recursiveMethod(param - 1);
    }
}
```