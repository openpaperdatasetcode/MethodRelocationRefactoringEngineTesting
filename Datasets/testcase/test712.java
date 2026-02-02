package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;
import java.util.List;

// Source class: abstract, type parameter, two anonymous inner classes, different package from target
public abstract class SourceClass<T extends Number> {
    // Per_condition: source contains target class field
    private TargetClass targetField;

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        /**
         * Method Javadoc (method feature)
         * Varargs method, Object return, private access, in source inner class
         * @param args varargs parameter
         * @return Object result
         */
        private Object methodToRefactor(String... args) {
            // Variable call (target class field)
            String targetValue = targetField != null ? targetField.getTargetValue() : "default";
            
            // WhileStatement feature: public modifier, super.field, 1 iteration, pos=source
            public void whileLogic() {
                SuperClass superObj = new SuperClass();
                int count = 1; // target_feature: 1
                while (count > 0) {
                    targetValue += superObj.superField; // super.field
                    count--;
                }
            }
            whileLogic();

            // Constructor feature: default modifier, 3, target, constructor, ClassName::methodName, pos=exception handling, return List<String>
            default List<String> constructorLogic() {
                List<String> list = new ArrayList<>();
                try {
                    // ClassName::methodName (method reference)
                    list.forEach(TargetClass::getTargetValue);
                    // Constructor invocation (target class), target_feature: 3
                    for (int i = 0; i < 3; i++) {
                        TargetClass targetInstance = new TargetClass(); // target class constructor
                        list.add(targetInstance.getTargetValue());
                    }
                } catch (Exception e) {
                    // No new exception feature (no throw new)
                    list.add("error");
                } // pos: exception handling statements
                return list;
            }
            constructorLogic();

            // Enhanced for statement (varargs iteration)
            for (String arg : args) { // enhanced for statement
                targetValue += arg;
            }

            // First anonymous inner class (source class feature)
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(targetValue);
                }
            };
            runnable1.run();

            // Second anonymous inner class (source class feature)
            Runnable runnable2 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(constructorLogic());
                }
            };
            runnable2.run();

            // No new exception (only catch, no throw new)
            try {
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception
            }

            return targetValue;
        }
    }

    // Super class for WhileStatement's super.field
    static class SuperClass {
        String superField = "superValue";
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target class: strictfp modifier, local inner class feature
strictfp class TargetClass {
    private String targetValue = "targetValue";

    public String getTargetValue() {
        return targetValue;
    }

    // Local inner class (target_feature)
    public void process() {
        class LocalInnerClass {
            void printValue() {
                System.out.println(targetValue);
            }
        }
        LocalInnerClass localObj = new LocalInnerClass();
        localObj.printValue();
    }
}