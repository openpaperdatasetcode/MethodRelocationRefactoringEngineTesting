package com.example;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Super class for source_class extends feature
class SuperClass<T> {
    protected String superField = "superFieldValue";
}

// Source generic class (final modifier, generic, same package, extends, local inner class, anonymous inner class)
final class SourceClass<T extends CharSequence> extends SuperClass<T> {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerProtectedValue";

    // Method to be refactored (instance, List<String> return, default access, position: source)
    @CustomAnnotation // has_annotation feature
    List<String> targetMethod(TargetClass<String> param) { // per_condition: target parameter
        // ForStatement (static modifier, ClassName.field, 2, pos: diff_package_others)
        static void forStatementBlock() {
            String classNameField = TargetClass.STATIC_FIELD; // ClassName.field
            int num = 2; // target_feature: 2
            for (int i = 0; i < num; i++) { // ForStatement
                System.out.println(classNameField + i);
            }
        }
        forStatementBlock();

        // Super keywords usage
        String superValue = super.superField;

        // Variable call
        String targetValue = param.getValue();

        // Access outer protected field
        String protectedValue = this.outerProtectedField;

        // Local inner class (source_class feature)
        class LocalInnerClass<U> {
            String process(String val) {
                return val + "_processed";
            }
        }
        LocalInnerClass<String> local = new LocalInnerClass<>();

        // Anonymous inner class (source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner in source method");
            }
        };
        anonymousInner.run();

        // Prepare result list
        List<String> result = new ArrayList<>();
        result.add(local.process(targetValue));
        result.add(superValue);
        result.add(protectedValue);

        // No new exception
        return result;
    }
}

// Target generic class (public modifier, generic, anonymous inner class target_feature)
public class TargetClass<T> {
    // Static field for ClassName.field in ForStatement
    public static final String STATIC_FIELD = "TargetStaticField";

    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target class");
        }
    };

    public T getValue() {
        return value;
    }
}