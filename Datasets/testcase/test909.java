package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.ArrayList;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Super class for super.methodName() feature
class EnumSuperClass {
    protected Object superMethod(int val) {
        return "super_" + val; // method_feature: 1
    }
}

// Target enum class: protected modifier, anonymous inner class (target_feature)
protected enum TargetEnum extends EnumSuperClass {
    TARGET_1, TARGET_2;

    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    // Chain methods for obj.m1().m2().m3() feature
    public TargetEnum m1() { return this; }
    public TargetEnum m2() { return this; }
    public TargetEnum m3() { return this; }

    public void executeAnonymous() {
        anonymousInner.run();
    }

    public int getOrdinalValue() {
        return this.ordinal() + 1; // method_feature: 1
    }
}

// Source enum class: private modifier, member inner + static nested class (source_feature)
private enum SourceEnum {
    SOURCE_1, SOURCE_2;

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static Object innerInstanceMethod(TargetEnum target) {
            return super.superMethod(target.getOrdinalValue()); // super.methodName(arguments)
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        {
            // instance code blocks (pos for instance method feature)
            SourceStaticNested.innerInstanceMethod(TargetEnum.TARGET_1);
        }

        // Instance method feature: public modifier, 1, inner_class, instance, super.methodName(), pos=instance code blocks, return Object
        public Object instanceHelperMethod(TargetEnum target) {
            return SourceStaticNested.innerInstanceMethod(target);
        }
    }

    // call_method: target type, public modifier, normal + obj.m1().m2().m3(), pos=if/else, return int
    public int targetCallMethod(TargetEnum target) {
        // pos=if/else conditions
        if (target == TargetEnum.TARGET_1) {
            // obj.m1().m2().m3() target_feature
            target.m1().m2().m3().executeAnonymous();
            return target.getOrdinalValue();
        } else {
            return 0;
        }
    }

    // Normal method: private access, List<String> return type, Target class parameter (per_condition), method_position=source
    @RefactorAnnotation // has_annotation feature
    private List<String> refactorMethod(TargetEnum targetParam) {
        // Type declaration statement (method feature)
        List<String> resultList;
        resultList = new ArrayList<>();

        // Variable call feature
        int varCall = targetParam.getOrdinalValue();

        // this(arguments) feature
        SourceEnum source = SourceEnum.this;
        resultList.add(source.name() + "_" + varCall);

        // Execute instance helper method (inner_class feature)
        SourceMemberInner inner = new SourceMemberInner();
        Object instanceResult = inner.instanceHelperMethod(targetParam);
        resultList.add(instanceResult.toString());

        // Execute call_method
        int callResult = targetCallMethod(targetParam);
        resultList.add("callResult: " + callResult);

        // No new exception thrown feature
        return resultList;
    }
}