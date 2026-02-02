```java
package samepackage;

public class Source {
    public class BaseClass {
        void baseMethod() {}
    }

    public class Inner extends BaseClass {
        private Inner next;
        private int value = 42;

        protected final void recursiveMethod(Target<String> target, int depth) {
            if (depth <= 0) return;
            
            int[] array = {1, 2, 3};
            super.baseMethod();
            
            try {
                if (target == null) throw new NullPointerException();
                
                target.performAction();
                Runnable r = () -> System.out.println(this.value);
                r.run();
                
                int num = getNumber();
                switch (num) {
                    case 1: break;
                    default: System.out.println("Default case");
                }
                
                BaseClass obj = new BaseClass() {
                    @Override void baseMethod() {
                        System.out.println("Subclass method");
                    }
                };
                obj.baseMethod();
                
            } catch (NullPointerException e) {
                System.out.println("NullPointerException caught");
            }
            
            if (next != null) {
                next.recursiveMethod(target, depth - 1);
            }
        }
        
        private int getNumber() {
            return 3;
        }
    }
}

final class Target<T> extends BaseClass {
    void performAction() {
        System.out.println("Target action");
    }
}

class BaseClass {}
```