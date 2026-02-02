package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 1; // target_feature: 1
}

// Same package others class for ConstructorInvocation pos
class SamePackageOthers {
    // ConstructorInvocation feature: static modifier, obj.field, 1, pos=same_package_others
    public static <T extends TargetEnum> TargetEnum.TargetInner createTargetInner(T targetEnum) {
        // pos: same_package_others
        TargetEnum.TargetInner inner = targetEnum.new TargetInner();
        inner.innerField = 1; // obj.field feature, target_feature: 1
        return inner;
    }
}

// Parent class for source enum extends feature (enum can only extend Enum, so use generic wrapper)
class EnumParent<T> {
    protected T outerPrivateField; // For access_outer_private feature

    protected EnumParent(T value) {
        this.outerPrivateField = value;
    }
}

// Source enum class: public, same package as target, type parameter, extends (wrapper)
public enum SourceEnum<T extends CharSequence> extends EnumParent<T> {
    INSTANCE("source_1"); // target_feature: 1

    // Per_condition: source contains target enum field
    private final TargetEnum targetField = TargetEnum.VALUE1;
    private final T privateValue; // Outer private field for access_outer_private

    // Type parameter initialization
    SourceEnum(T value) {
        super(value);
        this.privateValue = value;
    }

    // Source_inner_rec (inner recursive class for method_position)
    public static class SourceInnerRec {
        // call_method: inner_class type, final modifier, accessor, (parameters) -> methodBody, pos=functional interfaces, return void
        public final void callMethod(TargetEnum.TargetInner inner) {
            // pos: functional interfaces (Consumer as functional interface)
            Consumer<TargetEnum.TargetInner> consumer = (param) -> { // (parameters) -> methodBody
                // target_feature: accessor (call get/set methods)
                param.setInnerValue(param.getInnerValue() + "_lambda_1"); // target_feature: 1
            };
            consumer.accept(inner);
        }

        // Overloaded method 1 (overload_exist)
        @ProcessAnnotation(1) // has_annotation feature, target_feature: 1
        protected static Object processTarget(TargetEnum target) {
            // Empty statement
            ;

            // ConstructorInvocation feature call (same_package_others)
            TargetEnum.TargetInner inner = SamePackageOthers.createTargetInner(target);

            // Variable call (target enum and inner class)
            String innerValue = inner.getInnerValue();
            
            // Expression statement
            inner.setInnerValue(innerValue + "_expr_1"); // target_feature: 1

            // Used_by_reflection feature
            try {
                // Reflectively access obj.field
                Field field = TargetEnum.TargetInner.class.getDeclaredField("innerField");
                field.setAccessible(true);
                field.set(inner, 1); // target_feature: 1

                // Reflectively invoke constructor
                Constructor<TargetEnum.TargetInner> ctor = TargetEnum.TargetInner.class.getDeclaredConstructor(TargetEnum.class);
                ctor.setAccessible(true);
                TargetEnum.TargetInner reflectInner = ctor.newInstance(target);
            } catch (Exception e) {
                // No_new_exception feature
                inner.setInnerValue("reflection_error_1"); // target_feature: 1
            }

            // Access_outer_private feature (access outer enum private field)
            inner.setInnerValue(SourceEnum.INSTANCE.privateValue + "_outer_private_1"); // target_feature: 1

            return inner;
        }

        // Overloaded method 2 (overload_exist feature)
        @ProcessAnnotation(1)
        protected static Object processTarget(TargetEnum target, String extra) {
            Object baseResult = processTarget(target);
            return baseResult.toString() + "_overload_" + extra + "_1"; // target_feature: 1
        }
    }

    // Method to refactor: static, Object return, protected access, in source_inner_rec
    protected static Object methodToRefactor(TargetEnum targetParam) {
        // Per_condition: method contains target parameter
        Object result = SourceInnerRec.processTarget(targetParam);

        // Call overloaded method (overload_exist)
        result = SourceInnerRec.processTarget(targetParam, "extra_1"); // target_feature: 1

        // Call call_method (inner_class type)
        SourceInnerRec innerRec = new SourceInnerRec();
        innerRec.callMethod(TargetEnum.VALUE1.new TargetInner());

        // No_new_exception additional handling
        try {
            Integer.parseInt(result.toString());
        } catch (NumberFormatException e) {
            result = "formatted_" + result + "_1"; // target_feature: 1
        }

        return result;
    }
}

/**
 * Target enum class: default modifier, javadoc, anonymous inner class feature
 * @since 1.0
 */
enum TargetEnum {
    /**
     * First enum constant (target_feature: 1)
     */
    VALUE1,
    /**
     * Second enum constant
     */
    VALUE2;

    /**
     * Inner class of TargetEnum with anonymous inner class feature
     */
    public class TargetInner {
        int innerField; // obj.field feature
        private String innerValue = "target_inner_1"; // target_feature: 1

        public TargetInner() {
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Anonymous inner: " + innerValue);
                }
            };
            anonymous.run();
        }

        // Accessor methods (target_feature: accessor)
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }
}