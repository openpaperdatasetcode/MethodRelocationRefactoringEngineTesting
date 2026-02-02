```java
package pkg97664;

import java.awt.Point;

abstract class Source97664 {
    private target_inner_rec targetField;

    public static class SourceInner {
        protected class ProtectedInner {
        }

        @Annotation97664
        public static final void methodToMove() {
            SourceInner outer = new SourceInner();
            Object o = outer.new ProtectedInner();
            ProtectedInner p = (ProtectedInner) o;

            int x = 1;

            Parent97664 parent = new Parent97664() {
                int foo() {
                    return x > 0 ? super.parentMethod(1, 2) : 0;
                }
            };

            SomeInterface obj = new SomeInterface() {
                public int methodWithReturn() {
                    return new Point().x + 3;
                }
            };
        }
    }

    SomeInterface anonymousField = new SomeInterface() {
        public int methodWithReturn() {
            return 0;
        }
    };
}

class Parent97664 {
    protected int parentMethod(int a, int b) {
        return a + b;
    }
}

abstract class target_inner_rec {
    public void methodWithLocalClass() {
        class LocalClass {
        }
        LocalClass lc = new LocalClass();
    }
}

@interface Annotation97664 {
}

interface SomeInterface {
    int methodWithReturn();
}
```