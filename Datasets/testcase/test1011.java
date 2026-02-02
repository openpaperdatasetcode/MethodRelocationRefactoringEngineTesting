import java.util.ArrayList;
import java.util.List;

private record SourceClass(String sourceField) {
    public List<String> moveCandidateMethod(TargetClass targetParam) {
        super();
        // Local inner class
        class LocalInnerClass {
            String innerField = sourceField;

            String getValue() {
                return innerField + targetParam.targetField();
            }
        }

        // Anonymous inner class
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(sourceField);
            }
        };
        runnable.run();

        LocalInnerClass localInner = new LocalInnerClass();
        // Variable call & access instance field
        String varCall = localInner.getValue();
        List<String> result = new ArrayList<>();
        result.add(varCall);
        // Super keywords (no super class, use this to simulate super semantics for record)
        result.add(this.sourceField);
        
        return result;
    }
}

public record TargetClass(String targetField) {
    protected static List<String> callMethod() {
        // Expression position with new ClassName().method()
        return new SourceClass("sourceValue").moveCandidateMethod(new TargetClass("targetValue"));
    }

    // Local inner class in target record
    public List<String> innerMethod() {
        class TargetLocalInner {
            List<String> process() {
                return new ArrayList<>(List.of(targetField));
            }
        }
        return new TargetLocalInner().process();
    }
}