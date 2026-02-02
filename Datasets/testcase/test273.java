// Different package for EmptyStatement (diff_package_others position)
package com.other;
import com.refactoring.movemethod.SourceSealedEnum;

// Static EmptyStatement class with specified features
public static class EmptyStatement {
    // obj.field feature
    public String field;

    // Feature "3" (numeric literal usage)
    public int calculate() {
        return this.field != null ? this.field.length() + 3 : 3;
    }

    // Empty statement implementation
    public void execute() {
        ; // Empty statement
    }
}

// Original package
package com.refactoring.movemethod;
import com.other.EmptyStatement;
import java.sql.SQLException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for method feature (attribute values of annotations for inner instance method)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodMetadata {
    String value() default "";
    Class<?> methodRef() default InstanceMethodHolder.class;
}

// Parent class for method_feature "parent_class"
class ParentClass {
    public Object parentInstanceMethod(String arg) {
        return arg + "_parent_processed";
    }
}

// Holder for instance method in annotation attributes
class InstanceMethodHolder extends ParentClass {
    // Instance method with specified features (pos: annotation attribute values)
    @Override
    public Object parentInstanceMethod(String arg) {
        // Feature "1" (numeric literal)
        int count = 1;
        // Feature "parent_class" (call super method)
        Object superResult = super.parentInstanceMethod(arg);
        // Feature "instanceReference.methodName(arguments)"
        EmptyStatement instanceRef = new EmptyStatement();
        instanceRef.field = superResult.toString();
        instanceRef.execute();
        // Return combined result
        return superResult + "_" + count + "_" + instanceRef.calculate();
    }
}

// Sealed source enum class (same package as target, type parameter, permits, static/member inner classes)
public sealed enum SourceSealedEnum<T extends Number> permits SourceSealedEnum.PermittedEnum1, SourceSealedEnum.PermittedEnum2 {
    INSTANCE1,
    INSTANCE2;

    // Permitted enum subclasses (permits feature)
    enum PermittedEnum1 extends SourceSealedEnum<Integer> { PERMIT1 }
    enum PermittedEnum2 extends SourceSealedEnum<Double> { PERMIT2 }

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        String staticNestedField;
    }

    // Member inner class (source class feature)
    public class SourceMemberInner {
        int memberInnerField;
    }

    // Instance field for access_instance_field feature
    private T instanceField;

    // Varargs method to refactor (default access, returns Object, has target parameter)
    @MethodMetadata(methodRef = InstanceMethodHolder.class) // Annotation with method in attributes
    Object moveTargetMethod(TargetSealedEnum... targetParams) {
        // Depends_on_inner_class feature
        SourceMemberInner innerObj = new SourceMemberInner();
        innerObj.memberInnerField = 10;

        // Access_instance_field feature
        this.instanceField = (T) Integer.valueOf(innerObj.memberInnerField);

        for (int i = 0; i < targetParams.length; i++) {
            TargetSealedEnum targetParam = targetParams[i];
            // Variable call feature
            TargetSealedEnum.target_inner targetInner = targetParam.new target_inner();
            
            // Continue statement feature
            if (i % 2 == 0) {
                continue;
            }

            // Requires_try_catch and SQLException feature
            try {
                if (targetParam == null) {
                    throw new SQLException("Target parameter is null at index: " + i);
                }
                // EmptyStatement usage (diff_package_others)
                EmptyStatement emptyStmt = new EmptyStatement();
                emptyStmt.field = targetInner.innerField;
                emptyStmt.execute(); // Empty statement execution
                emptyStmt.calculate(); // Uses "3" feature
            } catch (SQLException e) {
                System.err.println("Caught SQLException: " + e.getMessage());
            }
        }

        // Instance method call from annotation attribute (instanceReference.methodName)
        InstanceMethodHolder instanceHolder = new InstanceMethodHolder();
        Object methodResult = instanceHolder.parentInstanceMethod("test_arg");

        // Return this; feature
        return this;
    }
}

// Sealed target enum class (same package as source, static nested class)
public sealed enum TargetSealedEnum permits TargetSealedEnum.PermittedTarget {
    TARGET1,
    TARGET2;

    // Permitted subclass (sealed requirement)
    enum PermittedTarget extends TargetSealedEnum { PERMIT_TARGET }

    // Static nested class (target_inner - target class for method)
    public static class target_inner {
        String innerField = "target_inner_field";
    }
}