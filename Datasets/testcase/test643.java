import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {}

protected class SourceClass extends ParentClass {
    private String outerPrivateField = "privateValue";
    
    public static class StaticNestedSourceClass {
        public String getNestedValue() {
            return "nested";
        }
    }

    @Override
    Object sourceMethod(TargetClass<String> targetParam) {
        String varCall = this.outerPrivateField;
        StaticNestedSourceClass nested = new StaticNestedSourceClass();
        
        labeledBlock: {
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(varCall);
                }
            }.run();
            
            @TestAnnotation
            int annotatedVar = 1;
            
            switch (annotatedVar) {
                case 1:
                    String superFieldVal = super.superField;
                    targetParam.staticNestedTargetClass.processValue(superFieldVal);
                    break labeledBlock;
                default:
                    break;
            }
        }
        
        try {
            return targetParam.processTarget(varCall + nested.getNestedValue());
        } catch (Exception e) {
            return null;
        }
    }
}

class ParentClass {
    String superField = "superValue";
}

class TargetClass<T> {
    public static class StaticNestedTargetClass {
        public static void processValue(String value) {}
    }
    
    public Object processTarget(T value) {
        return value;
    }
}