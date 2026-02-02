package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Functional interface for source class implementation
interface OperationExecutor {
    void execute();
}

// Others class for instance method feature
class OthersClass {
    List<String> othersInstanceMethod(int num) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(num));
        return list;
    }
}

// Superclass for super keywords feature
class SuperClass {
    protected String superField = "super_value";
}

// Source normal class (default modifier, same package, implements + two local inner classes)
class SourceClass extends SuperClass implements OperationExecutor {
    // Instance method to be moved (public, void return, source position)
    public void moveableMethod(TargetClass targetParam) {
        // NullPointerException feature (declared, no new instantiation - no_new_exception)
        if (targetParam == null) {
            throw new NullPointerException();
        }

        // AssertStatement (protected modifier, source pos, obj.field + 1 features)
        protected int assertVar = targetParam.targetField + 1; // obj.field + 1
        assert assertVar > 0 : "Assertion failed";

        // First local inner class (source feature)
        class SourceLocalInner1 {
            int innerField1 = 3;
        }
        SourceLocalInner1 localInner1 = new SourceLocalInner1();

        // Second local inner class (duplicated source feature)
        class SourceLocalInner2 {
            String innerField2 = "local_inner_2";
        }
        SourceLocalInner2 localInner2 = new SourceLocalInner2();

        // Instance method feature (public modifier, array initialization pos, 3 + others_class + instance + instanceReference.methodName(arguments))
        public OthersClass othersInstance = new OthersClass();
        List<String>[] arrayInit = new List[] {
            othersInstance.othersInstanceMethod(localInner1.innerField1) // 3 + others_class + instance + instanceReference.methodName(arguments)
        };

        // Type declaration statement feature
        class TypeDeclClass {
            String typeField = targetParam.targetField;
        }
        TypeDeclClass typeDecl = new TypeDeclClass();

        // Expression statement feature
        String exprStmt = super.superField + typeDecl.typeField; // super keywords feature

        // Variable call feature
        String varCall = exprStmt + localInner2.innerField2 + arrayInit[0].get(0);

        // No new exception instantiation (no_new_exception feature)
        System.out.println(varCall);
    }

    // Implementation of OperationExecutor interface
    @Override
    public void execute() {
        moveableMethod(new TargetClass());
    }
}

// Target normal class (default modifier, empty target features)
class TargetClass {
    int targetField = 10;
}