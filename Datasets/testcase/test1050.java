package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class (final modifier, same package, member inner + static nested class)
final class SourceClass {
    // Static nested class (source feature)
    static class StaticNestedSource {
        // Member inner class (source feature)
        class MemberInnerClass {
            // Method to be refactored (accessor, Object return, default access, source_inner position)
            @CustomAnnotation // has_annotation feature
            Object getTargetField(TargetClass.TargetInnerRec targetParam) {
                // Variable call + access target field (per_condition)
                Object targetField = targetParam.innerField;
                // Access instance method
                targetParam.instanceMethod();

                // Switch + break statement
                switch (targetParam.fieldType) {
                    case 1:
                        targetField = "TYPE_1";
                        break;
                    case 2:
                        targetField = "TYPE_2";
                        break;
                    default:
                        targetField = "DEFAULT";
                        break;
                }

                // No new exception
                return targetField;
            }

            // Static code block with call_method (pos: Static code blocks)
            static {
                AbstractInnerClass inner = new ConcreteInnerClass();
                String result = inner.callMethod(new TargetClass.TargetInnerRec());
                System.out.println(result);
            }
        }
    }

    // Abstract inner class for call_method (abstract feature)
    abstract static class AbstractInnerClass {
        // call_method (inner_class type, public modifier, super.methodName())
        public String callMethod(TargetClass.TargetInnerRec param) {
            super.toString(); // super.methodName() feature
            return param.innerField.toString();
        }
    }

    // Concrete implementation of abstract inner class
    static class ConcreteInnerClass extends AbstractInnerClass {}
}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Target class (private modifier, javadoc + anonymous inner class)
private class TargetClass {
    /**
     * Javadoc for TargetInnerRec (target_inner_rec)
     * This is the recursive inner class of target
     */
    public static class TargetInnerRec {
        Object innerField;
        int fieldType;

        // Instance method (access_instance_method feature)
        void instanceMethod() {
            // Anonymous inner class (target feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    System.out.println(innerField);
                }
            };
            anonymous.run();
        }
    }
}