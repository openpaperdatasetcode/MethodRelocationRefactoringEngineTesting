package refactoring.test;

// Target enum container (for private modifier)
class TargetEnumContainer {
    // Target enum class: private modifier, static nested class (target_feature)
    private enum TargetEnum {
        TARGET_1(3), TARGET_2(5); // obj.field + 3 for TryStatement

        public final int objField; // obj.field for TryStatement

        // Static nested class (target_feature)
        public static class TargetStaticNested {
            public static int calculate(int val) {
                return val * 2;
            }
        }

        TargetEnum(int objField) {
            this.objField = objField;
        }

        // Overload method 1 for overload_exist feature
        public int process() {
            return objField;
        }

        // Overload method 2 for overload_exist feature
        public int process(int multiplier) {
            return objField * multiplier;
        }
    }
}

// Source enum class: default modifier, two member inner classes (source_feature)
enum SourceEnum {
    SOURCE_1, SOURCE_2;

    // First member inner class (source_feature)
    public class FirstMemberInner {
        public int helperAdd(int a, int b) {
            return a + b;
        }
    }

    // Second member inner class (source_feature)
    public class SecondMemberInner {
        public int helperMultiply(int a, int b) {
            return a * b;
        }
    }

    // TryStatement feature: static modifier, obj.field, 3, pos=source
    private static int tryStatementFeature(TargetEnumContainer.TargetEnum target) {
        int result = 0;
        try {
            if (target.objField == 3) { // obj.field + 3
                result = TargetEnumContainer.TargetEnum.TargetStaticNested.calculate(target.objField);
            }
        } catch (NullPointerException e) {
            // No new exception thrown feature
            result = 0;
        }
        return result;
    }

    // Instance method: protected access, base return type (int), target parameter (per_condition)
    protected int refactorMethod(TargetEnumContainer.TargetEnum targetParam) {
        // Variable call feature
        int varCall = targetParam.objField;

        // If statement feature
        int ifResult = 0;
        if (varCall >= 3) {
            ifResult = varCall + 2;
        } else {
            ifResult = varCall;
        }

        // Expression statement feature
        FirstMemberInner firstInner = new FirstMemberInner();
        int exprResult = firstInner.helperAdd(varCall, ifResult);

        // Overload_exist feature (call overloaded methods)
        int overload1 = targetParam.process();
        int overload2 = targetParam.process(2);

        // Execute TryStatement feature
        int tryResult = tryStatementFeature(targetParam);

        // Use second member inner class
        SecondMemberInner secondInner = new SecondMemberInner();
        int finalResult = secondInner.helperMultiply(exprResult, tryResult);

        // No new exception thrown feature
        return finalResult + overload1 + overload2;
    }
}