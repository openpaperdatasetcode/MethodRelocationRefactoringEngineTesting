```java
package test;

import java.util.*;

interface SuperInterface {
    default int superMethod() {
        return 0;
    }
}

record SourceRecord() {
    private static int staticCounter = 1;
    private int instanceCounter = 5;

    class MemberInnerClass {
        int innerField = 10;
    }

    static class StaticNestedClass {}

    synchronized List<String> methodToMove(TargetRecord target) {
        class LocalClass {
            void process() {
                System.out.println("Processing in local class");
            }
        }

        List<String> results = new ArrayList<>();
        int counter = 0;
        
        try {
            LocalClass local = new LocalClass();
            local.process();
            
            do {
                if (counter < target.id()) {
                    if (instanceCounter > staticCounter) {
                        results.add("Entry " + counter);
                    }
                }
                counter++;
            } while (counter < 3);
            
            MemberInnerClass inner = new MemberInnerClass();
            System.out.println(inner.innerField);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }
}

private record TargetRecord(int id) implements SuperInterface {
    class TargetInnerClass {
        void innerMethod() {
            System.out.println("Inner method called");
        }
    }
    
    protected int callMethod() {
        int base = SuperInterface.super.superMethod();
        List<Integer> nums = new ArrayList<>();
        nums.add(base);
        nums.add(id);
        return nums.stream().mapToInt(i -> i).sum();
    }
}
```