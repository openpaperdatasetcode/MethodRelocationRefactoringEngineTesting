package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Super class for super.methodName(arguments) feature
class SuperSourceClass {
    protected TargetClass superMethod(int value) {
        return new TargetClass("super_" + value);
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Target class: public modifier, anonymous inner class (target_feature), same package as source
public class TargetClass {
    public int targetField = 2; // obj.field + 2 for ConstructorInvocation feature
    private String data;

    public TargetClass() {}

    public TargetClass(String data) {
        this.data = data;
    }

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };

    // Overload method 1 for overload_exist feature
    public void process(int value) {
        this.targetField = value;
    }

    // Overload method 2 for overload_exist feature
    public void process(String value) {
        this.data = value;
    }

    public String getData() {
        return data;
    }

    public int getTargetField() {
        return targetField;
    }
}

// Source class: protected modifier, member inner + static nested class (source_feature), same package as target
protected class SourceClass extends SuperSourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_feature)
    static class SourceStaticNested {
        public static int staticValue = 1; // numbers=1 for InfixExpression feature
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        public String innerField = "innerValue";
    }

    // ConstructorInvocation feature: private modifier, obj.field, 2, pos=source
    private TargetClass constructorInvocationFeature(TargetClass obj) {
        // obj.field + 2
        return new TargetClass("constructed_" + obj.targetField);
    }

    // call_method: source type, protected modifier, static + super.methodName(arguments), pos=ternary operators, return_type=TargetClass
    protected static TargetClass sourceCallMethod(int value) {
        SourceClass source = new SourceClass();
        // Ternary operator (pos) + super.methodName(arguments)
        return value == 1 ? source.superMethod(1) : source.superMethod(2);
    }

    // Instance method: default access, void return type, method_position=source
    @RefactorAnnotation // has_annotation feature
    void refactorMethod() {
        // Constructor invocation (method feature)
        TargetClass newTarget = new TargetClass("newInstance");

        // Execute ConstructorInvocation feature
        TargetClass constructorTarget = constructorInvocationFeature(targetField);

        // Switch case (method feature)
        int switchVal = targetField.getTargetField();
        switch (switchVal) {
            case 2:
                targetField.process(2);
                break;
            default:
                targetField.process("default");
                break;
        }

        // InfixExpression feature: numbers=1, default modifier, exp=InfixExpression
        int infixResult = SourceStaticNested.staticValue + 1; // 1 + 1 (InfixExpression)

        // Variable call (method feature)
        String varCall = targetField.getData();
        System.out.println(varCall + "_" + infixResult);

        // Overload_exist (call overloaded methods)
        targetField.process(1);
        targetField.process("overload");

        // Execute call_method (ternary operator pos)
        TargetClass callResult = sourceCallMethod(infixResult);

        // Use member inner class
        SourceMemberInner inner = new SourceMemberInner();
        System.out.println(inner.innerField);

        // No new exception thrown (method feature)
    }
}