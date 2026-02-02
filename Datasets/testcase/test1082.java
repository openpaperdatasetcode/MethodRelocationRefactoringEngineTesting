package com.refactoring.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Source record class (private modifier, same package, static nested class, anonymous inner class)
private record SourceRecord(String data) {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceRecord.this.data);
        }
    };

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord() {
        /**
         * Abstract method to be refactored (javadoc feature)
         * @param param TargetRecord inner record parameter
         * @return Object result
         */
        @CustomAnnotation // has_annotation feature
        strictfp abstract Object targetMethod(TargetRecord.InnerTargetRecord param);

        // Concrete method implementing feature logic (backing for abstract method structure)
        strictfp Object targetMethodImpl(TargetRecord.InnerTargetRecord param) {
            // Constructor invocation
            TargetRecord targetInstance = new TargetRecord("targetData");
            TargetRecord.InnerTargetRecord innerRecord = new TargetRecord.InnerTargetRecord(1);

            // EmptyStatement (private modifier, ClassName.field, 1, pos: same_package_target)
            private int emptyStmtNum = 1; // 1
            ; // EmptyStatement
            String classNameField = TargetRecord.STATIC_FIELD; // ClassName.field

            // Switch case
            switch (param.value()) {
                case 1:
                    // Accessor method call (synchronized modifier, 3, target, OuterClass.InnerClass.methodName(), base type return)
                    int accessorResult = accessorMethod(targetInstance, 3); // 3 in method_feature
                    break;
                default:
                    accessorResult = 0;
                    break;
            }

            // Variable call
            int targetValue = param.value();

            // No new exception (no_new_exception)
            return accessorResult + targetValue;
        }

        // Accessor method (synchronized modifier, base type return, target, OuterClass.InnerClass.methodName())
        private synchronized int accessorMethod(TargetRecord target, int num) { // 3 in method_feature, accessor type
            try {
                // exception throwing statements pos + OuterClass.InnerClass.methodName()
                if (target.data().isEmpty()) throw new IllegalArgumentException();
                return target.getInnerValue(num); // target in method_feature
            } catch (IllegalArgumentException e) {
                return num;
            }
        }
    }
}

// Custom annotation for has_annotation feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Target record class (public modifier, same package, empty target_feature)
public record TargetRecord(String data) {
    // Static field for EmptyStatement (ClassName.field)
    public static final String STATIC_FIELD = "targetStaticField"; // ClassName.field

    // Inner target record (target_inner_rec)
    record InnerTargetRecord(int value) {}

    // Accessor method for OuterClass.InnerClass.methodName() feature
    public int getInnerValue(int num) {
        return num * 2;
    }
}