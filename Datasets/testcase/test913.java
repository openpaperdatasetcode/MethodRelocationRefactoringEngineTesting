package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// Target inner record (target_inner_rec - target class of method)
public record TargetInnerRec(String id, int value) {}

// Target class container (for private modifier)
class TargetContainer {
    // Target class: private modifier, static nested class (target_feature)
    private static class TargetClass<T> {
        public T targetData;

        // Static nested class (target_feature)
        public static class TargetStaticNested {
            public static String formatRec(TargetInnerRec rec) {
                return rec.id() + "_" + rec.value();
            }
        }

        public TargetClass(T targetData) {
            this.targetData = targetData;
        }

        public TargetInnerRec createInnerRec() {
            return new TargetInnerRec("REC_" + targetData, (Integer) targetData);
        }
    }
}

// Source class: protected modifier, type parameter, static nested + anonymous inner class (source_feature)
protected class SourceClass<T> {
    // Static nested class (source_feature)
    public static class SourceStaticNested<U> {
        public static <U> String staticHelper(U data) {
            return data.toString();
        }
    }

    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class executed");
        }
    };

    // Inner recursive class (source_inner_rec - method_position)
    public class SourceInnerRecursive<T> {
        // Instance method: public access, List<String> return type, target parameter (per_condition)
        public List<String> refactorMethod(TargetContainer.TargetClass<T> targetParam) {
            List<String> resultList = new ArrayList<>();

            // Variable call feature
            T varCall = targetParam.targetData;
            TargetInnerRec innerRec = targetParam.createInnerRec();

            // ParenthesizedExpression feature: numbers=1, public modifier, exp=ParenthesizedExpression
            public int parenthesizedResult = ((Integer) varCall + 1); // ParenthesizedExpression with 1

            // Continue statement feature
            for (int i = 0; i < 5; i++) {
                if (i == parenthesizedResult) {
                    continue; // Continue statement
                }
                resultList.add("Iteration: " + i);
            }

            // () -> System.out.println(this.value) feature (Lambda with this reference)
            Consumer<TargetInnerRec> lambda = rec -> System.out.println(rec.value());
            lambda.accept(innerRec);

            // Use target static nested class
            String formattedRec = TargetContainer.TargetClass.TargetStaticNested.formatRec(innerRec);
            resultList.add(formattedRec);

            // Execute anonymous inner class
            anonymousInner.run();

            // No new exception thrown feature
            return resultList;
        }
    }
}