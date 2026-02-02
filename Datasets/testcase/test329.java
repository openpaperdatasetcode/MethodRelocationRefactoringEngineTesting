package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Generic source class with default modifier, same package as target
class SourceClass<T> {
    // Static nested class (first occurrence)
    public static class StaticNestedOne {}
    // Static nested class (second occurrence)
    public static class StaticNestedTwo {}

    protected String superField = "superFieldValue";

    // Overriding method (implements interface method for overriding context)
    @Override
    public List<String> targetMethod(TargetClass<String>.NestedInner targetParam, String... args) {
        List<String> result = new ArrayList<>();

        // For statement
        for (String arg : args) {
            // Variable call
            String localVar = arg;
            // If statement
            if (localVar.length() > 3) {
                result.add(localVar);
            }
        }

        // Assignment with number 3 and default modifier
        int num = 3;
        num += 1; // Assignment expression

        // Inner class with VariableDeclarationStatement (private modifier, super.field + 1)
        class InnerClass {
            private int innerVar = SourceClass.this.superField.length() + 1;
        }
        InnerClass inner = new InnerClass();

        // No new exception (no exception instantiation, only return)
        return result;
    }
}

// Generic target class with default modifier
class TargetClass<U> {
    // Static nested class (target_feature)
    public static class TargetStaticNested {}

    // Member inner class (target_inner_rec)
    public class NestedInner {
        private U value;

        public NestedInner(U value) {
            this.value = value;
        }

        public U getValue() {
            return value;
        }
    }
}

// Interface to enable overriding in SourceClass
interface MethodOverrideInterface {
    List<String> targetMethod(TargetClass<String>.NestedInner targetParam, String... args);
}

// Make SourceClass implement the interface to satisfy overriding feature
class SourceClass<T> implements MethodOverrideInterface {}