package com.refactoring.movemethod;

// Diff package class for DoStatement pos: diff_package_others
package com.other;
public class DiffPackageHelper {
    public static void executeDoLogic(com.refactoring.movemethod.SourceClass.SourceInnerClass.InnerRecursiveClass innerObj, 
                                     com.refactoring.movemethod.TargetClass targetParam) {
        innerObj.doLogic(targetParam);
    }
}

package com.refactoring.movemethod;
import com.other.DiffPackageHelper;

// Source class: public, same package as target, member inner class, anonymous inner class
public class SourceClass {
    // Private outer field for access_outer_private feature
    private int outerPrivateField = 6429;

    // Member inner class (source class feature)
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec - method position)
        class InnerRecursiveClass {
            private int innerField = 2; // For DoStatement target_feature: 2

            // Method to refactor: instance, base type (int) return, private access, in source_inner_rec
            // Per_condition: contains target class parameter
            private int methodToRefactor(TargetClass targetParam) {
                // Variable call (target parameter's inner field via anonymous class)
                String targetValue = targetParam.getAnonymousData();
                int result = 0;

                // Access outer private field (access_outer_private feature)
                result += SourceClass.this.outerPrivateField;

                // DoStatement feature: private modifier, obj.field, 2, pos=diff_package_others
                private void doLogic(TargetClass param) {
                    int count = this.innerField; // obj.field (this.innerField), target_feature: 2
                    do {
                        result += param.getAnonymousData().length();
                        count--;
                    } while (count > 0);
                }
                DiffPackageHelper.executeDoLogic(this, targetParam); // pos: diff_package_others

                // Override violation (attempt to override final method from Object)
                @Override
                public final String toString() { // Final method cannot be overridden (violation)
                    return String.valueOf(result);
                }

                // Requires_try_catch feature
                try {
                    result += Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // Handle exception (required try-catch)
                    result = 0;
                }

                // Anonymous inner class (source class feature)
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Result: " + result);
                    }
                };
                runnable.run();

                return result; // Base type (int) return
            }
        }
    }

    // Additional anonymous inner class (source class feature)
    public void sourceAnonymousFeature() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous class");
            }
        };
        anonymousRunnable.run();
    }
}

// Target class: public, same package as source, anonymous inner class feature
public class TargetClass {
    private String targetData = "6429";

    public String getAnonymousData() {
        // Anonymous inner class (target class feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetData);
            }
        };
        anonymousRunnable.run();
        return targetData;
    }
}