package com.refactoring.test;

import java.util.function.Supplier;

// Source enum class (public, extends, anonymous inner class, local inner class)
public enum SourceEnum implements CustomInterface {
    INSTANCE;

    // Extends feature (via interface implementation with extended class)
    @Override
    public Object process(TargetEnum param) { // Overriding method, default access, Object return, target parameter (per_condition)
        /**
         * Method Javadoc
         * @param param target enum parameter
         * @return processed object
         */
        // Requires try-catch
        try {
            // Access instance field
            String instanceField = param.memberInner.field;
            // Variable call
            Object value = param.getValue();

            // Local inner class (source_class feature)
            class LocalInnerClass {
                public int getNumber() { return 3; }
            }

            // Anonymous inner class (source_class feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    LocalInnerClass local = new LocalInnerClass();
                    local.getNumber();
                }
            };

            // Instance method in functional interface (pos: functional interfaces)
            Supplier<Integer> supplier = LocalInnerClass::getNumber; // ClassName::methodName
            int result = supplier.get(); // base type return, 3 in method_feature, inner_class, instance

            // Call method in object initialization (pos: object initialization)
            SubClass subClass = new SubClass(this.callMethod(result)); // this.methodName(arguments)

            return subClass.getObject();
        } catch (Exception e) {
            return new Object();
        }
    }

    // Call method (sub_class, protected, generic, this.methodName(arguments), return Object)
    protected <T> Object callMethod(T arg) {
        return arg;
    }
}

// Extended interface for source enum
interface CustomInterface {
    Object process(TargetEnum param);
}

// Target enum class (protected, member inner class)
protected enum TargetEnum {
    TARGET_INSTANCE;

    // Member inner class (target_feature)
    public class MemberInnerClass {
        String field = "targetField";
    }

    final MemberInnerClass memberInner = new MemberInnerClass();

    public Object getValue() {
        return memberInner.field;
    }
}

// Sub class (call_method type: sub_class)
class SubClass extends SourceEnum {
    private final Object data;

    public SubClass(Object data) {
        this.data = data;
    }

    public Object getObject() {
        return data;
    }
}