import java.util.function.Consumer;

// Source class: default modifier, same package, two member inner classes
class SourceClass {
    // Static field for depends_on_static_field feature
    static int staticField = 99;
    // Instance field for access_instance_field feature
    int instanceField = 5;
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // First member inner class (fulfills source_class feature)
    class FirstMemberInner {}
    // Second member inner class (fulfills source_class feature twice)
    class SecondMemberInner {}

    // Target method: instance, void return, protected access, in source class
    protected void processData(TargetClass param) {
        // Constructor invocation
        TargetClass newTargetInstance = new TargetClass();
        
        // Variable call to target parameter
        int targetVar = param.innerField;
        
        // Access instance field (access_instance_field)
        int instanceVal = this.instanceField;
        
        // Depends on static field (depends_on_static_field)
        int combinedVal = targetVar + instanceVal + SourceClass.staticField;
        
        // No new exception thrown (no_new_exception)
        System.out.println(combinedVal);
    }
}

// Target class: protected modifier, has local inner class (target_feature)
protected class TargetClass {
    int innerField = 10;

    public void outerMethod() {
        // Local inner class (fulfills target_feature)
        class LocalInnerClass {
            int localField = innerField;
        }
    }
}

// Call method container: others_class type, public modifier, generic + lambda in for loop
class OtherClass {
    // Call method: public, generic, (parameters) -> methodBody in for loop, returns int
    public <T extends TargetClass> int invokeTargetMethod(T targetParam) {
        SourceClass source = new SourceClass();
        int count = 0;

        // For loop containing lambda (parameters) -> methodBody (target_feature)
        for (int i = 0; i < 3; i++) {
            Consumer<T> consumer = (param) -> source.processData(param); // (parameters) -> methodBody
            consumer.accept(targetParam);
            count++;
        }

        return count; // int return type
    }
}