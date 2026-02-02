package com.refactoring.movemethod;

// Source class: generic class, private modifier, same package, permits feature
private sealed class SourceClass<T extends Number> permits SourcePermittedClass {
    private String sourceField = "sourceValue";

    /**
     * Method javadoc feature
     * This instance method processes target class parameter and returns Object
     * @param targetParam the target inner recursive class parameter
     * @return processed Object result
     */
    protected Object moveableInstanceMethod(TargetClass<T>.InnerRecursiveTarget targetParam) { // per_condition: contains target parameter
        // Variable call feature
        String localVar = sourceField;
        localVar = targetParam.innerField;

        // Access_instance_method feature
        targetParam.innerMethod();
        SourceClass<T> sourceInstance = new SourcePermittedClass<>();
        sourceInstance.instanceHelperMethod();

        // Constructor feature: public modifier, method_feature [3, inner_class, constructor, method ref], pos=constructor parameter list, return Object
        class ConstructorParamClass {
            public ConstructorParamClass(Object param) {}
        }
        ConstructorParamClass constructorInstance = new ConstructorParamClass(
            targetParam::innerMethod // instanceReference::methodName (method_feature)
        );

        // Constructor invocation feature
        TargetClass<T>.InnerRecursiveTarget newTargetInstance = new TargetClass<T>().new InnerRecursiveTarget();
        newTargetInstance.innerField = "3"; // method_feature "3"

        // Expression statement feature
        localVar = localVar.concat(newTargetInstance.innerField);
        sourceField = localVar;

        // Super keywords feature
        class SuperClassUsage extends SourceClass<T> {
            void useSuper() {
                super.sourceField = "superAssignedValue"; // super keywords
            }
        }
        new SuperClassUsage().useSuper();

        // No_new_exception feature (no custom exceptions instantiated/thrown)
        return constructorInstance;
    }

    // Instance helper method for access_instance_method feature
    private void instanceHelperMethod() {}
}

// Permitted class for source_class permits feature
final class SourcePermittedClass<T extends Number> extends SourceClass<T> {}

// Target class: generic class, abstract modifier, target_feature: static nested class
abstract class TargetClass<U> {
    // Static nested class (target_feature)
    static class StaticNestedTarget<V> {
        V nestedField;
    }

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRecValue";

        // Instance method for access_instance_method & method reference features
        Object innerMethod() { // return_type Object (constructor feature)
            // Recursive inner class usage
            new InnerRecursiveTarget().innerMethod();
            return innerField;
        }
    }
}