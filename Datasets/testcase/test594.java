package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Diff package class for AssertStatement pos: diff_package_others
package com.other;
public class DiffPackageHelper {
    public static <T> void executeAssertLogic(com.refactoring.movemethod.SourceClass<T>.SourceInnerClass innerObj, com.refactoring.movemethod.TargetClass<T>.TargetInnerClass targetParam) {
        innerObj.assertLogic(targetParam);
    }
}

package com.refactoring.movemethod;
import com.other.DiffPackageHelper;

// Super class for super.field/super keywords usage
class SuperClass {
    protected int superField = 1; // For AssertStatement target_feature: 1
}

// Source class: generic, private, anonymous inner class, member inner class, same package as target
private class SourceClass<T> extends SuperClass {
    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Instance field for access_instance_field feature
        private String instanceField = "sourceInnerField";

        // Method to refactor: accessor, List<String> return, final access, in source inner class
        // Per_condition: contains target class parameter
        final List<String> getTargetValues(TargetClass<T>.TargetInnerClass targetParam) {
            // Variable call (target parameter's instance field)
            String targetValue = targetParam.innerValue;
            
            // Access instance field
            targetValue += this.instanceField;
            
            // Super keywords (access super class field)
            targetValue += SuperClass.super.superField;
            
            // Constructor invocation (target inner class)
            TargetClass<T>.TargetInnerClass newTargetInner = new TargetClass<T>().new TargetInnerClass();
            
            // Lambda feature: () -> System.out.println(this.value)
            Runnable lambda = () -> System.out.println(this.instanceField);
            lambda.run();
            
            // AssertStatement feature: private modifier, super.field, 1, pos=diff_package_others
            private void assertLogic(TargetClass<T>.TargetInnerClass param) {
                // super.field usage
                assert super.superField == 1 : "Super field not 1"; // target_feature: 1 (super.field value)
                assert param.innerValue != null : "Target value is null";
            }
            DiffPackageHelper.executeAssertLogic(this, targetParam); // pos: diff_package_others

            // Anonymous inner class (source class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(targetValue);
                }
            };
            anonymousRunnable.run();

            // No new exception feature
            List<String> result = new ArrayList<>();
            try {
                result.add(targetValue);
                result.add(newTargetInner.innerValue);
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                result.add("parse_error");
            }

            return result;
        }
    }
}

// Target class: generic, default modifier, extends, member inner class, same package as source
class TargetClass<U> extends SuperClass {
    // Member inner class (target_inner_rec - target inner recursive)
    public class TargetInnerClass {
        U innerValue = (U) "targetInnerValue";
    }
}