package com.refactor.movemethod;

import java.util.Objects;

// Diff package class for WhileStatement pos: diff_package_others
package com.refactor.other;
public class DiffPackageOthers {
    int field = 2; // obj.field with value 2 for WhileStatement target_feature
}

// Back to original package
package com.refactor.movemethod;
import com.refactor.other.DiffPackageOthers;
import java.util.function.Function;

// Super class for source_class extends feature
class SourceSuperClass {}

// Source normal class (protected modifier, same package as target, extends + static nested + anonymous inner class)
protected class SourceClass extends SourceSuperClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        int nestedValue = 3;
    }

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Varargs method to refactor (final access, returns TargetClass Type, source position)
    public final TargetClass refactorMethod(String... varargs) {
        // Variable call feature
        sourceAnonymous.run();
        SourceStaticNested staticNested = new SourceStaticNested();
        int varCall = staticNested.nestedValue;

        // WhileStatement (private modifier, obj.field=2, pos: diff_package_others)
        DiffPackageOthers diffObj = new DiffPackageOthers();
        int count = 0;
        while (count < 2) {
            diffObj.field = 2; // obj.field target_feature with value 2
            count++;
        }

        // Try statement feature
        try {
            // ExpressionMethodReference (numbers=3, abstract modifier)
            Function<Integer, String> methodRef = String::valueOf;
            String numStr = methodRef.apply(3); // numbers=3 feature
            varCall = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            // No_new_exception feature (no explicit throw new Exception)
            varCall = 3;
        }

        // Overload exist feature (overloaded method)
        refactorMethod(3, varargs);

        // Return TargetClass Type
        return targetField;
    }

    // Overload method (overload_exist feature)
    private TargetClass refactorMethod(int num, String... varargs) {
        return new TargetClass();
    }
}

// Target normal class (public modifier, anonymous inner class target_feature)
public class TargetClass {
    // Anonymous inner class (target_feature)
    private final Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    // Inner class for call_method target_feature
    public class TargetInnerClass {
        public int innerMethod() {
            targetAnonymous.run();
            return 3;
        }
    }
}

// Others_class for call_method (private modifier, if/else pos, normal + outerInstance.new InnerClass().method())
class OthersClass {
    // Call method (private modifier, others_class type, if/else pos, returns int)
    private int callMethod() {
        SourceClass outerInstance = new SourceClass();
        TargetClass target = outerInstance.refactorMethod();
        
        // If/else conditions position
        if (target != null) {
            // outerInstance.new InnerClass().methodName() target_feature
            return target.new TargetInnerClass().innerMethod();
        } else {
            return 0;
        }
    }
}