package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for call_method pos: annotation attribute values
@Retention(RetentionPolicy.RUNTIME)
@interface MethodCallAnnotation {
    String value() default "instanceReference.methodName(targetParam)"; // target_feature: instanceReference.methodName(arguments)
}

// Parent class for method_feature: parent_class
class SourceParent {
    protected void parentInstanceMethod() { // method_feature: instance
        System.out.println("Parent method called");
    }
}

// Source class: normal, protected modifier, same package, static nested + anonymous inner classes
protected class SourceClass extends SourceParent {
    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6125";
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            new SourceClass().methodToMove(new TargetClass().new TargetInnerRecursive());
        }
    };

    // Overloaded method 1 (method type: overloading)
    private List<String> methodToMove(TargetClass.TargetInnerRecursive targetParam) {
        // per_condition: method contains target inner recursive parameter
        if (targetParam == null) {
            return new ArrayList<>();
        }

        // method types parameter is:none (only target param, no other explicit params)
        // super keywords feature
        super.toString();

        // variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        String varCall = staticNested.nestedField;

        // Instance method (type: instance, modifier: protected, pos: if/else, return void)
        protected void nestedInstanceMethod() {
            int count = 1; // method_feature: "1"
            // pos: if/else conditions
            if (count == 1) {
                // method_feature: super.methodName()
                super.parentInstanceMethod(); // method_feature: parent_class + instance
            } else {
                targetParam.innerField = varCall;
            }
        }
        nestedInstanceMethod();

        // no_new_exception (no explicit new Exception instantiation)
        List<String> result = new ArrayList<>();
        result.add(varCall + "_" + targetParam.innerField);
        return result;
    }

    // Overloaded method 2 (method type: overloading)
    private List<String> methodToMove() {
        return methodToMove(new TargetClass().new TargetInnerRecursive());
    }
}

// Target class: normal, public modifier, static nested class (target_feature)
public class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private int nestedVal = 6125;
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        public String innerField = "target_inner_rec_6125";

        public TargetStaticNested getStaticNested() {
            return new TargetStaticNested();
        }
    }
}

// Others class for call_method type: others_class
class CallMethodClass {
    // Call method: others_class type, default modifier, normal, pos: annotation attributes, returns TargetClass Type
    @MethodCallAnnotation // pos: the attribute values of annotations
    public TargetClass.TargetInnerRecursive callMethod() {
        SourceClass source = new SourceClass();
        TargetClass.TargetInnerRecursive target = new TargetClass().new TargetInnerRecursive();
        // target_feature: instanceReference.methodName(arguments)
        List<String> result = source.methodToMove(target);
        target.innerField = result.toString();
        return target; // return_typr: TargetClass Type
    }
}