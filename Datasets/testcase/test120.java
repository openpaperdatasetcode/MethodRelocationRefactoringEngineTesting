```java
package move.method.test;

class Target {
    void doSomething() {}
}

class Helper {}

class Base {
    Base(int x) {}
}

class Container {
    private static int privateStaticField = 42;

    private static class Source extends Base {
        Source() {
            super(0);
        }

        class InnerMemberClass {}

        private Target targetField = new Target();
        private int value;

        public int recursiveMethod(int n) {
            this.value = n;
            
            InnerMemberClass inner = new InnerMemberClass();
            
            Base base = new Base(10) {};
            
            class LocalClass<T extends Number> {
                T number;
                LocalClass(T number) { 
                    this.number = number; 
                }
                T getNumber() { 
                    return number; 
                }
            }
            
            LocalClass<Integer> local = new LocalClass<>(5);
            int num = local.getNumber();
            
            int outerPrivate = Container.privateStaticField;
            
            if (n <= 0) {
                targetField.doSomething();
                return 1;
            }
            
            if (n > 10) {
                someStaticMethod();
            } else {
                someStaticMethod();
            }
            
            return n * recursiveMethod(n - 1);
        }

        protected static void someStaticMethod() {}
    }
}
```