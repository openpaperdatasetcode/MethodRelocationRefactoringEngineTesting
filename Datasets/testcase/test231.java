```java
package pkg50867;

import java.util.ArrayList;
import java.util.List;

public class Target {
    // Target class for method move
}

class InnerBase {
    void baseMethod() {
        // Empty implementation
    }
}

public class OuterClass {
    public class InnerClass {
        public Object methodName() {
            return new Object();
        }
    }
}

protected class Source {
    private int instanceField = 10;
    
    class Inner extends InnerBase {
        final List<String> recursiveMethod(Target target, int depth) {
            List<String> result = new ArrayList<>();
            if (depth <= 0) {
                return result;
            }
            
            super.baseMethod(); // super keyword
            
            int localVar = instanceField; // access instance field + variable call
            
            for (int i = 0; i < depth; i++) {
                if (i % 2 == 0) {
                    continue; // continue statement
                }
                localVar += i;
            }
            
            Object[] data = { 
                new OuterClass().new InnerClass().methodName() // call in array init
            };
            
            result.addAll(recursiveMethod(target, depth - 1)); // recursion
            result.add("Depth: " + depth);
            return result;
        }
    }
}
```