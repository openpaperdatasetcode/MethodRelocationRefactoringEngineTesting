package com.source;

import com.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

public abstract class SourceClass {
    // First member inner class
    class InnerClass1 {
        // Second member inner class (source_inner_rec)
        class InnerClass2 {
            // Varargs method to be refactored (private, returns TargetClass, in source_inner_rec)
            private TargetClass moveThisMethod(TargetClass... targets) {
                // VariableDeclarationStatement (public modifier, accesses ClassName.field)
                public int fieldAccess = TargetClass.NESTED_STATIC_FIELD;
                
                // Array initialization with instance feature (returns List<String>, obj.m1().m2().m3())
                List<String>[] array = new List[] {
                    new ArrayList<>() {{
                        add(targets[0].m1().m2().m3());
                    }}
                };
                
                // Labeled statement
                label: {
                    if (targets.length > 0) break label;
                }
                
                // Type declaration statement
                class LocalType {}
                LocalType localInstance = new LocalType();
                
                // Variable call
                int localVar = 0;
                localVar = fieldAccess + localVar;
                
                // No new exception thrown
                
                return targets.length > 0 ? targets[0] : new TargetClass();
            }
        }
    }
}

package com.target;

import java.util.List;

public class TargetClass {
    // Static nested class (target_inner_rec)
    public static class NestedStaticClass {
        // Protected static call method (Object return type, superTypeReference in constructor param)
        protected static Object callMethod(SuperType.superMethod("arg")) {
            return new Object();
        }
        
        public List<String> m1() {
            return new java.util.ArrayList<>();
        }
        
        public List<String> m2() {
            return new java.util.ArrayList<>();
        }
        
        public String m3() {
            return "test";
        }
    }
    
    public static int NESTED_STATIC_FIELD = 1;
    
    public TargetClass() {}
    
    public TargetClass(Object param) {}
}

class SuperType {
    public static Object superMethod(String arg) {
        return new Object();
    }
}