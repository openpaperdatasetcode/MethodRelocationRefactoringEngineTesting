package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for source enum implements feature
interface SourceInterface {
    List<String> moveMethod(TargetEnum.TargetInner targetParam);
}

// Sub-enums for permits feature
enum SubEnum1 extends SourceEnum {
    SUB1;
    SubEnum1() { super("sub1"); }
}

enum SubEnum2 extends SourceEnum {
    SUB2;
    SubEnum2() { super("sub2"); }
}

// Source enum class (default modifier, same package, implements + permits + static nested + member inner class)
enum SourceEnum implements SourceInterface permits SubEnum1, SubEnum2 {
    INSTANCE("instanceValue");

    private final String enumField;

    // Static nested class (source feature)
    static class SourceStaticNested {
        int nestedField = 2; // 2 from method_feature
    }

    // Member inner class (source feature)
    public class SourceMemberInner {
        int processValue(int val) {
            return val * 2;
        }
    }

    SourceEnum(String enumField) {
        this.enumField = enumField;
    }

    // Base method for overriding
    @Override
    @RefactorAnnotation // has_annotation feature
    public List<String> moveMethod(TargetEnum.TargetInner targetParam) { // method types parameter is:none (only target param)
        // Per_condition: contains target parameter (targetParam)
        List<String> result = new ArrayList<>();
        if (targetParam == null) {
            return result;
        }

        // Instance method (type:instance, modifier:public, method_feature:2/source/instance/instanceReference.methodName(arguments))
        public int instanceMethod(SourceMemberInner inner, int num) {
            return inner.processValue(num); // instanceReference.methodName(arguments)
        }

        // Array initialization with instance method call (pos:array initialization)
        int[] intArray = {instanceMethod(new SourceMemberInner(), new SourceStaticNested().nestedField)}; // 2 from method_feature

        // Type declaration statement
        SourceStaticNested staticNested = new SourceStaticNested();
        TargetEnum.TargetInner targetInner = targetParam;

        // Variable call
        String varCall = targetInner.innerField;
        result.add(varCall);
        result.add(String.valueOf(intArray[0]));

        // Used by reflection
        try {
            Method method = TargetEnum.TargetInner.class.getDeclaredMethod("setInnerField", String.class);
            method.setAccessible(true);
            method.invoke(targetInner, enumField + "_reflected");
        } catch (Exception e) {
            // No new exception (only handle reflection exceptions internally)
        }

        // No new exception
        return result;
    }
}

// Target enum class (default modifier, anonymous inner class feature)
enum TargetEnum {
    VALUE1, VALUE2;

    // Target inner class (target_inner)
    public class TargetInner {
        String innerField = "targetInnerValue";

        // Anonymous inner class (target_feature)
        private final Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                innerField = "anonymousModified";
            }
        };

        public void setInnerField(String value) {
            this.innerField = value;
            anonymousInner.run();
        }
    }
}