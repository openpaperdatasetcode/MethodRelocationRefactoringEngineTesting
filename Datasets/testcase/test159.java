```java
package pkg;

class Container {
    protected static class SourceClass {
        public static class Nested1 {}
        static class Nested2 {}

        private static void methodToMove(TargetClass target) {
            class LocalType {
                void execute() {
                    System.out.println("Executing local type");
                }
            }
            LocalType lt = new LocalType();
            lt.execute();
            int val = target.field;
        }
    }
}

non-sealed class TargetClass {
    int field = 5;

    void createAnonymous() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
        r.run();
    }
}
```