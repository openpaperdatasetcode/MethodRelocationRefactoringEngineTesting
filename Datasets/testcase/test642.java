public class TargetClass {
    public static class StaticNestedTargetClass {
        public static int staticField = 10;
        public StaticNestedTargetClass() {}
    }
    
    public int targetInstanceMethod() {
        return StaticNestedTargetClass.staticField;
    }
}

protected class SourceClass {
    private TargetClass targetField;
    
    class MemberInnerClass {
        public int getValue() {
            return TargetClass.StaticNestedTargetClass.staticField;
        }
    }
    
    public int sourceMethod(Object... varargs) {
        super();
        targetField = new TargetClass();
        if (targetField == null) {
            throw new NullPointerException();
        }
        
        TargetClass.StaticNestedTargetClass rawNested = new TargetClass.StaticNestedTargetClass();
        int varCall = rawNested.staticField;
        
        class LocalInnerClass {
            public int useStaticField() {
                return TargetClass.StaticNestedTargetClass.staticField;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        
        try {
            int val = targetField.targetInstanceMethod() + localInner.useStaticField() + new MemberInnerClass().getValue();
            return val;
        } catch (Exception e) {
            return 0;
        }
    }
}