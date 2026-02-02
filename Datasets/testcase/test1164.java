// Source package: com.source
package com.source;

import com.target.PublicTargetClass;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Others class for overriding method_feature: others_class
class OthersClass {
    public List<String> baseMethod(String val) {
        List<String> list = new ArrayList<>();
        list.add(val + "_others_base");
        return list;
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnno {}

// Source normal class (default modifier, different package, two anonymous inner classes)
class SourceClass extends OthersClass {
    // per_condition: source contains the field of the target
    private final PublicTargetClass targetField = new PublicTargetClass("init_value_5853");

    // First anonymous inner class (source_class feature)
    private final Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner 1: " + targetField.getValue());
        }
    };

    // Second anonymous inner class (source_class feature)
    private final java.util.function.Function<String, String> anonymousInner2 = new java.util.function.Function<String, String>() {
        @Override
        public String apply(String s) {
            return s.toUpperCase() + "_anonymous2";
        }
    };

    // Overriding method (public modifier, 2, others_class, overriding, obj.m1().m2().m3())
    @Override
    public List<String> baseMethod(String val) {
        int num = 2; // method_feature: 2
        
        // obj.m1().m2().m3() chain call
        String chainVal = new PublicTargetClass(val)
                .getValue()
                .toString()
                .concat("_chain_" + num);
        
        List<String> result = super.baseMethod(chainVal);
        result.add(chainVal + "_overridden");
        return result;
    }

    /**
     * Method with has_annotation feature
     * @param param Target class parameter
     * @return Object
     */
    @ProcessAnno // has_annotation feature
    public Object processTarget(PublicTargetClass param) { // per_condition: target parameter, normal type, public access
        List<String> result = new ArrayList<>();

        // Requires try-catch feature
        try {
            // Overriding method call in if/else conditions (pos: if/else conditions)
            if (param.getValue().length() > 0) {
                result = this.baseMethod(param.getValue()); // this.methodName(arguments) + overriding call
            } else {
                result = this.baseMethod("default_val");
            }

            // raw_type feature (use raw type of target static nested class)
            PublicTargetClass.StaticNestedClass rawStaticObj = new PublicTargetClass.StaticNestedClass(); // raw type
            String staticVal = rawStaticObj.getStaticVal();
            result.add(staticVal + "_raw_type");

            // Variable call (target static nested class + main class)
            param.setValue(param.getValue() + "_processed_" + staticVal);
            result.add(param.getValue());

            // Trigger anonymous inner classes
            anonymousInner1.run();
            result.add(anonymousInner2.apply(param.getValue()));

        } catch (NullPointerException e) {
            result.add("Try-catch handled: " + e.getMessage());
        }

        // No new exception (core logic safe)
        return result;
    }
}

// Target package: com.target
package com.target;

// Target normal class (public modifier, static nested class target_feature)
public class PublicTargetClass {
    private String value;

    // Static nested class (target_feature)
    public static class StaticNestedClass<T> {
        private String staticVal = "TARGET_STATIC_VAL_5853";

        public String getStaticVal() {
            return staticVal;
        }
    }

    // Constructor
    public PublicTargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}