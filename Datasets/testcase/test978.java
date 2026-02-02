package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Parent class for source_class extends feature and method_feature "parent_class"
class SourceParentClass {
    protected List<String> parentMethod(TargetClass<String>.TargetInner target) {
        List<String> result = new ArrayList<>();
        result.add(target.innerField + "_parent_" + 3);
        return result;
    }
}

// Target class (normal class, public modifier, type parameter feature)
public class TargetClass<T> {
    // Target inner class (target_inner)
    public class TargetInner {
        T innerField; // For per_condition (source contains this field)
        
        public void setInnerField(T value) {
            this.innerField = value;
        }
        
        public T getInnerField() {
            return this.innerField;
        }
    }
}

// Source class (normal class, public modifier, same package, extends + static nested + anonymous inner class)
public class SourceClass extends SourceParentClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static <T> void helperMethod(TargetClass<T>.TargetInner inner) {
            inner.setInnerField((T) ("helper_" + 3));
        }
    }

    // Instance method (default modifier, method_feature:3/parent_class/instance/this.methodName(arguments), pos:functional interfaces, return List<String>)
    <T> List<String> instanceMethod(TargetClass<T>.TargetInner target) {
        // Functional interfaces position
        Function<TargetClass<T>.TargetInner, List<String>> func = t -> this.parentMethod(t); // this.methodName(arguments) + parent_class
        List<String> result = func.apply(target);
        result.add("instance_" + 3); // 3 from method_feature
        return result;
    }

    // Method to be refactored (instance, void return, protected access, source position)
    protected <T> void moveMethod(TargetClass<T>.TargetInner targetParam) {
        // Per_condition: source contains the field of the target (access targetParam.innerField)
        if (targetParam == null) {
            return;
        }

        // Instance method call (pos:functional interfaces)
        List<String> instanceResult = this.instanceMethod(targetParam); // this.methodName(arguments)

        // Variable call
        T varCall = targetParam.getInnerField(); // Access target field (per_condition)
        targetParam.setInnerField((T) (varCall + "_var_modified"));

        // Uses outer this
        SourceClass.this.useOuterThis(targetParam);

        // Used by reflection
        try {
            Method getFieldMethod = TargetClass.TargetInner.class.getMethod("getInnerField");
            T reflectedVal = (T) getFieldMethod.invoke(targetParam);
            targetParam.setInnerField((T) (reflectedVal + "_reflection"));
        } catch (Exception e) {
            // No new exception (handle reflection exceptions internally)
        }

        // Anonymous inner class (source_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                SourceStaticNested.helperMethod(targetParam);
            }
        };
        anonymous.run();

        // No new exception
    }

    // Helper method for uses_outer_this feature
    private <T> void useOuterThis(TargetClass<T>.TargetInner inner) {
        inner.setInnerField((T) (inner.getInnerField() + "_outer_this"));
    }
}