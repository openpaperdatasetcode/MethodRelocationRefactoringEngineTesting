package com.refactoring.movemethod;

import com.refactoring.others.ContinueHelper;

// Diff package class for ContinueStatement pos: diff_package_others
package com.refactoring.others;
public class ContinueHelper {
    public int helperCount = 1; // ContinueStatement target_feature: "1"
    public String helperField = "diff_package_helper_6152";
}

package com.refactoring.movemethod;
// Functional interface for source implements feature
interface SourceProcessor {
    int process(String data);
}

// Functional interface for target implements feature
interface TargetProcessor<T> {
    T compute(T value);
}

// Source class: normal, protected modifier, same package, implements + two member inner classes
protected class SourceClass implements SourceProcessor {
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass();

    // Instance field for access_instance_field feature
    private String instanceField = "source_instance_6152";

    // First member inner class (source feature)
    protected class FirstSourceInner {
        private String innerField1 = "inner1_6152";
    }

    // Second member inner class (recursive - method_position: source_inner_rec)
    public class SecondSourceInner {
        private String innerField2 = "inner2_6152";

        // ContinueStatement feature (type: ContinueStatement, modifier: protected, pos: diff_package_others)
        protected void continueStatementLogic() {
            ContinueHelper helper = new ContinueHelper();
            // target_feature: this.field (inner class field)
            this.innerField2 = helper.helperField; // target_feature: this.field
            int count = helper.helperCount; // target_feature: "1"

            // pos: diff_package_others (uses ContinueHelper from different package)
            for (int i = 0; i < 5; i++) {
                if (i == count) { // target_feature: "1"
                    continue; // ContinueStatement feature
                }
                innerField2 += "_loop_" + i;
            }
        }

        // Method to be refactored: instance, base type (int) return, public access, source_inner_rec
        public int methodToMove() {
            // access_instance_field feature
            SourceClass.this.instanceField = innerField2 + "_updated";
            String instanceVal = SourceClass.this.instanceField;

            // variable call feature
            FirstSourceInner firstInner = new FirstSourceInner();
            String varCall = firstInner.innerField1 + this.innerField2 + instanceVal;

            // synchronized statement feature
            Object lock = new Object();
            synchronized (lock) {
                continueStatementLogic();
                // Access target inner recursive class (target_inner_rec)
                TargetClass.TargetInnerRecursive targetInner = targetField.new TargetInnerRecursive();
                targetInner.setData(varCall);
            }

            // Recursive method call (source_inner_rec context)
            int recursiveResult = recursiveProcess(3);

            // no_new_exception (no explicit new Exception instantiation)
            return varCall.length() + recursiveResult;
        }

        // Recursive method (source_inner_rec context)
        private int recursiveProcess(int val) {
            if (val <= 0) return 0;
            return val + recursiveProcess(val - 1);
        }
    }

    // implements feature method implementation
    @Override
    public int process(String data) {
        return new SecondSourceInner().methodToMove() + data.length();
    }
}

// Target class: normal, default modifier, implements + static nested class (target_features)
class TargetClass implements TargetProcessor<String> {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String staticField = "target_static_6152";
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        private String data;

        public String getData() {
            return data + "_" + new TargetStaticNested().staticField;
        }

        public void setData(String data) {
            this.data = data;
        }

        // Recursive method (recursive class context)
        public int recursiveLength(int val) {
            if (val <= 0) return data.length();
            return val + recursiveLength(val - 1);
        }
    }

    // implements feature method implementation
    @Override
    public String compute(String value) {
        return new TargetInnerRecursive().getData() + "_computed";
    }
}