// Diff package others class for ConstructorInvocation pos
package com.refactoring.others;
import com.refactoring.movemethod.TargetClass;

public class DiffPackageHelper {
    // ConstructorInvocation feature: static modifier, obj.field, 1, pos=diff_package_others
    public static <T> TargetClass<T> createTargetInstance() {
        TargetClass<T> target = new TargetClass<>("diff_package_1"); // target_feature: 1
        target.innerField = 1; // obj.field feature, target_feature: 1
        TargetClass.TargetStaticNested<T> nested = new TargetClass.TargetStaticNested<>();
        nested.nestedField = (T) ("nested_1"); // target_feature: 1
        target.setNested(nested);
        return target;
    }
}

// Back to source package
package com.refactoring.movemethod;
import com.refactoring.others.DiffPackageHelper;

// Sealed interface with permits for source class feature
sealed interface SourceInterface permits SourceClass, SourceClass.FirstStaticNested, SourceClass.SecondStaticNested {}

// Source class: default modifier, same package as target, permits, two static nested classes
final class SourceClass implements SourceInterface {
    // First static nested class (source feature)
    static final class FirstStaticNested implements SourceInterface {
        public static <T> T processValue(T val) {
            return (T) (val.toString() + "_first_static_1"); // target_feature: 1
        }
    }

    // Second static nested class (source feature)
    static final class SecondStaticNested implements SourceInterface {
        public static <T> T adjustValue(T val) {
            return (T) (val.toString() + "_second_static_1"); // target_feature: 1
        }
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Method to refactor: instance, Object return, public access, in source_inner
        public <T> Object methodToRefactor(TargetClass<T> targetParam) {
            // NullPointerException feature
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }

            // ConstructorInvocation feature call (diff_package_others)
            TargetClass<T> ctorTarget = DiffPackageHelper.createTargetInstance();

            // Variable call (target class and static nested class)
            T targetValue = targetParam.value();
            T nestedValue = targetParam.getNested().nestedField;
            targetParam.innerField = 1; // obj.field feature, target_feature: 1

            // Use static nested classes (source feature)
            T processed1 = FirstStaticNested.processValue(targetValue);
            T processed2 = SecondStaticNested.adjustValue(nestedValue);

            // No_new_exception feature
            try {
                Integer.parseInt(processed1.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                processed1 = (T) ("formatted_" + processed1);
            }

            // Variable call for ctorTarget (ConstructorInvocation result)
            Object result = ctorTarget.value() + "_" + processed1 + "_" + processed2;

            return result;
        }
    }
}

// Target class: protected, type parameter, static nested class feature
protected class TargetClass<T> {
    // Type parameter (target_feature)
    private final T value;
    int innerField; // obj.field feature

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        U nestedField; // obj.field feature
    }

    private TargetStaticNested<T> nested;

    public TargetClass(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public TargetStaticNested<T> getNested() {
        return nested;
    }

    public void setNested(TargetStaticNested<T> nested) {
        this.nested = nested;
    }
}