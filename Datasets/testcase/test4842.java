package test.refactoring;

sealed class SourceClass<T extends Number> permits SubSource {
    static class StaticNestedClass {
        int nestedField;
        
        void nestedMethod() {}
    }
    
    class MemberInnerClass {
        String innerData;
    }
    
    default void methodToMove(TargetClass<String> target) {
        private StaticNestedClass var = new StaticNestedClass();
        var.nestedField = 1;
        
        overloadedMethod(1);
        overloadedMethod("string");
        overloadedMethod(target);
        
        new MemberInnerClass().innerData = "test";
        target.new AnonymousInnerClass() {
            void doSomething() {
                System.out.println("Anonymous class");
            }
        }.doSomething();
    }
    
    default TargetClass<T> overloadedMethod(int param) {
        return new TargetClass<>(param.toString());
    }
    
    default TargetClass<T> overloadedMethod(String param) {
        return new TargetClass<>(param);
    }
    
    default TargetClass<T> overloadedMethod(TargetClass<String> param) {
        return new TargetClass<>(param.data);
    }
}

non-sealed class SubSource extends SourceClass<Integer> {}

class TargetClass<U> {
    U data;
    
    TargetClass(U data) {
        this.data = data;
    }
    
    Object anonymousHolder = new Object() {
        void doSomething() {}
    };
}
    