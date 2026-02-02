```java
package pkg;

public record Source() {
    public static class StaticNested {}
    
    public class MemberInner {
        private TargetHolder.target field;
        
        public final java.util.List<String> methodToMove() {
            class LocalClass {
                public LocalClass() {
                    this(1);
                }
                public LocalClass(int a) {
                    super();
                }
            }
            ;
            if (field != null) {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add("test");
                return list;
            }
            return new java.util.ArrayList<>();
        }
    }
}

class TargetHolder {
    protected record target() {}
}
```