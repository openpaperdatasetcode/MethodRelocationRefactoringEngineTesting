package source;

import target.TargetClass;

public class SourceClass {
    // Source contains the field of the target (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Anonymous inner class 1
    private Runnable anonymous1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class 1");
        }
    };
    
    // Anonymous inner class 2
    private Runnable anonymous2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class 2");
        }
    };
    
    // Method to be refactored
    protected synchronized int methodToMove() {
        // Expression statement
        int result = 0;
        
        // Variable call (uses targetField from source)
        TargetClass.MemberInner inner = targetField.new MemberInner();
        
        // Switch statement containing instance method calls
        switch (inner.getValue()) {
            case 1:
                result = (int) inner.synchronizedMethod1();
                break;
            case 2:
                result = (int) inner.synchronizedMethod2("argument");
                break;
            case 3:
                result = (int) inner.synchronizedMethod3(1, 2, 3);
                break;
            default:
                result = -1;
        }
        
        // Return base type
        return result;
    }
}
    
package target;

public class TargetClass {
    // Member inner class (target_feature)
    public class MemberInner {
        private int value;
        
        // 3 synchronized instance methods with instance references (method_feature)
        public synchronized Object synchronizedMethod1() {
            return 1;
        }
        
        public synchronized Object synchronizedMethod2(String arg) {
            return 2;
        }
        
        public synchronized Object synchronizedMethod3(int a, int b, int c) {
            return 3;
        }
        
        public int getValue() {
            return value;
        }
    }
}
    
