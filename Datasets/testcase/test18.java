package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Functional interface for varargs feature pos=functional interfaces
@FunctionalInterface
interface VarargsFunction {
    List<String> apply(String... args);
}

// Parent class for call_method (parent_class type)
class ParentClass {
    // Call_method: protected modifier, normal, instanceReference.methodName(arguments), return Object
    protected Object parentMethod(String arg) {
        return "parentValue_" + arg;
    }
}

// Source class: record class, public modifier, same package, empty features
public record SourceRecord(String sourceField, TargetRecord<String> targetField) { // per_condition: contains target field
    // Method: overloading, void return, protected access, source position
    protected void moveableMethod() {
        // Variable call feature
        String localVar = sourceField;
        localVar = targetField.targetField();

        // Type declaration statement feature
        class LocalTypeDeclaration {
            String localField = "localTypeValue";
        }
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Expression statement feature
        localVar = localVar.concat(localType.localField);
        targetField.innerClassMethod();

        // Varargs feature: default modifier, method_feature [1, target, varargs, method ref], pos=functional interfaces, return List<String>
        VarargsFunction varargsFunc = targetField::varargsMethod; // instanceReference::methodName
        List<String> varargsResult = varargsFunc.apply("1", targetField.targetField()); // method_feature "1", "target", "varargs"

        // Access_outer_private feature (access private field of outer record)
        String outerPrivate = this.sourceField;

        // Requires_try_catch feature
        try {
            int parsed = Integer.parseInt(varargsResult.get(0));
        } catch (NumberFormatException e) {
            localVar = "0";
        }

        // Call_method: parent_class, pos=do-while, return Object
        ParentClass parentInstance = new ParentClass();
        int counter = 0;
        do {
            Object callResult = parentInstance.parentMethod(localVar); // instanceReference.methodName(arguments)
            counter++;
        } while (counter < 1);

        // Return statement feature
        return;
    }

    // Overloading method (overloading type feature)
    protected void moveableMethod(String additionalParam) {
        // Variable call feature
        String localVar = targetField.targetField() + additionalParam;
        
        // Requires_try_catch feature
        try {
            localVar = String.valueOf(Integer.parseInt(localVar));
        } catch (NumberFormatException e) {
            localVar = "default";
        }
        
        // Return statement feature
        return;
    }
}

// Target class: record class, default modifier, target_feature: type parameter, implements, member inner class
record TargetRecord<T> implements Runnable { // implements feature (type parameter + implements)
    T targetField;

    // Member inner class (target_feature)
    class MemberInnerTarget {
        void innerMethod() {}
    }

    // Constructor for record initialization
    public TargetRecord(T targetField) {
        this.targetField = targetField;
    }

    // Varargs method for varargs feature (instanceReference::methodName)
    default List<String> varargsMethod(String... args) { // return_type List<String>
        List<String> result = new ArrayList<>();
        for (String arg : args) {
            result.add(arg);
        }
        return result;
    }

    // Helper method for variable call
    void innerClassMethod() {
        new MemberInnerTarget().innerMethod();
    }

    @Override
    public void run() {} // implements Runnable
}