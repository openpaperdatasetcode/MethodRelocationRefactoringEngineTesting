package refactoring.test;

public class SourceClass extends ParentClass {
    private TargetClass target = new TargetClass<String>() {
        @Override
        public void abstractMethod() {}
    };
    
    class FirstInner {
        protected String innerField = "inner";
    }
    
    class SecondInner {
        public FirstInner getFirst() {
            return new FirstInner();
        }
    }
    
    @FunctionalInterface
    interface FunctionalIF {
        void execute();
    }
    
    @Override
    @Deprecated
    strictfp TargetClass<String> instanceMethod() {
        type declaration statement;
        volatile int var = 1;
        
        if (target == null) {
            throw new NullPointerException();
        }
        
        new TargetClass<String>(super.protectedField) {};
        
        FunctionalIF func = () -> {
            new FirstInner();
            new SecondInner().getFirst();
            target.instanceMethod("arg");
        };
        
        func.execute();
        return target;
    }
    
    strictfp TargetClass<String> instanceMethod(int param) {
        return target;
    }
}

abstract class TargetClass<T> {
    public T targetField;
    
    public TargetClass(String value) {
        this.targetField = (T) value;
    }
    
    public abstract void abstractMethod();
    
    public void instanceMethod(String arg) {}
}

class ParentClass {
    protected String protectedField = "parent";
}
