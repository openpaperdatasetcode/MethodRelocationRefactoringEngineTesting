package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Different package for ExpressionStatement (same_package_others position)
package com.other;
import com.refactoring.movemethod.SourceClass;

// Protected ExpressionStatement class with specified features
protected class ExpressionStatement {
    // obj.field feature
    public String field;

    // Feature "1" (numeric literal usage)
    public void execute() {
        this.field = this.field + "_" + 1;
        // Expression statement core
        System.out.println(this.field);
    }
}

// Back to original package
package com.refactoring.movemethod;
import com.other.ExpressionStatement;
import java.util.ArrayList;
import java.util.List;

// Annotation for call_method position (attribute values of annotations)
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodRef {
    String value() default "";
    Class<?> method() default SourceClass.class;
}

// Public normal source class (same package as target, member/anonymous inner class features)
public class SourceClass {
    // Member inner class (source feature)
    public class SourceMemberInner {
        String innerData = "source_inner_data";
    }

    // Private call method (type: source, modifier: private, pos: annotation attributes)
    @CallMethodRef(method = SourceClass.class)
    private List<String> callMethod() {
        List<String> result = new ArrayList<>();
        // Target_feature: normal (standard method logic)
        result.add("call_method_normal");
        // Target_feature: super.methodName()
        result.add(super.toString());
        return result;
    }

    // Instance method to refactor (public access, returns List<String>, target parameter)
    public List<String> moveTargetMethod(TargetClass targetParam) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            return new ArrayList<>();
        }

        // Type declaration & variable call for ExpressionStatement
        ExpressionStatement exprStmt = new ExpressionStatement();
        // obj.field feature usage
        exprStmt.field = "expr_field";
        // Execute expression statement (pos: same_package_others)
        exprStmt.execute();

        // Variable call feature (target's member inner class)
        TargetClass.target_inner targetInner = targetParam.new target_inner();
        targetInner.setInnerField(exprStmt.field);

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                targetInner.setInnerField(targetInner.getInnerField() + "_anonymous");
            }
        };
        anonymousRunnable.run();

        // Call private method (pos: annotation attribute values)
        List<String> callResult = this.callMethod();

        // Build result list
        List<String> result = new ArrayList<>();
        result.add(targetInner.getInnerField());
        result.addAll(callResult);

        // No_new_exception feature (no custom exceptions thrown)
        return result;
    }
}

// Default modifier normal target class (same package as source, member inner class feature)
class TargetClass {
    // Member inner class (target_feature: target_inner)
    public class target_inner {
        private String innerField;

        public void setInnerField(String innerField) {
            this.innerField = innerField;
        }

        public String getInnerField() {
            return innerField;
        }
    }
}