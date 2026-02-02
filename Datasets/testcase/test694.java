// Diff package others for TypeDeclarationStatement pos
package com.refactoring.others;
import com.refactoring.movemethod.SourceClass;
import com.refactoring.movemethod.TargetClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 1;
}

// TypeDeclarationStatement: private modifier, super.field, 1, pos=diff_package_others
private class TypeDeclarationClass<T> {
    // super.field feature, target_feature: 1
    protected String superField = "super_field_1";

    public List<String> process(TargetClass<T>.TargetInnerRec inner) {
        return inner.getProcessedList();
    }
}

// Back to source package
package com.refactoring.movemethod;

import com.refactoring.others.TypeDeclarationClass;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for overriding feature
class ParentSourceClass<T> {
    // Parent method for overriding
    public List<String> parentMethod(TargetClass<T>.TargetInnerRec inner) throws Exception {
        return new ArrayList<>();
    }
}

// Source class: private normal class, same package as target, member inner + static nested class
private class SourceClass<T> extends ParentSourceClass<T> {
    // Outer private field for access_outer_private feature
    private final String outerPrivateField = "outer_private_1"; // target_feature: 1

    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_1");

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) (val + "_static_1"); // target_feature: 1
        }
    }

    // Member inner class (source_inner - method_position)
    public class SourceInnerClass extends TypeDeclarationClass<T> {
        // has_annotation feature
        @ProcessAnnotation(1)
        // Method to refactor: overriding, List<String> return, strictfp access, in source_inner
        @Override
        public strictfp List<String> parentMethod(TargetClass<T>.TargetInnerRec innerParam) throws Exception { // requires_throws
            // Per_condition: method contains target parameter
            if (innerParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null"); // requires_throws
            }

            // TypeDeclarationStatement: private modifier, super.field, 1, pos=diff_package_others
            TypeDeclarationClass<T> typeDecl = new TypeDeclarationClass<>();
            String superFieldValue = typeDecl.superField; // super.field feature, target_feature: 1

            List<String> result = new ArrayList<>();
            int count = 0;

            while (true) {
                // Break statement
                if (count >= 1) { // target_feature: 1
                    break; // break statement
                }

                // Access_outer_private feature (access outer private field)
                String privateValue = SourceClass.this.outerPrivateField;
                
                // Variable call (target inner recursive class)
                T innerValue = innerParam.getInnerValue();
                result.add(innerValue + "_" + privateValue + "_" + superFieldValue);

                // Call call_method (target type)
                Object callResult = innerParam.callMethod();
                result.add(callResult.toString());

                count++;
            }

            // Variable call for targetField (per_condition)
            result.add(targetField.getInnerRec().getInnerValue() + "_field_1"); // target_feature: 1

            return result;
        }
    }
}

// Target class: private normal class, member inner class feature
private class TargetClass<T> {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec() {
            this.innerValue = (T) "inner_rec_1"; // target_feature: 1
        }

        // call_method: target type, protected modifier, overriding, new ClassName().method(), pos=functional interfaces, return Object
        protected Object callMethod() {
            // pos: functional interfaces (Function)
            Function<TargetInnerRec, Object> func = (param) -> {
                // new ClassName().method() + overriding feature
                TargetSubClass<T> subClass = new TargetSubClass<>((T) "sub_1");
                return subClass.overrideMethod(param); // overriding feature
            };
            return func.apply(this);
        }

        // Variable call methods
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }

        public List<String> getProcessedList() {
            List<String> list = new ArrayList<>();
            list.add(innerValue.toString() + "_processed_1"); // target_feature: 1
            return list;
        }
    }

    // Subclass for overriding feature
    private class TargetSubClass<U> extends TargetClass<U> {
        public TargetSubClass(U value) {
            super(value);
        }

        // Overriding method
        public Object overrideMethod(TargetInnerRec inner) {
            return inner.getInnerValue() + "_overridden_1"; // target_feature: 1
        }
    }

    // Getter for inner recursive class (per_condition variable call)
    public TargetInnerRec getInnerRec() {
        return new TargetInnerRec();
    }

    public T getValue() {
        return value;
    }
}