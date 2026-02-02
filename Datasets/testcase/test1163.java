import java.util.ArrayList;
import java.util.List;

// Source normal class (default modifier, same package, local inner class, static nested class)
class SourceClass {
    // Field for OuterClass.this.x feature
    private String outerField = "SOURCE_OUTER_FIELD_5852";

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<T> {
        static String format(T val) {
            return val.toString().toUpperCase() + "_static_source";
        }
    }

    // Parent method for overriding
    List<String> processTarget(PublicTargetClass<?> param) {
        return new ArrayList<>();
    }

    // Inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method to be refactored (overriding, List<String> return, final access)
        @Override
        final List<String> processTarget(PublicTargetClass<String> param) { // per_condition: target parameter
            List<String> result = new ArrayList<>();

            // SuperConstructorInvocation (private modifier, ClassName.field, 1, pos: same_package_others)
            private void superConstructorBlock() {
                // same_package_others position (logical separation)
                class SamePackageOthers {
                    void invokeSuper() {
                        // ClassName.field (target static field)
                        String classField = PublicTargetClass.CLASS_FIELD;
                        int num = 1; // target_feature: 1
                        
                        // SuperConstructorInvocation (target member inner class constructor)
                        PublicTargetClass<String>.InnerClass innerObj = param.new InnerClass(classField + "_" + num);
                        result.add(innerObj.getInnerValue());
                    }
                }
                new SamePackageOthers().invokeSuper();
            }

            // Execute super constructor block
            superConstructorBlock();

            // OuterClass.this.x feature (access outer class field via qualified this)
            String outerVal = SourceClass.this.outerField; // OuterClass.this.x
            result.add(outerVal + "_outer_this_access");

            // Variable call (target type parameter + member inner class)
            param.setValue(outerVal + "_processed_" + param.getValue());
            PublicTargetClass<String>.InnerClass targetInner = param.new InnerClass(param.getValue());
            result.add(targetInner.getInnerValue());

            // Local inner class (source_class feature)
            class LocalInnerClass {
                void enhanceList(List<String> list) {
                    list.replaceAll(s -> StaticNestedSourceClass.format(s));
                }
            }
            new LocalInnerClass().enhanceList(result);

            // No new exception
            return result;
        }
    }
}

// Target normal class (public modifier, type parameter, member inner class target_feature)
public class PublicTargetClass<T> {
    // ClassName.field for SuperConstructorInvocation feature
    public static final String CLASS_FIELD = "TARGET_CLASS_FIELD_5852";
    private T value;

    // Member inner class (target_feature)
    public class InnerClass {
        private T innerValue;

        // SuperConstructorInvocation (inner class constructor)
        public InnerClass(T value) {
            super(); // Implicit super constructor for Object
            this.innerValue = value;
        }

        // Variable call: getter/setter
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Type parameter feature (target_feature)
    public PublicTargetClass(T value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}