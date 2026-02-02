package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for call_method pos (attribute values of annotations)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorCallAnnotation {
    String value() default "instanceReference.methodName(arguments)";
}

// Target class container (for private modifier)
class TargetContainer {
    // Target class: private modifier, anonymous inner class (target_feature)
    private static class TargetClass {
        public int thisField = 2; // this.field + 2 for SwitchStatement

        // Target inner class (target_inner - target class of method)
        public class TargetInner {
            private int innerValue;

            public TargetInner(int innerValue) {
                this.innerValue = innerValue;
            }

            public int getInnerValue() {
                return innerValue;
            }
        }

        // Anonymous inner class (target_feature)
        private Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + thisField);
            }
        };

        public void executeAnonymous() {
            anonymousInner.run();
        }
    }
}

// Abstract interface for abstract method feature
interface AbstractSourceInterface {
    TargetContainer.TargetClass.TargetInner abstractMethod(int val);
}

// Source class: protected modifier, two static nested classes (source_feature)
protected class SourceClass {
    // Source contains target field (per_condition)
    private TargetContainer.TargetClass targetField = new TargetContainer.TargetClass();
    
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 3; // numbers=3 for ArrayInitializer
    
    // First static nested class (source_feature)
    public static class FirstStaticNested {
        public static int staticHelper(int val) {
            return val * 1; // method_feature: 1
        }
    }

    // Second static nested class (source_feature)
    public static class SecondStaticNested {
        // Overriding method for call_method feature
        public int overriddenMethod(TargetContainer.TargetClass.TargetInner inner) {
            return inner.getInnerValue() + 1;
        }
    }

    // SwitchStatement feature: private modifier, this.field, 2, pos=source
    private void switchStatementFeature() {
        int switchVal = targetField.thisField; // this.field + 2
        switch (switchVal) {
            case 2:
                targetField.executeAnonymous();
                break;
            default:
                break;
        }
    }

    // Abstract method feature: private modifier, 1, source, abstract, this.methodName(), pos=object initialization, return TargetClass Type
    private AbstractSourceInterface abstractHelper = new AbstractSourceInterface() {
        @Override
        public TargetContainer.TargetClass.TargetInner abstractMethod(int val) {
            // Object initialization (pos) + this.methodName(arguments)
            return SourceClass.this.targetField.new TargetInner(FirstStaticNested.staticHelper(val));
        }
    };

    // call_method: source type, final modifier, overriding + instanceReference.methodName(), pos=annotation attributes, return int
    @RefactorCallAnnotation("instanceReference.methodName(arguments)") // pos: annotation attribute values
    private final int sourceCallMethod() {
        // InstanceReference.methodName(arguments)
        SecondStaticNested instanceRef = new SecondStaticNested() {
            @Override
            public int overriddenMethod(TargetContainer.TargetClass.TargetInner inner) { // overriding feature
                return super.overriddenMethod(inner) + 3;
            }
        };
        return instanceRef.overriddenMethod(abstractHelper.abstractMethod(1));
    }

    // Instance method: protected access, void return type, method_position=source
    protected void refactorMethod() {
        // ArrayInitializer feature: numbers=3, private modifier, exp=ArrayInitializer
        private int[] arr = {1, 2, 3}; // ArrayInitializer with 3 elements (numbers=3)

        // Expression statement feature
        int expr = arr[2] + outerProtectedField;

        // Variable call feature
        int varCall = targetField.thisField;

        // Access outer protected feature
        int outerProtected = this.outerProtectedField;

        // Uses outer this feature
        TargetContainer.TargetClass.TargetInner outerThisInner = SourceClass.this.targetField.new TargetInner(varCall);

        // Execute SwitchStatement feature
        switchStatementFeature();

        // Execute abstract method feature
        TargetContainer.TargetClass.TargetInner abstractResult = abstractHelper.abstractMethod(1);

        // Execute call_method
        int callResult = sourceCallMethod();

        // Return statement feature
        if (expr > 5) {
            return;
        }

        // No new exception thrown feature
        System.out.println("varCall: " + varCall + ", outerProtected: " + outerProtected + ", callResult: " + callResult);
    }
}