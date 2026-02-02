package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6145Annotation {}

// Others class for method_feature: others_class
class OthersClass {
    // Accessor method (method_feature: accessor)
    public static List<String> getProcessedList(String val, int count) { // method_feature: "2" (count param)
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(val + "_" + i);
        }
        return list;
    }
}

// Parent class for overriding feature
abstract class SourceParent {
    public abstract Object process(TargetClass target);
}

// Source abstract normal class: abstract modifier, same package, local inner + member inner classes
abstract class SourceClass extends SourceParent {
    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "source_member_inner_6145";
    }

    // Overload exist feature (overloaded method)
    public final Object process(TargetClass target, String extra) {
        target.setData(extra);
        return process(target);
    }

    // Method to be refactored: overriding, Object return, final access, source position
    @Refactor6145Annotation // has_annotation feature
    @Override
    public final Object process(TargetClass targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // throw statement feature
            throw new IllegalArgumentException("TargetClass parameter cannot be null (ID:6145)");
        }

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // Accessor method (type: accessor, modifier: public, pos: for, return List<String>)
        public List<String> accessorMethod() {
            List<String> result = new ArrayList<>();
            // pos: for statement
            for (int i = 0; i < 2; i++) { // method_feature: "2"
                // method_feature: others_class + accessor + ClassName.methodName(arguments)
                List<String> subList = OthersClass.getProcessedList(varCall, 2); // method_feature: "2"
                result.addAll(subList);
            }
            return result; // return_type: List<String>
        }
        List<String> accessorResult = accessorMethod();

        // Local inner class (source feature)
        class SourceLocalInner {
            private Object wrapResult(List<String> list) {
                return list.toArray();
            }
        }
        Object wrappedResult = new SourceLocalInner().wrapResult(accessorResult);

        // no_new_exception (no explicit new Exception instantiation beyond throw statement requirement)
        targetParam.setData(wrappedResult.toString());
        
        // return statement feature
        return wrappedResult;
    }
}

// Target class: normal, public modifier, empty target_feature
public class TargetClass {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

// Concrete implementation for abstract source class (compilation support)
class SourceConcrete extends SourceClass {}