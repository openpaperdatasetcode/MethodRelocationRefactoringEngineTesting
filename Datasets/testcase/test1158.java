import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.function.Function;

// Super class for super.field/super constructor invocation feature
class TargetSuperClass<T> {
    protected T superField = (T) "TARGET_SUPER_FIELD_5868";
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Source protected generic class (same package, local inner class, member inner class)
protected class SourceClass<T extends CharSequence> {
    // Field for uses_outer_this feature
    private String outerField = "SOURCE_OUTER_FIELD_5868";
    // per_condition: source contains the field of the target
    private final TargetClass<T> targetField = new TargetClass<>((T) "init_value_5868");

    // Member inner class (source_class feature)
    public class MemberInnerClass<U> {
        // Recursion method (protected modifier, 1, inner_class, recursion, OuterClass.InnerClass.methodName())
        protected TargetClass<U> recursiveMethod(TargetClass<U> target, int count) {
            count = 1; // method_feature: 1
            if (count <= 0) {
                return target;
            }
            // Recursion call + OuterClass.InnerClass.methodName() format
            target.setValue((U) (target.getValue() + "_recursion_step_" + count));
            return SourceClass.MemberInnerClass.this.recursiveMethod(target, count - 1);
        }

        // Overriding method for call_method feature
        public int baseMethod(String val) {
            return val.length();
        }
    }

    // Sub inner class for call_method overriding feature
    public class SubInnerClass extends MemberInnerClass<T> {
        @Override
        public int baseMethod(String val) {
            // OuterClass.InnerClass.methodName() call
            return SourceClass.SubInnerClass.super.baseMethod(val) + 1;
        }
    }

    /**
     * Method with has_annotation feature
     * @param `this` target parameter (method types parameter is:keywords - using 'this' as param with backtick)
     * @return base type (int)
     */
    @ProcessAnnotation // has_annotation feature
    private int processTarget(TargetClass<T> `this`) { // method types parameter is:keywords, per_condition: target parameter
        // Super constructor invocation (implicit for inner class)
        super();

        // DoStatement (private modifier, super.field, 1, pos: same_package_target)
        private void doLoopBlock() {
            // same_package_target position (logical separation)
            class SamePackageTargetClass {
                void execute() {
                    T superFieldVal = `this`.superField; // super.field
                    int num = 1; // target_feature: 1

                    // Do statement
                    int count = 0;
                    do {
                        String processed = superFieldVal + "_do_loop_" + count + "_" + num;
                        `this`.setValue((T) processed);
                        count++;
                    } while (count < 3);
                }
            }
            new SamePackageTargetClass().execute();
        }

        // Execute do loop block
        doLoopBlock();

        // Recursion method call in functional interfaces (pos: functional interfaces)
        MemberInnerClass<T> innerObj = new MemberInnerClass<>();
        Function<TargetClass<T>, TargetClass<T>> func = t -> innerObj.recursiveMethod(t, 1); // functional interface
        TargetClass<T> recursiveResult = func.apply(`this`);

        // Call method in Lambda expressions (pos: Lambda expressions, inner_class, overriding, OuterClass.InnerClass.methodName())
        SubInnerClass subInner = new SubInnerClass();
        Function<String, Integer> lambda = s -> SourceClass.SubInnerClass.this.baseMethod(s); // OuterClass.InnerClass.methodName()
        int callResult = lambda.apply(recursiveResult.getValue().toString());

        // super keywords feature (access super class member)
        String superKeywordVal = String.valueOf(`this`.superField); // super keywords

        // throw statement (controlled exception)
        if (`this`.getValue() == null) {
            throw new IllegalArgumentException("Target value cannot be null"); // throw statement
        }

        // uses_outer_this feature (access outer class field)
        String outerThisVal = SourceClass.this.outerField; // uses_outer_this
        `this`.setValue((T) (`this`.getValue() + "_outer_this_" + outerThisVal));

        // used_by_reflection feature (invoke target method via reflection)
        int reflectionResult = 0;
        try {
            Method getValueMethod = TargetClass.class.getDeclaredMethod("getValue");
            T reflectedVal = (T) getValueMethod.invoke(`this`);
            reflectionResult = reflectedVal.length();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Variable call (target type parameter + member inner class)
        TargetClass<T>.InnerClass targetInner = `this`.new InnerClass();
        targetInner.setInnerValue((T) (`this`.getValue() + "_inner_class_val"));
        int innerValLength = targetInner.getInnerValue().length();

        // Combine results (base type return)
        int finalResult = callResult + reflectionResult + innerValLength;

        // No new exception (core logic safe)
        return finalResult;
    }

    // Local inner class (source_class feature)
    class LocalInnerClassProcessor<U> {
        U process(U val) {
            return (U) (val + "_local_inner_processed");
        }
    }
}

// Target generic class (default modifier, type parameter, member inner class target_feature)
class TargetClass<T> extends TargetSuperClass<T> {
    private T value;

    // Member inner class (target_feature)
    public class InnerClass {
        private T innerValue;

        // Variable call: getter/setter
        public T getInnerValue() { return innerValue; }
        public void setInnerValue(T value) { this.innerValue = value; }
    }

    // Type parameter feature (target_feature)
    public TargetClass(T value) {
        super(); // Super constructor invocation
        this.value = value;
    }

    // Variable call: getter/setter
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
}