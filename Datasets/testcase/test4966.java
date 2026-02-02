package test;

import java.util.List;

public class ParentClass {
    // Super method for invocation
    protected List<String> methodName(String arg) {
        return List.of(arg);
    }
    
    // Field used in super constructor invocation
    protected int parentField;
    
    public ParentClass(int value) {
        this.parentField = value;
    }
}
    package test;

import java.util.List;

protected class SourceClass extends ParentClass {
    // Field of target class (per_condition)
    private TargetClass target = new TargetClass();
    
    // Constructor with super constructor invocation
    public SourceClass() {
        super(42);
    }
    
    // Member inner class containing overloading method (method_position: source_inner)
    public class InnerSource {
        // Overloading method to be refactored
        public int overloadedMethod() {
            return 1;
        }
        
        // Overloading method (type: overloading)
        public int overloadedMethod(String param) {
            // Variable call
            String value = target.targetField;
            
            // Continue statement
            for (int i = 0; i < 5; i++) {
                if (i == 2) continue;
            }
            
            // Private overloading method call (method_feature)
            List<String> result = privateOverloadMethod(value);
            
            // Property assignment with method call
            target.targetField = result.get(0);
            
            return result.size();
        }
        
        // Private overloading method (type: overloading)
        private List<String> privateOverloadMethod(String input) {
            // Super method invocation
            return super.methodName(input);
        }
    }
    
    // Local inner class (source_feature)
    public void createLocalClass() {
        class LocalSource {
            void doSomething() {
                System.out.println("Local inner class");
            }
        }
    }
    
    // Anonymous inner class (source_feature)
    public Runnable createAnonymousClass() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
    }
}
    package test;

import java.util.List;

class TargetClass {
    // Field contained in source (per_condition)
    public String targetField = "target";
    
    // Local inner class (target_feature)
    public void methodWithLocalClass() {
        class LocalInner {
            private String process(String input) {
                return input.toUpperCase();
            }
        }
    }
    
    // Method to demonstrate override violation
    public int overloadedMethod() {
        return 0;
    }
}
    
