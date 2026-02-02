import java.util.ArrayList;
import java.util.List;

// Source class: normal, public modifier, same package, static nested + member inner class
public class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Instance field for access_instance_field feature
    private String instanceField = "sourceInstanceField";

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static String staticHelper(String input) {
            return input.toUpperCase();
        }
    }

    // Member inner class (source_class feature)
    public class SourceMemberInnerClass {
        public String innerMethod(String input) {
            return input + "_innerProcessed";
        }
    }

    // Method to refactor: varargs, List<String> return, protected access, source position
    protected List<String> methodToRefactor(String... args) {
        // Variable call feature
        String localVar = "base";
        List<String> result = new ArrayList<>();

        // Type declaration statement feature
        int loopCount;
        SourceMemberInnerClass innerInstance;

        // Access instance field (access_instance_field feature)
        localVar += "_" + this.instanceField;

        // For statement feature
        loopCount = args.length > 0 ? args.length : 3;
        for (int i = 0; i < loopCount; i++) {
            String currentArg = args.length > i ? args[i] : "default_" + i;
            // Variable call + static nested class usage
            String staticProcessed = SourceStaticNestedClass.staticHelper(currentArg);
            // Member inner class usage + variable call
            innerInstance = new SourceMemberInnerClass();
            String innerProcessed = innerInstance.innerMethod(staticProcessed);
            result.add(innerProcessed + "_" + localVar);
        }

        // No new exception feature (no 'new Exception()' statements)
        return result;
    }
}

// Target class: normal, default modifier, member inner class target_feature
class TargetClass {
    // Member inner class (target_feature)
    public class TargetMemberInnerClass {
        public void innerHelper() {}
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method
        protected List<String> methodToRefactor(String... args) {
            SourceClass source = new SourceClass();
            return source.methodToRefactor(args);
        }
    }
}