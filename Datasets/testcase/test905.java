package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.ArrayList;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Others class for recursion method_feature
class OthersClass {
    private int count = 1; // method_feature: 1

    public int recursiveMethod(int n) {
        if (n <= 0) break; // BreakStatement (corrected logic for recursion termination)
        count += n;
        return this.recursiveMethod(n - 1); // recursion + instanceReference.methodName(arguments)
    }
}

/**
 * Target class Javadoc (target_feature: javadoc)
 * Default modifier, local inner class (target_feature)
 */
class TargetClass {
    public int objField = 1; // obj.field + 1 for BreakStatement feature

    // Target inner record (target_inner_rec - target class of method)
    public record TargetInnerRec(String id, List<String> data) {}

    // Local inner class (target_feature)
    public TargetInnerRec processData() {
        class LocalInnerClass {
            private List<String> generateList() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) { // numbers=3 for ThisExpression
                    list.add("item_" + i);
                }
                return list;
            }
        }
        LocalInnerClass inner = new LocalInnerClass();
        return new TargetInnerRec("REC_001", inner.generateList());
    }
}

// Super class for overriding feature
class SourceSuperClass {
    public List<String> refactorMethod() {
        return new ArrayList<>();
    }
}

// Source class: public modifier, extends, member inner + static nested class (source_feature)
public class SourceClass extends SourceSuperClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static int staticValue = 1;
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public transient int breakCounter = 0; // transient modifier for BreakStatement

        // BreakStatement feature: transient modifier, obj.field, 1, pos=source
        public transient void breakStatementFeature() {
            for (int i = 0; i < 5; i++) {
                breakCounter++;
                if (targetField.objField == 1) { // obj.field + 1
                    break; // BreakStatement
                }
            }
        }
    }

    // Recursion method feature: protected modifier, 1, others_class, recursion, instanceReference.methodName(), pos=array initialization, return base type
    protected int recursionHelperMethod() {
        // Array initialization (pos)
        int[] arr = new int[1]; // method_feature: 1
        arr[0] = SourceStaticNested.staticValue;

        // Instance reference for recursion
        OthersClass instanceRef = new OthersClass();
        return instanceRef.recursiveMethod(arr[0]); // instanceReference.methodName(arguments)
    }

    // Overriding method: public access, List<String> return type, method_position=source
    @Override
    @RefactorAnnotation // has_annotation feature
    public List<String> refactorMethod() {
        // Constructor invocation feature
        TargetClass newTarget = new TargetClass();
        TargetInnerRec innerRec = newTarget.processData();

        // Variable call feature
        int varCall = targetField.objField;

        // ThisExpression feature: numbers=3, protected modifier, exp=ThisExpression
        protected int thisExpr = this.recursionHelperMethod() + 3; // ThisExpression + numbers=3

        // Execute BreakStatement feature
        SourceMemberInner inner = new SourceMemberInner();
        inner.breakStatementFeature();

        // Execute recursion helper method
        int recursionResult = recursionHelperMethod();

        // Prepare return list
        List<String> result = new ArrayList<>(innerRec.data());
        result.add("varCall: " + varCall);
        result.add("recursionResult: " + recursionResult);
        result.add("thisExpr: " + thisExpr);

        // No new exception thrown feature
        return result;
    }
}