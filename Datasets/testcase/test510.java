// Diff package for ContinueStatement pos: diff_package_target
package com.refactor.other;

import com.refactor.movemethod.TargetClass;

// Helper class for obj.field feature (diff package)
public class DiffPackageHelper {
    public int field = 1; // Value 1 for ContinueStatement target_feature
}

// Source package
package com.refactor.movemethod;

import com.refactor.other.DiffPackageHelper;

// Parent class for call_method (parent_class type)
class SourceParentClass {
    // Call method (default modifier, parent_class type, exception handling pos, returns TargetClass Type)
    TargetClass callMethod() {
        SourceClass source = new SourceClass();
        TargetClass.TargetInner targetInner = new TargetClass().new TargetInner("inner_1"); // Number 1

        try {
            // ClassName.methodName(arguments) target_feature
            Object result = SourceClass.processTarget(targetInner);
            // Accessor target_feature (call getter method)
            String innerData = targetInner.getData();
            return new TargetClass(innerData);
        } catch (Exception e) {
            // Exception handling statements position
            return new TargetClass("error_1"); // Number 1
        }
    }
}

// Source normal class (protected modifier, same package, two anonymous inner classes)
protected class SourceClass extends SourceParentClass {
    // First anonymous inner class (source_class feature)
    private final Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class: 1"); // Number 1
        }
    };

    // Second anonymous inner class (source_class feature - duplicate)
    private final Runnable secondAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class: 1"); // Number 1
        }
    };

    // Instance method to refactor (public access, returns Object, source position, target param - per_condition)
    public Object refactorMethod(TargetClass.TargetInner targetParam) {
        // Variable call feature
        firstAnonymous.run();
        secondAnonymous.run();
        String varCall = targetParam.getData();

        // Super constructor invocation feature (implicit super() in constructor, explicit demo here)
        SourceClass source = new SourceClass() {
            {
                super(); // Super constructor invocation
            }
        };

        // Constructor invocation feature
        TargetClass newTarget = new TargetClass("constructor_1"); // Number 1
        TargetClass.TargetInner newInner = newTarget.new TargetInner("inner_constructor_1"); // Number 1

        // ContinueStatement (private modifier, obj.field=1, pos: diff_package_target)
        private void continueStatementDemo() {
            DiffPackageHelper diffObj = new DiffPackageHelper(); // Diff package target
            for (int i = 0; i < 5; i++) {
                if (diffObj.field == 1) { // obj.field target_feature (value 1)
                    continue; // Continue statement feature (duplicate for clarity)
                }
                varCall += i;
            }
        }
        continueStatementDemo();

        // Continue statement feature (explicit)
        for (int i = 0; i < 5; i++) {
            if (i == 1) { // Value 1 for target_feature
                continue;
            }
            varCall += i;
        }

        // No_new_exception feature (no explicit throw new Exception)
        return varCall + "_" + newInner.getData();
    }

    // Static method for ClassName.methodName() feature
    public static Object processTarget(TargetClass.TargetInner target) {
        SourceClass source = new SourceClass();
        return source.refactorMethod(target);
    }
}

// Target normal class (public modifier, empty target_feature)
public class TargetClass {
    private String data;

    public TargetClass() {
        this.data = "default_1"; // Number 1
    }

    public TargetClass(String data) {
        this.data = data;
    }

    // Target_inner (inner class for target class)
    public class TargetInner {
        private String innerData;

        public TargetInner(String innerData) {
            this.innerData = innerData;
        }

        // Accessor method for call_method accessor feature
        public String getData() {
            return innerData;
        }
    }

    // Accessor method
    public String getData() {
        return data;
    }
}