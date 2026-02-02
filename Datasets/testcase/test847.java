import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface GenericMethodAnnotation {}

// Others class for call_method feature
class OthersClass {
    // Call method: others_class type, protected modifier, constructor, super.methodName(arguments), ternary pos, return String
    protected String callMethod(String arg) {
        // Ternary operators position for call_method
        String result = (arg == null) ? 
            new OthersClass().helperMethod("default") : 
            super.toString(); // super.methodName(arguments)
        
        // Constructor feature (call constructor in ternary)
        return new OthersClass().processResult(result);
    }

    protected String helperMethod(String input) {
        return input + "_helper";
    }

    protected String processResult(String input) {
        return input + "_processed";
    }
}

// Source class: normal, default modifier, same package, anonymous inner + static nested class
class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    private int innerField; // Field for this.field = 1 feature

    // Static nested class (source_class feature)
    static class SourceStaticNestedClass<T> {
        public T processValue(T value) {
            return value;
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Generic method to refactor: Object return, public access, source_inner position
        @GenericMethodAnnotation // has_annotation feature
        public <T extends CharSequence> Object genericMethod(T param) {
            // Variable call feature
            String localVar = "genericVar";
            localVar += param.toString();

            // With bounds feature (generic with bounds)
            SourceStaticNestedClass<T> boundedNested = new SourceStaticNestedClass<>();
            T boundedValue = boundedNested.processValue(param);

            // ConstructorInvocation: private modifier, this.field, 1, pos: source
            privateConstructorInvocation();

            // Call others_class method (call_method feature)
            OthersClass others = new OthersClass();
            String othersResult = others.callMethod(localVar);
            localVar += othersResult;

            // No new exception feature (no 'new Exception()' statements)
            return localVar + "_" + targetField.getFieldValue();
        }

        // Private ConstructorInvocation implementation (pos: source)
        private void privateConstructorInvocation() {
            this.innerField = 1; // this.field, 1 (ConstructorInvocation target_feature)
            new SourceClass(); // Constructor invocation
        }
    }
}

/**
 * TargetClass - protected modifier, javadoc + member inner class target_feature
 * This class is the target for Move Method refactoring
 */
protected class TargetClass {
    private String fieldValue = "targetValue";

    // Member inner class (target_feature)
    class TargetInnerClass {
        public void innerMethod() {}
    }

    // Target inner class for method relocation
    public class target_inner {
        // Placeholder for moved generic method
        @GenericMethodAnnotation
        public <T extends CharSequence> Object genericMethod(T param) {
            SourceClass source = new SourceClass();
            SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.genericMethod(param);
        }
    }

    public String getFieldValue() {
        return fieldValue;
    }
}