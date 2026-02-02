```java
package test.case98138;

import java.util.ArrayList;

abstract class Source<T> extends BaseSource {
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
        }
    };

    protected int sourceField = 5;

    public Object methodToMove(Target target) {
        class LocalClass {
            private int x;
            private Target targetRef;

            LocalClass(Target target) {
                this.targetRef = target;
            }

            public void useTargetField() {
                x = targetRef.field1;
            }

            public int getX() {
                return x;
            }
        }

        LocalClass lc = new LocalClass(target);
        lc.useTargetField();
        int y = lc.getX();

        ArrayList list = new ArrayList();
        list.add(y);

        int sf = sourceField;

        int staticVal = Other.staticField;

        abstract class LocalAbstract {
            abstract void methodLocal();
        }

        class LocalConcrete extends LocalAbstract {
            @Override
            void methodLocal() {
            }
        }

        LocalAbstract la = new LocalConcrete();
        la.methodLocal();

        AbstractClass obj = new AbstractClass() {
            @Override
            public void methodInAnon() {
            }
        };
        obj.methodInAnon();

        return list;
    }
}

class BaseSource {
}

class BaseTarget {
}

class Target extends BaseTarget {
    int field1 = 5;

    class InnerTarget {
    }
}

class Other {
    public static int staticField = 10;
}

abstract class AbstractClass {
    public abstract void methodInAnon();
}
```