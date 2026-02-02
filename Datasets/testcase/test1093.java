package com.example;

import java.util.List;
import java.util.ArrayList;

// Source class (protected modifier, normal class, same package, anonymous inner class, static nested class)
protected class SourceClass {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in source");
        }
    };

    // Method to be refactored (instance, List<String> return, default access, position: source)
    List<String> targetMethod(TargetClass param) { // per_condition: target parameter
        // Super constructor invocation
        super();

        // Super keywords usage
        super.toString();

        // VariableDeclarationStatement (private modifier, this.field, 3, pos: diff_package_target)
        private void varDeclBlock() {
            this.field = "sourceField"; // this.field
            int num = 3; // target_feature: 3
            List rawList = new ArrayList(); // raw_type (pre-declaration for usage)
        }
        varDeclBlock();

        // Variable call
        String targetValue = param.getValue();

        // Raw type usage
        List rawList = new ArrayList(); // raw_type
        rawList.add(targetValue);

        // Depends on inner class
        param.initLocalInner(); // trigger target's local inner class logic

        // NullPointerException
        if (targetValue == null) {
            throw new NullPointerException();
        }

        // No new exception
        return new ArrayList<>(rawList);
    }

    // this.field for VariableDeclarationStatement
    private String field;
}

// Target class (private modifier, normal class, local inner class target_feature)
private class TargetClass {
    private String value = "targetValue";

    public String getValue() {
        return value;
    }

    // Local inner class (target_feature)
    void initLocalInner() {
        class LocalInnerClass {
            String process(String val) {
                return val + "_processed";
            }
        }
        LocalInnerClass local = new LocalInnerClass(); // depends_on_inner_class
        this.value = local.process(this.value);
    }
}