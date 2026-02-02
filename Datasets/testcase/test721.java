package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Source class: abstract, member inner class, local inner class, same package as target
public abstract class SourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    private static int staticField = 100;

    // Member inner class (source class feature)
    abstract class SourceInnerClass {
        // Super method for overriding feature
        abstract TargetClass methodToRefactor();

        // Overriding method: private access, TargetClass return, in source inner class
        @Override
        private TargetClass methodToRefactor() {
            // Variable call (target field and its inner class)
            String targetInnerValue = targetField.targetInnerClass.getInnerValue();
            
            // For statement
            List<String> values = new ArrayList<>();
            for (int i = 0; i < staticField; i++) { // Use static field in for loop
                values.add(targetInnerValue + i);
            }
            
            // Depends on static field
            targetField.targetInnerClass.setInnerValue(targetInnerValue + staticField);
            
            // Local inner class (source class feature)
            class LocalInnerClass {
                void processValues() {
                    values.forEach(System.out::println);
                }
            }
            new LocalInnerClass().processValues();
            
            // Used by reflection feature
            try {
                Method method = TargetClass.TargetInnerClass.class.getDeclaredMethod("getInnerValue");
                method.setAccessible(true);
                method.invoke(targetField.targetInnerClass);
            } catch (Exception e) {
                // No new exception feature (no throw new exception)
                values.add("reflection_error");
            }
            
            // No new exception feature
            try {
                Integer.parseInt(targetInnerValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                values.clear();
            }
            
            return targetField; // TargetClass Type return
        }
    }
}

// Target class: strictfp, member inner class feature, same package as source
strictfp class TargetClass {
    // Member inner class (target class feature)
    public class TargetInnerClass {
        private String innerValue = "target_inner_value";

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Instance of member inner class (used in source class variable call)
    TargetInnerClass targetInnerClass = new TargetInnerClass();
}