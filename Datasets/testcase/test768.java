package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6112Annotation {}

// Sealed parent class for permits feature
sealed class SealedParent<T> permits SourceClass {
    protected T sealedField;
}

// Source class: normal, default modifier, same package, type parameter + permits + member inner + local inner classes
final class SourceClass<T> extends SealedParent<T> {
    // Member inner class (source feature)
    public class SourceInnerClass {
        private String innerField = "source_inner_6112";
        
        // Instance method for method_feature: inner_class, instance
        public int innerInstanceMethod(int val) {
            return val * 2; // method_feature: "2"
        }
    }

    // Method to be refactored: instance, void return, private access, source position
    @Refactor6112Annotation // has_annotation feature
    private void methodToMove(List<T> listParam, TargetClass<T> targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            return;
        }

        // variable call feature
        SourceInnerClass innerObj = new SourceInnerClass();
        String varCall = innerObj.innerField;

        // Local inner class (source feature) - declared in refactored method
        class SourceLocalInner {
            private T localField;
            
            public void setLocalField(T val) {
                this.localField = val;
            }
        }

        // Nested instance method (type: instance, modifier: public, pos: exception throwing statements)
        public int nestedInstanceMethod() {
            SourceLocalInner localInner = new SourceLocalInner();
            try {
                if (listParam.isEmpty()) {
                    throw new IllegalStateException("List is empty"); // exception throwing statements pos
                }
                // method_feature: instanceReference::methodName
                listParam.forEach(localInner::setLocalField);
                // method_feature: "2", inner_class, instance
                return innerObj.innerInstanceMethod(2); // return base type (int)
            } catch (IllegalStateException e) {
                // no_new_exception (no explicit new Exception instantiation)
                e.printStackTrace();
                return 0;
            }
        }

        // Execute nested instance method
        nestedInstanceMethod();
        
        // Variable call usage
        targetParam.targetInnerClass.innerField = varCall;
    }

    // Static method for sub_class call method
    public static SourceClass<String> createSourceInstance() {
        return new SourceClass<>();
    }
}

// Target class: normal, default modifier, member inner class (target_feature)
class TargetClass<T> {
    // Member inner class (target_feature)
    public class TargetInnerClass {
        public String innerField = "target_inner_6112";
        
        // Chained methods for obj.m1().m2().m3() feature
        public TargetInnerClass m1() { return this; }
        public TargetInnerClass m2() { return this; }
        public String m3() { return innerField; }
    }

    public TargetInnerClass targetInnerClass = new TargetInnerClass();
}

// Subclass for call_method type: sub_class
class SourceSubClass<T> extends SourceClass<T> {
    // Static code block for call_method pos: Static code blocks
    static {
        callMethod();
    }

    // Call method: sub_class type, final modifier, static, obj.m1().m2().m3(), returns Object
    public static final Object callMethod() {
        SourceClass<String> source = SourceClass.createSourceInstance();
        TargetClass<String> target = new TargetClass<>();
        List<String> list = new ArrayList<>();
        list.add("6112");
        
        // target_feature: obj.m1().m2().m3()
        String chainedResult = target.targetInnerClass.m1().m2().m3();
        source.methodToMove(list, target);
        
        return chainedResult;
    }
}