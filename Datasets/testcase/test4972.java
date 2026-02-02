package test;

protected record SourceClass(TargetClass targetField) {
    static class InnerSource1 {
        // Static nested class 1
    }
    
    static class InnerSource2 {
        // Static nested class 2
        
        private List<String> recursiveMethod(int depth) {
            if (depth <= 0) {
                return new ArrayList<>(List.of("Base case"));
            }
            
            List<String> result = new ArrayList<>();
            result.add("Current depth: " + depth);
            
            // Uses outer this
            result.addAll(new SourceClass(this.outer().targetField).new InnerSource2().recursiveMethod(depth - 1));
            
            // Array initializer
            String[] values = {"value1", "value2"};
            result.addAll(Arrays.asList(values));
            
            // Variable call
            String var = "Variable";
            result.add(var);
            
            return result;
        }
        
        private SourceClass outer() {
            return new SourceClass(new TargetClass());
        }
    }
}
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Javadoc for TargetClass
 * This class is the target for the move method refactoring
 */
private record TargetClass() extends ParentClass {
    static class InnerTarget {
        // Static nested class in target
    }
}

class ParentClass {
    // Parent class for TargetClass extension
}
