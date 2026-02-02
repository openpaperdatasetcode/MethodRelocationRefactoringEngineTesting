package com.sourcepkg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

strictfp class SourceClass<T extends Number & Comparable<T>> {
    // Field referencing target class for per_condition
    private final com.targetpkg.TargetClass.TargetStaticNested targetField = new com.targetpkg.TargetClass.TargetStaticNested();
    
    // Static nested class as required feature
    public static class SourceStaticNested {
        private int value;
    }
    
    // Member inner class as required feature
    public class SourceMemberInner {
        public String getInnerValue() {
            return "inner";
        }
    }
    
    // Target method to be moved - meets all specified features
    private List<String> methodToMove(T param) {
        // Empty statement
        ;
        
        // Variable call
        String var = targetField.toString();
        List<String> result = new ArrayList<>();
        
        // NullPointerException possibility
        if (param == null) {
            throw new NullPointerException("Param cannot be null");
        }
        
        // With bounds (uses type parameter bounds)
        if (param.compareTo((T) Integer.valueOf(10)) > 0) {
            result.add("greater");
        }
        
        // Requires try-catch block
        try {
            // Used by reflection
            Method method = com.targetpkg.OtherClass.class.getDeclaredMethod("privateConstructorCall");
            method.setAccessible(true);
            method.invoke(null);
            
            // ConstructorInvocation (private, diff_package_others, super.field access)
            new com.targetpkg.OtherClass.PrivateConstructorClass();
        } catch (Exception e) {
            result.add(e.getMessage());
        }
        
        return result;
    }
}

package com.targetpkg;

final class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        // Nested static class for target_inner_rec
        public static class TargetInnerRec {
            private String data;
            
            public TargetInnerRec(String data) {
                this.data = data;
            }
        }
    }
}

package com.targetpkg;

class OtherClass {
    // Super class with field for ConstructorInvocation target_feature
    static class SuperClass {
        protected int field = 1; // super.field with value 1
    }
    
    // Private constructor class (diff_package_others)
    private static class PrivateConstructorClass extends SuperClass {
        // Private constructor with super.field access
        private PrivateConstructorClass() {
            super.field = 1;
        }
    }
    
    // Method for reflection access
    private static void privateConstructorCall() {
        new PrivateConstructorClass();
    }
}