// Different package for VariableDeclarationStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetEnum;

public class DiffPackageOthers {
    // VariableDeclarationStatement (private modifier, this.field, 3, pos=diff_package_others)
    private static void varDeclStatement(TargetEnum target) {
        private int fieldVal = 3; // 3 from target_feature
        target.field = fieldVal; // this.field access
    }
}

// Main package
package com.refactoring.test;

import com.refactoring.others.DiffPackageOthers;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

/**
 * Target enum class (public modifier, javadoc + static nested class features)
 * Javadoc feature included for target_class
 */
public enum TargetEnum {
    INSTANCE;

    int field; // For per_condition (source contains this field)
    protected String outerProtected = "protected_val"; // For access_outer_protected

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static String staticMethod(TargetEnum target) {
            return target.field + "_static_" + 3;
        }
    }
}

// Source enum class (public modifier, same package, empty feature list)
@RefactorAnnotation // has_annotation feature
public enum SourceEnum {
    INSTANCE;

    // Inner recursive class (source_inner_rec for method_position)
    class SourceInnerRecursive {
        // call_method (protected modifier, static + instanceReference::methodName, pos=do-while, return String)
        protected static String sourceMethod(TargetEnum target) {
            return target.field + "_source_" + 3;
        }

        // Overloading method 1
        @RefactorAnnotation // has_annotation feature
        strictfp TargetEnum moveMethod(TargetEnum targetParam) {
            return moveMethod(targetParam, 3);
        }

        // Overloading method 2 (method to be refactored)
        @RefactorAnnotation // has_annotation feature
        strictfp TargetEnum moveMethod(TargetEnum targetParam, int val) {
            // Per_condition: contains target parameter
            if (targetParam == null) {
                return TargetEnum.INSTANCE;
            }

            // VariableDeclarationStatement (pos=diff_package_others)
            DiffPackageOthers.varDeclStatement(targetParam);

            // Super constructor invocation (Enum superclass)
            super.toString();

            // Variable call (access target field - per_condition)
            int varCall = targetParam.field;
            targetParam.field = varCall + 3;

            // Access outer protected field
            String protectedVal = targetParam.outerProtected;
            targetParam.field += protectedVal.length();

            // Requires try-catch
            try {
                // call_method (pos=do-while, static + instanceReference::methodName)
                int count = 0;
                do {
                    var sourceFunc = SourceInnerRecursive::sourceMethod; // instanceReference::methodName
                    String callResult = sourceFunc.apply(targetParam);
                    targetParam.field += callResult.length();
                    count++;
                } while (count < 3);
            } catch (Exception e) {
                targetParam.field = 3;
            }

            // Return TargetEnum type
            return targetParam;
        }
    }
}