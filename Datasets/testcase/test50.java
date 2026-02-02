```java
package pkg93713;

import java.util.*;

sealed class TargetClass permits DummySubclass {
    void targetMethod() {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Anonymous in target");
            }
        };
        r.run();
    }
}

final class DummySubclass extends TargetClass {}

class SourceClass {
    TargetClass targetField;
    
    static class StaticNested {}
    
    public final List<String> finalMethod() {
        ChainHelper helper = new ChainHelper();
        boolean condition = helper.m1().m2().m3().check();
        
        List<String> result = condition ? new ArrayList<>() : new LinkedList<>();
        
        Base b = new Base("test") {
            void doSomething() {
                mylabel: {
                    super.doSomething();
                    System.out.println("Labeled block");
                }
            }
        };
        b.doSomething();
        
        return result;
    }
    
    static class ChainHelper {
        ChainHelper m1() { return this; }
        ChainHelper m2() { return this; }
        ChainHelper m3() { return this; }
        boolean check() { return true; }
    }
    
    static class Base {
        Base(String s) {}
        void doSomething() {}
    }
}
```