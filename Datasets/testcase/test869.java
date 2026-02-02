package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Different package class for ConstructorInvocation pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetEnum;

public class DiffPackageOthers {
    // ConstructorInvocation (protected modifier, obj.field, 1, pos=diff_package_others)
    protected DiffPackageOthers(TargetEnum.TargetInnerRec obj) {
        obj.field = 1;
    }
}

package com.refactoring.test;
import com.refactoring.others.DiffPackageOthers;
import java.util.function.Predicate;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for target enum implements feature
interface TargetInterface {
    default String getValue() { return "default"; }
}

// Source enum class (public modifier, same package, no features)
public enum SourceEnum {
    INSTANCE;

    // Inner class for method position (source_inner)
    static class SourceInnerClass {
        private int instanceField = 5; // For access_instance_field

        // Method to be refactored (static, base type return, strictfp access)
        @RefactorAnnotation // has_annotation feature
        public strictfp static int moveMethod(TargetEnum.TargetInnerRec targetParam) {
            // Per_condition: contains target parameter
            if (targetParam == null) return -1;

            // ConstructorInvocation (diff_package_others position)
            new DiffPackageOthers(targetParam);

            // With bounds (generic with bounds)
            class BoundedGeneric<T extends Number & Comparable<T>> {
                T process(T val) { return val; }
            }
            BoundedGeneric<Integer> bounded = new BoundedGeneric<>();

            // Variable call + access_instance_field
            int varCall = new SourceInnerClass().instanceField;
            targetParam.field = varCall + bounded.process(1);

            // Super keywords
            Object superObj = new Object();
            String superStr = superObj.toString();

            // While loop with static nested method (pos=while, method_feature:2/target/static/obj.m1().m2().m3())
            int count = 0;
            while (count < 2) {
                List<String> list = staticNestedMethod(targetParam); // 2 from method_feature
                count++;
                if (list.isEmpty()) break; // break statement
            }

            // Override violation (invalid override attempt)
            class OverrideViolation extends TargetEnum.TargetInnerRec {
                // Invalid override (different return type)
                @Override
                public int getField() { return field; } // Violation if parent returns String
            }

            // Exception handling with call_method (pos=exception handling statements)
            try {
                if (targetParam.field < 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                List<String> result = TargetEnum.callMethod(targetParam, 10); // ClassName.methodName(arguments)
                targetParam.field = result.size();
            }

            // No new exception
            return targetParam.field; // base type return
        }

        // Static nested method (type:static, modifier:private, method_feature:2/target/static/obj.m1().m2().m3())
        private static List<String> staticNestedMethod(TargetEnum.TargetInnerRec target) {
            List<String> list = new ArrayList<>();
            // obj.m1().m2().m3() feature
            list.add(target.m1().m2().m3());
            return list;
        }
    }
}

// Target enum class (default modifier, javadoc + implements + anonymous inner class)
/**
 * Javadoc feature for TargetEnum
 * This enum implements TargetInterface and contains anonymous inner class
 */
enum TargetEnum implements TargetInterface {
    VALUE1, VALUE2;

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        int field; // obj.field feature

        // Method chain for obj.m1().m2().m3()
        public TargetInnerRec m1() { return this; }
        public TargetInnerRec m2() { return this; }
        public String m3() { return String.valueOf(field); }

        // Override violation base method
        public String getField() { return String.valueOf(field); }

        // Anonymous inner class (target_feature)
        private final Predicate<Integer> anonymousInner = new Predicate<Integer>() {
            @Override
            public boolean test(Integer t) {
                return t > field;
            }
        };
    }

    // call_method (target type, final modifier, instance + ClassName.methodName(arguments))
    public final static List<String> callMethod(TargetInnerRec param, int num) {
        List<String> list = new ArrayList<>();
        // Instance method call
        list.add(param.m3());
        list.add(String.valueOf(num));
        return list;
    }
}