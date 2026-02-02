import java.util.ArrayList;

public @interface TargetAnnotation {
    // Anonymous inner class
    Class<?> value() default Runnable.class;
    
    protected String callMethod() {
        try {
            return super.annotationType().getName();
        } catch (Exception e) {
            return "error";
        }
    }
}

@interface SourceAnnotation {
    // Member inner class
    class MemberInner {
        public int targetField;
    }
    
    // Static nested class
    static class StaticNested {
        public String data;
    }
    
    default void methodToMove() {
        // Type declaration statement
        StaticNested var;
        var = new StaticNested();
        
        // Two public assignments
        MemberInner inner = new MemberInner();
        inner.targetField = 10;
        var.data = "test";
        
        // Variable call and access instance field
        String result = var.data;
        if (inner.targetField > 5) {
            TargetAnnotation annotation = SourceAnnotation.class.getAnnotation(TargetAnnotation.class);
            System.out.println(annotation.callMethod());
        }
    }
}
    