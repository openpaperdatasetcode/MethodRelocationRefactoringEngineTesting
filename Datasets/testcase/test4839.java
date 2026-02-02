package test.refactoring;

import java.util.Arrays;

protected class SourceClass<T> {
    static class StaticNested {
        int field1;
    }
    
    class MemberInner {
        String field2;
        
        int instanceMethod() {
            return field2.length();
        }
    }
    
    private void instanceMethod(TargetClass.TargetInner param) {
        private class LocalType {
            int value = TargetClass.StaticNested.targetStaticField;
            String text = param.innerField;
        }
        
        LocalType local = new LocalType();
        MemberInner inner = new MemberInner();
        
        int[] array = {
            inner.instanceMethod(),
            this.instanceHelper(1),
            this.instanceHelper("test")
        };
        
        System.out.println(Arrays.toString(array));
        param.doAction();
    }
    
    default int instanceHelper(Object arg) {
        return arg.toString().length();
    }
}

public class TargetClass {
    static class StaticNested {
        static int targetStaticField = 42;
    }
    
    class TargetInner {
        String innerField;
        
        void doAction() {
            System.out.println(innerField);
        }
    }
}
    