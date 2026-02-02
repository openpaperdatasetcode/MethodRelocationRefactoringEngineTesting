package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface GenericMethodAnnotation {}

// Source abstract normal class (abstract modifier, same package, empty features)
abstract class SourceClass {
    // Private field for access_outer_private feature
    private int outerPrivateField = 2;

    /**
     * Generic method to be moved (public, base type return, source position)
     * @param <T> Generic type parameter
     * @param targetParam Target class parameter (satisfies pre-condition)
     * @return int (base type) result
     */
    @GenericMethodAnnotation
    public <T extends TargetClass> int moveableMethod(T targetParam) {
        // StringLiteral with public modifier and number 2 feature
        public String stringLiteral = "literal_2"; // Number 2 in literal

        // Access_outer_private feature (access outer class private field)
        int outerPrivateAccess = this.outerPrivateField;

        // Variable call feature (target class member inner class field + string literal + private field)
        int varCall = targetParam.innerClass.innerField + outerPrivateAccess + stringLiteral.length();

        // No new exception instantiation (no_new_exception feature)
        return varCall;
    }

    // Abstract method (required for abstract class)
    protected abstract void abstractMethod();
}

// Target normal class (private modifier, member inner class target feature)
private class TargetClass {
    // Member inner class (target feature)
    public class TargetMemberInner {
        int innerField = 10;
    }

    // Instance of member inner class
    TargetMemberInner innerClass = new TargetMemberInner();
}