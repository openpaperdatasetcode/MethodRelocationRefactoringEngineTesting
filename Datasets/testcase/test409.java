import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for method feature: has_annotation
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnotation {}

// Source class: final normal class, same package, extends + two local inner classes
final class SourceClass extends ParentClass {
    // Super constructor invocation (inherited from ParentClass)
    public SourceClass() {
        super();
    }

    // Target method: instance, void return, default access, in source class
    @RefactorTestAnnotation // has_annotation feature
    void processData(TargetClass.TargetInnerClass.TargetInnerRecClass param) {
        // Type declaration statement
        String targetVar;
        
        // Variable call to target parameter
        targetVar = param.targetField;
        
        // No new exception thrown (no_new_exception)
        System.out.println(targetVar);
        
        // First local inner class (fulfills source_class feature)
        class LocalInnerOne {
            void dummy() {}
        }
        
        // Second local inner class (fulfills source_class feature twice)
        class LocalInnerTwo {
            void dummy() {}
        }
    }
}

// Parent class for SourceClass extends feature
class ParentClass {}

// Target class: public normal class, no extra target_features
public class TargetClass {
    // First level inner class
    class TargetInnerClass {
        // Target inner recursive class (target_inner_rec)
        class TargetInnerRecClass {
            String targetField = "target_value";
        }
    }
}

// Call method container: inner_class type, strictfp modifier, overriding, new ClassName().method()
class CallerClass {
    // Inner class for call_method type
    strictfp class InnerCaller extends BaseCaller {
        // Overriding feature (implements target_feature)
        @Override
        int invokeMethod() {
            try {
                // Exception throwing statements (pos)
                SourceClass source = new SourceClass();
                TargetClass target = new TargetClass();
                TargetClass.TargetInnerClass inner = target.new TargetInnerClass();
                TargetClass.TargetInnerClass.TargetInnerRecClass rec = inner.new TargetInnerRecClass();
                
                // new ClassName().method() (target_feature)
                source.processData(rec);
                return 0;
            } catch (Exception e) {
                // Exception handling (pos: exception throwing statements)
                return -1;
            }
        }
    }
    
    // Base class for overriding
    abstract class BaseCaller {
        abstract int invokeMethod();
    }
}