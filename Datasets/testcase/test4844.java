package test.refactoring;

protected record SourceClass(int targetField, String name) {
    static class NestedStaticClass {
        private int value;
        
        NestedStaticClass(int value) {
            this.value = value;
        }
    }
    
    public Object process() {
        class LocalInnerClass {
            void doSomething() {
                System.out.println("Local inner class in SourceClass");
            }
        }
        
        LocalInnerClass local = new LocalInnerClass();
        local.doSomething();
        return sourceMethod(1, 2, 3);
    }
    
    private static final String STATIC_FIELD = "static_value";
    
    private Object sourceMethod(Object... args) throws IllegalArgumentException {
        if (args == null) {
            throw new IllegalArgumentException("Args cannot be null");
        }
        int count = 0;
        for (Object arg : args) {
            count++;
            if (count > targetField) {
                break;
            }
        }
        Object var = new NestedStaticClass(5).value;
        return STATIC_FIELD + var;
    }
}

record TargetClass(String data) {
    /**
     * Javadoc for TargetClass
     */
    public TargetClass {
        class TargetInnerRec {
            void helper() {
                System.out.println("Inner class in TargetClass");
            }
        }
    }
}
