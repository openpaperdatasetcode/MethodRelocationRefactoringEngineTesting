package test;

import java.util.ArrayList;
import java.util.List;

class SourceClass {
    // Inner class containing recursive constructor (method_position: source_inner_rec)
    class SourceInner {
        private List<String> data;
        
        // Private constructor to be refactored (type: constructor)
        private SourceInner(TargetClass<String> target) {
            // Super constructor invocation
            super();
            
            // Check target parameter (per_condition)
            if (target == null) {
                // Throw statement
                throw new IllegalArgumentException("Target cannot be null");
            }
            
            this.data = new ArrayList<>();
            
            // Variable call (target class member)
            int count = target.getCount();
            
            // For statement
            for (int i = 0; i < count; i++) {
                data.add("Item " + i);
            }
            
            // Raw type usage
            TargetClass rawTarget = new TargetClass();
            
            // While statement
            int index = 0;
            while (index < target.inner.getValues().size()) {
                data.add(target.inner.getValues().get(index));
                index++;
            }
            
            // Recursive constructor invocation
            if (count > 5) {
                new SourceInner(new TargetClass<>(count - 5));
            }
        }
        
        public List<String> getData() {
            return data;
        }
    }
}
   package test;

import java.util.List;

public class TargetClass<T> {
    // Type parameter (target_feature)
    private T value;
    int count;
    
    // Member inner class (target_feature)
    public class TargetInner {
        private List<String> values;
        
        public TargetInner(List<String> values) {
            this.values = values;
        }
        
        public List<String> getValues() {
            return values;
        }
    }
    
    public TargetInner inner;
    
    public TargetClass() {
        this.count = 0;
        this.inner = new TargetInner(List.of());
    }
    
    public TargetClass(int count) {
        this.count = count;
        this.inner = new TargetInner(List.of("Inner1", "Inner2"));
    }
    
    public int getCount() {
        return count;
    }
}
     