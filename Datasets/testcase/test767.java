package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6109Annotation {}

// Same package other class for WhileStatement pos: same_package_others
class WhileHelper {
    protected int count = 3; // WhileStatement target_feature: "3"
    protected String helperField = "helper_field"; // WhileStatement target_feature: "obj.field"
}

// Source sealed record class: private, same package, permits + anonymous inner + static nested classes
private sealed record SourceRecord(String sourceField) permits SourceRecordImpl {
    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String staticNestedField = "static_nested_6109";
    }

    // Recursive inner class (method_position: source_inner_rec)
    private class SourceInnerRecursive {
        // Private outer field for access_outer_private feature
        private final String outerPrivateField = SourceRecord.this.sourceField;

        // Method to be refactored: varargs, List<String> return, private access, source_inner_rec
        @Refactor6109Annotation // has_annotation feature
        private List<String> methodToMove(TargetRecord targetParam, String... varargs) {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                // constructor invocation
                targetParam = new TargetRecord("default_target_6109");
            }

            // variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            String varCall = staticNested.staticNestedField;

            // access_outer_private feature
            String outerPrivateVal = this.outerPrivateField;

            // WhileStatement feature (type: WhileStatement, modifier: protected, pos: same_package_others)
            WhileHelper whileHelper = new WhileHelper();
            protected void whileStatementLogic() {
                int i = 0;
                while (i < whileHelper.count) { // target_feature: "3"
                    varCall += whileHelper.helperField + i; // target_feature: obj.field
                    i++;
                }
            }
            whileStatementLogic();

            // no_new_exception (no explicit new Exception instantiation)
            List<String> result = new ArrayList<>();
            for (String arg : varargs) {
                result.add(arg + varCall + outerPrivateVal + targetParam.targetField());
            }

            return result;
        }
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            new SourceInnerRecursive().methodToMove(new TargetRecord("anon_target"), "anon_arg1", "anon_arg2");
        }
    };

    // Call method: source type, public modifier, static, ClassName.methodName(), pos=ternary, returns String
    public static String callMethod(int condition) {
        SourceRecord source = new SourceRecordImpl("source_6109");
        TargetRecord target = new TargetRecord("call_target");
        // pos: ternary operators, target_feature: ClassName.methodName(arguments)
        return condition > 0 
            ? source.new SourceInnerRecursive().methodToMove(target, "arg1").toString()
            : source.new SourceInnerRecursive().methodToMove(target, "arg1", "arg2").toString();
    }
}

// Source record implementation for permits feature
private final class SourceRecordImpl extends SourceRecord {
    public SourceRecordImpl(String sourceField) {
        super(sourceField);
    }
}

// Target record class: private, member inner class (target_feature)
private record TargetRecord(String targetField) {
    // Member inner class (target_feature)
    private class TargetInnerClass {
        private String innerField = "target_inner_6109";
        
        public String getCombinedData(TargetRecord record) {
            return record.targetField() + this.innerField;
        }
    }
}