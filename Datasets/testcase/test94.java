```java
package p1;

import p2.TargetClass;
import java.util.*;

public class SourceClass extends SuperSource {
    private String data = "data";

    public class InnerSource {
        public void innerMethod() {}
    }

    public void useAnonymous() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous");
            }
        };
        r.run();
    }

    @Override
    public synchronized List<String> recursiveMethod(TargetClass target, int depth) throws IllegalArgumentException {
        if (depth <= 0) {
            return new ArrayList<>();
        }

        List<String> results = new ArrayList<>();
        switch (depth) {
            case 1:
                results.add("One");
                break;
            case 2:
                results.add("Two");
                break;
            default:
                results.add("Many");
        }

        for (String item : new String[]{"A", "B", "C"}) {
            results.add(item);
        }

        synchronized(this) {
            if (this.data == null) {
                throw new IllegalArgumentException("Invalid data");
            }
            results.add(this.data);
        }

        List<String> subResults = super.recursiveMethod(target, depth - 1);
        results.addAll(subResults);

        results.forEach(s -> Outer.Inner.process(s));
        
        target.abstractMethod();
        this.recursiveMethod(target, depth - 1);
        return results;
    }
}

class SuperSource {
    public List<String> recursiveMethod(TargetClass target, int depth) {
        return Collections.singletonList("Super");
    }
}

class Outer {
    public static class Inner {
        public static Object process(String s) {
            return s.toLowerCase();
        }
        public static Object process(String s, int i) {
            return s.repeat(i);
        }
    }
}
```

```java
package p2;

import java.util.List;

public abstract class TargetClass extends BaseClass {
    public abstract void abstractMethod();
    
    public class InnerTarget {
        public void targetInnerMethod() {}
    }
}

class BaseClass {
    public void baseMethod() {}
}
```