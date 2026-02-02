```java
// id: 2608
package pkg;

import java.util.ArrayList;
import java.util.List;

class Source {
    private Target targetField;

    public static class Nested1 {
        protected List<String> theMethod(int depth) {
            if (depth <= 0) {
                return new ArrayList<>();
            }

            class LocalClass<T extends Number> {
                T field;

                LocalClass() {
                    super();
                    this.field = (T) Double.valueOf(Math.PI);
                }

                double getValue() {
                    return this.field.doubleValue();
                }
            }

            LocalClass<Double> localObj = new LocalClass<>();
            double value = localObj.getValue();

            for (int i = 0; i < 2; i++) {
                if (i == 1) continue;
            }

            List<String> result = theMethod(depth - 1);
            result.add(String.valueOf(value));
            return result;
        }
    }

    public static class Nested2 {}
}

class SuperTarget {
    void baseMethod() {}
}

class Target extends SuperTarget {
    {
        syncCall();
    }

    synchronized void syncCall() {
        Source.staticMethod();
        super.baseMethod();
    }

    public static class Nested {}

    static void staticMethod() {}
}
```