import java.lang.reflect.Method;

class SourceClass permits SubSourceClass {
    private TargetClass targetField = new TargetClass() {};
    
    public static class StaticNestedSourceClass {}
    
    class MemberInnerSourceClass {
        private void sourceInnerRecMethod() {
            String varCall = targetField.toString();
            
            try {
                Method method = TargetClass.StaticNestedTargetClass.class.getMethod("innerStaticMethod");
                method.invoke(null);
            } catch (Exception e) {
                // No new exception thrown
            }
            
            if (targetField != null) {
                sourceInnerRecMethod(); // Recursive call (source_inner_rec)
            }
        }
        
        public void triggerInnerMethod() {
            sourceInnerRecMethod();
        }
    }
}

class SubSourceClass extends SourceClass {}

abstract class TargetClass {
    public static class StaticNestedTargetClass {
        public static void innerStaticMethod() {}
    }
}