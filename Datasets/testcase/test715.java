package com.refactoring.movemethod;

import java.util.List;

// Diff package class for LabeledStatement pos: diff_package_others
package com.other;
public class DiffPackageHelper {
    public static void executeLabeledLogic(com.refactoring.movemethod.SourceClass<String>.FirstInnerClass innerObj) {
        innerObj.labeledLogic();
    }
}

package com.refactoring.movemethod;
import com.other.DiffPackageHelper;

// Permitted classes for SourceClass permits feature
final class PermittedClass1 extends SourceClass<Integer> {}
final class PermittedClass2 extends SourceClass<Long> {}

// Source class: private, type parameter, permits, two member inner classes, same package as target
private sealed class SourceClass<T extends Number> permits PermittedClass1, PermittedClass2 {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    private static int staticField = 6409;

    // First member inner class
    class FirstInnerClass {
        private int innerField = 1; // For LabeledStatement target_feature: 1

        // Overloading method 1: final access, void return, in source inner class
        final void methodToRefactor(TargetClass targetParam) {
            // Variable call (target field & parameter)
            String targetData = targetField.getInnerData();
            targetData += targetParam.getInnerData();

            // Type declaration statement
            String rawString;

            // Raw_type feature
            List rawList = new java.util.ArrayList(); // Raw type usage
            rawList.add(targetData);

            // Depends on static field
            rawList.add(staticField);

            // LabeledStatement feature: private modifier, obj.field, 1, pos=diff_package_others
            private void labeledLogic() {
                label:
                for (int i = 0; i < this.innerField; i++) { // obj.field (this.innerField), target_feature: 1
                    if (i == 0) break label;
                    targetData += i;
                }
            }
            DiffPackageHelper.executeLabeledLogic(this); // pos: diff_package_others

            // Override violation (attempt to override final method)
            @Override
            public final String toString() { // Final method cannot be overridden (violation)
                return targetData;
            }

            // No new exception feature
            try {
                Integer.parseInt(targetData);
            } catch (NumberFormatException e) {
                // No throw new exception
                rawList.add("error");
            }
        }

        // Overloading method 2 (overloading feature)
        final void methodToRefactor(TargetClass targetParam, int extra) {
            // Type declaration statement
            int extraValue = extra + staticField;

            // Variable call
            targetParam.setInnerData(String.valueOf(extraValue));

            // No new exception
            try {
                Long.parseLong(targetParam.getInnerData());
            } catch (NumberFormatException e) {
                // No throw new exception
            }
        }
    }

    // Second member inner class
    class SecondInnerClass {
        private String secondInnerField = "secondInner";
    }
}

// Target class: final, anonymous inner class feature, same package as source
final class TargetClass {
    private String innerData = "targetData6409";

    public String getInnerData() {
        // Anonymous inner class (target_feature)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(innerData);
            }
        };
        runnable.run();
        return innerData;
    }

    public void setInnerData(String data) {
        this.innerData = data;
    }
}