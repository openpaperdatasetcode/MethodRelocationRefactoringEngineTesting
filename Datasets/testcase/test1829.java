package test.pkg;

import java.lang.annotation.*;
import org.junit.Assert;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

abstract class SourceClass<T> {
    private static int STATIC_FIELD = 10;
    protected TargetClass targetField;
    private int value = 5;
    
    public SourceClass() {
        this.targetField = new TargetClass();
    }
    
    @CustomAnnotation
    public final void methodToMove(String... args) {
        // Access instance field
        System.out.println("Value: " + this.value);
        
        // Depend on static field
        int result = STATIC_FIELD * 2;
        
        // Expression statement
        Runnable r = () -> System.out.println(this.value);
        
        // Assert statement accessing super field through target
        Assert.assertTrue("Test failed", targetField.superField > 0);
        
        // Variable call
        String test = "variable";
        System.out.println(test);
    }
    
    // Anonymous inner class
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };
    
    // Member inner class
    class MemberInnerClass {
        private int innerValue;
        
        public MemberInnerClass(int val) {
            this.innerValue = val;
        }
        
        public void display() {
            System.out.println("Inner value: " + innerValue);
        }
    }
}

abstract class TargetClass {
    protected int superField = 1;
    
    // Member inner class
    public class TargetMemberInner {
        private String name;
        
        public TargetMemberInner(String name) {
            this.name = name;
            superMethod();
        }
        
        protected void superMethod() {
            System.out.println("Super method called");
        }
    }
}

class CallerClass {
    protected TargetClass getTarget() {
        return new TargetClass() {};
    }
    
    protected void testMethod() {
        CallerClass caller = new CallerClass();
        TargetClass target = caller.getTarget();
    }
}