import java.util.List;

abstract class SourceClass<T> {
    private TargetClass<T> targetField = new TargetClass<>();
    
    class MemberInnerSourceClass {
        public void innerMethod() {
            System.out.println(SourceClass.this.targetField);
        }
    }
    
    protected static Object staticHelperMethod(int num, TargetClass<T> target) {
        return target.staticNestedTargetClass.staticField;
    }
    
    final void sourceMethod() {
        String varCall = String.valueOf(TargetClass.StaticNestedTargetClass.staticField);
        // Expression statement
        new MemberInnerSourceClass().innerMethod();
        
        // Local inner class
        class LocalInnerSourceClass {
            void useTargetField() {
                SourceClass.this.targetField.toString();
            }
        }
        
        for (int i = 0; i < 3; i++) {
            // Static method call in for loop (matches static feature spec)
            Object staticResult = SourceClass.staticHelperMethod(i, SourceClass.this.new MemberInnerSourceClass().innerMethod() != null ? targetField : null);
            varCall = staticResult.toString();
        }
        
        try {
            // Depends on static field
            int val = TargetClass.StaticNestedTargetClass.staticField;
            new LocalInnerSourceClass().useTargetField();
        } catch (Exception e) {
            // No new exception thrown
        }
    }
}

/**
 * Generic target class with javadoc and static nested class
 * @param <U> Type parameter for target class
 */
protected class TargetClass<U> {
    public static class StaticNestedTargetClass {
        public static int staticField = 3;
    }
}