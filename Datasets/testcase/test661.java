import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Annotation for varargs method in attribute values
@Retention(RetentionPolicy.RUNTIME)
@interface VarargsAnnotation {
    String value() default "";
}

// Source class: generic, abstract, same package, extends + member inner + anonymous inner
abstract class SourceClass<T extends Number> extends ParentClass {
    protected String outerProtectedField = "protectedValue"; // For access_outer_protected

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        public int innerMethod() {
            return 1;
        }

        public void chainMethod() {}
    }

    // Varargs method (matches varargs feature spec)
    @VarargsAnnotation(value = "varargs:" + 1) // pos: annotation attribute values
    private void varargsHelperMethod(MemberInnerSourceClass innerObj, Object... varargs) {
        // obj.m1().m2().m3() chain call
        innerObj.innerMethod();
        innerObj.chainMethod();
    }

    // Call method (source type, protected modifier, normal, instanceReference::methodName, do-while pos, int return)
    protected int callMethod(TargetClass<T> targetParam) {
        MemberInnerSourceClass innerRef = new MemberInnerSourceClass();
        return innerRef::innerMethod; // Instance reference method reference
    }

    // Overloaded method (overload_exist feature)
    final List<String> sourceMethod(TargetClass<T> targetParam, int num) {
        return new ArrayList<>();
    }

    // Target method: varargs, List<String> return, final access, source position
    final List<String> sourceMethod(TargetClass<T> targetParam, Object... varargs) {
        List<String> result = new ArrayList<>();
        String varCall = this.outerProtectedField; // Variable call + access_outer_protected

        // CaseDefaultExpression: numbers=1, public modifier
        public int caseVal = 1;
        switch (caseVal) {
            case 1:
                result.add("case 1");
                break;
            default:
                result.add("default");
                break;
        }

        // otherObject.process(this)
        OtherClass otherObj = new OtherClass();
        otherObj.process(this);

        // Access target instance method (access_instance_method)
        targetParam.memberInnerTargetClass.innerInstanceMethod();

        // Varargs helper method call (annotation attribute pos)
        varargsHelperMethod(new MemberInnerSourceClass(), varargs);

        // Do-while with call_method
        int count = 1;
        do {
            count += callMethod(targetParam);
            // Target_inner_rec: call recursive inner method of target
            targetParam.memberInnerTargetClass.recursiveMethod(count);
        } while (count < 3);

        // Anonymous inner class (source_class feature)
        new Runnable() {
            @Override
            public void run() {
                result.add(varCall);
            }
        }.run();

        try {
            // No new exception thrown
            return result;
        } catch (Exception e) {
            return result;
        }
    }
}

class ParentClass {}

class OtherClass {
    public void process(SourceClass<?> source) {}
}

// Target class: generic, abstract, member inner class feature
abstract class TargetClass<U> {
    // Member inner class (target_feature)
    class MemberInnerTargetClass {
        public void innerInstanceMethod() {}

        // Recursive method (target_inner_rec)
        public void recursiveMethod(int count) {
            if (count > 0) {
                recursiveMethod(count - 1);
            }
        }
    }

    MemberInnerTargetClass memberInnerTargetClass = new MemberInnerTargetClass();
}