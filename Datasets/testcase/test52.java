```java
package pkg;

import java.util.ArrayList;
import java.util.List;

@interface CustomAnnotation {}

class SourceClass {
    static List<String> staticData = new ArrayList<>();

    TargetClass targetField;

    class InnerClassA {}
    class InnerClassB {}

    @CustomAnnotation
    public strictfp List<String> recursiveMethod(int depth) {
        if (depth <= 0) {
            return new ArrayList<>(SourceClass.staticData);
        }
        
        List<String> partial = this.recursiveMethod(depth - 1);
        partial.add("Depth: " + depth);

        for (int i = 0; i < depth; i++) {
            if (i == 2) {
                break;
            }
            String current = partial.get(i);
            partial.set(i, current + "!");
        }
        return partial;
    }
}

class TargetClass {
    /**
     * Javadoc comment for TargetClass
     */
    class target_inner_rec {
    }
}
```