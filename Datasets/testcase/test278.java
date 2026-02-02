// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.reflect.Method;
import java.util.function.Supplier;

// Annotation for has_annotation feature
@interface InstanceMethodAnnotation {}

// Super class for super constructor invocation feature
class SuperSourceClass {
    public SuperSourceClass() {}
}

// Source class: normal class, default modifier, different package with target, anonymous inner class, local inner class
class SourceClass extends SuperSourceClass {
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticVal2"; // method_feature "2"
    // per_condition: source contains target class field (optional, method has target parameter)
    private TargetClass targetField = new TargetClass();

    // Member inner class (method_position: source_inner)
    class InnerSourceClass {
        /**
         * Method javadoc feature
         * Instance method processing TargetClass with abstract feature in Lambda
         * @param targetParam the target class parameter (per_condition)
         * @return processed Object result
         * @throws NullPointerException if target parameter is null
         */
        @InstanceMethodAnnotation // has_annotation feature
        private Object moveableInstanceMethod(TargetClass targetParam) {
            // Variable call feature
            String localVar = targetParam.getTargetField();
            localVar = staticSourceField; // Access static field

            // NullPointerException feature
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }

            // Abstract feature: public modifier, method_feature [2, inner_class, abstract, new ClassName().method()], pos=Lambda expressions, return TargetClass Type
            public abstract class AbstractInnerClass { // inner_class + abstract
                public abstract TargetClass abstractMethod(); // abstract method

                // new ClassName().method() + method_feature "2"
                public TargetClass invokeMethod() {
                    return new TargetClass().processField("2"); // method_feature "2"
                }
            }

            // pos=Lambda expressions
            Supplier<TargetClass> lambdaSupplier = () -> {
                AbstractInnerClass abstractInstance = new AbstractInnerClass() {
                    @Override
                    public TargetClass abstractMethod() {
                        return new TargetClass(); // return TargetClass Type
                    }
                };
                return abstractInstance.invokeMethod(); // new ClassName().method()
            };
            TargetClass lambdaResult = lambdaSupplier.get();

            // Super constructor invocation feature
            new SuperSourceClass() {};

            // Access_instance_method feature
            localVar = targetParam.processField(localVar + "_2"); // method_feature "2"

            // Depends_on_static_field feature
            staticSourceField = localVar + "_updatedStatic2"; // method_feature "2"
            targetParam.setTargetField(staticSourceField);

            // Requires_try_catch feature
            try {
                // Used_by_reflection feature
                Method method = targetParam.getClass().getDeclaredMethod("processField", String.class);
                method.setAccessible(true);
                method.invoke(targetParam, localVar);
            } catch (Exception e) {
                // Handle reflection exceptions (requires_try_catch)
                localVar = "reflectionError2"; // method_feature "2"
            }

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    localVar += "_anonymous2"; // method_feature "2"
                }
            };
            anonymousRunnable.run();

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateTarget(TargetClass target) {
                    target.setTargetField(localVar);
                }
            }
            new LocalInnerSource().updateTarget(targetParam);

            // Return Object type (target instance)
            return lambdaResult;
        }
    }
}
// Target class package
package com.refactoring.target;

// Target class: normal class, default modifier, different package with source, empty target_feature
class TargetClass {
    private String targetField = "targetInit2"; // method_feature "2"

    // Accessor methods for variable call / access_instance_method
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    // Instance method for access_instance_method
    public String processField(String param) {
        return param + "_processed";
    }
}
