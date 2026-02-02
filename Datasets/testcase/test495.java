package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Parent class for call_method (parent_class type)
abstract class ParentClass {
    // Final call method (parent_class type, exception handling pos, instance + lambda, returns List<String>)
    final List<String> callMethod() {
        List<String> result = new ArrayList<>();
        SourceAbstractClass source = new SourceAbstractClass() {};
        TargetAbstractClass.TargetInnerRec targetParam = new TargetAbstractClass.TargetInnerRec("test");

        try {
            // Instance feature (call instance method)
            int methodResult = source.refactorMethod(targetParam);
            result.add(String.valueOf(methodResult));
            
            // (parameters) -> methodBody target_feature
            Consumer<TargetAbstractClass.TargetInnerRec> lambda = (param) -> {
                source.refactorMethod(param);
                result.add(param.getData());
            };
            lambda.accept(targetParam);
        } catch (NullPointerException e) {
            // Exception handling statements position
            result.add("NPE caught: " + e.getMessage());
        }
        return result;
    }
}

// Source abstract class (protected modifier, same package, static nested + local inner class)
protected abstract class SourceAbstractClass extends ParentClass {
    private int instanceVar; // Field for this.var = var feature

    // Static nested class (source_class feature)
    static class SourceStaticNested {
        int nestedValue = 10;
        public int getNestedValue() { return nestedValue; }
    }

    // Instance method to refactor (public access, base type return, has target param - per_condition)
    public int refactorMethod(TargetAbstractClass.TargetInnerRec targetParam) {
        // NullPointerException feature
        if (targetParam == null) {
            throw new NullPointerException("Target parameter cannot be null");
        }

        // Variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        int varCall = staticNested.getNestedValue();

        // Local inner class (source_class feature) - depends_on_inner_class feature
        class SourceLocalInner {
            int processValue(int val) {
                return val * 2;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.processValue(varCall); // depends_on_inner_class

        // For statement feature
        for (int i = 0; i < varCall; i++) {
            this.instanceVar = i; // this.var = var feature
        }

        // Constructor invocation feature
        TargetAbstractClass.TargetInnerRec newRec = new TargetAbstractClass.TargetInnerRec("new_data");
        varCall += newRec.getData().length();

        // No_new_exception feature (no explicit throw new Exception beyond NPE)
        if (this.instanceVar == 0) {
            varCall = 0;
        }

        // Return base type (int)
        return varCall;
    }
}

// Target abstract class (protected modifier, javadoc target_feature)
/**
 * TargetAbstractClass - Complete Javadoc for target_feature
 * This abstract class contains inner record for move method refactoring target
 */
protected abstract class TargetAbstractClass {
    // Target_inner_rec (inner record for target class)
    public record TargetInnerRec(String data) {
        public String getData() {
            return data;
        }
    }
}