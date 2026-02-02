package com.refactoring.movemethod;

// Diff package class for VariableDeclarationStatement position
package diffpackage;
public class DiffPackageOthers {
    public int sharedField = 1;
}

package com.refactoring.movemethod;
import diffpackage.DiffPackageOthers;
import java.util.function.Consumer;

// Source normal class (protected modifier, same package, two member inner classes)
protected class SourceClass {
    private int sourceField = 1;

    // First member inner class (duplicated feature)
    class MemberInnerClass1 {
        void innerMethod1() {}
    }

    // Second member inner class (duplicated feature)
    class MemberInnerClass2 {
        void innerMethod2() {}
    }

    // Interface for overriding feature
    interface OverrideInterface {
        void overrideMethod();
    }

    // Instance method to be moved (public, base type return, generic parameter, source position)
    public <T extends TargetClass> int moveableMethod(T targetParam) {
        // VariableDeclarationStatement (private, diff_package_others pos, this.field + 1 features)
        private DiffPackageOthers diffObj = new DiffPackageOthers();
        private int localVar = this.sourceField + diffObj.sharedField; // this.field + 1

        // Overriding feature (default modifier, exception handling pos, 3 + inner_class + overriding + method ref)
        try {
            OverrideInterface overrideObj = new MemberInnerClass1() implements OverrideInterface {
                @Override
                void overrideMethod() {
                    int num = 3;
                    Consumer<String> consumer = SourceClass.MemberInnerClass2::innerMethod2; // ClassName::methodName
                    consumer.accept(String.valueOf(num));
                }
            };
            overrideObj.overrideMethod();
        } catch (Exception e) {
            // No new exception instantiation (no_new_exception feature)
        }

        // Variable call feature
        String varCall = String.valueOf(localVar) + targetParam.targetField;
        
        // Return base type (int)
        return localVar + 3;
    }
}

// Target normal class (public modifier, anonymous inner class feature)
public class TargetClass {
    String targetField = "target_field";

    // Anonymous inner class as target feature
    {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                targetField = "updated_by_anonymous";
            }
        };
        anonymousInner.run();
    }
}