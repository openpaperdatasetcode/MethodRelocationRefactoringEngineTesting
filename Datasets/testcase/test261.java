package com.refactoring.movemethod;

import diffpackage.TargetInnerDiffPackage;
import java.util.List;

// Different package class for VariableDeclarationStatement pos=diff_package_target
package diffpackage;
import com.refactoring.movemethod.TargetRecord;
public class TargetInnerDiffPackage {
    public static TargetRecord.InnerTargetClass diffPackageObj = new TargetRecord("diffVal1").new InnerTargetClass();
    static {
        diffPackageObj.innerField = "objField1"; // target_feature "obj.field" + "1"
    }
}

package com.refactoring.movemethod;

// Functional interface for source record implements feature
interface RecordInterface<T extends CharSequence> {
    TargetRecord.InnerTargetClass overridingMethod(TargetRecord.InnerTargetClass targetParam);
}

// Source class: record class, public modifier, same package, implements, member inner class (duplicate)
public record SourceRecord<T extends String & Comparable<T>>( // with_bounds feature
        String sourceField,
        TargetRecord targetField // per_condition: source contains target field
) implements RecordInterface<T> {

    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outerProtected1"; // numbers "1"

    // Member inner class (source feature, duplicate as specified)
    public class MemberInnerSource1 {
        String innerField = "inner1"; // numbers "1"
    }

    // Member inner class (source feature, duplicate as specified)
    public class MemberInnerSource2 {
        String innerField = "inner2";
    }

    /**
     * Method javadoc feature
     * Overriding method from RecordInterface, returns TargetRecord.InnerTargetClass
     * @param targetParam the inner target class parameter (per_condition)
     * @return updated TargetRecord.InnerTargetClass instance
     */
    @Override // overriding method type
    protected TargetRecord.InnerTargetClass overridingMethod(TargetRecord.InnerTargetClass targetParam) {
        // Variable call feature
        String localVar = targetParam.innerField;
        localVar = this.outerProtectedField; // access_outer_protected

        // VariableDeclarationStatement: private modifier, obj.field, 1, pos=diff_package_target
        private void varDeclLogic() {
            // pos=diff_package_target (uses diffpackage class)
            TargetRecord.InnerTargetClass obj = diffpackage.TargetInnerDiffPackage.diffPackageObj; // obj.field
            int val = 1; // target_feature "1"
            localVar = obj.innerField + val; // VariableDeclarationStatement usage
        }
        varDeclLogic();

        // Constructor invocation feature (target inner class constructor)
        TargetRecord.InnerTargetClass newTargetInstance = this.targetField().new InnerTargetClass();

        // with_bounds feature (use bounded generic type)
        T boundedVal = (T) localVar.substring(0, 1); // numbers "1" + with_bounds

        // Access_outer_protected feature (explicit)
        newTargetInstance.innerField = this.outerProtectedField;

        // Update target instance
        newTargetInstance.innerField = localVar + "_updated" + boundedVal;

        // no_new_exception feature (no custom exceptions instantiated/thrown)
        return newTargetInstance; // return TargetClass Type (target_inner)
    }
}

// Target class: record class, public modifier, same package, target_feature: javadoc, static nested class
/**
 * Javadoc feature for TargetRecord
 * This record class contains static nested class and inner target class
 * InnerTargetClass is the target_inner class for method targeting
 */
public record TargetRecord(String targetData) {
    // Inner target class (target_inner - method's target class)
    public class InnerTargetClass {
        String innerField = "targetInner1"; // numbers "1"
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget<U extends Number> { // with_bounds compatibility
        U nestedField = (U) Integer.valueOf(1); // numbers "1"
    }
}