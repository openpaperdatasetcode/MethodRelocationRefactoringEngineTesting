import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessEnumAnnotation {}

// Parent class for super.field access (enum implicitly extends Enum, use inner class super)
class EnumParent {
    protected int superField = 2; // Matches "2" in VariableDeclarationStatement target_feature
}

// Source enum class (default modifier, same package as target)
enum SourceEnum {
    INSTANCE;

    // Private static field for depends_on_static_field feature
    private static int staticCounter = 0;

    // First member inner class (source_class feature)
    class FirstInnerClass extends EnumParent {
        private String privateData = "PrivateInnerData"; // For access_outer_private

        public String getPrivateData() {
            return privateData;
        }
    }

    // Second member inner class (source_class feature)
    class SecondInnerClass {}

    // Static code block (pos for constructor method_feature)
    static {
        // Constructor with specified features (default modifier, pos: Static code blocks, return TargetClass type)
        class InnerConstructorHelper {
            private TargetEnum targetInstance;

            // Default modifier constructor (method_feature: constructor)
            InnerConstructorHelper() {
                int val = 1; // method_feature: 1
                // method_feature: inner_class + new ClassName().method()
                this.targetInstance = new TargetEnum();
                this.targetInstance.setData("Static block init: " + val);
            }

            // Return TargetClass type
            public TargetEnum getTargetInstance() {
                return targetInstance;
            }
        }
        InnerConstructorHelper helper = new InnerConstructorHelper();
        staticCounter = helper.getTargetInstance().ordinal(); // depends_on_static_field
    }

    // Overloading method 1 (strictfp access, List<String> return)
    @ProcessEnumAnnotation // has_annotation feature
    strictfp List<String> processTarget(TargetEnum targetParam) throws IOException {
        return processTarget(targetParam, "default");
    }

    // Overloading method 2 (overloading feature, strictfp access, List<String> return)
    @ProcessEnumAnnotation // has_annotation feature
    strictfp List<String> processTarget(TargetEnum targetParam, String... args) {
        List<String> result = new ArrayList<>();
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            result.add("Null target parameter");
            return result;
        }

        // Requires try-catch block
        try {
            // Super constructor invocation (enum constant constructor context)
            super();

            // VariableDeclarationStatement (private modifier, pos: source, target_feature: super.field, 2)
            private int localVar = new FirstInnerClass().superField; // target_feature: super.field (value 2)
            
            // Constructor invocation
            FirstInnerClass innerInstance = new FirstInnerClass();
            // Access outer private (access_outer_private feature)
            result.add("Outer private data: " + innerInstance.getPrivateData());

            // Raw type usage (raw_type feature)
            List rawList = new ArrayList(); // Raw type
            rawList.add(localVar);
            result.addAll(rawList);

            // While statement
            int counter = 0;
            while (counter < localVar) {
                // Variable call to target enum
                targetParam.setCounter(counter);
                result.add("Counter: " + targetParam.getCounter());
                // Depends on static field
                staticCounter++;
                counter++;
            }

            // IOException (no explicit new Exception - no_new_exception)
            if (staticCounter > 5) {
                throw new IOException("Static counter exceeds limit");
            }

            // Expression statement
            targetParam.setData("Processed: " + localVar);
        } catch (IOException e) {
            result.add("IOException: " + e.getMessage());
        } catch (Exception e) {
            result.add("Exception: " + e.getMessage());
        }

        return result;
    }
}

// Target enum class (default modifier, same package as source, empty target_feature)
enum TargetEnum {
    VALUE1, VALUE2;

    private String data;
    private int counter;

    // Variable call methods
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}