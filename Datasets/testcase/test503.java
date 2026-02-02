// Source package (different from target)
package com.refactor.source;

import com.refactor.target.TargetGenericClass;
import java.util.ArrayList;
import java.util.List;

// Functional interface for overriding feature
interface GenericInterface<T> {
    List<String> process(T targetParam);
}

// Source final generic class (final modifier, different package, two static nested classes)
public final class SourceGenericClass<T extends CharSequence> implements GenericInterface<TargetGenericClass<T>> {
    // First static nested class (source_class feature)
    public static class SourceStaticNested1<U> {
        U nestedValue;
        public SourceStaticNested1(U value) { this.nestedValue = value; }
        public U getValue() { return nestedValue; }
    }

    // Second static nested class (source_class feature - duplicate as specified)
    public static class SourceStaticNested2<U> {
        U nestedValue;
        public SourceStaticNested2(U value) { this.nestedValue = value; }
        public U getValue() { return nestedValue; }
    }

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Overriding method (private access, returns List<String>, target param - per_condition)
        @Override
        private List<String> process(TargetGenericClass<T> targetParam) {
            List<String> result = new ArrayList<>();

            // NullPointerException feature
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }

            // Variable call feature
            SourceStaticNested1<Integer> nested1 = new SourceStaticNested1<>(3); // Number 3
            SourceStaticNested2<String> nested2 = new SourceStaticNested2<>("nested_3"); // Number 3
            String varCall = nested1.getValue() + "_" + nested2.getValue();
            result.add(varCall);

            // ArrayInitializer with numbers=3, public modifier feature
            public String[] arr = new String[]{"3", "three", String.valueOf(3)}; // ArrayInitializer exp (value 3)
            for (String s : arr) {
                result.add(s);
            }

            // Access instance method feature
            String instanceResult = instanceMethod();
            result.add(instanceResult);

            // Depends_on_inner_class feature (uses inner class method)
            result.add(processInner(targetParam));

            // No_new_exception feature (no explicit throw new Exception beyond NPE)
            return result;
        }

        // Instance method for access_instance_method feature
        private String instanceMethod() {
            return "instance_method_3"; // Number 3
        }

        // Inner class method for depends_on_inner_class feature
        private String processInner(TargetGenericClass<T> target) {
            return target.getData() + "_processed_3"; // Number 3
        }
    }
}

// Target package (different from source)
package com.refactor.target;

import com.refactor.source.SourceGenericClass;
import java.util.List;

// Target protected generic class (protected modifier, anonymous inner class target_feature)
protected class TargetGenericClass<T> {
    private T data;

    public TargetGenericClass(T data) {
        this.data = data;
        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + data);
            }
        };
        targetAnonymous.run();
    }

    public T getData() {
        return data;
    }

    // Method chain for obj.m1().m2().m3() feature
    public TargetGenericClass<T> m1() { return this; }
    public TargetGenericClass<T> m2() { return this; }
    public String m3() { return data.toString() + "_3"; } // Number 3
}

// Sub class for call_method (sub_class type, default modifier, instance + method chain, ternary pos)
package com.refactor.sub;

import com.refactor.source.SourceGenericClass;
import com.refactor.target.TargetGenericClass;
import java.util.List;

class SubClass extends SourceGenericClass<String> {
    // Call method (default modifier, sub_class type, ternary operators pos, returns TargetClass Type)
    TargetGenericClass<String> callMethod() {
        SourceInnerClass inner = new SourceInnerClass();
        TargetGenericClass<String> target = new TargetGenericClass<>("test_3");

        // Ternary operators position + instance feature + obj.m1().m2().m3() target_feature
        TargetGenericClass<String> result = (inner.process(target) != null) 
            ? target.m1().m2().m3() != null ? target : new TargetGenericClass<>("fallback_3") 
            : new TargetGenericClass<>("default_3");

        return result;
    }
}