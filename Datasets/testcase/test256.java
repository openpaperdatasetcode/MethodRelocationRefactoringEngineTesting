package com.refactoring.movemethod;

import diffpackage.ExpressionHelper;
import java.util.Arrays;

// Different package class for ExpressionStatement pos=diff_package_others
package diffpackage;
public class ExpressionHelper {
    public static String superField = "superVal3"; // target_feature "super.field" + "3"
    public static int[] array = {3, 1, 2}; // For ArrayAccess exp
}

package com.refactoring.movemethod;

// Parent class for call_method (parent_class type) and super.field feature
class ParentSourceClass<T> {
    protected String superField = "parentSuper3"; // super.field with "3"
    protected T parentField;

    // Chain methods for obj.m1().m2().m3() feature
    public ParentSourceClass<T> m1() { return this; }
    public ParentSourceClass<T> m2() { return this; }
    public TargetClass.InnerRecursiveTarget m3() {
        return new TargetClass().new InnerRecursiveTarget(); // Return TargetClass Type
    }

    // Static code block for call_method pos=Static code blocks
    static {
        // call_method: private modifier, static, obj.m1().m2().m3(), return TargetClass Type
        private static TargetClass.InnerRecursiveTarget callMethodLogic() {
            return new ParentSourceClass<String>().m1().m2().m3(); // obj.m1().m2().m3()
        }
        TargetClass.InnerRecursiveTarget staticBlockResult = callMethodLogic();
    }
}

// Source class: normal class, abstract modifier, same package, type parameter, local inner class, static nested class
abstract class SourceClass<T extends Number> extends ParentSourceClass<T> { // Type parameter feature
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    protected String outerProtectedField = "outerProtected3"; // For access_outer_protected

    // Static nested class (source feature)
    public static class StaticNestedSource<U> { // Type parameter in static nested class
        U nestedField;
    }

    // Method: instance, return void, private access, source position
    private void moveableInstanceMethod(TargetClass.InnerRecursiveTarget targetParam) throws Exception { // per_condition: contains target_inner_rec parameter
        // Variable call feature
        String localVar = targetParam.innerField;
        localVar = super.superField; // super.field access

        // ExpressionStatement: private modifier, super.field, 3, pos=diff_package_others
        private void expressionLogic() {
            // pos=diff_package_others (uses diffpackage class)
            diffpackage.ExpressionHelper.superField = "updatedSuper3"; // target_feature "super.field" + "3"
            localVar = diffpackage.ExpressionHelper.superField; // ExpressionStatement
            diffpackage.ExpressionHelper.array[0] = 3; // numbers "3"
        }
        expressionLogic();

        // Empty statement feature (semicolon only)
        ;

        // Super constructor invocation feature
        new ParentSourceClass<T>() {};

        // While statement feature
        int counter = 0;
        while (counter < 3) { // numbers "3"
            localVar += counter;
            counter++;
        }

        // Expression statement feature (additional)
        localVar = localVar.toUpperCase();
        targetParam.innerField = localVar;
        diffpackage.ExpressionHelper.array[0] = Integer.parseInt(localVar); // ExpressionStatement

        // Numbers: 3, protected modifier, exp=ArrayAccess
        protected int arrayAccessExp() {
            // exp=ArrayAccess + numbers "3" + protected modifier
            return diffpackage.ExpressionHelper.array[3 - 0]; // ArrayAccess with "3"
        }
        localVar = String.valueOf(arrayAccessExp());

        // Access_outer_protected feature
        localVar = this.outerProtectedField; // Access outer protected field

        // Access_instance_field feature
        targetParam.innerField = localVar; // Access target inner instance field
        this.targetField.publicField = localVar; // Access target instance field

        // Local inner class (source feature)
        class LocalInnerSource {
            void updateTarget(TargetClass.InnerRecursiveTarget target) {
                target.innerField = localVar + "_inner3"; // numbers "3"
            }
        }
        new LocalInnerSource().updateTarget(targetParam);

        // Requires_throws feature (declare and throw checked exception)
        if (localVar.isEmpty()) throw new Exception("Empty variable"); // throws Exception

        // no_new_exception (implicit: no custom exceptions instantiated)
    }

    // Abstract method (required for abstract class)
    public abstract T abstractMethod();
}

// Target class: normal class, private modifier, same package, target_feature: javadoc, static nested class
/**
 * Javadoc feature for TargetClass
 * This class contains static nested class and inner recursive class for method targeting
 * InnerRecursiveTarget is the recursive inner class (target_inner_rec)
 */
private class TargetClass {
    public String publicField = "targetVal3"; // numbers "3"

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRec3"; // numbers "3"

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget {
        static int staticNestedVal = 3; // numbers "3"
    }
}