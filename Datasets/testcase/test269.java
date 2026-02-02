package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Source interface (no modifier, same package as target, local/member inner class features)
interface SourceInterface {
    // Member inner class (source feature)
    class MemberInnerClass {
        String innerField;

        public MemberInnerClass(String innerField) {
            this.innerField = innerField;
        }
    }

    // Field referencing target interface (per_condition: source contains target's field)
    TargetInterface targetField = TargetInterface.INSTANCE;

    // Varargs method to refactor (default access, void return, no parameter type restriction)
    default void moveTargetMethod(Object... args) {
        // Method types parameter is:none (varargs with no specific type constraint)
        // Constructor invocation feature
        MemberInnerClass innerObj = new MemberInnerClass("source_inner");

        // CaseDefaultExpression with number 2 (public modifier, exp: CaseDefaultExpression)
        public int caseValue = switch (args.length) {
            case 1 -> 1;
            case 2 -> 2; // Feature "2" (numeric literal)
            default -> 0;
        };

        // With_bounds feature (generic with bounds)
        <T extends CharSequence & Comparable<T>> T processBoundType(T input) {
            return input;
        }

        // Variable call feature
        TargetInterface.TargetStaticNested staticNested = new TargetInterface.TargetStaticNested();
        staticNested.setData(innerObj.innerField + "_" + caseValue);

        // Used_by_reflection feature
        try {
            Method method = TargetInterface.TargetStaticNested.class.getMethod("getData");
            Object reflectedData = method.invoke(staticNested);
            System.out.println(reflectedData);
        } catch (Exception e) {
            // No_new_exception feature (no new custom exceptions thrown)
            e.printStackTrace();
        }

        // Local inner class (source feature)
        class LocalInnerClass {
            void printData() {
                System.out.println(staticNested.getData());
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.printData();
    }
}

// Target interface (default modifier, same package as source, static nested class feature)
interface TargetInterface {
    // Static instance (for targetField in source)
    TargetInterface INSTANCE = new TargetInterface() {};

    // Static nested class (target_feature)
    static class TargetStaticNested {
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}