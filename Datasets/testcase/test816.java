package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: default modifier, implements interface, same package as target
interface SourceInterface {
    void interfaceMethod();
}

class SourceClass implements SourceInterface {
    private String field = "sourceField";

    // Static nested class (source class feature)
    static class StaticNestedSource {
        String nestedField = "nested";
    }

    // Anonymous inner class (source class feature)
    SourceInterface anonymousInner = new SourceInterface() {
        @Override
        public void interfaceMethod() {
            System.out.println("Anonymous inner class");
        }
    };

    @Override
    public void interfaceMethod() {
        // Implementation required by interface
    }

    // Annotation for method feature (has_annotation)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnnotation {}

    // Overloading method 1: private access, base return type (int), target parameter (per_condition)
    @MethodAnnotation
    private int refactorMethod(TargetClass targetParam) {
        // Variable call
        String varCall = targetParam.staticNestedTarget.nestedTargetField;
        int value = 5;

        // IfStatement (feature with public modifier, this.field, 1)
        if (this.field != null && value == 1) {
            value += 1;
        }

        // Switch statement
        switch (value) {
            case 1:
                value++;
                break;
            case 5:
                value += 2;
                break;
            default:
                value = 0;
        }

        // Lambda expressions (pos) with instance feature, protected modifier, super.methodName(arguments)
        Runnable lambda = () -> {
            // Instance feature, others_class, super.methodName(arguments)
            SuperClass superObj = new SuperClass();
            List<String> result = superObj.superMethod(1, varCall);
        };
        lambda.run();

        // No new exception thrown
        return value;
    }

    // Overloading method 2 (method type: overloading)
    @MethodAnnotation
    private boolean refactorMethod(TargetClass targetParam, String extraParam) {
        return targetParam != null;
    }
}

// Super class for super.methodName(arguments) feature
class SuperClass {
    protected List<String> superMethod(int num, String arg) {
        List<String> list = new ArrayList<>();
        list.add(arg + num);
        return list;
    }
}

// Target class: default modifier, same package as source
class TargetClass {
    // Static nested class (target_feature)
    static class StaticNestedTarget {
        String nestedTargetField = "targetNested";
    }

    StaticNestedTarget staticNestedTarget = new StaticNestedTarget();
}