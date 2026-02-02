// Source package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 1; // target_feature: 1
}

// Same package target helper for ForStatement pos
package com.refactoring.target;
import com.refactoring.source.SourceClass;

class SamePackageTargetHelper {
    // ForStatement feature: private modifier, obj.field, 1, pos=same_package_target
    private static <T> void forStatementLogic(TargetClass<T> target) {
        // pos: same_package_target
        for (int i = 0; i < 1; i++) { // target_feature: 1
            target.targetField = (T) ("for_processed_" + i); // obj.field feature
        }
    }
}

// Back to source package
package com.refactoring.source;
import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetInterface;
import com.refactoring.target.SamePackageTargetHelper;

// Source class: strictfp normal class, different package from target, two static nested classes
strictfp class SourceClass {
    // First static nested class (source feature)
    static class FirstStaticNested {
        public static <T> T processValue(T val) {
            return (T) (val.toString() + "_first_static_1"); // target_feature: 1
        }
    }

    // Second static nested class (source feature)
    static class SecondStaticNested {
        public static <T> T adjustValue(T val) {
            return (T) (val.toString() + "_second_static_1"); // target_feature: 1
        }
    }

    // Abstract inner class for abstract method feature
    abstract static class AbstractInnerClass {
        // Abstract method feature: synchronized modifier, 1, source, abstract, new ClassName().method(), pos=ctor param list, return List<String>
        @ProcessAnnotation(1) // has_annotation
        public abstract synchronized List<String> abstractMethod(TargetClass<String> target); // method_feature: abstract, synchronized
    }

    // Concrete implementation of abstract inner class
    static class ConcreteInnerClass extends AbstractInnerClass {
        @Override
        public synchronized List<String> abstractMethod(TargetClass<String> target) {
            List<String> result = new ArrayList<>();
            // pos: the parameter list of constructors (new ClassName().method() as param)
            result.add(new FirstStaticNested().processValue(target.getTargetField())); // new ClassName().method(), method_feature: source
            result.add(target.getTargetField() + "_abstract_1"); // method_feature: 1
            return result;
        }
    }

    // Method to refactor: instance, TargetClass return, protected access, in source class
    @ProcessAnnotation(1) // has_annotation feature
    protected <T> TargetClass<T> methodToRefactor(TargetClass<T> targetParam) {
        // ForStatement feature call (same_package_target)
        SamePackageTargetHelper.forStatementLogic(targetParam);

        // Variable call (target class fields/methods)
        T targetValue = targetParam.getTargetField();
        
        // Expression statement
        targetValue = FirstStaticNested.processValue(targetValue); // expression statement
        targetParam.setTargetField(targetValue); // expression statement
        targetValue = SecondStaticNested.adjustValue(targetValue); // expression statement

        // Used_by_reflection feature
        try {
            // Access target field via reflection
            Field field = TargetClass.class.getDeclaredField("targetField");
            field.setAccessible(true);
            field.set(targetParam, targetValue);

            // Invoke target method via reflection
            Method method = TargetClass.class.getDeclaredMethod("setTargetField", Object.class);
            method.invoke(targetParam, SecondStaticNested.adjustValue(targetValue));
        } catch (Exception e) {
            // No_new_exception feature
            targetParam.setTargetField((T) ("reflection_error_" + e.getMessage() + "_1")); // target_feature: 1
        }

        // Abstract method feature usage (pos: constructor parameter list)
        AbstractInnerClass abstractInstance = new ConcreteInnerClass();
        List<String> abstractResult = abstractInstance.abstractMethod((TargetClass<String>) targetParam); // new ClassName().method()
        targetParam.setTargetField((T) abstractResult.get(0)); // expression statement

        // No_new_exception additional handling
        try {
            Integer.parseInt(targetParam.getTargetField().toString());
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetParam.setTargetField((T) ("formatted_" + targetParam.getTargetField()));
        }

        return targetParam; // Return TargetClass Type
    }
}

// Target package
package com.refactoring.target;

// Interface for target class implements feature
interface TargetInterface<T> {
    T getTargetField();
    void setTargetField(T value);
}

// Target class: public normal class, implements interface, anonymous inner class feature
public class TargetClass<T> implements TargetInterface<T> {
    // obj.field feature (instance field)
    T targetField;

    public TargetClass() {
        // Anonymous inner class (target_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner: " + targetField);
            }
        };
        anonymous.run();
    }

    @Override // implements feature
    public T getTargetField() {
        return targetField;
    }

    @Override // implements feature
    public void setTargetField(T value) {
        this.targetField = value;
    }
}