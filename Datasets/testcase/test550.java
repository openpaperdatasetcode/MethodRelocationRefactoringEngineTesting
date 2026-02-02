package com.refactor;

import java.util.List;

// Source class: enum, strictfp modifier, same package as target, implements interface + anonymous inner + static nested class
strictfp enum SourceEnum implements SampleInterface {
    ENUM_VALUE1, ENUM_VALUE2;

    // Target class field reference (per_condition)
    private TargetEnum targetField = TargetEnum.TARGET_VALUE1;

    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Source inner class (method_position: source_inner)
    class SourceInner {
        // Method to refactor: instance, returns TargetEnum, protected, in source_inner
        protected TargetEnum methodToMove() {
            // ReturnStatement (static modifier, this.field, pos: source, value 1)
            static int field = 1;
            this.field = field;
            if (this.field == 1) {
                return TargetEnum.TARGET_VALUE1;
            }

            // Generic method feature (public modifier, 2, source, generic, ClassName.methodName(arguments) in switch)
            int switchVar = 2;
            switch (switchVar) {
                case 2:
                    Object genericResult = SourceInner.<String>genericMethod("arg");
                    break;
                default:
                    break;
            }

            // Return statement (additional)
            TargetEnum returnVal = targetField;

            // Variable call (target field access)
            String varCall = targetField.nestedStaticClassField;

            // No new exception thrown

            return returnVal;
        }

        // Generic method for method_feature (overload exists)
        public <T> Object genericMethod(T param) {
            return param;
        }

        // Overload of genericMethod (overload_exist feature)
        public <T, U> Object genericMethod(T param1, U param2) {
            return param1 + ":" + param2;
        }

        // Field for ReturnStatement (this.field)
        int field;
    }

    // Anonymous inner class (source feature)
    SampleInterface anonymousInner = new SampleInterface() {
        @Override
        public void interfaceMethod() {
            SourceEnum.this.SourceInner inner = new SourceInner();
            inner.methodToMove();
        }
    };

    @Override
    public void interfaceMethod() {
        // Implements feature - interface method implementation
    }
}

// Target class: enum, default modifier, static nested class (target_feature)
enum TargetEnum {
    TARGET_VALUE1, TARGET_VALUE2;

    // Static nested class (target_feature)
    static class TargetStaticNested {}

    // Target field (referenced in source)
    String nestedStaticClassField = "targetFieldValue";
}

// Interface for source enum implements feature
interface SampleInterface {
    void interfaceMethod();
}