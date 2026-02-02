```java
// File: Source.java
package pkg;

public interface Source extends SomeInterface {
    class InnerInSource extends Base {
        private int helper() {
            return 1;
        }

        protected <T extends Number> int recursiveMethod(Target target, int n) {
            if (n <= 0) {
                return 0;
            }

            int value = this.protectedField;
            int step = helper();

            class LocalType {
                int multiply(int a, int b) {
                    return a * b;
                }
            }
            LocalType local = new LocalType();
            int product = local.multiply(n, step);

            int nextN = n - 1;
            int result = recursiveMethod(target, nextN) + product;

            if (target == null) {
                target = new Target() {
                    @Override
                    public int getValue() {
                        return 1;
                    }
                };
            }
            result += target.getValue();

            return result;
        }
    }

    static Object anonymousField = new Object() {};
    
    static void methodWithLocalClass() {
        class LocalClass {}
        new LocalClass();
    }
}

// File: Base.java
package pkg;

public class Base {
    protected int protectedField = 42;
}

// File: SomeInterface.java
package pkg;

public interface SomeInterface {}

// File: Target.java
package pkg;

strictfp interface Target {
    class InnerInTarget {}
    
    default void methodWithAnonymous() {
        new Object() {
            void anonymousMethod() {}
        }.anonymousMethod();
    }
    
    int getValue();
}
```