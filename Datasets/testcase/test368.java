package com.refactoring.movemethod;

import java.util.function.Consumer;

// Source normal class (default modifier, same package, type parameter feature)
class SourceClass<T extends TargetClass> {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Overloading method 1 (final, return Object, source position)
    public final Object refactorMethod(TargetClass targetParam) {
        return refactorMethod(targetParam, "default");
    }

    // Overloading method 2 (final, return Object, source position - main refactor method)
    public final Object refactorMethod(TargetClass targetParam, String suffix) {
        // Uses outer this feature
        SourceClass<?> outerThis = SourceClass.this;
        
        // Variable call feature
        int localVar = outerThis.targetField != null ? outerThis.targetField.getValue() : 0;
        
        // Type declaration statement feature
        class LocalTypeDeclaration {}
        LocalTypeDeclaration typeDecl = new LocalTypeDeclaration();
        
        // Labeled statement feature
        label: {
            if (targetParam == null) {
                break label; // labeled statement break
            }
            localVar += targetParam.getValue();
        }

        // Depends on inner class (target's member inner class)
        TargetClass.TargetInner inner = targetParam.new TargetInner();
        localVar += inner.getInnerValue();

        // Return statement feature
        if (localVar > 10) {
            // No new exception feature (no throw new Exception)
            return inner.processData(String.valueOf(localVar) + suffix);
        }

        // Call method reference (ClassName::methodName) via static code block
        TargetClass.staticInit();
        return localVar;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (protected modifier, member inner class feature)
protected class TargetClass {
    private int value = 7058; // id reference

    // Member inner class (target_feature)
    public class TargetInner {
        private int innerValue = 5;

        public int getInnerValue() {
            return innerValue;
        }

        public String processData(String data) {
            return data + "_processed";
        }
    }

    // Call method (target, final modifier, normal feature, return void)
    public final void targetCallMethod(String data) {
        System.out.println("Processed: " + data);
    }

    // Static code block (pos for call_method: Static code blocks)
    static {
        // ClassName::methodName target_feature
        Consumer<String> consumer = TargetClass::targetCallMethod;
        consumer.accept("static_block_data_" + 7058);
    }

    // Static init method for source class access
    public static void staticInit() {}

    // Getter for value
    public int getValue() {
        return value;
    }

    // Setter for value
    public void setValue(int value) {
        this.value = value;
    }
}