package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Final normal source class (same package as target, permits/anonymous inner/static nested class features)
final class SourceClass {
    // Static nested class (source feature)
    public static class SourceStaticNested {
        // Inner record for method_position: source_inner_rec
        public record SourceInnerRec(String data) {
            // Final instance method to refactor (void return, final access, target parameter)
            public final void moveTargetMethod(TargetClass targetParam) {
                // Per_condition: method contains target parameter
                if (targetParam == null) {
                    return;
                }

                // TypeLiteral with number 3 (public modifier, exp: TypeLiteral)
                public final Integer typeLiteral = 3;

                // Variable call feature
                TargetClass.MemberInner innerObj = targetParam.new MemberInner();
                innerObj.setInnerData("source_data_" + typeLiteral);

                // Overload_exist feature: overloaded method exists
                overloadMethod(1);
                overloadMethod("string_param");
            }

            // Overload method 1 (for overload_exist feature)
            private void overloadMethod(int num) {}

            // Overload method 2 (for overload_exist feature)
            private void overloadMethod(String str) {}
        }
    }

    // Permits feature (simulated for final class with sealed hierarchy compatibility)
    public sealed interface PermitsInterface permits SourceClass.SourceStaticNested {}

    // Synchronized call method (type: source, modifier: synchronized, pos: if/else)
    public synchronized List<String> callMethod(SourceStaticNested.SourceInnerRec innerRec) {
        List<String> result = new ArrayList<>();
        
        // If/else position for call_method
        if (innerRec != null) {
            // Accessor feature (getter call)
            String data = innerRec.data();
            // Super.methodName(arguments) feature (simulated super call)
            result.add(super.toString() + "_" + data);
        } else {
            result.add("empty_data");
        }

        // Anonymous inner class feature (source class feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                result.add("anonymous_processed");
            }
        };
        anonymousRunnable.run();

        return result;
    }
}

// Private normal target class (same package as source, member inner class feature)
private class TargetClass {
    // Member inner class (target_feature)
    public class MemberInner {
        private String innerData;

        // Accessor for inner data (support variable call)
        public void setInnerData(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return this.innerData;
        }
    }
}