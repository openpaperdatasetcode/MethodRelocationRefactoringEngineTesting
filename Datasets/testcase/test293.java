package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Superclass for source class extension
abstract class SuperSourceClass {}

// Others class for call_method feature
class OthersClass {
    protected List<String> othersMethod() {
        super.toString(); // super.methodName() feature
        return new ArrayList<>();
    }
}

// Source abstract class (abstract modifier, same package, extends + static nested + anonymous inner class)
abstract class SourceClass extends SuperSourceClass {
    // Target class field to satisfy pre-condition
    TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {
        String nestedField = "static_nested";
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(targetField.staticNested.targetField);
        }
    };

    // Varargs method to be moved (protected, returns List<String>, source position)
    protected List<String> moveableMethod(String... varargs) {
        List<String> result = new ArrayList<>();

        // Break statement feature
        for (int i = 0; i < varargs.length; i++) {
            if (i == 2) {
                break;
            }
            // Access instance field feature (target class static nested field)
            result.add(varargs[i] + targetField.staticNested.targetField);
        }

        // Expression statement feature
        String exprStmt = new SourceStaticNested().nestedField + targetField.instanceField;
        
        // Variable call feature
        String varCall = exprStmt + anonymousInner.toString();
        
        // Call_method feature (others_class, protected, constructor + super.methodName(), functional interfaces pos)
        Supplier<List<String>> supplier = () -> {
            OthersClass othersObj = new OthersClass(); // Constructor feature
            return othersObj.othersMethod(); // super.methodName() inside othersMethod
        };
        result.addAll(supplier.get());

        // No new exception instantiation (no_new_exception feature)
        result.add(varCall);
        return result;
    }
}

// Target abstract class (public modifier, static nested class target feature)
public abstract class TargetClass {
    // Instance field for access_instance_field feature
    String instanceField = "target_instance";

    // Static nested class (target feature)
    static class TargetStaticNested {
        String targetField = "target_static";
    }

    // Instance of static nested class
    TargetStaticNested staticNested = new TargetStaticNested();

    // Abstract method (required for abstract class)
    public abstract void abstractTargetMethod();
}