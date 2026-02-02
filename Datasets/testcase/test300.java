package com.refactoring.movemethod;

import java.sql.SQLException;
import java.util.function.Consumer;

// Diff package class for WhileStatement position
package diffpackage;
public class DiffPackageOthers {
    public int sharedField = 2;
}

package com.refactoring.movemethod;
import diffpackage.DiffPackageOthers;

// Functional interface for source class implementation
interface DatabaseOperation {
    void execute() throws SQLException;
}

// Source normal class (private modifier, same package, implements + two member inner classes)
private class SourceClass implements DatabaseOperation {
    // Target class field to satisfy pre-condition
    TargetClass targetField = new TargetClass();

    // First member inner class (duplicated feature)
    private class SourceMemberInner1 {
        void innerMethod1() {}
    }

    // Second member inner class (duplicated feature)
    private class SourceMemberInner2 {
        int innerField = 2;
    }

    // Instance method to be moved (final, void return, source position)
    final void moveableMethod() throws SQLException {
        // WhileStatement (protected modifier, diff_package_others pos, this.field + 2 features)
        protected DiffPackageOthers whileObj = new DiffPackageOthers();
        int whileVar = this.targetField.staticNested.targetField.length() + whileObj.sharedField; // this.field + 2
        while (whileVar > 0) {
            whileVar--;
        }

        // Instance method feature (protected modifier, functional interfaces pos, 2 + source + instance + new ClassName().method())
        protected Consumer<Integer> instanceFunc = num -> {
            if (num == 2) { // Number 2 feature
                // new ClassName().method() call (source + instance)
                new SourceMemberInner2().innerMethod1();
            }
        };
        instanceFunc.accept(2);

        // TypeLiteral with abstract modifier and number 3 feature
        abstract class TypeLiteral<T> {
            private final int literalNum = 3;
        }
        TypeLiteral<String> typeLiteral = new TypeLiteral<>() {};

        // Variable call feature
        String varCall = targetField.staticNested.targetField + whileObj.sharedField + typeLiteral.literalNum;

        // Call_method feature (sub_class, default modifier, static + outerInstance.new InnerClass().methodName() in array initialization)
        String[] callArray = {
            TargetSubClass.staticMethod(), // static feature
            this.new SourceMemberInner1().innerMethod1().toString() // outerInstance.new InnerClass().methodName()
        };

        // No new exception instantiation (no_new_exception feature) - only declares SQLException
        System.out.println(varCall + callArray[0]);
    }

    // Implementation of DatabaseOperation interface
    @Override
    public void execute() throws SQLException {
        moveableMethod();
    }
}

// Target normal class (default modifier, static nested class target feature)
class TargetClass {
    // Static nested class (target feature)
    static class TargetStaticNested {
        String targetField = "target_inner_field";
    }

    // Instance of static nested class (target_inner reference)
    TargetStaticNested staticNested = new TargetStaticNested();
}

// Subclass for call_method feature
class TargetSubClass extends TargetClass {
    // Static method for call_method feature
    static String staticMethod() {
        return "subclass_static_result";
    }
}