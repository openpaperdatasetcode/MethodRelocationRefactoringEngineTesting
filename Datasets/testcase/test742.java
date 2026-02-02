import java.util.ArrayList;
import java.util.List;

// Source class: abstract, public modifier, same package, static nested + anonymous inner class
public abstract class SourceClass {
    // Private outer field for access_outer_private feature
    private static int outerPrivateField = 1; // Matches "1" in TryStatement target_feature

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        private int innerField = 1; // this.field = 1 for TryStatement feature

        // TryStatement feature: private modifier, this.field, 1, pos: diff_package_others
        private int privateTryStatement() {
            int result = 0;
            try {
                this.innerField = 1; // this.field = 1
                result = this.innerField + SourceClass.outerPrivateField;
            } catch (Exception e) {
                // No new exception feature (catch existing, no new Exception instantiation)
                result = 1; // Fallback to 1
            }
            return result;
        }

        // Instance method feature: public modifier, 1, inner_class, instance, instanceReference.methodName(), if/else pos, List<String> return
        public List<String> instanceMethod(int num) {
            List<String> result = new ArrayList<>();
            // if/else conditions position for instance method feature
            if (num == 1) { // Matches "1" in method_feature
                SourceStaticNestedClass instanceRef = new SourceStaticNestedClass(); // instanceReference
                result.add(instanceRef.privateTryStatement() + "_instance"); // instanceReference.methodName(arguments)
            } else {
                result.add("default_" + num);
            }
            return result;
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class: " + outerPrivateField);
        }
    };

    // Static method to refactor: base type (int) return, public access, source position
    public static int staticMethod(TargetClass targetParam) { // per_condition: contains target parameter
        // Variable call feature
        int localVar = 1;
        String varStr = "staticVar";

        // Access outer private field (access_outer_private feature)
        localVar += SourceClass.outerPrivateField;

        // Labeled statement feature
        syncLabel:
        synchronized (SourceClass.class) { // Synchronized statement feature
            SourceStaticNestedClass nestedInstance = new SourceStaticNestedClass();
            // TryStatement feature invocation (diff_package_others pos)
            localVar += nestedInstance.privateTryStatement();

            // Instance method feature invocation (if/else pos)
            List<String> instanceResult = nestedInstance.instanceMethod(localVar);
            varStr += "_" + instanceResult.get(0);

            break syncLabel; // Labeled statement usage
        }

        // No new exception feature (no 'new Exception()' statements)
        return localVar + targetParam.getInnerValue();
    }
}

// Target class: abstract, public modifier, static nested class target_feature
public abstract class TargetClass {
    private int innerValue = 5;

    // Static nested class (target_feature)
    public static class target_inner {
        // Placeholder for moved static method
        public static int staticMethod(TargetClass targetParam) {
            return SourceClass.staticMethod(targetParam);
        }
    }

    public int getInnerValue() {
        return innerValue;
    }
}