package com.refactoring.movemethod;

import com.refactoring.others.ReturnHelper;

import java.util.function.Function;

// Diff package class for ReturnStatement pos: diff_package_others
package com.refactoring.others;
public class ReturnHelper<T> {
    protected int superField = 1; // ReturnStatement target_feature: "1"
    public T helperData;
}

package com.refactoring.movemethod;
// Parent class for overriding & superTypeReference features
class OverrideParent<T> {
    protected int baseMethod(int val) {
        return val * 3; // method_feature: "3"
    }
}

// Source abstract generic class: abstract modifier, same package, static nested + local inner classes
abstract class SourceClass<T> extends OverrideParent<T> {
    // per_condition: source contains field of target
    protected TargetClass<T> targetField = new TargetClass<>();

    // Static nested class (source feature)
    private static class SourceStaticNested<T> {
        private String nestedField = "static_nested_6134";
    }

    // Instance method to be refactored: instance, Object return, protected access, source position
    protected Object methodToMove() {
        // uses_outer_this feature
        SourceClass<T> outerThis = SourceClass.this;

        // type declaration statement feature
        SourceStaticNested<T> staticNested = new SourceStaticNested<>();
        TargetClass<T>.TargetStaticNested targetStatic = targetField.new TargetStaticNested();

        // variable call feature
        String varCall = staticNested.nestedField + targetStatic.nestedVal;

        // Local inner class (source feature)
        class SourceLocalInner<T> extends OverrideParent<T> {
            // Overriding method (type: overriding, modifier: private, pos: property assignment, return base type)
            @Override
            private int baseMethod(int val) { // method_feature: overriding
                int count = 3; // method_feature: "3"
                // pos: property assignment
                targetField.targetProp = (T) String.valueOf(val * count); // inner_class context
                // method_feature: superTypeReference.methodName(arguments)
                return super.baseMethod(count); // return base type (int)
            }
        }
        SourceLocalInner<T> localInner = new SourceLocalInner<>();
        int overrideResult = localInner.baseMethod(3);

        // ReturnStatement feature (type: ReturnStatement, modifier: protected, pos: diff_package_others)
        protected Object returnStatementLogic() {
            ReturnHelper<T> helper = new ReturnHelper<>();
            // target_feature: super.field
            int val = helper.superField; // target_feature: "1"
            helper.helperData = (T) (varCall + "_" + val);
            return helper.helperData;
        }

        // no_new_exception (no explicit new Exception instantiation)
        return new Object() {
            @Override
            public String toString() {
                return outerThis.targetField.targetProp + "_" + varCall + "_" + overrideResult;
            }
        };
    }
}

// Target protected generic class: protected modifier, static nested class (target_feature)
protected class TargetClass<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested<T> {
        public String nestedVal = "target_static_6134";
    }

    public T targetProp;

    // SuperTypeReference method for method_feature
    public <T> T processData(T val) {
        return val;
    }
}