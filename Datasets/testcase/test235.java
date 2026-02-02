// Source interface package
package com.refactoring.source;

import com.refactoring.target.TargetInterface;
import diffpackage.TypeDeclarationHelper;

// Different package class for TypeDeclarationStatement pos=diff_package_others
package diffpackage;
public class TypeDeclarationHelper {
    public static int helperVal = 1; // target_feature "1"
    public static String superField = "superField1"; // target_feature "super.field"
}

package com.refactoring.source;

// Super interface for super.field feature
interface SuperInterface {
    String SUPER_FIELD = "superVal1"; // super.field with "1"
    default void superMethod() {}
}

// Source class: interface, no modifier, different package with target, member inner class, anonymous inner class
interface SourceInterface extends SuperInterface {
    // per_condition: source contains target interface field
    TargetInterface targetField = new TargetInterface() {};

    // Member inner class (source feature, method_position: source_inner)
    class InnerSourceClass {
        // Method: varargs, return Object, default access, source_inner position
        Object moveableVarargsMethod(String... args) throws Exception { // requires_throws feature
            // Variable call feature
            String localVar = targetField.getTargetField();
            localVar = String.valueOf(1); // numbers "1"

            // TypeDeclarationStatement: public modifier, super.field, 1, pos=diff_package_others
            public class DiffPackageTypeDeclaration {
                String field = diffpackage.TypeDeclarationHelper.superField; // target_feature "super.field"
                int val = diffpackage.TypeDeclarationHelper.helperVal; // target_feature "1"
            }
            DiffPackageTypeDeclaration typeDecl = new DiffPackageTypeDeclaration();

            // Type declaration statement feature (additional)
            class LocalTypeDeclaration {
                String typeField = "typeDecl1"; // numbers "1"
            }
            LocalTypeDeclaration localType = new LocalTypeDeclaration();

            // Numbers: 1, default modifier, exp=SuperMethodReference
            default String superMethodRefExp() {
                // exp=SuperMethodReference + numbers "1" + default modifier
                return SuperInterface.super::superMethod + "_" + 1;
            }
            localVar = superMethodRefExp();

            // Constructor invocation feature (anonymous inner class constructor)
            TargetInterface.InnerRecursiveTarget newTargetInstance = 
                new TargetInterface() {}.new InnerRecursiveTarget();

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    localVar += args[0];
                }
            };
            anonymousRunnable.run();

            // requires_throws feature (declares throws Exception)
            if (args == null) throw new Exception("Null arguments");

            return newTargetInstance;
        }
    }
}
// Target interface package
package com.refactoring.target;

// Target class: interface, public modifier, different package with source, target_feature: local inner class
public interface TargetInterface {
    String TARGET_FIELD = "targetVal1"; // numbers "1"

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRec1"; // numbers "1"

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }

        // Local inner class (target_feature)
        void localInnerClassDemo() {
            class LocalInnerTarget {
                String localField = "localTarget1"; // numbers "1"
            }
            LocalInnerTarget localInner = new LocalInnerTarget();
        }
    }

    // Getter for variable call feature
    default String getTargetField() {
        return TARGET_FIELD;
    }
}