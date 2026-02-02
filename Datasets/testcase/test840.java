// Target class package (different from source)
package com.target;

import java.util.List;
import java.util.ArrayList;

// Target class: default modifier, type parameter + member inner class (target_feature)
class TargetClass<T> {
    protected int superField = 3; // super.field + 3 for DoStatement
    public T value;

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public List<String> getInnerData() {
            return new ArrayList<>(List.of("inner1", "inner2", "inner3")); // 3 for method_feature
        }
    }

    public TargetClass(T value) {
        this.value = value;
    }

    // Normal method for call_method: superTypeReference.methodName(arguments)
    public List<String> processData(List<String> data) {
        data.add("processed_" + superField);
        return data;
    }
}

// Subclass of TargetClass for method_feature: sub_class
package com.target;

import java.util.List;
import java.util.ArrayList;

class TargetSubClass extends TargetClass<String> {
    public TargetSubClass() {
        super("subClassValue");
    }

    public static List<String> staticHelper(List<String> args) {
        return new ArrayList<>(args);
    }
}

// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import com.target.TargetSubClass;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// Parent class for superTypeReference
class SourceParent {
    public List<String> parentMethod(List<String> data) {
        data.add("parent_" + 3);
        return data;
    }
}

// Source class: public modifier, local inner + anonymous inner class (source_feature)
public class SourceClass extends SourceParent {
    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };

    // DoStatement feature: private modifier, super.field, 3, pos=source
    private void doStatementFeature(TargetClass<?> target) {
        int count = 0;
        do {
            count++;
            if (count == target.superField) { // super.field + 3
                break; // break statement feature
            }
        } while (count < 5);
    }

    // Static method feature: protected modifier, 3, sub_class, static, this.methodName(), pos=Lambda, return_type=List<String>
    protected static List<String> staticHelperMethod(TargetSubClass subClass) {
        // Lambda expressions (pos)
        Supplier<List<String>> lambda = () -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) list.add("lambda_" + i); // 3 for method_feature
            // this.methodName(arguments)
            return subClass.staticHelper(list);
        };
        return lambda.get();
    }

    // Overriding method: private access, Object return type, target parameter (per_condition)
    @Override
    private Object refactorMethod(TargetClass<String> targetParam) {
        // Variable call feature
        String varCall = targetParam.value;

        // () -> System.out.println(this.value) feature
        Runnable lambdaPrint = () -> System.out.println(targetParam.value);
        lambdaPrint.run();

        // Execute DoStatement feature
        doStatementFeature(targetParam);

        // Call static helper method (sub_class)
        TargetSubClass subClass = new TargetSubClass();
        List<String> staticResult = staticHelperMethod(subClass);

        // call_method: target type, public modifier, normal + superTypeReference.methodName(), pos=collection operations
        TargetClass.TargetInnerClass inner = targetParam.new TargetInnerClass();
        List<String> collectionData = inner.getInnerData();
        // Collection operations (pos) + superTypeReference.methodName(arguments)
        List<String> callResult = targetParam.processData(collectionData);

        // Local inner class (source_feature)
        class LocalInnerClass {
            public Object getCombined() {
                return varCall + "_" + staticResult + "_" + callResult;
            }
        }

        // No new exception thrown feature
        return new LocalInnerClass().getCombined();
    }
}