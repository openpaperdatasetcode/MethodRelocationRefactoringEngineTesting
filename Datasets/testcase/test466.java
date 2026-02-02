package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface EnumMethodAnnotation {}

// Source enum class (protected modifier adjusted to package-private per Java spec, same package as target, local + member inner class)
enum SourceEnum {
    INSTANCE;

    // Member inner class feature (source enum feature)
    public class EnumMemberInnerClass {
        String innerField = "memberInnerValue";
    }

    // Target generic method (default access, void return, in source enum)
    // Precondition: method contains target enum parameter
    @EnumMethodAnnotation // has_annotation feature
    public <T extends SourceEnum> void targetMethod(TargetEnum targetParam) {
        // Super constructor invocation (in anonymous inner class context within enum)
        Runnable anonymousSub = new Runnable() {
            {
                super(); // Super constructor invocation
            }
            @Override
            public void run() {}
        };

        // Type declaration statement feature
        class EnumLocalTypeDecl {
            String typeField = targetParam.memberInner.innerField;
        }
        EnumLocalTypeDecl typeDeclObj = new EnumLocalTypeDecl();

        // Variable call feature
        EnumMemberInnerClass innerObj = new EnumMemberInnerClass();
        String varCall = innerObj.innerField + typeDeclObj.typeField;
        System.out.println(varCall);

        // Uses_outer_this feature (access outer enum instance via this)
        SourceEnum outerThis = SourceEnum.this;
        System.out.println(outerThis.name());

        // No_new_exception (implicit NPE if targetParam is null, no explicit new exception)
        if (targetParam == null) {
            throw new NullPointerException(); // Reuse standard exception, no new custom exception
        }
    }

    // Local inner class feature (source enum feature)
    public void enumLocalInnerClass() {
        class EnumLocalInnerClass {
            void invokeTargetMethod(TargetEnum targetParam) {
                SourceEnum.this.targetMethod(targetParam); // Use outer this
            }
        }
        new EnumLocalInnerClass().invokeTargetMethod(TargetEnum.VALUE);
    }
}

// Target enum class (default modifier, member inner class target_feature)
enum TargetEnum {
    VALUE;

    // Member inner class target_feature
    public class EnumMemberInnerClass {
        String innerField = "targetMemberInnerValue";
    }

    // Instance of member inner class for access
    public EnumMemberInnerClass memberInner = new EnumMemberInnerClass();
}