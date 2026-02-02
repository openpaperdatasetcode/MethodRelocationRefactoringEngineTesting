package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// Source record class: default modifier, same package, anonymous inner + local inner classes
record SourceRecord(String sourceField) {
    // Private outer field for access_outer_private feature
    private final String outerPrivateField = "outer_private_6133";

    // Instance field for access_instance_field feature
    private String instanceField = "instance_field_6133";

    // Method to be refactored: normal, List<String> return, default access, source position
    List<String> methodToMove(TargetRecord targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // constructor invocation (target record constructor)
            targetParam = new TargetRecord("default_target_6133");
        }

        // access_outer_private feature
        String outerPrivateVal = this.outerPrivateField;

        // access_instance_field feature
        this.instanceField = outerPrivateVal + "_updated";
        String instanceFieldVal = this.instanceField;

        // variable call feature
        TargetRecord.TargetStaticNested staticNested = targetParam.new TargetStaticNested();
        String varCall = staticNested.nestedField;

        // Local inner class (source feature) - declared in refactored method
        class SourceLocalInner {
            private void processData(List<String> list) {
                list.add(outerPrivateVal);
                list.add(instanceFieldVal);
                list.add(varCall);
                list.add(targetParam.targetField());
            }
        }

        // Anonymous inner class (source feature)
        Consumer<List<String>> anonymousProcessor = new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) {
                new SourceLocalInner().processData(strings);
            }
        };

        // no_new_exception (no explicit new Exception instantiation)
        List<String> result = new ArrayList<>();
        anonymousProcessor.accept(result);
        return result;
    }
}

// Target record class: public modifier, static nested class (target_feature)
public record TargetRecord(String targetField) {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedField = "target_static_nested_6133";
    }
}