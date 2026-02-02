package refactoring.test;

// Parent class for target record's extends feature
protected class RecordParent {
    protected int outerProtectedField = 3; // For access_outer_protected
}

// Source record class: public modifier, same package, empty features
public record SourceRecord(String data) {
    // Varargs method to be refactored (all specified features)
    protected Object refactorMethod(TargetRecord.TargetInnerClass targetParam, String... args) { // per_condition + varargs type
        // Variable call (target inner class field)
        targetParam.innerData = data;
        targetParam.counter = 3;
        // Access outer protected field (from target's parent class)
        int protectedVal = targetParam.getOuterProtectedField();

        // Try statement
        try {
            // Do-while statement with varargs method call (pos: do-while)
            int i = 0;
            do {
                int baseResult = varargsHelperMethod(targetParam, "arg" + i, "arg" + (i + 1));
                targetParam.counter += baseResult;
                i++;
            } while (i < 3);
        } catch (Exception e) {
            // No new exception
            return "error:" + e.getMessage();
        }

        // No new exception, return Object
        return targetParam;
    }

    // Varargs helper method (type:varargs, default modifier, method_feature:3/inner_class/varargs/outerInstance.new InnerClass().methodName())
    default int varargsHelperMethod(TargetRecord.TargetInnerClass innerParam, String... varargs) { // varargs feature
        int result = 3; // 3 (method_feature)
        // inner_class feature + outerInstance.new InnerClass().methodName()
        TargetRecord targetInstance = new TargetRecord("helper");
        TargetRecord.TargetInnerClass innerInstance = targetInstance.new TargetInnerClass(); // outerInstance.new InnerClass()
        result += innerInstance.calculate(varargs); // methodName(arguments)

        return result;
    }
}

// Target record class: protected modifier, extends + static nested class features
protected record TargetRecord(String recordData) extends RecordParent { // extends feature
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 3;
    }

    // Target inner class (target_inner)
    public class TargetInnerClass {
        String innerData;
        int counter = 3;

        // Method for outerInstance.new InnerClass().methodName()
        public int calculate(String... args) { // varargs feature
            return args.length + 3; // 3 (method_feature)
        }

        // Access outer protected field from parent class
        public int getOuterProtectedField() {
            return TargetRecord.super.outerProtectedField;
        }
    }
}