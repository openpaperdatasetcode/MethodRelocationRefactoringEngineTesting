import java.util.List;
import java.util.ArrayList;

record SourceClass(String sourceField) {
    static int staticField = 42;

    // First static nested class
    static class StaticNestedClass1 {
        static int getStaticValue() {
            return staticField * 2;
        }
    }

    // Second static nested class
    static class StaticNestedClass2 {
        String process(String input) {
            return input + "_processed";
        }
    }

    public void moveCandidateMethod(TargetClass targetParam) {
        super(); // Super constructor invocation for record
        // Constructor invocation
        StaticNestedClass2 nested2 = new StaticNestedClass2();
        // For statement
        List<String> items = new ArrayList<>();
        for (int i = 0; i < staticField; i++) {
            items.add(nested2.process(sourceField + i));
        }
        // Variable call
        String varCall = nested2.process(targetParam.targetField());
        // Depends on static field
        int staticVal = StaticNestedClass1.getStaticValue();
        // Uses outer this
        String outerThisVal = SourceClass.this.sourceField();
        // this.methodName(arguments)
        this.helperMethod(varCall, staticVal);
        // No new exception thrown
        try {
            targetParam.localInnerProcessor();
        } catch (Exception e) {
            // Silent handling, no new exception instantiation
        }
    }

    private void helperMethod(String str, int num) {
        // Dummy helper method for this.methodName(arguments)
    }
}

record TargetClass(String targetField) {
    void localInnerProcessor() {
        // Local inner class in target record
        class LocalInnerClass {
            String processTargetField() {
                return targetField + "_inner_processed";
            }
        }
        LocalInnerClass inner = new LocalInnerClass();
        inner.processTargetField();
    }
}