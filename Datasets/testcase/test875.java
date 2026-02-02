package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static org.junit.Assert.assertEquals;

// Different package class for AssertStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // AssertStatement (private modifier, this.field, 2, pos=diff_package_others)
    private static void assertTargetField(TargetClass target) {
        assertEquals(2, target.targetField); // this.field (target.field) + 2 from target_feature
    }
}

package com.refactoring.test;
import com.refactoring.others.DiffPackageOthers;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source class (public modifier, same package, static nested + local inner class)
public class SourceClass {
    protected String outerProtectedField = "protectedValue"; // For access_outer_protected

    // Static nested class (source feature)
    public static class SourceStaticNested {
        int nestedField = 3; // 3 from method_feature
    }

    // Method to be refactored (instance, Object return, protected access, source position)
    @RefactorAnnotation // has_annotation feature (duplicated as per spec)
    @RefactorAnnotation // Second has_annotation feature
    protected Object moveMethod(TargetClass targetParam) {
        // Per_condition: source contains target's field
        if (targetParam == null) {
            throw new NullPointerException("Target parameter is null"); // NullPointerException feature
        }
        int targetField = targetParam.targetField;

        // Call AssertStatement from diff_package_others (pos=diff_package_others)
        DiffPackageOthers.assertTargetField(targetParam);

        // Instance method (type:instance, modifier:default, method_feature:3/source/instance/this.methodName(arguments))
        default Object instanceMethod(int num) {
            return this.processValue(num); // this.methodName(arguments)
        }

        // If/else conditions with instance method call (pos:if/else conditions)
        Object instanceResult;
        if (targetField > 0) {
            instanceResult = instanceMethod(new SourceStaticNested().nestedField); // 3 from method_feature
        } else {
            instanceResult = instanceMethod(0);
        }

        // Variable call + access_outer_protected
        String varCall = this.outerProtectedField;
        targetParam.targetField = varCall.length() + 2; // 2 from AssertStatement target_feature

        // Local inner class (source feature)
        class LocalInnerClass {
            Object modifyTarget(TargetClass target) {
                return target.targetField + "_modified";
            }
        }
        Object modified = new LocalInnerClass().modifyTarget(targetParam);

        // Ternary operator with call_method (pos:ternary operators)
        TargetSubClass subClass = new TargetSubClass();
        (targetField > 2 ? subClass::callMethod : subClass::callMethod).accept(targetParam);

        // No new exception (only pre-defined NullPointerException)
        return modified;
    }

    // Helper method for instanceMethod
    private Object processValue(int num) {
        return num * 2;
    }
}

// Target class (protected modifier, local inner class feature)
protected class TargetClass {
    int targetField; // For variable call/access

    // Local inner class (target_feature)
    class TargetLocalInner {
        void updateField(int value) {
            TargetClass.this.targetField = value;
        }
    }
}

// Subclass for call_method (sub_class type)
class TargetSubClass extends TargetClass {
    // call_method (private modifier, instance + superTypeReference.methodName(arguments))
    private void callMethod(TargetClass param) {
        // SuperTypeReference.methodName(arguments)
        super.toString();
        new TargetLocalInner().updateField(param.targetField + 3); // 3 from method_feature
    }
}