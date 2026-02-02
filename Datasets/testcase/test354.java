package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Functional interface for target class implementation
interface DataProcessor {
    String process(String input);
}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface AccessorAnnotation {}

// Source normal class (default modifier, same package, static nested + local inner class)
class SourceClass {
    // Target class field to satisfy pre-condition
    TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {
        int nestedField = 1;
        
        // Overload method for overload_exist feature
        final Object callMethod(String... varargs) {
            // super.methodName(arguments) feature (call to parent class method)
            return super.toString() + varargs[0];
        }
    }

    // Accessor method to be moved (final, base type return, source position)
    @AccessorAnnotation
    final int getTargetValue() {
        // Local inner class (source feature) - depends_on_inner_class
        class SourceLocalInner {
            int innerValue = targetField.staticNested.targetValue;
            
            // Overload method for overload_exist
            int getTargetValue(String param) {
                return innerValue + param.length();
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();

        // Generic feature (public modifier, for pos, 1 + target + generic + this.methodName(arguments))
        public <T extends TargetClass> List<String> genericMethod(T target) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 1; i++) { // Number 1 feature
                // this.methodName(arguments) call
                list.add(String.valueOf(this.getTargetValue(target.getSuffix())));
            }
            return list;
        }

        // Type declaration statement feature
        class TypeDeclClass {
            int value = localInner.innerValue;
        }
        TypeDeclClass typeDecl = new TypeDeclClass();

        // this(arguments) constructor invocation (object initialization pos for call_method)
        SourceStaticNested nestedObj = new SourceStaticNested() {
            {
                // call_method: varargs + super.methodName(arguments) in object initialization
                Object callResult = callMethod("arg1", "arg2"); // varargs feature
            }
        };

        // Variable call feature
        int varCall = typeDecl.value + genericMethod(targetField).size();

        // No new exception instantiation (no_new_exception feature)
        return varCall;
    }

    // Overload of accessor method (overload_exist feature)
    final int getTargetValue(String suffix) {
        return targetField.staticNested.targetValue + suffix.length();
    }
}

// Target normal class (public modifier, implements interface, static nested class)
public class TargetClass implements DataProcessor {
    // Static nested class (target feature)
    public static class TargetStaticNested {
        int targetValue = 10;
    }

    TargetStaticNested staticNested = new TargetStaticNested();

    // Implementation of DataProcessor interface
    @Override
    public String process(String input) {
        return input + "_processed";
    }

    // Method for generic feature call
    String getSuffix() {
        return "suffix";
    }
}