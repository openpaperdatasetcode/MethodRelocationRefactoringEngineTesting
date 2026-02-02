package com.refactoring.movemethod;

import java.io.IOException;

// Source generic class: normal, public modifier, same package, type parameter + anonymous inner + member inner classes
public class SourceClass<T> {
    // per_condition: source contains field of target
    private TargetClass<String> targetField = new TargetClass<>();

    // Protected outer field for access_outer_protected feature
    protected T outerProtectedField;

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "source_member_inner_6140";
    }

    // Method to be refactored: instance, base type (int) return, protected access, source position
    protected int methodToMove() throws IOException {
        // access_outer_protected feature (raw type usage for raw_type feature)
        SourceClass rawSource = new SourceClass(); // raw_type
        rawSource.outerProtectedField = (T) Integer.valueOf(6140);
        T protectedVal = (T) rawSource.outerProtectedField;

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // Access target inner recursive class (target_inner_rec)
        TargetClass<String>.TargetInnerRecursive targetInner = targetField.new TargetInnerRecursive();

        // try statement + requires_try_catch feature
        int result = 0;
        try {
            // if statement feature
            if (protectedVal == null) {
                // throw statement + IOException feature
                throw new IOException("Outer protected field is null (ID:6140)");
            }

            // Process target inner recursive field
            targetInner.innerField = varCall + "_" + protectedVal.toString();
            result = targetInner.innerField.length();

        } catch (IOException e) {
            // requires_try_catch (handle checked exception)
            e.printStackTrace();
            result = -1;
        }

        // Anonymous inner class (source feature)
        Runnable anonymousProcessor = new Runnable() {
            @Override
            public void run() {
                targetInner.processData(result);
            }
        };
        anonymousProcessor.run();

        return result; // base type (int) return
    }
}

// Target generic class: normal, protected modifier, type parameter + member inner class (target_features)
protected class TargetClass<T> {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        public String innerField;

        public void processData(int val) {
            this.innerField += "_processed_" + val;
        }

        // Recursive method (recursive class context)
        public int recursiveProcess(int val) {
            if (val <= 0) return 0;
            return val + recursiveProcess(val - 1);
        }
    }
}