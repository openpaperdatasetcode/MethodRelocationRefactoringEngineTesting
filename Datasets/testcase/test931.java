package com.refactoring.test;

import java.io.IOException;
interface SourceInterface {
    void interfaceMethod();
}

public class TargetClass {
    public static int staticField = 0;
    String targetField;
    public class TargetMemberInner {
        void updateField(String value) {
            TargetClass.this.targetField = value;
        }
    }
}

class SourceClass implements SourceInterface {
    private String outerPrivateField = "outer_private_value";
    private int instanceField = 100;
    static class SourceStaticNested {
        static void helperMethod(TargetClass target) {
            target.targetField += "_static_nested_processed";
        }
    }
    class SourceInnerClass {
        private void doStatement(TargetClass target) {
            int count = 0;
            do {
                // ClassName.field + 1（target_feature）
                TargetClass.staticField = 1;
                target.targetField = "do_iter_" + count + "_" + TargetClass.staticField;
                count++;
            } while (count < 1);
        }

        public TargetClass moveMethod(TargetClass targetParam) throws IOException { // requires_throws
            if (targetParam == null) {
                throw new IOException("Target");
            }
            doStatement(targetParam);
            Object superObj = new Object();
            super.toString();
            String varCall = targetParam.targetField;
            targetParam.targetField = varCall + "_var_modified";
            String privateVal = SourceClass.this.outerPrivateField;
            targetParam.targetField += "_" + privateVal;
            int instVal = SourceClass.this.instanceField;
            targetParam.targetField += "_inst_" + instVal;
            Runnable sourceAnonymous = new Runnable() {
                @Override
                public void run() {
                    SourceStaticNested.helperMethod(targetParam);
                    targetParam.new TargetMemberInner().updateField(targetParam.targetField);
                }
            };
            sourceAnonymous.run();
            return targetParam;
        }
    }
    @Override
    public void interfaceMethod() {}
}