import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Source strictfp normal class (same package, member inner class, static nested class)
strictfp class SourceClass {
    // per_condition: source contains the field of the target
    private final ProtectedTargetClass<String> targetField = new ProtectedTargetClass<>("init_value_5846");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<T extends CharSequence> {
        static String format(T val) {
            return val.toString().toUpperCase() + "_static_source";
        }
    }

    // Parent method for overriding
    void processTarget(ProtectedTargetClass<?> param) {
        System.out.println("Parent processTarget: " + param.getValue());
    }

    // Member inner class (source_class feature)
    class MemberInnerClass<T extends CharSequence & Comparable<T>> { // with_bounds feature
        void enhanceValue(ProtectedTargetClass<T> target) {
            T enhanced = (T) (target.getValue() + "_inner_enhanced");
            target.setValue(enhanced);
        }
    }

    /**
     * Method with has_annotation feature
     * @param param Target class parameter (type parameter + member inner class)
     */
    @ProcessAnnotation // has_annotation feature
    @Override // overriding type
    void processTarget(ProtectedTargetClass<String> param) { // per_condition: target parameter
        // Super constructor invocation (implicit for inner class)
        super();

        // SynchronizedStatement (protected modifier, this.field, 2, pos: same_package_target)
        protected void syncBlock() {
            synchronized (param) { // SynchronizedStatement
                param.customField = 2; // this.field (target's field) + 2 (target_feature)
                param.getValue(); // Access target field in same_package_target position
            }
        }

        // Execute synchronized block
        syncBlock();

        // with_bounds feature (type parameter with bounds)
        MemberInnerClass<String> innerObj = new MemberInnerClass<>();
        innerObj.enhanceValue(param);

        // Variable call (target member inner class + type parameter)
        ProtectedTargetClass<String>.InnerClass targetInner = param.new InnerClass();
        targetInner.setInnerValue(param.getValue() + "_target_inner");
        String innerVal = targetInner.getInnerValue();

        // Core logic
        System.out.println("Overridden processTarget: " + innerVal);

        // return statement (void return)
        return;
    }
}

// Target protected normal class (type parameter, member inner class target_feature)
protected class ProtectedTargetClass<T> {
    // this.field for SynchronizedStatement feature
    public int customField;
    private T value;

    // Member inner class (target_feature)
    public class InnerClass {
        private T innerValue;

        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Type parameter feature (target_feature)
    public ProtectedTargetClass(T value) {
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