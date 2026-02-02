package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Permitted classes for source class permits feature
final class PermittedClass1 extends SourceClass {}
final class PermittedClass2 extends SourceClass {}

// Source class: public, permits, two member inner classes, same package as target
public sealed class SourceClass permits PermittedClass1, PermittedClass2 {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // First member inner class
    class FirstInnerClass {
        private int innerField = 3; // For ConstructorInvocation target_feature: 3

        // Method to refactor: instance, TargetClass return, protected, in source inner class
        protected TargetClass methodToRefactor() {
            // Type declaration statement
            List<String> dataList;
            
            // Variable call (target field and its static nested class)
            String targetValue = targetField.getStaticNestedClass().nestedField;
            
            // Constructor invocation (source inner class)
            SecondInnerClass secondInner = new SecondInnerClass();
            
            // Access instance method
            targetValue += secondInner.instanceMethod();
            
            // ConstructorInvocation feature: private modifier, this.field, 3, pos=source
            private void constructorLogic() {
                int count = this.innerField; // this.field, target_feature: 3
                for (int i = 0; i < count; i++) {
                    // Constructor invocation (target class)
                    TargetClass newTarget = new TargetClass();
                    targetValue += newTarget.getStaticNestedClass().nestedField;
                }
            }
            constructorLogic();

            // Normal method feature: public modifier, 1, others_class, normal, obj.m1().m2().m3(), pos=for, return List<String>
            public List<String> normalHelperMethod() {
                dataList = new ArrayList<>();
                int limit = 1; // method_feature: 1
                // pos: for loop
                for (int i = 0; i < limit; i++) {
                    // others_class usage
                    OtherClass otherObj = new OtherClass();
                    // obj.m1().m2().m3() feature
                    String chainResult = otherObj.m1().m2().m3();
                    dataList.add(chainResult);
                }
                return dataList;
            }
            normalHelperMethod();

            // No new exception feature
            try {
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception
                dataList.add("error");
            }

            return targetField; // TargetClass Type return
        }

        // Instance method for access_instance_method feature
        private String instanceMethod() {
            return "instanceMethodValue";
        }
    }

    // Second member inner class (source class feature)
    class SecondInnerClass {
        public String instanceMethod() {
            return "secondInnerValue";
        }
    }
}

// Target class: public, static nested class feature, same package as source
public class TargetClass {
    // Static nested class (target class feature)
    public static class TargetStaticNestedClass {
        public String nestedField = "targetNestedValue";
    }

    public TargetStaticNestedClass getStaticNestedClass() {
        return new TargetStaticNestedClass();
    }
}

// Others class for normal method feature
class OtherClass {
    public String m1() {
        return "m1_";
    }

    public String m2() {
        return "m2_";
    }

    public String m3() {
        return "m3";
    }
}