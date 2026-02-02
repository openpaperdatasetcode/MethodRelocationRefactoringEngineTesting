import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Parent class for enum extends (enum can only extend Enum implicitly, use static nested class workaround for extends feature)
class EnumParent {
    protected static int parentField = 1; // Matches "1" in ReturnStatement target_feature
}

// Source enum class (default modifier, same package as target, "extends" via nested parent reference)
enum SourceEnum {
    INSTANCE;

    // First static nested class (source_class feature)
    static class FirstStaticNested extends EnumParent {}

    // Second static nested class (source_class feature)
    static class SecondStaticNested {
        public static String formatData(String data) {
            return "Formatted: " + data;
        }
    }

    // Instance method to be moved (protected, List<String> return, source position)
    protected List<String> processTarget(TargetEnum targetParam) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            // NullPointerException (no_new_exception: no explicit new Exception())
            throw new NullPointerException("Target enum parameter cannot be null");
        }

        List<String> result = new ArrayList<>();
        int counter = 0;

        // Do statement
        do {
            // Variable call to target inner class
            TargetEnum.InnerClass inner = targetParam.new InnerClass();
            inner.setData("Data: " + counter);
            // Depends_on_inner_class: rely on target inner class for data processing
            result.add(inner.getData());
            counter++;
        } while (counter < FirstStaticNested.parentField); // Use parent field (value 1)

        // ReturnStatement (public modifier, pos: source, target_feature: obj.field, 1)
        public List<String> returnStatementHelper() {
            // obj.field access (target_feature: obj.field)
            int fieldVal = targetParam.ordinal();
            result.add("Field value: " + (fieldVal + 1)); // target_feature: 1
            return result;
        }

        // Used by reflection feature
        try {
            Method method = TargetEnum.InnerClass.class.getDeclaredMethod("getData");
            TargetEnum.InnerClass inner = targetParam.new InnerClass();
            String reflectedData = (String) method.invoke(inner);
            result.add("Reflected data: " + reflectedData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnStatementHelper();
    }
}

// Target enum class (default modifier, same package as source)
enum TargetEnum {
    VALUE1, VALUE2;

    // Member inner class (target_feature)
    public class InnerClass {
        private String data;

        // Variable call methods
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}