package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Functional interface for superTypeReference feature
interface SourceFunctionalInterface {
    Object process(int num);
}

// Source record class (public modifier, same package, local inner + anonymous inner class)
public record SourceRecord(String id, TargetRecord targetField) { // per_condition: contains target field

    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "source_static_1"; // Number 1 for method_feature

    // Anonymous inner class (source_class feature)
    private final SourceFunctionalInterface sourceAnonymous = new SourceFunctionalInterface() {
        @Override
        public Object process(int num) {
            return "anonymous_processed_" + num;
        }
    };

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Instance method to refactor (protected access, returns Object, source_inner position)
        protected Object refactorMethod() {
            // Variable call feature
            Object anonResult = sourceAnonymous.process(1); // Number 1 for method_feature
            String varCall = anonResult.toString();

            // Local inner class (source_class feature)
            class SourceLocalInner {
                // Access instance method feature
                String instanceMethod() {
                    return SourceRecord.this.id + "_local_inner_1"; // Number 1 for method_feature
                }
            }
            SourceLocalInner localInner = new SourceLocalInner();
            varCall += localInner.instanceMethod(); // access_instance_method feature

            // Depends_on_static_field feature
            varCall += STATIC_FIELD;

            // Raw type feature (unparameterized generic)
            List rawList = new ArrayList(); // Raw type usage
            rawList.add(varCall);

            // Break statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 1) { // Number 1 for method_feature
                    break;
                }
                rawList.add(String.valueOf(i));
            }

            // Instance method (default modifier, 1 + source + instance + superTypeReference, object initialization pos)
            Object instanceMethod() {
                // Object initialization position
                SourceFunctionalInterface superType = sourceAnonymous; // superTypeReference
                // superTypeReference.methodName(arguments) + Number 1
                return superType.process(1); // source + instance feature
            }

            // No_new_exception feature (no explicit throw new Exception)
            return instanceMethod();
        }

        // Overridden method for call_method overriding feature
        public List<String> callMethodBase() {
            return new ArrayList<>();
        }
    }

    // Inner class for call_method (inner_class type, strictfp modifier, overriding + method reference)
    public strictfp class SourceCallerInner extends SourceInnerClass {
        // Call method (strictfp modifier, inner_class type, collection operations pos, returns List<String>)
        @Override // overriding target_feature
        public List<String> callMethodBase() {
            // Collection operations position
            List<String> result = new ArrayList<>();
            // ClassName::methodName target_feature
            result.add((String) SourceRecord.SourceInnerClass::refactorMethod);
            result.add((String) refactorMethod());
            return result;
        }
    }
}

// Target record class (protected modifier, empty target_feature)
protected record TargetRecord(String data) {}