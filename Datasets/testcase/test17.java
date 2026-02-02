```java
public class SourceClass {
    private int value = 10;
    
    Runnable anonymous1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous1: " + value);
        }
    };
    
    Runnable anonymous2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous2: " + value);
        }
    };

    protected List<String> methodToMove(TargetClass<Integer> target) {
        List<String> result = new ArrayList<>();
        result.add("Value: " + value);
        return result;
    }

    protected List<String> methodToMove(String input) {
        return Collections.singletonList(input);
    }
}

public class TargetClass<T> {
    public class InnerClass {
        private T data;
        
        public InnerClass(T data) {
            this.data = data;
        }
        
        public T getData() {
            return data;
        }
    }
}
```