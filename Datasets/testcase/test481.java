// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source normal class (protected modifier, anonymous inner + static nested class)
protected class SourceClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        public int nestedField = 1; // target_feature: 1 for EmptyStatement
    }

    // Instance method to refactor (private, return List<String>, source position)
    private List<String> refactorMethod(TargetClass.TargetInner targetParam) {
        List<String> result = new ArrayList<>();
        
        // Variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        int localVar = staticNested.nestedField;
        
        // With bounds feature
        for (int i = 0; i < 5; i++) { // bound: i < 5
            result.add(String.valueOf(i));
            if (i == 3) continue;
        }

        // EmptyStatement (private modifier, same_package_target pos, obj.field + 1)
        TargetClass.EmptyHelper emptyHelper = new TargetClass.EmptyHelper();
        emptyHelper.field = 1; // obj.field, target_feature 1
        ; // EmptyStatement (private modifier, same_package_target pos)

        // Anonymous inner class (source class feature)
        Consumer<TargetClass.TargetInner> anonConsumer = new Consumer<TargetClass.TargetInner>() {
            @Override
            public void accept(TargetClass.TargetInner inner) {
                // No new exception feature (no throw new Exception)
                if (inner == null) return;
                result.add(inner.getInnerValue());
            }
        };

        // Process target inner class parameter
        if (targetParam != null) {
            anonConsumer.accept(targetParam);
        }

        // Call method (inner_class, protected, pos: while, static + instanceReference.methodName)
        int count = 0;
        while (count < localVar) { // pos: while
            int callResult = TargetClass.TargetInner.staticInnerMethod(count); // static target_feature
            result.add(String.valueOf(callResult));
            // instanceReference.methodName(arguments)
            int instanceResult = targetParam.instanceInnerMethod(callResult);
            result.add(String.valueOf(instanceResult));
            count++;
        }

        return result;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass<>("init");
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target normal class (public, type parameter + member inner class)
public class TargetClass<T> {
    private T typeParam;

    // Member inner class (target_inner)
    public class TargetInner {
        private String innerValue;

        // Call method (inner_class, protected, static + instanceReference.methodName)
        protected static int staticInnerMethod(int arg) { // static target_feature
            return arg * 1;
        }

        protected int instanceInnerMethod(int arg) { // instanceReference.methodName
            return arg + 1;
        }

        public String getInnerValue() {
            return innerValue;
        }

        public TargetInner(String value) {
            this.innerValue = value;
        }
    }

    // EmptyHelper for EmptyStatement (same_package_target pos)
    public class EmptyHelper {
        public int field; // obj.field target_feature
    }

    // Constructor
    public TargetClass(T typeParam) {
        this.typeParam = typeParam;
    }
}