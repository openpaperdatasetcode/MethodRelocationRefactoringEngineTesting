import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for has_annotation feature
@interface GenericMethodAnno {}

// Wrapper class to enable private modifier for source class (top-level can't be private)
class PackageLevelWrapper {
    // Source class: normal, private modifier, same package, static nested + local inner class
    private class SourceClass {
        // Instance field for access_instance_field feature
        private String instanceField = "sourceInstanceField";

        // Static nested class (source_class feature)
        private static class SourceStaticNestedClass {
            public static String processString(String input) {
                return input.toUpperCase();
            }
        }

        // Source inner recursive structure (method_position: source_inner_rec)
        public class SourceInnerRec {
            // Generic method to refactor: List<String> return, default access, source_inner_rec position
            @GenericMethodAnno // has_annotation feature
            public <T> List<String> genericMethod(TargetClass targetParam, T... args) { // per_condition: contains target parameter
                // Variable call feature
                String localVar = "baseValue";
                List<String> result = new ArrayList<>();

                // Access instance field (access_instance_field feature)
                localVar += "_" + SourceClass.this.instanceField;

                // Uses outer this feature
                SourceClass outerThis = SourceClass.this;
                localVar += "_outerThis_" + outerThis.instanceField;

                // Labeled statement feature
                processLoop:
                for (int i = 0; i < args.length; i++) {
                    // Switch case feature
                    switch (i) {
                        case 0:
                            localVar += "_case0_" + args[i];
                            break;
                        case 1:
                            localVar += "_case1_" + args[i];
                            break;
                        default:
                            break processLoop; // Use labeled statement to break loop
                    }
                    result.add(localVar);
                }

                // Call method: inner_class type, default modifier, overloading, (parameters) -> methodBody, ternary pos, int return
                int callResult = (result.isEmpty()) ? 
                    this.callMethod(1, localVar) : 
                    this.callMethod((String s) -> s.length(), localVar); // (parameters) -> methodBody + ternary pos

                // Variable call + call method result
                result.add("callResult_" + callResult);

                // No new exception feature (no 'new Exception()' statements)
                return result;
            }

            // Call method (overloading variant 1)
            int callMethod(int num, String str) { // overloading feature
                return num + str.length();
            }

            // Call method (overloading variant 2)
            int callMethod(Function<String, Integer> func, String str) { // (parameters) -> methodBody
                return func.apply(str);
            }
        }

        // Method with local inner class (source_class feature)
        public void methodWithLocalInner() {
            class LocalInnerClass {
                public void innerProcess(TargetClass target) {
                    System.out.println(target.getFieldValue() + "_localInner");
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();
            localInner.innerProcess(new TargetClass());
        }
    }

    // Target class: normal, default modifier, anonymous inner class target_feature
    class TargetClass {
        private String fieldValue = "targetFieldValue";

        // Anonymous inner class (target_feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class: " + fieldValue);
            }
        };

        // Target inner recursive structure for method relocation
        public static class target_inner_rec {
            // Placeholder for moved generic method
            @GenericMethodAnno
            public <T> List<String> genericMethod(TargetClass targetParam, T... args) {
                PackageLevelWrapper wrapper = new PackageLevelWrapper();
                PackageLevelWrapper.SourceClass source = wrapper.new SourceClass();
                PackageLevelWrapper.SourceClass.SourceInnerRec innerRec = source.new SourceInnerRec();
                return innerRec.genericMethod(targetParam, args);
            }
        }

        public String getFieldValue() {
            return fieldValue;
        }
    }
}