import java.util.ArrayList;
import java.util.List;

// Source abstract generic class (abstract modifier, same package as target)
abstract class SourceClass<S> {
    // First static nested class (source_class feature)
    static class FirstStaticNestedClass {
        // Static field for depends_on_static_field feature
        public static int staticField = 5;
        
        // Method to be moved (instance, List<String> return, default access, source_inner position)
        List<String> processTarget(TargetClass<T>.NestedStaticClass targetParam) {
            List<String> result = new ArrayList<>();
            // Constructor invocation
            TargetClass<T> targetInstance = new TargetClass<>();
            // Variable call to static field (depends_on_static_field)
            int switchVar = FirstStaticNestedClass.staticField;
            
            // Switch case statement
            switch (switchVar) {
                case 5:
                    // Variable call to target parameter
                    targetParam.addValue("Case 5");
                    result.add(targetParam.getValue());
                    break;
                case 10:
                    targetParam.addValue("Case 10");
                    result.add(targetParam.getValue());
                    break;
                default:
                    targetParam.addValue("Default Case");
                    result.add(targetParam.getValue());
            }
            return result;
        }
    }

    // Second static nested class (source_class feature)
    static class SecondStaticNestedClass {
        private String nestedField;
    }
}

// Target public generic class (public modifier, same package as source)
public class TargetClass<T> {
    // Static nested class (target_feature, target_inner for method's target class)
    static class NestedStaticClass {
        private String value;
        
        public void addValue(String val) {
            this.value = val;
        }
        
        public String getValue() {
            return this.value;
        }
    }
}