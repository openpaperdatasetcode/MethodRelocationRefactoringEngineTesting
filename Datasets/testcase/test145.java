```java
package pkg23833;

import java.util.ArrayList;
import java.util.List;

public class TargetClass<T> implements java.io.Serializable {
    public static int staticField = 10;
    public class TargetInner {
        public void innerMethod() {
            System.out.println("TargetInner method");
        }
    }
}

class SourceClass {
    private TargetClass<String> targetField = new TargetClass<>();
    private int outerPrivate = 5;

    public static class StaticNested {}

    public class MemberInner {
        public void accessOuter() {
            int value = outerPrivate;
        }
    }

    final void methodToMove() {
        new Helper(3, TargetClass.staticField);
        
        int counter = 0;
        do {
            List<String> result = processData(3);
            counter++;
        } while (counter < 2);
        
        for (int i = 0; i < 1; i++) {
            int loopVar = i;
        }
        
        int localVar = 10;
        localVar++;
        
        MemberInner inner = new MemberInner();
        inner.accessOuter();
    }
    
    private class Helper {
        private Helper(int num, int field) {}
    }
    
    public List<String> processData(int param) {
        super.toString();
        return new ArrayList<>();
    }
}

class Helper {
    // Empty class for package structure
}
```