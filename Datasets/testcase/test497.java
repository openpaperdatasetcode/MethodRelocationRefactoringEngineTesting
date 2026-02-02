package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface EnumAnnotation {}

// Subclass for method_feature "sub_class"
class TargetEnumSubClass extends TargetEnum<String> {
    @Override
    protected int subClassMethod(int num) {
        return num * 1; // Number 1 for method_feature
    }
}

// Source enum class (public modifier, same package, two static nested classes)
public enum SourceEnum {
    ENUM_VALUE1, ENUM_VALUE2;

    // Per_condition: source contains target class field
    private final TargetEnum<String> targetField = TargetEnum.create("target_1"); // Number 1

    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_1"; // Number 1

    // First static nested class (source_class feature)
    public static class SourceStaticNested1 {
        String data1 = "nested1_1"; // Number 1
        public String getData() { return data1; }
    }

    // Second static nested class (source_class feature - duplicate)
    public static class SourceStaticNested2 {
        String data2 = "nested2_1"; // Number 1
        public String getData() { return data2; }
    }

    // Inner record for method_position: source_inner_rec
    public record SourceInnerRec() {
        // has_annotation feature
        @EnumAnnotation
        // Abstract method (protected access, returns TargetClass Type, source_inner_rec position)
        protected abstract TargetEnum<String> refactorMethod();

        // Concrete implementation (fulfills all method features)
        @Override
        protected TargetEnum<String> refactorMethod() {
            // Variable call feature
            SourceStaticNested1 nested1 = new SourceStaticNested1();
            SourceStaticNested2 nested2 = new SourceStaticNested2();
            String varCall = nested1.getData() + "_" + nested2.getData();

            // Access_outer_protected feature
            varCall += SourceEnum.this.outerProtectedField;

            // Type declaration statement feature
            class LocalTypeDeclaration {
                String process(String val) {
                    return val + "_processed_1"; // Number 1
                }
            }
            LocalTypeDeclaration localType = new LocalTypeDeclaration();
            varCall = localType.process(varCall);

            // For statement feature
            List<String> collection = new ArrayList<>();
            for (int i = 0; i < 1; i++) { // Number 1
                collection.add(varCall + i);
            }

            // Instance method (private modifier, 1 + sub_class + instance + super.methodName(), collection operations pos)
            private int instanceMethod() {
                // Collection operations position
                collection.forEach(s -> s += "_collection_1"); // Number 1
                TargetEnumSubClass subClass = new TargetEnumSubClass();
                // super.methodName() target_feature + Number 1
                return subClass.subClassMethod(1); // sub_class + instance feature
            }
            int instanceResult = instanceMethod();

            // Access instance method feature
            String instanceMethodResult = SourceEnum.this.instanceMethod();
            varCall += instanceMethodResult;

            // Super keywords feature (enum implicitly extends Enum, call super method)
            String superStr = SourceEnum.ENUM_VALUE1.super.toString();
            varCall += superStr;

            // Empty statement feature
            ; // Empty statement

            // No_new_exception feature (no explicit throw new Exception)
            return TargetEnum.create(varCall + "_" + instanceResult);
        }
    }

    // Instance method for access_instance_method feature
    private String instanceMethod() {
        return "instance_method_1"; // Number 1
    }
}

// Target enum class (public modifier, type parameter + member inner class target_feature)
public enum TargetEnum<T> {
    TARGET_VALUE1, TARGET_VALUE2;

    private T data;

    // Member inner class (target_feature)
    public class TargetMemberInner {
        String validate(T input) {
            return input == null ? "default_1" : input.toString(); // Number 1
        }
    }

    // Factory method for instance creation
    public static <T> TargetEnum<T> create(T data) {
        TargetEnum<T> instance = TargetEnum.TARGET_VALUE1;
        instance.data = data;
        // Use member inner class
        TargetMemberInner inner = instance.new TargetMemberInner();
        instance.data = (T) inner.validate(data);
        return instance;
    }

    // Method for sub_class override
    protected int subClassMethod(int num) {
        return num;
    }

    public T getData() {
        return data;
    }
}