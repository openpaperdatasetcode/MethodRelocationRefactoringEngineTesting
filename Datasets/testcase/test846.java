import java.util.ArrayList;
import java.util.List;

// Wrapper class to enable private modifier for abstract source class (top-level can't be private)
class PackageLevelWrapper {
    // Source class: abstract, private modifier, same package, static nested + member inner class
    private abstract class SourceClass {
        // Satisfy per_condition: source contains target class field
        private TargetClass targetField = new TargetClass() {};

        // Static nested class (source_class feature)
        private static class SourceStaticNestedClass {
            public static String processString(String input) {
                return input.trim();
            }
        }

        // Member inner class (source_class feature)
        private class SourceMemberInnerClass {
            public String modifyString(String s) {
                return s + "_modified";
            }
        }

        // Source inner class (method_position: source_inner)
        public class SourceInnerClass {
            // Method to refactor: instance, List<String> return, final access, source_inner position
            public final List<String> methodToRefactor(List<String> paramList) { // method types parameter is:List
                // Variable call feature
                String localVar = "initialValue";
                List<String> result = new ArrayList<>();

                // Labeled statement feature
                processLoop:
                for (int i = 0; i < paramList.size(); i++) {
                    // If statement feature
                    if (paramList.get(i) == null || paramList.get(i).isEmpty()) {
                        break processLoop; // Use labeled statement
                    }

                    // Try statement feature
                    try {
                        // Variable call + static nested class usage
                        String processed = SourceStaticNestedClass.processString(paramList.get(i));
                        // Member inner class usage + variable call
                        SourceMemberInnerClass inner = new SourceMemberInnerClass();
                        localVar = inner.modifyString(processed);
                        result.add(localVar);
                    } catch (NullPointerException e) {
                        // No new exception feature (catch existing NPE, no new Exception instantiation)
                        result.add("default_value");
                    }
                }

                // No new exception feature (no 'new Exception()' statements in entire method)
                return result;
            }
        }
    }
}

// Target class: abstract, abstract modifier, static nested class target_feature
abstract class TargetClass {
    // Static nested class (target_feature)
    public static class target {
        // Placeholder for moved method
        public final List<String> methodToRefactor(List<String> paramList) {
            PackageLevelWrapper wrapper = new PackageLevelWrapper();
            PackageLevelWrapper.SourceClass source = wrapper.new SourceClass() {}; // Instantiate abstract source
            PackageLevelWrapper.SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.methodToRefactor(paramList);
        }
    }
}