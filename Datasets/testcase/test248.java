```java
package pkg20265;

public class Container {
    public static int depth = 0;
    private int privateField = 0;

    protected interface TargetInterface {
        class TargetInner {
        }
    }

    public interface SourceInterface {
        TargetInterface.TargetInner targetField = null;

        class SourceInner {
        }

        default Object recursiveMethod() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("test");

            Runnable r = new Runnable() {
                @Override
                public void run() {
                }
            };

            Container outer = new Container();
            Object o = outer.new NonStaticInner().innerMethod();
            Object dummy = targetField;

            int count = 0;
            while (count < 1) {
                switch (count) {
                    case 0:
                        if (Container.depth < 1) {
                            Container.depth++;
                            recursiveMethod();
                        }
                        count++;
                        break;
                }
            }

            SourceInterface self = this;
            self.toString();
            int value = outer.privateField;

            return null;
        }
    }

    class NonStaticInner {
        public Object innerMethod() {
            return null;
        }
    }
}
```