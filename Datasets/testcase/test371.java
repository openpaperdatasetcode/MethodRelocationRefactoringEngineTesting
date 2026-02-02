package com.refactoring.movemethod;

// Different package for VariableDeclarationStatement pos (diff_package_others)
package com.refactoring.other;
public class VarDeclHelper {
    public int field = 1; // target_feature: obj.field, 1
}

// Back to main package
package com.refactoring.movemethod;
import com.refactoring.other.VarDeclHelper;

// Source normal class (public, same package, member inner + static nested class)
public class SourceClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    public static class StaticNestedClass {
        public int nestedValue = 10;

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Member inner class (source class feature)
    public class MemberInnerClass {
        public int innerValue = 5;
    }

    // Overloading method 1 (public, void return, source position)
    public void refactorMethod(TargetClass targetParam) {
        refactorMethod(targetParam, 1); // target_feature: 1
    }

    // Overloading method 2 (public, void return, source position - main refactor method)
    public void refactorMethod(TargetClass targetParam, int multiplier) {
        // Variable call feature
        StaticNestedClass staticNested = new StaticNestedClass();
        MemberInnerClass memberInner = new MemberInnerClass();
        int localVar = staticNested.getNestedValue() + memberInner.innerValue;

        // VariableDeclarationStatement (private modifier, diff_package_others pos, obj.field + 1)
        VarDeclHelper varHelper = new VarDeclHelper(); // diff_package_others pos
        private int declVar = varHelper.field * multiplier; // obj.field, target_feature 1 (private modifier)

        // Do statement feature
        int doCount = 0;
        do {
            localVar += declVar;
            doCount++;
        } while (doCount < 3);

        // No new exception feature (no throw new Exception)
        if (targetParam == null) {
            return;
        }

        // Process target parameter (per_condition: method has target parameter)
        targetField = targetParam;
        targetParam.processData(String.valueOf(localVar));
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (protected modifier, anonymous inner class feature)
protected class TargetClass {
    private String data;

    // Anonymous inner class (target_feature)
    public Runnable getAnonymousRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(TargetClass.this.data);
            }
        };
    }

    public void processData(String input) {
        this.data = input;
        getAnonymousRunnable().run();
    }
}