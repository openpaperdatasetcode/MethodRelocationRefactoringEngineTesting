```java
// File: p1/Source.java
package p1;

import p2.Container;

public class Source<T> extends Container {
    public class Inner1 {
        public class Inner2 {
            private int innerField = 5;

            private void innerMethod() {
                System.out.println("Inner method called");
            }

            target<Integer> moveMethod(target<Integer> t, String... items) {
                String s = super.toString();
                int val = this.innerField;
                this.innerMethod();
                return t;
            }
        }
    }
}

// File: p2/Container.java
package p2;

public class Container {
    protected class target<S> {
        public class InnerTarget {
        }
    }
}
```