package com.refactor;

import com.other.DiffPackageClass;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// Source class: normal, default modifier, same package as target, permits (sealed hierarchy) feature
sealed class SourceClass permits SourceSubClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();

    // Source inner class (source_inner - method position)
    class SourceInner {
        // Abstract inner class for abstract method feature
        public abstract class AbstractInnerClass {
            public abstract List<String> abstractMethod();
        }

        // Method to refactor: normal type, List<String> return, private access, in source_inner
        private List<String> methodToMove() {
            // AssertStatement (protected modifier, obj.field, 3, pos: diff_package_others)
            protected DiffPackageClass diffPackageObj = new DiffPackageClass();
            diffPackageObj.field = 3;
            assert diffPackageObj.field == 3 : "Field value must be 3";

            // Abstract method feature (public modifier, 1, inner_class, abstract, new ClassName().method() in functional interfaces)
            Supplier<List<String>> funcInterface = () -> new AbstractInnerClass() {
                @Override
                public List<String> abstractMethod() {
                    return new ArrayList<>();
                }
            }.abstractMethod();

            // Used by reflection (duplicate feature as per spec)
            try {
                Method method = SourceInner.class.getDeclaredMethod("methodToMove");
                method.setAccessible(true);
                method.invoke(this);

                // Second used_by_reflection occurrence
                Method abstractMethod = AbstractInnerClass.class.getDeclaredMethod("abstractMethod");
                abstractMethod.setAccessible(true);
            } catch (Exception e) {
                // No new exception thrown
            }

            // Variable call (target field access)
            String targetVar = targetField.innerClassInstance.innerField;

            // No new exception thrown
            List<String> result = new ArrayList<>();
            result.add(targetVar);
            result.addAll(funcInterface.get());
            return result;
        }
    }
}

// Permits subclass for source class feature
non-sealed class SourceSubClass extends SourceClass {}

// Target class: normal, abstract modifier, implements interface + anonymous inner class (target_feature)
abstract class TargetClass implements SampleInterface {
    // Target inner class (target_inner)
    class TargetInner {
        String innerField = "targetInnerFieldValue";
    }

    // Inner class instance for variable call
    TargetInner innerClassInstance = new TargetInner();

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(innerClassInstance.innerField);
        }
    };

    @Override
    public void interfaceMethod() {
        // Implements feature - interface method implementation
    }
}

// Interface for target class implements feature
interface SampleInterface {
    void interfaceMethod();
}

// Diff package class for AssertStatement pos: diff_package_others
package com.other;
public class DiffPackageClass {
    public int field;
}