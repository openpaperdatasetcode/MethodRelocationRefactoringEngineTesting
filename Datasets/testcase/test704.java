import java.lang.reflect.Method;

// Source class: normal, default modifier, same package, static nested + anonymous inner classes
class SourceClass {
    // Static nested class (source feature)
    static class SourceStaticNested {
        // Overload methods for overload_exist feature
        int process(TargetClass target) {
            return target.getValue();
        }

        int process(TargetClass target, int multiplier) {
            return target.getValue() * multiplier;
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Method: normal, Object return, public access, contains target parameter
    public Object moveCandidateMethod(TargetClass targetParam) {
        // Variable call
        SourceStaticNested nested = new SourceStaticNested();
        int varCall = nested.process(targetParam);
        
        // Overload_exist: call overloaded method
        int overloadedCall = nested.process(targetParam, 2);

        // Used by reflection
        try {
            // Get overloaded method via reflection
            Method processMethod1 = SourceStaticNested.class.getDeclaredMethod("process", TargetClass.class);
            Method processMethod2 = SourceStaticNested.class.getDeclaredMethod("process", TargetClass.class, int.class);
            
            // Invoke reflected methods
            varCall = (int) processMethod1.invoke(nested, targetParam);
            overloadedCall = (int) processMethod2.invoke(nested, targetParam, 2);
        } catch (Exception e) {
            // No new exception thrown
            return null;
        }

        // Execute anonymous inner class
        anonymousInner.run();
        
        // Return Object type (target_inner context)
        return targetParam;
    }
}

// Target class: normal, abstract modifier, empty target_feature
abstract class TargetClass {
    // Target_inner context (core field/method for target_inner reference)
    private int innerValue = 5;

    int getValue() {
        return innerValue;
    }

    // Abstract method required for abstract class
    public abstract void abstractMethod();
}