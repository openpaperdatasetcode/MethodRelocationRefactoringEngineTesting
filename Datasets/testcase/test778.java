package com.refactoring.movemethod;

import com.refactoring.others.VarDeclHelper;

import java.util.List;

// Diff package class for VariableDeclarationStatement pos: diff_package_others
package com.refactoring.others;
public class VarDeclHelper<T> {
    public String helperField = "diff_package_helper";
    public int helperCount = 1; // VariableDeclarationStatement target_feature: "1"
}

package com.refactoring.movemethod;
// Functional interface for target class implements feature
interface DataProcessor<T> {
    T process(T data);
}

// Sealed parent for non-sealed source class
sealed class SealedParent<T> permits SourceClass<T> {}

// Source generic class: non-sealed modifier, same package, two static nested classes
non-sealed class SourceClass<T> extends SealedParent<T> {
    // First static nested class (source feature)
    private static class FirstStaticNested {
        private String nestedField1 = "nested1_6128";
    }

    // Second static nested class (source feature)
    private static class SecondStaticNested {
        private int nestedField2 = 1; // VariableDeclarationStatement target_feature: "1"
    }

    // Inner class (method_position: source_inner)
    public class SourceInnerClass {
        private String innerField = "source_inner_field"; // VariableDeclarationStatement target_feature: "this.field"

        // Method to be refactored: instance, Object return, public access, source_inner
        public Object methodToMove(TargetClass<T> targetParam) {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                return null;
            }

            // super constructor invocation
            super();

            // super keywords feature
            String superStr = super.toString();

            // variable call feature
            FirstStaticNested staticNested = new FirstStaticNested();
            String varCall = staticNested.nestedField1;

            // with_bounds feature (type bound constraint)
            if (targetParam instanceof TargetClass<? extends CharSequence> boundedTarget) {
                varCall += boundedTarget.process((T) "bounded_data_6128");
            }

            // VariableDeclarationStatement feature (type: VariableDeclarationStatement, modifier: public, pos: diff_package_others)
            public void varDeclStatementLogic() {
                // diff_package_others position (uses class from different package)
                VarDeclHelper<T> helper = new VarDeclHelper<>();
                // target_feature: this.field
                this.innerField = helper.helperField;
                // target_feature: "1"
                int count = helper.helperCount;
                // VariableDeclarationStatement
                String processedVal = this.innerField + "_" + count;
                targetParam.targetStaticNested.nestedVal = processedVal;
            }
            varDeclStatementLogic();

            // no_new_exception (no explicit new Exception instantiation)
            return varCall + superStr + targetParam.targetStaticNested.nestedVal;
        }
    }
}

// Target generic class: protected modifier, implements + static nested class (target_features)
protected class TargetClass<T> implements DataProcessor<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedVal;
    }

    public TargetStaticNested targetStaticNested = new TargetStaticNested();

    @Override
    public T process(T data) {
        return (T) (data.toString() + "_processed_6128");
    }
}