package source;

import target.TargetClass;

protected class SourceClass {
    // Member inner classes (source features)
    protected class FirstInnerClass {
        // Class body
    }
    
    protected class SecondInnerClass {
        // Abstract method required by features
        public abstract Object abstractMethod();
    }
    
    // Method to be refactored
    protected void methodToRefactor(TargetClass targetParam) {
        // Type declaration statement with bounds
        Class<? extends Number> numberClass;
        
        // Variable call (uses target parameter)
        targetParam.doSomething();
        
        // Depends on static field of target
        int value = TargetClass.STATIC_FIELD_1;
        
        // Return statements with ClassName.field (3 required)
        if (value > 0) {
            return;
        }
        
        if (TargetClass.STATIC_FIELD_2 == 0) {
            return;
        }
        
        if (TargetClass.STATIC_FIELD_3 < 0) {
            return;
        }
        
        // Do-while loop containing abstract method call
        do {
            SecondInnerClass inner = new SecondInnerClass();
            Object result = inner.abstractMethod(); // this.methodName(arguments)
        } while (targetParam.isActive());
    }
}
package target;

import java.util.List;

private class TargetClass {
    // Static fields used by source class (3 required)
    static int STATIC_FIELD_1 = 10;
    static int STATIC_FIELD_2 = 20;
    static int STATIC_FIELD_3 = 30;
    
    // Anonymous inner class (target feature)
    private Runnable createRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class in TargetClass");
            }
        };
    }
    
    // Methods used by source class
    void doSomething() {
        // Implementation
    }
    
    boolean isActive() {
        return true;
    }
}
