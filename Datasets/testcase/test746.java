import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation for has_annotation and call_method (attribute values of annotations) feature
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface OverrideAnno {
    String methodRef() default "PackageWrapper.SourceRecord.SourceInnerClass.callMethod";
}

// Wrapper class to enable private modifier for record source class (top-level records can't be private)
class PackageWrapper {
    // Source class: record, private modifier, same package, member inner + local inner class
    private record SourceRecord(String sourceField) {
        // Member inner class (source_class feature)
        class SourceInnerClass {
            // Overriding method to refactor: void return, default access, source_inner position
            @OverrideAnno(methodRef = "PackageWrapper.SourceRecord.SourceInnerClass.callMethod") // call_method pos: annotation attribute
            @Override
            public void toString() {
                // Variable call feature
                String localVar = "innerVar";
                int loopCount = 1;

                // Try statement feature
                try {
                    // Access instance method feature
                    localVar = SourceRecord.this.sourceField() + "_" + localVar;
                    localVar = this.instanceHelperMethod(localVar);
                } catch (NullPointerException e) {
                    // No new exception feature (catch existing NPE, no new Exception instantiation)
                    localVar = "default_" + localVar;
                }

                // Generic method feature: protected modifier, 1, inner_class, generic, superTypeReference.methodName(), for pos, void return
                for (int i = 0; i < loopCount; i++) { // pos: for
                    this.<String>genericMethod(1, new InnerHelper(), localVar); // 1, inner_class, generic
                }

                // No new exception feature (no 'new Exception()' statements)
                System.out.println(localVar);
            }

            // Generic method (matches type/modifier/method_feature/pos/return_type)
            protected <T> void genericMethod(int num, InnerHelper inner, T param) { // 1, inner_class, generic
                // superTypeReference.methodName(arguments)
                CharSequence.super.toString();
                inner.processParam(param);
            }

            // Access instance method (access_instance_method feature)
            private String instanceHelperMethod(String input) {
                return input + "_processed";
            }

            // Call method: source type, default modifier, overriding, OuterClass.InnerClass.methodName(), annotation attr pos, void return
            void callMethod() {
                // OuterClass.InnerClass.methodName()
                PackageWrapper.SourceRecord.SourceInnerClass.this.toString(); // overriding feature
            }

            // Local inner class (source_class feature)
            private void methodWithLocalInner(TargetRecord targetParam) {
                class LocalInnerClass {
                    public void innerProcess(TargetRecord target) {
                        System.out.println(target.targetField() + "_localInner");
                    }
                }
                LocalInnerClass localInner = new LocalInnerClass();
                localInner.innerProcess(targetParam);
            }

            // Inner helper class for generic method feature
            private class InnerHelper {
                public <T> void processParam(T param) {
                    System.out.println(param + "_helper");
                }
            }
        }

        // Method to trigger inner class logic (per_condition: method contains target parameter)
        public void processTarget(TargetRecord targetParam) {
            SourceInnerClass inner = new SourceInnerClass();
            inner.methodWithLocalInner(targetParam); // Pass target parameter to inner class
        }
    }

    // Target class: record, public modifier, anonymous inner class target_feature
    public record TargetRecord(String targetField) {
        // Anonymous inner class (target_feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetField() + "_anonymous");
            }
        };

        // Target inner recursive structure for method relocation
        public static class target_inner_rec {
            // Placeholder for moved overriding method
            @OverrideAnno
            @Override
            public void toString() {
                PackageWrapper wrapper = new PackageWrapper();
                PackageWrapper.SourceRecord source = new PackageWrapper.SourceRecord("sourceVal");
                PackageWrapper.SourceRecord.SourceInnerClass inner = source.new SourceInnerClass();
                inner.toString();
            }
        }
    }
}