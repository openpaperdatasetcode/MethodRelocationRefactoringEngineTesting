package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6130Annotation {}

// Source record class: default modifier, same package, static nested + anonymous inner classes
record SourceRecord(String sourceField) {
    // Protected outer field for access_outer_protected feature
    protected final String outerProtectedField = "outer_protected_6130";

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_val";
        
        // Generic method for method_feature: generic
        protected static <T> void genericMethod(T val, int count) { // method_feature: "1" (count param default 1)
            System.out.println("Generic method called with: " + val + ", count: " + count);
        }
    }

    // Recursive inner class (method_position: source_inner_rec)
    public class SourceInnerRecursive {
        // Anonymous inner class (source feature)
        private Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                new SourceInnerRecursive().methodToMove(new TargetRecord<>("anon_target"));
            }
        };

        // Method to be refactored: instance, List<String> return, default access, source_inner_rec
        @Refactor6130Annotation // has_annotation feature
        List<String> methodToMove(TargetRecord<String> targetParam) {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                return new ArrayList<>();
            }

            // access_outer_protected feature
            String outerProtectedVal = SourceRecord.this.outerProtectedField;

            // variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            String varCall = staticNested.nestedField;

            // depends_on_inner_class feature (uses target's member inner class)
            TargetRecord<String>.TargetInnerClass targetInner = targetParam.new TargetInnerClass();
            String innerClassVal = targetInner.processData(varCall);

            // Generic method (type: generic, modifier: protected, pos: instance code blocks, return void)
            {
                // pos: instance code blocks
                int count = 1; // method_feature: "1"
                // method_feature: source + ClassName.methodName(arguments) + generic
                SourceStaticNested.genericMethod(outerProtectedVal, count); // method_feature: generic
            }

            // no_new_exception (no explicit new Exception instantiation)
            List<String> result = new ArrayList<>();
            result.add(varCall + "_" + outerProtectedVal + "_" + innerClassVal + "_" + targetParam.targetField());
            return result;
        }
    }
}

// Subclass for call_method type: sub_class
class SourceRecordSubClass extends SourceRecord {
    public SourceRecordSubClass(String sourceField) {
        super(sourceField);
    }

    // Call method: sub_class type, protected modifier, instance, pos=for, returns String
    protected String callMethod() {
        SourceInnerRecursive innerRec = new SourceInnerRecursive();
        TargetRecord<String> target = new TargetRecord<>("call_target_6130");
        StringBuilder sb = new StringBuilder();
        
        // pos: for statement
        for (int i = 0; i < 1; i++) { // method_feature: "1"
            // target_feature: instance + instanceReference.methodName(arguments)
            List<String> result = innerRec.methodToMove(target);
            sb.append(result.get(0));
        }
        
        return sb.toString();
    }
}

// Target record class: protected modifier, type parameter + member inner class (target_features)
protected record TargetRecord<T>(T targetField) {
    // Member inner class (target_feature)
    public class TargetInnerClass {
        public String processData(String val) {
            return val + "_" + targetField.toString() + "_inner";
        }
    }
}