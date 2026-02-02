package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source normal class (public, same package, two static nested classes feature)
public class SourceClass {
    // First static nested class (source class feature)
    public static class StaticNestedOne {
        public int nestedValue = 1;
    }

    // Second static nested class (source class feature - duplicate as per spec)
    public static class StaticNestedTwo {
        public static int staticField = 1;
    }

    // Inner recursive class (source_inner_rec position for refactor method)
    public class SourceInnerRec {
        private int innerVar = 10;

        // Overriding method (public, return List<String>, source_inner_rec position)
        @Override
        public List<String> toString() { // Overriding type (Object's toString)
            return refactorMethod(new TargetClass.TargetInner());
        }

        // Method to refactor (overriding, public, return List<String>, source_inner_rec)
        public List<String> refactorMethod(TargetClass.TargetInner targetParam) {
            // Variable call feature
            StaticNestedOne nestedOne = new StaticNestedOne();
            int localVar = nestedOne.nestedValue; // variable call

            // Raw type feature
            List rawList = new ArrayList(); // raw_type

            // Do statement feature
            int doCount = 0;
            do {
                rawList.add(String.valueOf(doCount + localVar));
                doCount++;
            } while (doCount < 5);

            // TypeDeclarationStatement (static modifier, inner class pos, super.field + 1)
            class InnerTypeDeclaration { // pos: inner class
                static class StaticType { // static modifier
                    public int getSuperField() {
                        return SuperClass.superField; // super.field, target_feature 1
                    }
                }
            }
            InnerTypeDeclaration.StaticType staticType = new InnerTypeDeclaration.StaticType();
            rawList.add(String.valueOf(staticType.getSuperField()));

            // Call method (source, final, pos: functional interfaces, static + instanceReference.methodName)
            Function<Integer, Integer> func = (param) -> SourceClass.finalStaticMethod(param); // functional interfaces pos
            int callResult = func.apply(localVar); // instanceReference.methodName(arguments)
            rawList.add(String.valueOf(callResult));

            // No new exception feature (no throw new Exception)
            if (targetParam == null) {
                return rawList;
            }

            // Process target parameter (per_condition: method has target parameter)
            rawList.add(targetParam.getInnerData());
            return rawList;
        }
    }

    // Call method (source, final, static, return int, instanceReference.methodName feature)
    public final static int finalStaticMethod(int arg) { // static target_feature
        return arg * StaticNestedTwo.staticField; // instanceReference.methodName context
    }

    // Constructor to initialize inner recursive class
    public SourceClass() {
        SourceInnerRec innerRec = new SourceInnerRec();
    }
}

// Super class for TypeDeclarationStatement (super.field feature)
class SuperClass {
    public static int superField = 1; // target_feature: 1, super.field
}

// Target normal class (public, member inner class feature)
public class TargetClass {
    // Member inner class (target_inner, target_feature)
    public class TargetInner {
        private String innerData = "target_inner_data_1";

        public String getInnerData() {
            return innerData;
        }
    }

    // Constructor to initialize inner class
    public TargetClass() {
        TargetInner inner = new TargetInner();
    }
}