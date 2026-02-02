package test.refactoring;

interface ParentInterface {
    default Object parentMethod() {
        return "parent";
    }
}

@interface SourceInterface<T> extends ParentInterface {
    static int staticField = 0;
    TargetInterface targetField();

    static class StaticNestedClass {
        int nestedValue;
    }

    default void recursiveMethod(int n) {
        class LocalInnerClass {
            void print() {
                System.out.println("Local inner class");
            }
        }
        
        new LocalInnerClass().print();
        Object var = targetField();
        
        try {
            if (n <= 0) {
                return;
            }
            
            // Reflection usage
            Class<?> cls = Class.forName("test.refactoring.SourceInterface");
            java.lang.reflect.Field field = cls.getField("staticField");
            
            // Lambda with method features
            Runnable r = () -> {
                Object result = parentMethod();
                System.out.println(result);
                System.out.println(staticField);
                System.out.println(var);
            };
            r.run();
            
            recursiveMethod(n - 1); // Recursion
            super.parentMethod();
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

@interface TargetInterface implements java.io.Serializable {
    class MemberInnerClass {
        String data;
    }
}
    