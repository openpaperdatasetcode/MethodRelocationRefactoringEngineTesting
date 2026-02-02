package com.example;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

// Super class for super.field feature
class SuperTargetClass {
    protected String superField = "superTargetField";
}

// Source normal class (public modifier, same package, type parameter, local inner class, static nested class)
public class SourceClass<T extends Number> {
    // Private outer field for access_outer_private
    private String outerPrivateField = "sourcePrivateField";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass<U> {}

    // Abstract method needs to be in abstract class, adjust to abstract inner class to meet requirements
    public abstract static class AbstractInnerClass {
        /**
         * Method Javadoc: This is the refactored abstract method
         * @param param Target class parameter
         * @return Base type (int) result
         * @throws NullPointerException if param is null
         */
        // Method to be refactored (abstract, base type return, public access, position: source)
        public abstract int targetMethod(TargetClass param); // per_condition: target parameter
    }

    // Concrete implementation to host method logic
    public static class ConcreteInnerClass extends AbstractInnerClass {
        @Override
        public int targetMethod(TargetClass param) {
            // NullPointerException
            if (param == null) {
                throw new NullPointerException("TargetClass parameter is null");
            }

            // Local inner class (source_class feature) - hosts EnhancedForStatement
            class LocalInnerClass {
                // EnhancedForStatement (private modifier, super.field, 3, pos: inner class)
                private void enhancedForBlock() {
                    String superField = param.superField; // super.field
                    List<String> list = Arrays.asList(superField, "3", "items");
                    int num = 3; // target_feature: 3
                    // EnhancedForStatement
                    for (String s : list) {
                        if (s.equals(String.valueOf(num))) break;
                    }
                }
            }
            new LocalInnerClass().enhancedForBlock();

            // Empty statement
            ;

            // Expression statement
            String targetValue = param.getValue();
            targetValue = targetValue + "_processed";

            // Variable call
            param.setValue(targetValue);

            // Access outer private field (via outer class instance)
            SourceClass<Integer> outerInstance = new SourceClass<>();
            String privateValue = outerInstance.outerPrivateField; // access_outer_private

            // Used by reflection
            try {
                Method method = AbstractInnerClass.class.getDeclaredMethod("targetMethod", TargetClass.class);
                method.setAccessible(true);
                method.invoke(this, param);
            } catch (Exception e) {
                // Throw statement
                throw new RuntimeException("Reflection call failed", e);
            }

            // No new exception (no custom exception instantiation)
            return targetValue.length() + privateValue.length();
        }
    }
}

// Target normal class (protected modifier, static nested class target_feature)
protected class TargetClass extends SuperTargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {}

    private String value = "targetValue";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}