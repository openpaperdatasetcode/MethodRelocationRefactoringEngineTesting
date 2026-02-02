import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@interface MethodAnno {}

// Source class: normal, abstract modifier, same package, type parameter + two static nested classes
abstract class SourceClass<T extends CharSequence> {
    // Source inner recursive structure (method_position: source_inner_rec)
    public class SourceInnerRec {
        private int innerField; // Field for this.field = 1 feature

        /**
         * Method to refactor - List<String> return, private access, source_inner_rec position
         * @param targetParam TargetClass parameter (satisfies per_condition)
         * @param keywords Method types parameter is:keywords
         * @return List<String> result
         * @throws IllegalArgumentException if keywords is null (requires_throws)
         */
        @MethodAnno // has_annotation feature
        private List<String> methodToRefactor(TargetClass targetParam, String... keywords) throws IllegalArgumentException {
            // Variable call feature
            String localVar = "test";
            List<String> result = new ArrayList<>();

            // VariableDeclarationStatement: private modifier, this.field, 1, pos: diff_package_target
            privateVariableDeclaration();

            // Super constructor invocation feature
            class NestedClass extends SourceInnerRec {
                public NestedClass() {
                    super(); // super constructor invocation
                }
            }
            new NestedClass();

            // Depends on inner class feature
            InnerHelper helper = new InnerHelper();
            result = helper.processKeywords(keywords);

            // Assert statement feature
            assert keywords != null : "Keywords cannot be null";

            // Requires_throws feature
            if (keywords == null) {
                throw new IllegalArgumentException("Keywords parameter is null");
            }

            // Variable call + keywords processing
            for (String keyword : keywords) {
                localVar = keyword;
                result.add(localVar + "_processed");
            }

            return result;
        }

        // Private VariableDeclarationStatement implementation (pos: diff_package_target)
        private void privateVariableDeclaration() {
            this.innerField = 1; // this.field, 1 (VariableDeclarationStatement feature)
        }

        // Inner helper class for depends_on_inner_class feature
        private class InnerHelper {
            public List<String> processKeywords(String[] keywords) {
                List<String> list = new ArrayList<>();
                if (keywords != null) {
                    for (String k : keywords) list.add(k);
                }
                return list;
            }
        }

        // Call method: inner_class type, final modifier, accessor, OuterClass.InnerClass.methodName(), ternary pos, void return
        private final void callMethod(String arg) {
            // Ternary operators position for call_method
            String val = (arg == null) ? 
                SourceClass.this.SourceStaticNestedClass1.accessorMethod() : 
                SourceClass.SourceStaticNestedClass2.accessorMethod(); // OuterClass.InnerClass.methodName()
            
            // Accessor feature (call accessor method)
            new InnerAccessor().setInnerField(innerField);
        }

        // Inner accessor class for accessor feature
        private class InnerAccessor {
            public void setInnerField(int field) {
                SourceInnerRec.this.innerField = field; // Accessor
            }

            public int getInnerField() {
                return SourceInnerRec.this.innerField; // Accessor
            }
        }
    }

    // First static nested class (source_class feature)
    public static class SourceStaticNestedClass1 {
        public static String accessorMethod() {
            return "static1";
        }
    }

    // Second static nested class (source_class feature)
    public static class SourceStaticNestedClass2 {
        public static String accessorMethod() {
            return "static2";
        }
    }
}

// Target class: normal, abstract modifier, anonymous inner class target_feature
abstract class TargetClass {
    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method
        private List<String> methodToRefactor(TargetClass targetParam, String... keywords) throws IllegalArgumentException {
            SourceClass<String> source = new SourceClass<String>() {}; // Instantiate abstract source
            SourceClass<String>.SourceInnerRec innerRec = source.new SourceInnerRec();
            return innerRec.methodToRefactor(targetParam, keywords);
        }
    }
}