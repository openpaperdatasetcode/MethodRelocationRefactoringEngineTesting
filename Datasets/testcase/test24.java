```java
package pkg;

import java.util.ArrayList;
import java.util.List;

public interface Source extends Super {
    @Override
    default List<String> methodName(Target target, String... args) {
        String value = args.length > 0 ? args[0] : "default";
        
        Base obj = new Base() {
            @Override
            public void methodName(String... innerArgs) {
                super.methodName(innerArgs);
            }
        };
        
        List<String> result = new ArrayList<>();
        result.add(value);
        obj.methodName(args);
        return result;
    }
}

interface Super {
    List<String> methodName(Target target, String... args);
}

interface Target {}

class Base {
    public void methodName(String... args) {}
}
```