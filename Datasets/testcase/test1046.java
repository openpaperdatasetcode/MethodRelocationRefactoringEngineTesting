package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

protected enum SourceEnum permits SubEnum1, SubEnum2 {
    INSTANCE("testValue");

    private final String value;
    private final MemberInnerClass innerClass = new MemberInnerClass();

    SourceEnum(String value) {
        this.value = value;
    }

    // Overload exist - overloaded methods
    public TargetEnum moveMethod(TargetEnum targetParam) {
        // Access outer private (access private field of outer enum)
        String outerPrivate = this.value;
        // Variable call & access instance field
        targetParam.nestedField = outerPrivate;
        
        // CreationReference feature (numbers:2, abstract, exp:CreationReference)
        Supplier<TargetEnum.StaticNested> supplier = TargetEnum.StaticNested::new;
        Supplier<MemberInnerClass> anotherSupplier = MemberInnerClass::new;

        // Lambda expression: () -> System.out.println(this.value)
        Runnable lambda = () -> System.out.println(this.value);
        lambda.run();

        // Used by reflection
        try {
            Method method = TargetEnum.class.getDeclaredMethod("getNestedField");
            method.setAccessible(true);
            method.invoke(targetParam);
        } catch (Exception e) {
            // No new exception
        }

        // For loop with callMethod
        for (int i = 0; i < callMethod(targetParam); i++) {
            // Loop body
        }

        return targetParam;
    }

    // Overload method (overload_exist)
    public TargetEnum moveMethod() {
        return TargetEnum.DEFAULT;
    }

    // call_method: private, pos=for, return_type=int, superTypeReference.methodName(arguments)
    private int callMethod(TargetEnum param) {
        return super.toString().length() + param.StaticNested.superTypeMethod(10);
    }

    // Member inner class
    public class MemberInnerClass {
        private String innerValue = "inner";
    }

    // Anonymous inner class
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceEnum.this.value);
        }
    };
}

private enum TargetEnum {
    DEFAULT;

    String nestedField;

    // Static nested class (target_feature)
    public static class StaticNested {
        public static int superTypeMethod(int num) {
            return num;
        }
    }

    public String getNestedField() {
        return this.nestedField;
    }
}

enum SubEnum1 extends SourceEnum {
    SUB1;
    SubEnum1() {
        super("sub1");
    }
}

enum SubEnum2 extends SourceEnum {
    SUB2;
    SubEnum2() {
        super("sub2");
    }
}