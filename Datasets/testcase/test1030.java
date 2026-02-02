package com.refactoring.test;

import java.lang.reflect.Method;

// Parent class for overriding feature
class SourceParentClass {
    protected Object processTarget(Object target) {
        return target;
    }
}

// Target class (normal class, public modifier, local inner class feature)
public class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        String innerField;
        TargetInnerRec nestedInner;

        // Chained methods for call_method "obj.m1().m2().m3()"
        public TargetInnerRec m1() {
            this.innerField += "_m1_1";
            return this;
        }

        public TargetInnerRec m2() {
            this.innerField += "_m2_1";
            return this;
        }

        public TargetInnerRec m3() {
            this.innerField += "_m3_1";
            return this;
        }

        // Local inner class (target_feature)
        public void processField() {
            class TargetLocalInner {
                void updateInnerField(TargetInnerRec inner) {
                    inner.innerField = inner.innerField + "_local_inner_1";
                }
            }
            new TargetLocalInner().updateInnerField(this);
        }
    }

    // Overriding method placeholder for call_method
    public TargetClass overrideMethod() {
        return this;
    }
}

// Source class (normal class, protected modifier, same package, member inner + local inner class)
protected class SourceClass extends SourceParentClass {
    // Static code block for static method pos
    static {
        staticMethod(null); // pos=Static code blocks
    }

    // Static method (private modifier, method_feature:1/source/static/super.methodName(), pos=Static code blocks, return void)
    private static <T extends CharSequence> void staticMethod(TargetClass.TargetInnerRec target) { // with_bounds (T extends CharSequence)
        if (target != null) {
            // super.methodName(arguments) (call Object superclass method)
            String superResult = super.toString() + "_static_1";
            target.innerField = superResult;
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // call_method (source type, strictfp modifier, overriding + obj.m1().m2().m3(), pos=property assignment, return TargetClass Type)
        strictfp TargetClass callMethod(TargetClass.TargetInnerRec target) {
            TargetClass outerTarget = new TargetClass();
            // Property assignment position + obj.m1().m2().m3() + overriding
            outerTarget.targetField = target.m1().m2().m3().innerField;
            return outerTarget.overrideMethod(); // overriding feature
        }
    }

    // Overriding method (method to be refactored)
    @Override
    private Object processTarget(Object targetObj) {
        // Cast to target_inner_rec and check per_condition (source contains target field)
        if (!(targetObj instanceof TargetClass.TargetInnerRec targetParam)) {
            return null;
        }

        // Requires try-catch
        try {
            // Static method call (pos=Static code blocks)
            staticMethod(targetParam);

            // Expression statement
            targetParam.innerField = targetParam.innerField.toUpperCase() + "_exp_1"; // expression statement

            // Variable call (access target field - per_condition)
            String varCall = targetParam.innerField;
            targetParam.innerField = varCall + "_var_modified_1";

            // Used by reflection
            Method processMethod = TargetClass.TargetInnerRec.class.getMethod("processField");
            processMethod.invoke(targetParam);

            // call_method invocation (pos=property assignment)
            SourceMemberInner inner = new SourceMemberInner();
            TargetClass resultTarget = inner.callMethod(targetParam);
            resultTarget.targetField = targetParam.innerField; // property assignment

            // Depends_on_inner_class (core logic relies on TargetInnerRec)
            targetParam.nestedInner = new TargetClass().new TargetInnerRec();
            targetParam.nestedInner.innerField = targetParam.innerField + "_nested_1";

            // Local inner class (source_feature)
            class SourceLocalInner {
                void updateNested(TargetClass.TargetInnerRec inner) {
                    inner.nestedInner.innerField += "_local_inner_1";
                }
            }
            new SourceLocalInner().updateNested(targetParam);

            return resultTarget;
        } catch (Exception e) {
            // No new exception thrown
            return targetParam;
        }
    }
}