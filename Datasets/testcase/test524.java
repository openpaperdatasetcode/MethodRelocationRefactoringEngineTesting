import java.util.ArrayList;
import java.util.List;

// Source normal class (strictfp modifier, generic with type parameter, same package as target)
strictfp class SourceClass<T> {
    // Field of target class (satisfies per_condition)
    private FinalTargetClass targetField = new FinalTargetClass();
    
    // Type parameter usage (source_class feature)
    private T genericData;

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public int getValue() {
            return 3; // Matches "3" in WhileStatement target_feature
        }
    }

    // Member inner class (source_class feature)
    public class SourceMemberInnerClass {
        public void useTargetField(FinalTargetClass target) {
            target.processData("Inner class data");
        }
    }

    // Varargs method to be moved (public, void return, source position)
    public void processTarget(String... values) {
        // Empty statement
        ;
        
        // Labeled statement
        label:
        for (String val : values) {
            if (val.isBlank()) {
                break label;
            }
            
            // WhileStatement (public modifier, pos: same_package_others, target_feature: this.field, 3)
            int counter = 0;
            SourceStaticNestedClass staticNested = new SourceStaticNestedClass();
            while (counter < staticNested.getValue()) { // target_feature: 3
                // this.field access (target_feature: this.field)
                this.genericData = (T) val + counter;
                // Variable call to target field
                targetField.setDataList(new ArrayList<>(List.of(this.genericData.toString())));
                counter++;
            }
            
            // Variable call to target's anonymous inner class method
            targetField.runAnonymousTask();
        }
    }

    // Setter for generic data (variable call support)
    public void setGenericData(T genericData) {
        this.genericData = genericData;
    }
}

// Target normal class (final modifier, same package as source)
final class FinalTargetClass {
    private List<String> dataList = new ArrayList<>();
    
    // Anonymous inner class (target_feature)
    private Runnable anonymousTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Processing data: " + dataList);
        }
    };

    // Variable call methods
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void runAnonymousTask() {
        anonymousTask.run();
    }

    public void processData(String data) {
        this.dataList.add(data);
    }
}