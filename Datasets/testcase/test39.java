```java
// File: pkg/SourceAnnotation.java
package pkg;

@interface SourceAnnotation {
    class Nested1 {
        protected Object methodToMove(TargetAnnotation.TargetNested param) {
            Runnable r = () -> {
                this.overloadedMethod(42);
            };
            r.run();
            
            int base = 5;
            new java.util.ArrayList() {{
                super();
            }};
            return base;
        }

        protected void overloadedMethod(int num) {
            System.out.println(num);
        }

        protected void overloadedMethod(String str) {
            System.out.println(str);
        }
    }

    class Nested2 {}
}

// File: pkg/TargetAnnotation.java
package pkg;

public @interface TargetAnnotation {
    class TargetNested {
        public void callerMethod() {
            java.util.List<String> items = new java.util.ArrayList<>();
            items.stream()
                 .map(new SourceAnnotation.Nested1()::overloadedMethod)
                 .count();
        }
    }
}
```