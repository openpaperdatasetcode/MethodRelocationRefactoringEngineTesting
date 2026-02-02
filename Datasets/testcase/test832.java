package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Parent class for method_feature: parent_class
abstract class ParentTargetClass<T> {
    protected int parentField = 3;
    
    protected int parentInstanceMethod(int value) {
        return value + parentField;
    }
}

// Target abstract class: non-sealed modifier, type parameter, local inner class (target_feature)
non-sealed abstract class TargetClass<T> extends ParentTargetClass<T> {
    public T targetField;
    private int thisField = 1; // this.field + 1 for ThrowStatement feature

    public TargetClass(T value) {
        this.targetField = value;
    }

    // Local inner class (target_feature)
    public T getProcessedData() {
        class LocalInnerTarget {
            T process(T data) {
                return data;
            }
        }
        return new LocalInnerTarget().process(targetField);
    }

    public static <T> int staticHelperMethod(int value) {
        return value * 3;
    }
}

// Others class for call_method feature
class OthersClass {
    // default modifier, normal, obj.m1().m2().m3(), pos=if/else, return_type=void
    void othersMethod(TargetClass<String> target) {
        String data = target.getProcessedData();
        if (data != null) {
            // obj.m1().m2().m3() chain
            String result = data.trim().toUpperCase().substring(0, 1);
            System.out.println(result);
        } else {
            System.out.println("default");
        }
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Source abstract class: public modifier, static nested + local inner class (source_feature)
public abstract class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("sourceTargetData");

    // Static nested class (source_feature)
    public static class StaticNestedSource {
        public static String getStaticData() {
            return "staticData";
        }
    }

    // ThrowStatement feature: private modifier, this.field, 1, pos=diff_package_others
    private void throwFeature() {
        int val = 1;
        if (val == this.targetField.thisField) {
            throw new IllegalArgumentException("Invalid value: " + val);
        }
    }

    // Instance method feature: protected modifier, 3, parent_class, instance, ClassName.methodName, pos=if/else, return_type=base type
    protected int instanceHelperMethod(TargetClass<String> target) {
        int result = 0;
        if (target.parentField == 3) {
            // ClassName.methodName(arguments)
            result = TargetClass.staticHelperMethod(3);
        } else {
            // parent_class instance method call
            result = target.parentInstanceMethod(3);
        }
        return result;
    }

    // Instance method: default access, Object return type, method_position=source
    @MethodAnnotation // has_annotation feature
    Object refactorMethod() {
        // Empty statement feature
        ;

        // Variable call feature
        String varCall = targetField.targetField;

        // Access instance method feature
        String processed = targetField.getProcessedData();

        // Execute throwFeature (handled to ensure no_new_exception)
        try {
            throwFeature();
        } catch (IllegalArgumentException e) {
            // No new exception thrown (only catch existing, no new instantiation)
            varCall = e.getMessage();
        }

        // Execute instanceHelperMethod (pos=if/else conditions)
        int helperResult = instanceHelperMethod(targetField);

        // call_method: others_class, default modifier, pos=if/else
        OthersClass others = new OthersClass();
        if (helperResult > 0) {
            others.othersMethod(targetField);
        } else {
            others.othersMethod(new TargetClass<>("defaultData"));
        }

        // Local inner class (source_feature)
        class LocalInnerSource {
            Object getResult() {
                return varCall + "_" + helperResult;
            }
        }

        // No new exception thrown feature
        return new LocalInnerSource().getResult();
    }
}