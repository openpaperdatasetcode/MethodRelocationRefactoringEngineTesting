import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Source normal class (public, generic with type parameter, same package as target)
public class SourceClass<T extends CharSequence> {
    // Field of target class (satisfies per_condition)
    private ProtectedTargetClass targetField = new ProtectedTargetClass();

    // Anonymous inner class (source_class feature)
    Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Local inner class (source_class feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printSourceData(T data) {
                System.out.println(data.toString());
            }
        }
        new LocalInnerClass().printSourceData((T) "Local inner class data");
    }

    // Method to be moved (instance, List<String> return, strictfp access, source position)
    @ProcessAnnotation // has_annotation feature
    strictfp List<String> processTarget() {
        List<String> result = new ArrayList<>();
        
        // Type declaration statement
        ProtectedTargetClass.InnerClass.RecursiveInnerClass recInner = 
            targetField.new InnerClass().new RecursiveInnerClass();
        
        // Constructor invocation (top-level)
        TargetConstructorHelper helper = new TargetConstructorHelper(targetField);
        
        // Inner class with private ConstructorInvocation (obj.field, 1 pos: inner class)
        class InnerConstructorClass {
            private int field = 1; // target_feature: 1
            
            private InnerConstructorClass() {
                // ConstructorInvocation with obj.field access (target_feature: obj.field)
                this.field = recInner.counter; // obj.field
            }
            
            public int getField() {
                return field;
            }
        }
        InnerConstructorClass innerConstructor = new InnerConstructorClass(); // private ConstructorInvocation
        
        // Default constructor with specified features (pos: if/else, return Object)
        class TargetConstructorHelper {
            private ProtectedTargetClass target;
            
            // Default modifier constructor
            TargetConstructorHelper(ProtectedTargetClass target) {
                this.target = target;
                int value = 1; // method_feature: 1
                
                // pos: if/else conditions
                if (value == 1) {
                    // method_feature: target + super.methodName()
                    Object superResult = this.target.new InnerClass().super.toString(); // super.methodName()
                    result.add(superResult.toString());
                } else {
                    result.add("Default constructor else case");
                }
            }
            
            // method_feature: constructor (explicit constructor usage)
            public Object getConstructorResult() {
                return this.target.toString();
            }
        }
        
        // Variable call
        recInner.setCounter(innerConstructor.getField());
        // Access instance method
        result.add(recInner.processData("Processed data"));
        
        // Additional constructor invocation
        recInner.resetCounter(new Integer(1));
        
        return result;
    }
}

// Target normal class (protected modifier, same package as source)
protected class ProtectedTargetClass {
    // Anonymous inner class (target_feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    // Member inner class (for target_inner_rec)
    class InnerClass {
        // Recursive inner class (target_inner_rec)
        class RecursiveInnerClass {
            int counter = 0;
            
            public String processData(String data) {
                return data + "_" + counter;
            }
            
            public void setCounter(int counter) {
                this.counter = counter;
            }
            
            public void resetCounter(Integer val) {
                this.counter = val;
            }
        }
    }
}