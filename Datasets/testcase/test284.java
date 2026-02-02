package com.refactoring.movemethod;

import java.util.function.Function;

/**
 * Target enum class with javadoc, type parameter and member inner class features
 * @param <T> Generic type parameter (type parameter target feature)
 */
private enum TargetEnum<T> {
    TARGET_INSTANCE("default_value");

    private final T value;
    private final TargetMemberInner innerClass = new TargetMemberInner();

    // Constructor for enum
    TargetEnum(T value) {
        this.value = value;
    }

    // Member inner class (target feature)
    public class TargetMemberInner {
        int innerField = 1;
    }

    // Getter for inner class (target_inner reference)
    public TargetMemberInner getInnerClass() {
        return innerClass;
    }

    // Getter for generic value
    public T getValue() {
        return value;
    }
}

// Source enum class (private modifier, same package, local inner + static nested class)
private enum SourceEnum {
    SOURCE_INSTANCE;

    // Static nested class (source feature)
    private static class SourceStaticNested {
        String nestedField = "static_nested";
    }

    // Inner record class containing the method to be moved (source_inner_rec position)
    private record SourceInnerRec(SourceEnum outerInstance) {
        // Varargs method to be moved (private, returns TargetEnum type, source_inner_rec position)
        private <V extends CharSequence & Comparable<V>> TargetEnum<V> moveableMethod(TargetEnum<V> targetParam, V... varargs) {
            // InfixExpression with protected modifier and number 1 feature
            protected int infixExpr = targetParam.getInnerClass().innerField + 1; // Number 1 feature

            // Constructor invocation feature
            SourceStaticNested staticNestedObj = new SourceStaticNested();

            // Empty statement feature
            ;

            // Type declaration statement feature
            class TypeDeclClass {
                V typeField = varargs[0];
            }
            TypeDeclClass typeDecl = new TypeDeclClass();

            // With bounds feature (generic type with bounds: V extends CharSequence & Comparable<V>)
            Function<V, Integer> boundedFunc = V::length;

            // Variable call feature
            String varCall = staticNestedObj.nestedField + targetParam.getInnerClass().innerField + infixExpr;

            // Overload_exist feature (call overloaded method)
            moveableMethod(targetParam, varargs, "overload_param");

            // Local inner class (source feature)
            class SourceLocalInner {
                int localField = infixExpr;
            }
            SourceLocalInner localInner = new SourceLocalInner();

            // Return statement feature
            return targetParam;
        }

        // Overloaded method for overload_exist feature
        private <V extends CharSequence & Comparable<V>> TargetEnum<V> moveableMethod(TargetEnum<V> targetParam, V[] varargs, String overloadParam) {
            // No new exception instantiation (no_new_exception feature)
            System.out.println(overloadParam + targetParam.getValue());
            return targetParam;
        }
    }
}