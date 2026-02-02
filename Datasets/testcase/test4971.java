package source;

import java.util.List;
import target.TargetAnnotation;

public @interface SourceAnnotation {
    // Member inner class
    class MemberInner {
        protected int value;
        
        // Constructor to be moved
        protected MemberInner(TargetAnnotation target, int param) {
            super(); // Super constructor invocation
            
            // Type declaration statement
            String typeDecl;
            
            // Variable call
            this.value = param;
            
            // Access outer protected (simulated)
            int outerVal = 10;
            
            // SwitchStatement with ClassName.field
            switch (target.getCode()) {
                case TargetAnnotation.STATIC_FIELD:
                    typeDecl = "case1";
                    break;
                default:
                    typeDecl = "default";
            }
            
            // 3 public MethodInvocations
            target.publicMethod1();
            target.publicMethod2();
            target.publicMethod3();
            
            // Lambda expression with instance features
            Runnable r = () -> {
                List<String> list = target.defaultMethod(); // default method
                String result = new SubClass().subClassMethod(); // sub_class
                super.toString(); // super.methodName()
            };
        }
    }
    
    // Method with local inner class
    default void sampleMethod() {
        // Local inner class
        class LocalInner {
            void doSomething() {}
        }
    }
}

class SubClass extends SuperClass {
    String subClassMethod() {
        return "subclass";
    }
}

class SuperClass {
    // Super class for demonstration
}
 
package target;

import java.util.List;

public @interface TargetAnnotation {
    int STATIC_FIELD = 1; // ClassName.field for switch statement
    
    // Static nested class
    static class NestedStaticClass {
        // Static nested class implementation
    }
    
    // Public methods for MethodInvocation (3 required)
    public void publicMethod1();
    public void publicMethod2();
    public void publicMethod3();
    
    // Default instance method
    default List<String> defaultMethod() {
        return List.of("default");
    }
    
    // Method to get code for switch statement
    int getCode();
}
      