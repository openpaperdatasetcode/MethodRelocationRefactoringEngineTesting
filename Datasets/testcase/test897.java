package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

// Super class for source extends and super constructor invocation
class SourceSuperClass {
    protected SourceSuperClass() {}
    public void superMethod(List<String> list) {}
}

// Target class: public modifier, member inner class (target_feature)
public class TargetClass {
    public String targetField = "targetValue";

    // Member inner class (target_feature)
    public class TargetInner {
        private List<String> innerList;

        public TargetInner(List<String> innerList) {
            this.innerList = innerList;
        }

        public List<String> getInnerList() {
            return innerList;
        }
    }

    // Method for override violation (invalid override signature)
    public void processList(List<String> list) throws IOException {}
}

// Source class: private modifier, extends, two anonymous inner classes (source_feature)
private class SourceClass extends SourceSuperClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Inner class (source_inner - method_position)
    public class SourceInner {
        // Instance method: protected access, void return type, List parameter (method types parameter is:List)
        protected void refactorMethod(List<String> paramList) throws IOException { // requires_throws
            // Super constructor invocation (method feature)
            super();

            // Constructor invocation feature
            TargetClass newTarget = new TargetClass();
            TargetClass.TargetInner targetInner = newTarget.new TargetInner(paramList);

            // Variable call feature
            String varCall = targetField.targetField;

            // Raw type feature
            List rawList; // Raw type declaration
            rawList = new ArrayList(); // Raw type initialization
            rawList.add(varCall);

            // Synchronized statement feature
            synchronized (this) {
                targetInner.getInnerList().addAll(rawList);
            }

            // Override violation (invalid override signature - no throws declaration)
            TargetClass invalidOverride = new TargetClass() {
                @Override
                public void processList(List<String> list) { // override_violation (missing throws IOException)
                    list.add("override_violation");
                }
            };

            // Requires throws: call method that declares IOException
            invalidOverride.processList(paramList);

            // No new exception thrown (adheres to requires_throws without new exceptions)
        }
    }

    // First anonymous inner class (source_feature)
    private Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            SourceInner inner = new SourceInner();
            try {
                inner.refactorMethod(new ArrayList<>());
            } catch (IOException e) {
                // Handle required throws
            }
        }
    };

    // Second anonymous inner class (source_feature)
    private Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class: " + targetField.targetField);
        }
    };
}