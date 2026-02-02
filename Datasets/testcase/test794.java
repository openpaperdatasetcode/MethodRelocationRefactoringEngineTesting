import java.util.Arrays;
import java.util.List;

// Annotation for has_annotation feature
@interface MethodAnnotation {}

// Source class: record, public modifier, same package, two anonymous inner classes
public record SourceClass(String sourceField) {
    // Source inner recursive structure (method_position: source_inner_rec)
    public class SourceInnerRec {
        private int innerField; // Field for this.field = 1 feature

        /**
         * Method to refactor - base type (int) return, private access, source_inner_rec position
         * @param targetParam TargetClass parameter (satisfies per_condition)
         * @return int (base type) result
         */
        @MethodAnnotation // has_annotation feature
        private int methodToRefactor(TargetClass targetParam) {
            // Variable call feature
            int localVar = 0;
            String var = "test";

            // Enhanced for statement (enhganced for statement)
            List<Integer> numbers = Arrays.asList(1, 2, 3);
            for (int num : numbers) {
                localVar += num;
            }

            // If statement feature
            if (targetParam != null) {
                localVar += targetParam.targetField();
            }

            // ExpressionStatement: private modifier, this.field, 1, pos: same_package_others
            privateExpressionStatement();

            // Overload exist feature (overloaded method exists)
            localVar = overloadMethod(1);
            localVar = overloadMethod(1, "overload");

            // No new exception feature (no 'new Exception()' statements)
            return localVar;
        }

        // Private ExpressionStatement implementation (pos: same_package_others)
        private void privateExpressionStatement() {
            this.innerField = 1; // this.field, 1 (ExpressionStatement feature)
        }

        // Overload method 1 (overload_exist feature)
        private int overloadMethod(int num) {
            return num * 2;
        }

        // Overload method 2 (overload_exist feature)
        private int overloadMethod(int num, String str) {
            return num + str.length();
        }
    }

    // First anonymous inner class (source_class feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            SourceInnerRec innerRec = new SourceInnerRec();
            innerRec.methodToRefactor(new TargetClass(0));
        }
    };

    // Second anonymous inner class (source_class feature)
    Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class execution");
        }
    };
}

// Target class: record, public modifier, static nested class target_feature
public record TargetClass(int targetField) {
    // Static nested class (target_feature)
    public static class target {
        // Placeholder for moved method
        private int methodToRefactor(TargetClass targetParam) {
            SourceClass source = new SourceClass("sourceVal");
            SourceClass.SourceInnerRec innerRec = source.new SourceInnerRec();
            return innerRec.methodToRefactor(targetParam);
        }
    }
}