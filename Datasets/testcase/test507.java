package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for super.field feature
class SourceParentClass {
    // Super.field with value 1 for VariableDeclarationStatement target_feature
    protected int superField = 1;
}

// Sub class for instance method_feature "sub_class"
class SourceSubClass extends SourceClass {
    @Override
    protected TargetClass<TargetClass.InnerRec> instanceMethod(TargetClass.InnerRec... params) {
        return new TargetClass<>("sub_class_1").new InnerRec("sub_inner_1");
    }
}

// Source normal class (public modifier, same package, empty feature list)
public class SourceClass extends SourceParentClass {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "static_1"; // Value 1 for target_feature

    // Inner record for method_position: source_inner_rec
    public record SourceInnerRec() {
        // Varargs method to refactor (protected access, returns List<String>, target param - per_condition)
        protected List<String> refactorMethod(TargetClass.InnerRec... targetParams) {
            List<String> result = new ArrayList<>();

            // Variable call feature
            String varCall = STATIC_FIELD + "_var";
            result.add(varCall);

            // VariableDeclarationStatement (private modifier, super.field=1, pos: source)
            private int localVar = SourceClass.super.superField; // super.field target_feature (value 1)
            result.add(String.valueOf(localVar));

            // Continue statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 1) { // Value 1 for target_feature
                    continue;
                }
                result.add(String.valueOf(i));
            }

            // Instance method (protected modifier, 1 + sub_class + instance + this.methodName(), static code blocks pos)
            static {
                SourceClass source = new SourceClass();
                // this.methodName(arguments) + sub_class + Number 1
                TargetClass.InnerRec innerRec = new TargetClass<>("instance_1").new InnerRec("inner_1");
                TargetClass<TargetClass.InnerRec> instanceResult = source.instanceMethod(innerRec);
                result.add(instanceResult.getData());
            }

            // Depends_on_static_field feature
            result.add(STATIC_FIELD + "_depends");

            // No_new_exception feature (no explicit throw new Exception)
            return result;
        }

        // Instance method for method_feature (returns TargetClass Type)
        protected TargetClass<TargetClass.InnerRec> instanceMethod(TargetClass.InnerRec... params) {
            return new TargetClass<>("instance_base_1").new InnerRec(params.length > 0 ? params[0].getData() : "default_1");
        }
    }

    // Call method (private modifier, source type, recursion + lambda, ternary pos, returns String)
    private String callMethod(int count) {
        // Ternary operators position
        String result = (count <= 1) ? // Value 1 for target_feature
            "base_case_1" :
            // (parameters) -> methodBody target_feature
            ((Function<Integer, String>) (param) -> callMethod(param - 1)).apply(count); // recursion target_feature

        // Trigger refactorMethod (per_condition: contains target param)
        SourceInnerRec innerRec = new SourceInnerRec();
        TargetClass<String> target = new TargetClass<>("call_target_1");
        innerRec.refactorMethod(target.new InnerRec("call_inner_1"));

        return result + "_recursion";
    }
}

// Target normal class (public modifier, type parameter target_feature)
public class TargetClass<T> {
    // Type parameter feature (core target_feature)
    private T data;

    public TargetClass(T data) {
        this.data = data;
    }

    // Target_inner_rec (inner record for target class)
    public class InnerRec {
        private String innerData;

        public InnerRec(String innerData) {
            this.innerData = innerData;
        }

        public String getData() {
            return innerData;
        }
    }

    public T getData() {
        return data;
    }
}