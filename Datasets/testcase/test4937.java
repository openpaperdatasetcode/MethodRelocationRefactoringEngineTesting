package test;

import java.lang.reflect.Method;
import java.util.List;

public class SourceClass<T> {
    // Member inner class
    public class SourceMemberInner {
        private T value;
        
        public SourceMemberInner(T value) {
            this.value = value;
        }
        
        public T getValue() {
            return value;
        }
    }
    
    // Static nested class
    public static class SourceStaticNested {
        private String data;
        
        public SourceStaticNested(String data) {
            this.data = data;
        }
        
        public String getData() {
            return data;
        }
    }
    
    // Recursive method to be refactored
    void recursiveMethod(TargetClass target, int depth) {
        // Base case for recursion
        if (depth <= 0) {
            return;
        }
        
        // Type declaration statement
        SourceMemberInner memberInner = new SourceMemberInner((T) "test");
        SourceStaticNested staticNested = new SourceStaticNested("static_data");
        
        // Variable call
        target.setCount(target.getCount() + 1);
        
        // Constructor invocation
        TargetClass.TargetStaticNested targetStatic = new TargetClass.TargetStaticNested();
        
        // Enhanced for statement
        for (String item : targetStatic.getItems()) {
            System.out.println("Item: " + item);
        }
        
        // Used by reflection
        try {
            Method method = TargetClass.class.getMethod("getCount");
            Integer count = (Integer) method.invoke(target);
        } catch (Exception e) {
            // No new exception
        }
        
        // Recursive invocation
        recursiveMethod(target, depth - 1);
    }
    
    // Override violation - incorrect signature for Object.toString()
    public String toString(TargetClass target) {
        return "SourceClass[target=" + target.getCount() + "]";
    }
}
package test;

import java.util.Arrays;
import java.util.List;

protected class TargetClass extends ParentClass {
    private int count;
    
    // Super constructor invocation
    public TargetClass() {
        super(100);
        this.count = 0;
    }
    
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private List<String> items;
        
        public TargetStaticNested() {
            this.items = Arrays.asList("a", "b", "c");
        }
        
        public List<String> getItems() {
            return items;
        }
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
package test;

public class ParentClass {
    private int parentValue;
    
    public ParentClass(int value) {
        this.parentValue = value;
    }
    
    public int getParentValue() {
        return parentValue;
    }
}
