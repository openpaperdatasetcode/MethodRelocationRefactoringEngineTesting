```java
package pkg;

public class TargetClass {
    public int field = 1;

    public TargetClass() {
        // Anonymous inner class in target class
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner in TargetClass");
            }
        };
    }
}

interface SomeInterface {
    void implementMe();
}

class SomeBaseClass {
    public SomeBaseClass() {
    }

    public void baseMethod() {
    }
}

public class TestContainer {
    private static class SourceClass implements SomeInterface {
        static Exception existingException = new RuntimeException();

        // Overloaded method to be moved
        static void methodToMove(TargetClass target) {
            int val = target.field; // Access target's field
            Object obj = new Object();
            obj.toString(); // Variable call and instance method access

            class LocalInner extends SomeBaseClass {
                LocalInner() {
                    super(); // Super constructor invocation
                }

                void innerMethod() {
                    super.baseMethod(); // Super keyword usage
                }
            }

            LocalInner inner = new LocalInner();
            inner.innerMethod();

            throw existingException; // Throw existing exception
        }

        // Overloaded method
        static void methodToMove() {
        }

        @Override
        public void implementMe() {
        }

        {
            // Anonymous inner class in source
            new Runnable() {
                @Override
                public void run() {
                }
            };
        }

        void localClassMethod() {
            // Local inner class in source
            class LocalClass {
            }
        }
    }

    public static final class CallerClass {
        {
            SourceClass ref = null;
            ref.methodToMove(new TargetClass()); // Instance reference call
        }
    }
}
```