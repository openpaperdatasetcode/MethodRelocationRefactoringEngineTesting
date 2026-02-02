package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for pos="the attribute values of annotations"
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {
    String varargsMethod() default "";
    String callMethod() default "";
}

// Functional interface for source_class implements feature
interface SourceFunctionalInterface {
    int processTarget(); // method types parameter is:none
}

// Target abstract class (public modifier, static nested class feature)
public abstract class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static int helperMethod(String input) {
            return input.length() + 3; // 3 from method_feature
        }
    }

    // Target inner class (target_inner)
    public abstract class TargetInner {
        abstract String getInnerField();
        abstract void setInnerField(String value);
    }
}

// Source abstract class (public modifier, same package, implements + two anonymous inner classes)
public abstract class SourceClass implements SourceFunctionalInterface {
    // Member inner class (source_inner for method_position)
    class SourceInnerClass {
        // Varargs method (public modifier, method_feature:3/inner_class/varargs/super.methodName(), pos=annotation values, return Object)
        @RefactorAnnotation(varargsMethod = "#{varargsMethod(targetInner, \"val1\", \"val2\", \"val3\")}")
        public <T extends CharSequence> Object varargsMethod(TargetInner targetInner, T... args) { // with_bounds (T extends CharSequence)
            // super.methodName() (call Object superclass method)
            String superResult = super.toString() + "_" + 3;
            StringBuilder sb = new StringBuilder(superResult);
            for (T arg : args) {
                sb.append(arg).append("_").append(3);
            }
            // depends_on_inner_class (rely on TargetInner)
            targetInner.setInnerField(sb.toString());
            return sb.toString();
        }

        // Overload exist method 1
        private int overloadMethod(TargetInner target) {
            return target.getInnerField().length();
        }

        // Overload exist method 2 (overload_exist feature)
        private int overloadMethod(TargetInner target, int multiplier) {
            return target.getInnerField().length() * multiplier * 3;
        }

        // call_method (strictfp modifier, normal + OuterClass.InnerClass.methodName(), pos=annotation values, return int)
        @RefactorAnnotation(callMethod = "#{SourceClass.SourceInnerClass.callMethod(targetInner)}")
        strictfp int callMethod(TargetInner targetInner) {
            // OuterClass.InnerClass.methodName()
            int staticResult = TargetClass.TargetStaticNested.helperMethod(targetInner.getInnerField());
            return staticResult + 3;
        }

        // Method to be refactored (instance, base type return, protected access, source_inner position)
        protected <T extends CharSequence> int moveMethod() { // method types parameter is:none, with_bounds
            // Create target instance (anonymous subclass of abstract TargetInner)
            TargetInner targetInner = new TargetClass().new TargetInner() {
                @Override
                String getInnerField() {
                    return TargetClass.this.targetField;
                }

                @Override
                void setInnerField(String value) {
                    TargetClass.this.targetField = value;
                }
            };

            // Per_condition: source contains the field of the target (access targetInner.getInnerField())
            targetInner.setInnerField("initial_" + 3);

            // Variable call (access target field - per_condition)
            String varCall = targetInner.getInnerField();

            // Varargs method call (pos=annotation values)
            Object varargsResult = varargsMethod(targetInner, (T) "arg1", (T) "arg2");

            // Overload exist feature
            int overloadResult1 = overloadMethod(targetInner);
            int overloadResult2 = overloadMethod(targetInner, 3);

            // call_method invocation (pos=annotation values)
            int callResult = callMethod(targetInner);

            // Anonymous inner class 1 (source_feature)
            Runnable anonymous1 = new Runnable() {
                @Override
                public void run() {
                    targetInner.setInnerField(varCall + "_anonymous1_" + 3);
                }
            };
            anonymous1.run();

            // Anonymous inner class 2 (source_feature)
            Runnable anonymous2 = new Runnable() {
                @Override
                public void run() {
                    targetInner.setInnerField(targetInner.getInnerField() + "_anonymous2_" + 3);
                }
            };
            anonymous2.run();

            // depends_on_inner_class (core logic relies on TargetInner)
            int finalResult = (overloadResult1 + overloadResult2 + callResult) * 3;

            // No new exception
            return finalResult; // base type return (int)
        }

        // Alias for TargetInner to match target class naming
        private abstract class TargetInner extends TargetClass.TargetInner {}
    }

    // Implements interface method (method types parameter is:none)
    @Override
    public int processTarget() {
        return new SourceInnerClass().moveMethod();
    }

    // Abstract method required for abstract class
    public abstract void abstractSourceMethod();
}