package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;

// Parent class for super keywords feature
class SourceParentClass {
    protected String parentMethod() {
        return "parent_value";
    }
}

// Source abstract class (public modifier, same package, type parameter + member inner + local inner class)
public abstract class SourceGenericClass<T extends CharSequence> extends SourceParentClass {
    // Per_condition: source contains target class field
    protected TargetAbstractClass targetField = new TargetAbstractClass() {};

    // Member inner record for method_position: source_inner_rec
    public record SourceInnerRec() {
        /**
         * Varargs method to refactor - method javadoc feature
         * @param targetParams target class parameters
         * @return Object result
         */
        public Object refactorMethod(TargetAbstractClass... targetParams) {
            // Variable call feature
            String varCall = SourceGenericClass.this.targetField.toString();

            // NullPointerException feature
            if (targetParams == null || targetParams.length == 0) {
                throw new NullPointerException("Target parameters cannot be null/empty");
            }

            // Type declaration statement feature
            class LocalTypeDeclaration<U> {
                U process(U val) {
                    // Uses_outer_this feature
                    return (U) (SourceGenericClass.this.parentMethod() + val.toString());
                }
            }
            LocalTypeDeclaration<String> localType = new LocalTypeDeclaration<>();
            varCall = localType.process(varCall);

            // Super keywords feature
            varCall += super.parentMethod();

            // Override_violation feature (attempt to override final method concept)
            try {
                // Simulate override violation (e.g., incompatible return type)
                @SuppressWarnings("unused")
                String invalidOverride = (String) SourceGenericClass.this.targetField.invalidOverride();
            } catch (ClassCastException e) {
                // No_new_exception feature (no explicit throw new Exception)
                varCall += "_override_violation";
            }

            // Local inner class (source_class feature)
            class SourceLocalInner {
                String processTarget(TargetAbstractClass target) {
                    return target.getStaticNestedValue() + "_local_inner";
                }
            }
            SourceLocalInner localInner = new SourceLocalInner();
            varCall += localInner.processTarget(targetParams[0]);

            // No_new_exception feature (no explicit throw new Exception beyond NPE)
            return varCall;
        }
    }

    // Member inner class for call_method (inner_class type)
    public class SourceInnerClass {
        // Call method (default modifier, inner_class type, ternary operators pos, returns List<String>)
        List<String> callMethod() {
            SourceInnerRec innerRec = new SourceInnerRec();
            TargetAbstractClass target = new TargetAbstractClass() {};

            // Ternary operators position + static + obj.m1().m2().m3() target_feature
            List<String> result = (innerRec != null)
                ? TargetAbstractClass.TargetStaticNested.staticMethod1() // static feature
                    .staticMethod2()
                    .staticMethod3() // obj.m1().m2().m3() chain
                : new ArrayList<>();

            // Trigger refactorMethod (per_condition: contains target param)
            innerRec.refactorMethod(target);
            return result;
        }
    }
}

// Target abstract class (protected modifier, static nested class target_feature)
protected abstract class TargetAbstractClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private static String nestedValue = "target_static_nested";

        public static List<String> staticMethod1() {
            return new ArrayList<>();
        }

        public static List<String> staticMethod2() {
            return new ArrayList<>();
        }

        public static List<String> staticMethod3() {
            return new ArrayList<>();
        }
    }

    // Method for override violation simulation
    public final Object invalidOverride() {
        return "invalid_override";
    }

    public String getStaticNestedValue() {
        return TargetStaticNested.nestedValue;
    }
}