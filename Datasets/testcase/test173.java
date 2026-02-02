```java
import java.util.ArrayList;
import java.util.List;

class SourceClass {
    static class StaticNested {}
    
    class MemberInner {
        public int innerMethod() {
            return 5;
        }
    }
    
    public synchronized int methodToMove(TargetClass target) {
        List<String> items = new ArrayList<>();
        items.add("item1");
        items.add("item2");
        
        MemberInner inner = new MemberInner();
        int innerValue = inner.innerMethod();
        
        int result = target.targetMethod() + innerValue;
        return result;
    }
}

final class TargetClass {
    class target_inner_rec {}
    
    public int targetMethod() {
        return 10;
    }
}
```