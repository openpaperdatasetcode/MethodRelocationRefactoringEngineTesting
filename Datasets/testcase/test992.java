package com.refactoring.test;

import java.util.function.Function;

// Parent class for source_class extends feature
class SourceParentClass {
    protected String parentField = "parent_default_1";

    protected String parentMethod(String input) {
        return input + "_parent_processed_1";
    }
}

// Target class (normal class, protected modifier, static nested class feature)
protected class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        String nestedField;

        // Chained methods for call_method "obj.m1().m2().m3()"
        public TargetStaticNested m1() {
            this.nestedField += "_m1_1";
            return this;
        }

        public TargetStaticNested m2() {
            this.nestedField += "_m2_1";
            return this;
        }

        public TargetStaticNested m3() {
            this.nestedField += "_m3_1";
            return this;
        }

        public String getNestedField() {
            return nestedField;
        }
    }

    // Inner class for depends_on_inner_class feature
    public class TargetInnerClass {
        public void updateTargetField(String value) {
            TargetClass.this.targetField = value; // uses_outer_this feature
        }
    }
}

// Subclass of TargetClass for method_feature "sub_class"
protected class TargetSubClass extends TargetClass {
    @Override
    public String toString() {
        return this.targetField + "_subclass_1";
    }
}

// Source class (normal class, protected modifier, same package, extends + two anonymous inner classes)
protected class SourceClass extends SourceParentClass {

    // Instance method (private modifier, method_feature:1/sub_class/instance/this.methodName(), pos=exception throwing, return TargetClass Type)
    private TargetClass instanceMethod(TargetClass target) {
        try { // pos=exception throwing statements
            if (target == null) {
                throw new NullPointerException("Target is null_1"); // NullPointerException + 1
            }
            // sub_class feature
            TargetSubClass subTarget = new TargetSubClass();
            // this.methodName(arguments)
            subTarget.targetField = this.parentMethod(target.targetField) + "_instance_1";
            return subTarget;
        } catch (NullPointerException e) {
            TargetClass defaultTarget = new TargetClass();
            defaultTarget.targetField = "exception_default_1";
            return defaultTarget;
        }
    }

    // Member inner class for call_method "inner_class" type
    class SourceInnerClass {
        // call_method (default modifier, normal + obj.m1().m2().m3(), pos=functional interfaces, return String)
        String callMethod(TargetClass target) {
            // pos=functional interfaces (lambda context)
            Function<TargetClass.TargetStaticNested, String> processNested = nested -> {
                // obj.m1().m2().m3() feature
                return nested.m1().m2().m3().getNestedField();
            };

            TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
            nested.nestedField = target.targetField + "_nested_init_1";
            return processNested.apply(nested);
        }
    }

    // Method to be refactored (normal, void return, default access, source position)
    void moveMethod(TargetClass targetParam) {
        // Per_condition: contains target parameter
        if (targetParam == null) {
            targetParam = new TargetClass();
            targetParam.targetField = "default_param_1";
        }

        // this.methodName(arguments) feature
        TargetClass processedTarget = this.instanceMethod(targetParam);

        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField;

        // depends_on_inner_class feature (rely on TargetInnerClass)
        TargetClass.TargetInnerClass inner = targetParam.new TargetInnerClass();
        inner.updateTargetField(varCall + "_depends_inner_1");

        // uses_outer_this feature (SourceClass's outer this)
        targetParam.targetField = SourceClass.this.parentField + "_outer_this_1";

        // First anonymous inner class (source_feature)
        Runnable anonymous1 = new Runnable() {
            @Override
            public void run() {
                targetParam.targetField += "_anon1_1";
            }
        };
        anonymous1.run();

        // Second anonymous inner class (source_feature)
        Runnable anonymous2 = new Runnable() {
            @Override
            public void run() {
                targetParam.targetField += "_anon2_1";
            }
        };
        anonymous2.run();

        // call_method invocation (pos=functional interfaces)
        SourceInnerClass sourceInner = new SourceInnerClass();
        String callResult = sourceInner.callMethod(targetParam);
        targetParam.targetField += "_call_result_" + callResult;

        // No new exception
    }
}