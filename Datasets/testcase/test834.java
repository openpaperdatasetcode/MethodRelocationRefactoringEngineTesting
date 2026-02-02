package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.ArrayList;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for call_method (parent_class type)
strictfp class TargetParentClass<T> {
    protected List<String> parentMethod(T... args) {
        List<String> list = new ArrayList<>();
        for (T arg : args) {
            list.add(arg.toString());
        }
        return list;
    }
}

/**
 * Target generic class Javadoc (target_feature: javadoc)
 * Sealed modifier, type parameter + member inner class (target_feature)
 * @param <T> Generic type parameter
 */
sealed class TargetClass<T> extends TargetParentClass<T> permits TargetSubClass<T> {
    public T instanceField; // For access_instance_field feature

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public void processVarargs(T... args) {
            for (int i = 0; i < 3; i++) { // method_feature: 3
                System.out.println(args[i]);
            }
        }
    }

    public TargetClass(T instanceField) {
        this.instanceField = instanceField;
    }
}

// Permitted subclass for sealed TargetClass
class TargetSubClass<T> extends TargetClass<T> {
    public TargetSubClass(T instanceField) {
        super(instanceField);
    }
}

// Source generic class: strictfp modifier, empty features, same package as target
strictfp class SourceClass<T> {
    // Source contains target field (per_condition)
    private TargetClass<T> targetField = new TargetClass<>((T) "sourceTargetData");

    // Inner recursive class (source_inner_rec - method_position)
    class SourceInnerRecursive {
        // Varargs method feature: synchronized modifier, 3, inner_class, varargs, new ClassName().method(), pos=array initialization, return_type=void
        private synchronized void varargsHelperMethod(T... args) {
            // Array initialization (pos)
            T[] arr = (T[]) new Object[3]; // method_feature: 3
            System.arraycopy(args, 0, arr, 0, Math.min(args.length, 3));
            
            // new ClassName().method() (inner_class feature)
            new TargetClass<T>((T) "helper").new TargetInnerClass().processVarargs(arr);
        }

        // Instance method: default access, Object return type
        @RefactorAnnotation // has_annotation feature
        Object refactorMethod() {
            // Variable call feature
            T varCall = targetField.instanceField;

            // Access instance field feature
            targetField.instanceField = (T) (varCall + "_updated");

            // Execute varargs helper method
            varargsHelperMethod((T) "arg1", (T) "arg2", (T) "arg3");

            // call_method: parent_class type, strictfp modifier, instance + super.methodName(), pos=if/else, return_type=List<String>
            List<String> callResult;
            if (varCall != null) { // if/else conditions (pos)
                // Instance feature + super.methodName(arguments)
                callResult = targetField.parentMethod((T) "call1", (T) "call2", (T) "call3");
            } else {
                callResult = new TargetParentClass<T>().parentMethod((T) "default");
            }

            // No new exception thrown feature
            return varCall + "_" + callResult;
        }
    }
}