package test;

import java.util.List;
import java.util.ArrayList;

// Source: private record class with type parameter, static nested and member inner classes
private record SourceRecord<T>(T value) {
    // Static nested class
    public static class SourceStaticNested {
        public static int staticField = 3; // "3" for target_feature
    }

    // Member inner class
    public class SourceInner {
        public T process(T data) {
            return data;
        }
    }

    // Overloaded method 1
    int overloadedMethod(TargetRecord target) {
        // Source contains target's field (per_condition)
        TargetRecord targetField = target;
        
        // Variable call
        int result = targetField.id();
        
        // Private ConstructorInvocation with ClassName.field and "3"
        SourceInner inner = new SourceInner();
        List<Integer> values = new ArrayList<>();
        values.add(SourceStaticNested.staticField); // ClassName.field (static nested class field)
        values.add(3); // "3" in target_feature
        
        return result + values.stream().mapToInt(Integer::intValue).sum();
    }

    // Overloaded method 2 (overload_exist)
    int overloadedMethod(TargetRecord target, String suffix) {
        int base = overloadedMethod(target);
        return base + suffix.length();
    }
}
    package test;

// Target: non-sealed record class with anonymous inner class
non-sealed record TargetRecord(int id, String name) {
    // Static initializer with anonymous inner class (target_feature)
    static {
        Runnable anonTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("TargetRecord anonymous class initialized");
            }
        };
        anonTask.run();
    }

    // Method for variable call
    public int getCalculatedValue() {
        return id * 2;
    }
}
    