package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.function.Supplier;

// Diff package class for ConstructorInvocation position
package com.refactoring.diff;
public class DiffPackageOthers {
    public int field = 1;
    public DiffPackageOthers(int val) { this.field = val; }
}

package com.refactoring.movemethod;
import com.refactoring.diff.DiffPackageOthers;

// Functional interface for source enum implementation
interface EnumHandler {
    Object handle();
}

// Source enum class (public modifier, same package, implements + static nested + anonymous inner class)
public enum SourceEnum implements EnumHandler {
    INSTANCE;

    // Target enum field to satisfy pre-condition
    private final TargetEnum targetField = TargetEnum.TARGET_INSTANCE;

    // Static field for depends_on_static_field feature
    private static final int STATIC_FIELD = 1;

    // Static nested class (source feature)
    private static class SourceStaticNested {
        String nestedField = "static_nested_val";
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(targetField.innerClass.innerField);
        }
    };

    // Normal method to be moved (private, returns Object, source position)
    private Object moveableMethod() {
        // ConstructorInvocation (public modifier, diff_package_others pos, this.field + 1)
        public DiffPackageOthers diffObj = new DiffPackageOthers(this.STATIC_FIELD); // this.field (STATIC_FIELD) + 1

        // Expression statement feature
        String exprStmt = new SourceStaticNested().nestedField + diffObj.field;

        // Access instance method feature (target enum instance method)
        String targetMethodResult = targetField.instanceMethod();

        // Variable call feature
        Object varCall = exprStmt + targetMethodResult + targetField.innerClass.innerField;

        // Depends_on_static_field feature
        varCall = varCall + STATIC_FIELD;

        // Used by reflection feature
        try {
            Method method = TargetEnum.class.getDeclaredMethod("instanceMethod");
            varCall = method.invoke(targetField);
        } catch (Exception e) {
            // No new exception instantiation (no_new_exception feature)
        }

        // Call_method feature (inner_class, synchronized, instance + super.methodName() in functional interfaces pos)
        Supplier<TargetEnum> supplier = () -> {
            synchronized (this) {
                return targetField.new InnerClassImpl().innerCallMethod();
            }
        };
        varCall = supplier.get();

        // No new exception instantiation (no_new_exception feature)
        return varCall;
    }

    // Implementation of EnumHandler interface
    @Override
    public Object handle() {
        return moveableMethod();
    }

    // Inner class for call_method feature
    private class InnerClassImpl {
        // Synchronized + instance + super.methodName() feature
        synchronized TargetEnum innerCallMethod() {
            super.toString(); // super.methodName(arguments)
            return targetField;
        }
    }
}

// Target enum class (public modifier, member inner class target feature)
public enum TargetEnum {
    TARGET_INSTANCE;

    // Member inner class (target feature)
    public class TargetMemberInner {
        String innerField = "target_inner_field";
    }

    // Instance of member inner class
    public final TargetMemberInner innerClass = new TargetMemberInner();

    // Instance method for access_instance_method feature
    public String instanceMethod() {
        return innerClass.innerField;
    }
}