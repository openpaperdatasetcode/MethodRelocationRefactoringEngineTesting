package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Interface for TargetClass implements feature
interface TargetProcessable {
    String process(String input);
}

/**
 * Target class Javadoc (target_feature: javadoc)
 * Public modifier, implements interface, member inner class (target_feature)
 */
public class TargetClass implements TargetProcessable {
    public String targetField = "target_value";

    // Member inner class (target_feature)
    public class TargetInner {
        private List<String> innerData = new ArrayList<>();

        public void addData(String data) {
            innerData.add(data);
        }

        public List<String> getInnerData() {
            return new ArrayList<>(innerData);
        }
    }

    public TargetClass() {
        // Default constructor for super constructor invocation
    }

    // Instance method for access_instance_method feature
    public TargetInner createInner() {
        return new TargetInner();
    }

    @Override
    public String process(String input) {
        return input + "_processed";
    }
}

// Source class: public modifier, anonymous inner + static nested class (source_feature)
public class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static String formatData(String data) {
            return "formatted_" + data;
        }
    }

    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // uses_outer_this feature
            TargetClass outerTarget = SourceClass.this.targetField;
            System.out.println("Anonymous inner: " + outerTarget.targetField);
        }
    };

    // Inner recursive class (source_inner_rec - method_position)
    public class SourceInnerRecursive {
        // Instance method: protected access, List<String> return type, method_position=source_inner_rec
        protected List<String> refactorMethod() {
            List<String> resultList = new ArrayList<>();

            // Super constructor invocation (method feature)
            TargetClass newTarget = new TargetClass();

            // Variable call feature (source contains target field)
            String varCall = targetField.targetField;
            resultList.add("Variable call: " + varCall);

            // Access instance method feature
            TargetClass.TargetInner targetInner = targetField.createInner();
            targetInner.addData(varCall);

            // depends_on_inner_class feature (logic depends on target inner class)
            if (!targetInner.getInnerData().isEmpty()) {
                resultList.addAll(targetInner.getInnerData());
            }

            // While statement feature
            int count = 0;
            while (count < 3) {
                String processed = targetField.process(SourceStaticNested.formatData(varCall + count));
                resultList.add("While iteration " + count + ": " + processed);
                count++;
            }

            // uses_outer_this feature
            SourceClass outerThis = SourceClass.this;
            resultList.add("Outer this target field: " + outerThis.targetField.targetField);

            // Used_by_reflection feature
            try {
                Method createInnerMethod = TargetClass.class.getMethod("createInner");
                TargetClass.TargetInner reflectInner = (TargetClass.TargetInner) createInnerMethod.invoke(targetField);
                reflectInner.addData("reflection_data");
                resultList.addAll(reflectInner.getInnerData());
            } catch (Exception e) {
                // No new exception thrown feature
                resultList.add("Reflection handled: " + e.getMessage());
            }

            // Execute anonymous inner class
            anonymousInner.run();

            // No new exception thrown feature
            return resultList;
        }
    }
}