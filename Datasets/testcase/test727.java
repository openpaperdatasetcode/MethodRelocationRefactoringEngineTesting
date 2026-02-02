import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    private String value = "sourceValue";

    // Local inner class (source_class feature)
    class LocalInnerClass {
        // Method in local inner class (method_position: source_inner)
        public Object methodToMove(String... args) throws Exception { // varargs, public, return Object, requires_throws
            // Type declaration statement (method feature)
            List<String> stringList;
            
            // Variable call (method feature)
            String localVar = "testVar";
            localVar = args.length > 0 ? args[0] : localVar;

            // ConstructorInvocation (static modifier, obj.field, 2)
            static {
                SourceClass source = new SourceClass();
                source.targetField.innerField = 2; // obj.field, 2
            }

            // Instance code blocks with instance method feature
            {
                // instance, 1, inner_class, new ClassName().method()
                LocalInnerClass inner = new LocalInnerClass();
                List<String> result = inner.instanceMethod(1); // 1, inner_class instance call
            }

            // Lambda: () -> System.out.println(this.value) (method feature)
            Runnable lambda = () -> System.out.println(this.value);
            lambda.run();

            // Used by reflection (method feature)
            Method reflectMethod = LocalInnerClass.class.getMethod("methodToMove", String[].class);
            Object reflectResult = reflectMethod.invoke(this, (Object) new String[]{"reflect"});

            if (localVar == null) {
                throw new IllegalArgumentException("Local variable is null"); // requires_throws
            }
            return reflectResult;
        }

        // Instance method for inner_class instance feature
        public List<String> instanceMethod(int num) { // return_type List<String>, instance
            return new ArrayList<>();
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            LocalInnerClass inner = new LocalInnerClass();
            try {
                inner.methodToMove("anonymous");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // Call method: source type, protected, recursion, super.methodName(), property assignment, return String
    protected String callMethod(int count) {
        // Property assignment position
        String result = "";
        if (count <= 0) {
            // Super method call
            result = super.toString(); // super.methodName()
        } else {
            // Recursion (call_method target_feature)
            result = callMethod(count - 1) + count; // recursion
        }
        this.value = result; // Property assignment pos
        return result;
    }
}

public class TargetClass {
    int innerField;

    // Target_feature: member inner class
    public class TargetInnerClass {
        private String innerValue;
    }

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved method
        public Object methodToMove(String... args) throws Exception {
            SourceClass source = new SourceClass();
            return source.new LocalInnerClass().methodToMove(args);
        }
    }
}