package com.refactor;

import com.other.DiffPackageOthers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Different package others class for EmptyStatement pos (diff_package_others)
package com.other;
import com.refactor.TargetEnum;

public class DiffPackageOthers {
    // EmptyStatement: private modifier, obj.field + 3, pos: diff_package_others
    public static <T> void emptyLogic(TargetEnum target) {
        private Object empty = null;
        ; // Empty statement
        target.field = "value_3"; // obj.field + 3 feature
    }
}

// Back to refactor package
package com.refactor;
import com.other.DiffPackageOthers;

// Parent class for call_method (parent_class type)
class EnumParentClass {
    // call_method: private modifier, overloading + instanceReference.methodName(arguments), pos: Lambda expressions, returns List<String>
    private List<String> callMethod(TargetEnum target) {
        List<String> list = new ArrayList<>();
        list.add(target.field);
        return list;
    }

    // Overloading feature for call_method
    private List<String> callMethod(TargetEnum target, String suffix) {
        List<String> list = new ArrayList<>();
        list.add(target.field + suffix);
        return list;
    }
}

// Source class: enum class, default modifier, same package as target, static nested + local inner class
enum SourceEnum<T extends TargetEnum> {
    INSTANCE;

    // per_condition: source contains field of target
    private TargetEnum targetField = TargetEnum.FIRST;

    // Static nested class feature
    public static class StaticNestedClass {
        public static <T> void processEnum(T target) {
            if (target instanceof TargetEnum te) {
                te.field = "static_3"; // 3 feature
            }
        }
    }

    // Method: varargs, void return, public access, in source class, generic method type parameter
    public <T extends TargetEnum> void processTarget(T... targetParams) {
        // Variable call (target parameter/field)
        T target = targetParams.length > 0 ? targetParams[0] : (T) targetField;
        
        // EmptyStatement from diff_package_others (obj.field + 3)
        DiffPackageOthers.emptyLogic((TargetEnum) target);
        
        // Local inner class feature (depends_on_inner_class)
        class LocalInnerClass {
            public void validateTarget(T target) {
                assert target.field != null : "Field is null (3)"; // assert statement + 3 feature
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        
        // Depends on inner class
        localInner.validateTarget(target);
        
        // Enhanced for statement
        TargetEnum[] enums = TargetEnum.values();
        for (TargetEnum te : enums) {
            // Switch statement
            switch (te) {
                case FIRST:
                    te.field = "first_3"; // 3 feature
                    break;
                case SECOND:
                    te.field = "second_3"; // 3 feature
                    break;
                default:
                    te.field = "default_3"; // 3 feature
            }
        }
        
        // Uses outer this
        target.field = SourceEnum.this.name() + "_3"; // uses_outer_this + 3 feature
        
        // Used by reflection
        try {
            Method setFieldMethod = TargetEnum.class.getDeclaredMethod("setField", String.class);
            setFieldMethod.setAccessible(true);
            setFieldMethod.invoke(target, "reflection_3"); // 3 feature
            
            // Overload exist feature (overloaded method)
            processTarget(target, "suffix_3");
            
            // call_method: parent_class type, private modifier, overloading + instanceReference.methodName(arguments), pos: Lambda expressions, returns List<String>
            EnumParentClass parent = new EnumParentClass();
            Function<TargetEnum, List<String>> lambda = parent::callMethod; // Lambda expressions pos
            List<String> result = lambda.apply((TargetEnum) target); // instanceReference.methodName(arguments)
            
        } catch (Exception e) {
            // No new exception - wrap existing
            throw new RuntimeException(e);
        }
    }

    // Overloaded method (overload_exist feature)
    public <T extends TargetEnum> void processTarget(T target, String suffix) {
        target.field += suffix;
    }
}

// Target class: enum class, default modifier, member inner class feature
enum TargetEnum {
    FIRST, SECOND;

    String field; // obj.field feature for EmptyStatement

    // Member inner class (target_feature)
    public class MemberInnerClass {
        public void modifyField(String value) {
            TargetEnum.this.field = value;
        }
    }

    // Setter for reflection access
    private void setField(String value) {
        this.field = value;
    }
}