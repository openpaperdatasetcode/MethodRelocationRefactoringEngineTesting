// Same package (implicit no package declaration)
strictfp sealed class SourceClass permits SourceSubClass {
    // Instance field for access_instance_field feature
    private String instanceField = "sourceInstanceField";

    // Method to refactor: varargs, Object return, default access, source position
    Object methodToRefactor(TargetClass<String> targetParam, Object... args) {
        // Satisfy per_condition: method contains target class parameter (targetParam)
        
        // Variable call feature
        Object localVar = "localVar";
        localVar = args.length > 0 ? args[0] : localVar;

        // Access instance field (access_instance_field feature)
        localVar = this.instanceField + localVar.toString();

        // Constructor invocation feature
        SourceSubClass subInstance = new SourceSubClass();

        // Super constructor invocation feature
        class SuperConstructorClass extends SourceClass {
            public SuperConstructorClass() {
                super(); // super constructor invocation
            }
        }
        new SuperConstructorClass();

        // Instance method feature (private modifier, 1, target, instance, new ClassName().method(), object initialization pos)
        Object instanceResult = privateInstanceMethod(1, targetParam); // object initialization position

        // No new exception feature (no 'new Exception()' statements)
        return instanceResult;
    }

    // Private instance method (matches instance type/modifier/method_feature/pos/return_type)
    private Object privateInstanceMethod(int num, TargetClass<String> target) { // 1, target, instance
        // new ClassName().method()
        return new TargetClass<String>().targetMethod(num);
    }
}

// Permits subclass (source_class feature: permits)
final class SourceSubClass extends SourceClass {}

// Target class: normal, public modifier, type parameter + anonymous inner class target_feature
public class TargetClass<T> {
    // Type parameter (target_feature)
    private T typeParam;

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + typeParam);
        }
    };

    // Method for new ClassName().method() call
    public Object targetMethod(int num) {
        return num + "_targetMethod";
    }

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved method
        Object methodToRefactor(TargetClass<String> targetParam, Object... args) {
            SourceClass source = new SourceSubClass();
            return source.methodToRefactor(targetParam, args);
        }
    }
}