package com.refactoring.test;

import java.lang.reflect.Method;

private abstract class SourceClass extends SuperClass implements CustomInterface {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        /**
         * Method Javadoc for targetMethod
         * @param args varargs parameters
         * @return TargetClass instance
         */
        TargetClass targetMethod(String... args) { // varargs, TargetClass Type return, default access
            // Requires try-catch
            try {
                // Local inner class (source_class feature)
                class LocalInnerClass {
                    int counter = 1; // target_feature 1
                }
                LocalInnerClass local = new LocalInnerClass();

                // BreakStatement (public modifier, this.field, 1, pos: source)
                public String thisField = this.targetField.toString(); // this.field
                outer:
                for (int i = 0; i < args.length; i++) {
                    if (i == local.counter) { // 1
                        break outer; // break statement
                    }
                    // Variable call
                    String targetValue = targetField.getValue();
                }

                // Do-while with instance method (pos: do-while)
                int num = 2; // method_feature 2
                do {
                    // obj.m1().m2().m3() (method_feature)
                    Object chainResult = instanceMethod(num).toString().toLowerCase().intern(); // target, instance
                    num--;
                } while (num > 0);

                // Used by reflection
                Method method = InnerSourceClass.class.getDeclaredMethod("targetMethod", String[].class);
                method.setAccessible(true);
                method.invoke(this, (Object) new String[]{"test"});

                return targetField;
            } catch (Exception e) { // requires_try_catch
                return new TargetClass();
            }
        }

        // Instance method (private modifier, return Object, target, instance, 2)
        private Object instanceMethod(int num) { // 2 in method_feature
            return targetField; // target in method_feature
        }
    }
}

// Super class for extends feature (source_class)
class SuperClass {}

// Interface for implements feature (source_class)
interface CustomInterface {}

public abstract class TargetClass {
    public String getValue() {
        return "targetValue";
    }
}