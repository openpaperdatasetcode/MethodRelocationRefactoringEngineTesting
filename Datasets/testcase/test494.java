package com.refactor.movemethod;

import java.util.List;

// Sealed interface for source_class permits feature
sealed interface EnumSealedInterface permits SourceEnum {}

// Source enum class (public modifier, same package, permits + local inner + member inner class)
public enum SourceEnum implements EnumSealedInterface {
    VALUE1, VALUE2;

    // Field for IfStatement this.field (value 1)
    private int field = 1;

    // Member inner class (source_class feature)
    public class SourceMemberInner {
        // Varargs method to refactor (public access, void return, source_inner position, target param - per_condition)
        @SuppressWarnings("rawtypes") // has_annotation feature (duplicate as specified)
        @Deprecated // second has_annotation feature
        public void refactorMethod(TargetEnum.TargetInnerRec... targetParams) {
            // Variable call feature
            String varCall = SourceEnum.this.name();
            List<String> boundedList = List.of(varCall);

            // With_bounds feature (type bound usage)
            class BoundedClass<T extends Number & Comparable<T>> {
                T process(T val) {
                    return val.compareTo((T) Integer.valueOf(1)) > 0 ? val : null;
                }
            }
            BoundedClass<Integer> boundedInstance = new BoundedClass<>();
            boundedInstance.process(1);

            // IfStatement (private modifier, this.field=1, pos: source)
            private if (this.field == 1) { // this.field target_feature with value 1
                varCall += "_field_1";
            } else {
                varCall += "_other";
            }

            // Super keywords feature
            String superStr = super.toString();
            varCall += superStr;

            // Local inner class (source_class feature)
            class SourceLocalInner {
                void processParams(TargetEnum.TargetInnerRec... params) {
                    for (TargetEnum.TargetInnerRec param : params) {
                        System.out.println(param.data());
                    }
                }
            }
            SourceLocalInner localInner = new SourceLocalInner();
            localInner.processParams(targetParams); // variable call to inner class method

            // No_new_exception feature (no explicit throw new Exception)
            if (targetParams.length == 0) {
                varCall += "_empty_params";
            }
        }
    }
}

// Target enum class (default modifier, local inner class target_feature)
enum TargetEnum {
    TARGET1, TARGET2;

    // Target_inner_rec (inner record for target class)
    public record TargetInnerRec(String data) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String validate(String input) {
                return input == null ? "default" : input;
            }

            public TargetInnerRec processRec(TargetInnerRec rec) {
                return new TargetInnerRec(validate(rec.data()));
            }
        }

        public TargetInnerRec {
            TargetLocalInner localInner = new TargetLocalInner();
            data = localInner.validate(data);
        }
    }
}