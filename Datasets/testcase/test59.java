```java
import java.util.function.Function;

public class TestCase80593 {

    public static class Source80593<T> {
        protected int protectedField = 5;

        public static class SuperClass {
            SuperClass(int x) {}
            void methodInSuper() throws Exception {}
        }

        public static class StaticNestedClass {}

        private int methodToMove(TestCase80593.Target80593 target, int... values) {
            class LocalClass extends SuperClass {
                LocalClass() {
                    super(1);
                }
                
                @Override
                void methodInSuper() {}
            }
            
            try {
                LocalClass obj = new LocalClass();
                Function<String, Integer> converter = Integer::valueOf;
                int sum = target.compute();
                return converter.apply("10") + protectedField + sum;
            } catch (Exception e) {
                return 0;
            }
        }
        
        private Runnable runner = new Runnable() {
            @Override
            public void run() {}
        };
    }

    public static class Target80593<T> {
        public static class NestedTarget {}
        
        public int compute() {
            return 3;
        }
    }
}
```