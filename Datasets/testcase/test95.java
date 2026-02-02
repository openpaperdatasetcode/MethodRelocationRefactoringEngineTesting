```java
import java.util.ArrayList;
import java.util.List;

interface SourceInterface {}
interface TargetInterface {}

public class Source implements SourceInterface {
    private static int outerPrivateStatic = 10;
    
    public static abstract class AbstractNested {
        protected abstract void abstractMethod();
    }
    
    public static class MethodContainer {
        private List<String> method(Target<String> target) {
            class LocalType {
                void localMethod() {
                    int y = outerPrivateStatic;
                }
            }
            
            new LocalType().localMethod();
            outerPrivateStatic++;
            return new ArrayList<>();
        }
        
        private void method() {}
    }
}

public class Target<T> implements TargetInterface {
    public static class NestedClass {}
}
```