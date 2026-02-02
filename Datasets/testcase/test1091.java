package com.example;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Custom annotation for call_method position (attribute values of annotations)
@interface CustomAnnotation {
    String value() default "com.example.OthersClass#outerInstance.new InnerClass().methodName()";
}

// Source generic class (public modifier, same package, anonymous inner class, member inner class)
public class SourceClass<T> {
    // per_condition: source contains the field of the target
    private TargetClass<String> targetField = new TargetClass<>();

    // Member inner class
    class MemberInnerClass {}

    // Anonymous inner class
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in source");
        }
    };

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord<U>(U data) {
        // Method to be refactored (instance, Object return, default access)
        Object targetMethod() {
            // EnhancedForStatement (private modifier, super.field, 1, pos: diff_package_others)
            private void enhancedForBlock() {
                SuperTargetClass superInstance = new SuperTargetClass();
                String superField = superInstance.superField; // super.field
                int num = 1; // target_feature: 1
                List<String> list = new ArrayList<>();
                list.add(superField + num);
                for (String s : list) { // EnhancedForStatement
                    System.out.println(s);
                }
            }
            enhancedForBlock();

            // Static code blocks with instance method call (pos: Static code blocks)
            static {
                // instanceReference::methodName (method_feature)
                Function<String, Object> methodRef = targetField::instanceMethod; // 1, target, instance
                Object instanceResult = methodRef.apply("test");
            }

            // Variable call
            String targetValue = targetField.staticNestedClass.value;

            // Depends on static field
            String staticFieldValue = TargetClass.staticField;
            targetField.setValue(staticFieldValue);

            // Call method (others_class, default modifier, instance, outerInstance.new InnerClass().methodName(), return List<String>)
            @CustomAnnotation("OthersClass#outerInstance.new InnerClass().methodName()") // pos: attribute values of annotations
            OthersClass others = new OthersClass();
            List<String> callResult = others.callMethod(targetField);

            // No new exception
            return callResult;
        }

        // Instance method (public modifier, Object return, 1, target, instance)
        public <V> Object instanceMethod(V value) {
            return value.toString();
        }
    }
}

// Target generic class (default modifier, implements, static nested class)
class TargetClass<T> implements CustomInterface<T> {
    // Static nested class (target_feature)
    static class StaticNestedClass {
        String value = "Static nested class value";
    }

    // Static field for depends_on_static_field
    static String staticField = "Target static field";

    StaticNestedClass staticNestedClass = new StaticNestedClass();
    private T value;

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    // Instance method for instanceReference::methodName
    public Object instanceMethod(String input) {
        return input + "_processed";
    }
}

// Super class for super.field
class SuperTargetClass {
    String superField = "Super class field";
}

// Interface for target_class implements feature
interface CustomInterface<T> {
    void setValue(T value);
}

// Others_class (call_method type)
class OthersClass {
    // Call method (default modifier, instance, outerInstance.new InnerClass().methodName(), return List<String>)
    List<String> callMethod(TargetClass<String> outerInstance) {
        TargetClass<String>.InnerClass inner = outerInstance.new InnerClass();
        return inner.methodName(); // outerInstance.new InnerClass().methodName()
    }
}

// Inner class in target for call_method
class TargetClass<T>.InnerClass {
    List<String> methodName() {
        List<String> list = new ArrayList<>();
        list.add("Inner class method result");
        return list;
    }
}