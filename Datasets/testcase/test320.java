package com.refactor;

import java.util.Objects;

/**
 * TargetEnum with Javadoc (target_feature: javadoc)
 * Public enum class with inner component (target_inner)
 */
public enum TargetEnum {
    INSTANCE("default_1"); // 1 feature

    // this.field for LabeledStatement feature
    private String field;

    // Target inner class (target_inner)
    public class TargetInner {
        private String innerValue;

        public TargetInner(String innerValue) {
            this.innerValue = innerValue + "_1"; // 1 feature
        }

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Constructor
    TargetEnum(String field) {
        this.field = field;
    }

    // Getter/Setter for field
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    // Super method for super.methodName() feature
    protected Object superMethod() {
        return new TargetInner(this.field);
    }
}

// Super enum helper class for override violation and super calls
abstract class SuperEnumHelper {
    protected Object genericMethod(TargetEnum.TargetInner... inners) {
        return inners[0];
    }
}

// Source enum class: protected modifier, same package as target, local inner + static nested class
protected enum SourceEnum {
    INSTANCE;

    // Private outer field for access_outer_private feature
    private String outerPrivateField = "private_1"; // 1 feature

    // Static nested class feature
    private static class StaticNestedClass extends SuperEnumHelper {
        // Generic method: public modifier, 1/inner_class/generic/super.methodName(), pos: constructor parameter list, return Object
        @Override
        public <T> Object genericMethod(T... inners) { // Override violation (raw type + generic mismatch)
            // Parameter list of constructors pos: super.methodName()
            TargetEnum.TargetInner inner = new TargetEnum.INSTANCE.new TargetInner(super.superMethod().toString());
            inner.setInnerValue(inner.getInnerValue() + "_1"); // 1 feature
            return inner;
        }
    }

    // Method: varargs, return Object, private access, in source class
    private Object processTarget(TargetEnum.TargetInner... targetParams) {
        // Variable call (target parameter)
        TargetEnum.TargetInner inner = targetParams.length > 0 ? targetParams[0] : new TargetEnum.INSTANCE.new TargetInner(outerPrivateField);

        // LabeledStatement: private modifier, this.field + 1, pos: source
        private LabeledBlock: {
            labelLoop:
            for (int i = 0; i < 3; i++) {
                if (i == 1) { // 1 feature
                    inner.setInnerValue(TargetEnum.INSTANCE.getField() + "_labeled_1"); // this.field + 1
                    break labelLoop;
                }
                continue labelLoop;
            }
        }

        // Access outer private field
        inner.setInnerValue(SourceEnum.this.outerPrivateField); // access_outer_private

        // Override violation (in local inner class)
        class LocalInnerClass extends StaticNestedClass {
            // Invalid override (return type mismatch + generic violation)
            @Override
            public Object genericMethod(Object... inners) { // override_violation
                return super.genericMethod((TargetEnum.TargetInner) inners[0]); // super.methodName()
            }
        }

        // Raw type usage (raw_type feature)
        TargetEnum rawTarget = TargetEnum.INSTANCE; // Raw type of target enum
        inner.setInnerValue(rawTarget.getField() + "_raw_1"); // 1 feature

        // call_method: source type, default modifier, instance + super.methodName(arguments), pos: exception throwing statements, returns TargetClass Type
        TargetEnum.TargetInner callResult = null;
        try {
            if (inner == null) {
                // Exception throwing statements pos
                throw new IllegalArgumentException("Inner cannot be null (1)"); // 1 feature
            }
            LocalInnerClass localInner = new LocalInnerClass();
            callResult = (TargetEnum.TargetInner) localInner.genericMethod(inner); // super.methodName(arguments)
        } catch (Exception e) {
            // No new exception - wrap existing
            throw new RuntimeException(e); // no_new_exception
        }

        // No new exception - wrap existing if any
        try {
            return callResult;
        } catch (Exception e) {
            throw new RuntimeException(e); // no_new_exception
        }
    }

    // call_method implementation (source type, default modifier)
    TargetEnum.TargetInner callMethod(TargetEnum.TargetInner inner) {
        try {
            // Exception throwing statements pos + super.methodName(arguments)
            if (Objects.isNull(inner)) {
                throw new NullPointerException("Inner is null (1)"); // 1 feature
            }
            return (TargetEnum.TargetInner) new StaticNestedClass().genericMethod(inner); // super.methodName(arguments)
        } catch (Exception e) {
            throw new RuntimeException(e); // no_new_exception
        }
    }
}