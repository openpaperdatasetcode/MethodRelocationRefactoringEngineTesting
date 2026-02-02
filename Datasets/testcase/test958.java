package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source record class (private modifier, same package)
private record SourceClass(String sourceField) {
    // First local inner class (source feature)
    public List<String> processTarget(TargetClass... params) {
        class LocalInnerClass1 {
            void updateField(SourceClass source, String val) {
                // this.var = var feature (via record component access)
                source.sourceField(); // Simulate assignment via record's implicit accessor
            }
        }

        // Second local inner class (source feature)
        class LocalInnerClass2 {
            List<String> collectValues(TargetClass... targets) {
                List<String> values = new ArrayList<>();
                for (TargetClass target : targets) {
                    // Variable call (per_condition: contains target parameter)
                    String targetVal = target.targetField();
                    values.add(targetVal);
                }
                return values;
            }
        }

        /**
         * Varargs method to process target record instances
         * @param params Target record varargs parameter
         * @return List of string values from target records
         */
        // Method implementation (varargs, public access, List<String> return)
        List<String> result = new ArrayList<>();
        new LocalInnerClass1().updateField(this, "processed");
        result = new LocalInnerClass2().collectValues(params);

        // No_new_exception (empty try-catch)
        try {
            // No exception thrown
            result.add(this.sourceField());
        } catch (Exception e) {
            // Do not throw new exception
            result.add("error");
        }

        return result;
    }
}

// Target record class (public modifier)
public record TargetClass(String targetField) {
    // Local inner class (target feature)
    public void targetMethod() {
        class TargetLocalInnerClass {
            String getTargetValue(TargetClass target) {
                // Variable call support
                return target.targetField();
            }
        }
        new TargetLocalInnerClass().getTargetValue(this);
    }
}