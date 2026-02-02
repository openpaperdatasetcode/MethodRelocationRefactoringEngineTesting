```java
package pkg7593;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

strictfp class Source {
    static class NestedStatic {}
    class MemberInner {
        final List<String> methodToMove(Target param) throws IOException {
            String data = "test";
            synchronized (this) {
                data = "synced";
            }
            if (data.isEmpty()) throw new IOException();
            int value = new Helper().overrideMethod() > 0 ? new Helper().overrideMethod() : 0;
            return Arrays.asList(data, String.valueOf(value));
        }
        
        final void methodToMove() {}
    }
}

class Target {
    class target_inner {}
    
    void createAnonymous() {
        Runnable r = new Runnable() {
            @Override public void run() {}
        };
    }
}

class BaseClass {
    public int overrideMethod() { return 1; }
}

class Helper extends BaseClass {
    @Override
    public int overrideMethod() { return 2; }
}
```