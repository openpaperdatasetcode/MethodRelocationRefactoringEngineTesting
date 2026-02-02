import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source class: default modifier, same package, implements/anonymous inner/member inner class
class SourceClass implements Runnable {
    // Protected field for access_outer_protected feature
    protected int outerProtectedField = 42;
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // Member inner class (first level)
    class SourceInnerClass {
        // Nested inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            /**
             * Method Javadoc (fulfills method javadoc feature)
             * @return List<String> result
             * @throws IOException if error occurs
             */
            @RefactorAnnotation // has_annotation feature
            List<String> processData() throws IOException { // requires_throws (IOException), method types parameter is:none
                // Type declaration statement
                String targetVar;
                // Variable call to target parameter (target class field)
                targetVar = SourceClass.this.targetField.targetValue;
                
                // Access outer protected field (access_outer_protected)
                int protectedVal = SourceClass.this.outerProtectedField;
                
                // Expression statement
                List<String> result = new ArrayList<>();
                result.add(targetVar);
                result.add(String.valueOf(protectedVal));
                
                // ParenthesizedExpression (numbers:2, modifier:public)
                int parenthesizedVal = (2 * protectedVal); // numbers:2, parenthesized expression
                
                // IOException feature (triggered conditionally)
                if (parenthesizedVal > 80) {
                    throw new IOException("Value exceeds threshold");
                }
                
                // Add parenthesized value to result
                result.add(String.valueOf(parenthesizedVal));
                return result;
            }
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    @Override
    public void run() {} // Implements Runnable method
}

// Target class: default modifier, anonymous inner class (target_feature)
class TargetClass {
    String targetValue = "target_data";

    // Anonymous inner class (fulfills target_feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(targetValue);
        }
    };

    // Call method: private modifier, static, instanceReference::methodName in lambda (pos: Lambda expressions)
    private static Object invokeSourceMethod() {
        SourceClass source = new SourceClass();
        SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
        SourceClass.SourceInnerClass.SourceInnerRecClass innerRec = inner.new SourceInnerRecClass();
        
        // Lambda expression containing instanceReference::methodName (target_feature)
        Supplier<List<String>> supplier = innerRec::processData; // instanceReference::methodName
        
        try {
            return supplier.get(); // Object return type
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}