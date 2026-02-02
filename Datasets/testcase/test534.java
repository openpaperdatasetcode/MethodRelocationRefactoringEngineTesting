import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Parent class for super keywords usage
class ParentClass {
    protected String superField = "SuperValue";
    
    protected String getSuperData() {
        return superField;
    }
}

// Source generic normal class (public modifier, same package as target, type parameter feature)
public class SourceClass<T extends CharSequence> extends ParentClass {
    // First local inner class (source_class feature)
    public void methodWithFirstLocalInner() {
        class FirstLocalInnerClass {
            T processData(T data) {
                return data;
            }
        }
        new FirstLocalInnerClass().processData((T) "First local inner data");
    }

    // Second local inner class (source_class feature)
    public void methodWithSecondLocalInner() {
        class SecondLocalInnerClass {
            int calculateLength(T data) {
                return data.length();
            }
        }
        new SecondLocalInnerClass().calculateLength((T) "Second local inner data");
    }

    // Overload method (overload_exist feature)
    public final Object processTarget(TargetClass targetParam) {
        return targetParam;
    }

    // Varargs method to be moved (final access, Object return, source position)
    @ProcessAnnotation // has_annotation feature
    public final Object processTarget(TargetClass targetParam, T... values) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            return null;
        }

        // IfStatement (public modifier, pos: diff_package_others, target_feature: obj.field, 2)
        public void ifStatementProcessing() {
            int threshold = 2; // target_feature: 2
            // obj.field access (target_feature: obj.field)
            if (targetParam.getStaticNested().getCounter() > threshold) {
                targetParam.getStaticNested().setData("Counter > 2");
            } else {
                targetParam.getStaticNested().setData("Counter ≤ 2");
            }
        }
        ifStatementProcessing();

        // Super keywords usage
        String superData = super.getSuperData(); // super method call
        targetParam.getStaticNested().setData(targetParam.getStaticNested().getData() + "_" + super.superField); // super field access

        // Variable call (target class fields/methods)
        for (T val : values) {
            targetParam.getStaticNested().setCounter(targetParam.getStaticNested().getCounter() + val.length());
        }

        // Return target class instance as Object
        return targetParam;
    }
}

// Target normal class (default modifier, same package as source)
class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNestedClass {
        private String data;
        private int counter = 0;

        // Variable call methods
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }

    private final TargetStaticNestedClass staticNested = new TargetStaticNestedClass();

    public TargetStaticNestedClass getStaticNested() {
        return staticNested;
    }
}