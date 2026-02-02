// Source package: com.source
package com.source;

import com.target.FinalTargetRecord;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RecordProcessAnnotation {}

// Super class for super.field feature
class TargetSuperClass {
    protected String superField = "TARGET_SUPER_FIELD_5870";
}

// Source public record class (different package, member inner class, local inner class)
public record SourceRecord(String data) {
    // per_condition: source contains the field of the target
    private final FinalTargetRecord targetField = new FinalTargetRecord("init_value_5870");

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        // Call method (source type, protected modifier, instance, ClassName.methodName(arguments))
        protected void callMethod(String val) {
            System.out.println("Source call method: " + val);
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String innerData) {
        /**
         * Final accessor method processing target inner class.
         * @param param Target inner class instance (target_inner)
         * @return List<String> processed results
         */
        @RecordProcessAnnotation // has_annotation feature
        final List<String> processTarget(FinalTargetRecord.InnerClass param) { // per_condition: target parameter, final access
            // Super constructor invocation (implicit for record/inner class)
            super();

            // type declaration statement
            List<String> result = new ArrayList<>();
            String superFieldVal;
            int num;

            // ThrowStatement (private modifier, super.field, 3, pos: source)
            private void throwBlock() {
                superFieldVal = param.superField; // super.field
                num = 3; // target_feature: 3

                // Throw statement
                if (param.getInnerValue().isEmpty()) {
                    throw new IllegalArgumentException(
                        "Empty inner value! super.field=" + superFieldVal + ", num=" + num
                    );
                }
            }

            // Execute throw block (safe execution with non-empty value)
            try {
                throwBlock();
            } catch (IllegalArgumentException e) {
                result.add("Throw handled: " + e.getMessage());
            }

            // Call method in expression (pos: expression, instance, ClassName.methodName(arguments))
            MemberInnerClass innerObj = new MemberInnerClass();
            String exprVal = param.getInnerValue() + "_expr_call";
            SourceRecord.MemberInnerClass.this.callMethod(exprVal); // ClassName.methodName(arguments)
            result.add(exprVal);

            // override_violation feature (attempt to override final method)
            try {
                // Attempt to access final method via reflection (simulate override violation)
                Class<?> innerClass = FinalTargetRecord.InnerClass.class;
                java.lang.reflect.Method finalMethod = innerClass.getDeclaredMethod("getInnerValue");
                if (java.lang.reflect.Modifier.isFinal(finalMethod.getModifiers())) {
                    result.add("Override violation: cannot override final method getInnerValue()");
                }
            } catch (NoSuchMethodException e) {
                result.add("Override violation check error: " + e.getMessage());
            }

            // Variable call (target member inner class + javadoc feature)
            String processedVal = param.getInnerValue() + "_processed_" + superFieldVal + "_" + num;
            param.setInnerValue(processedVal);
            result.add(param.getInnerValue());

            // local inner class (source_class feature)
            class LocalInnerProcessor {
                void enhanceList(List<String> list) {
                    list.replaceAll(String::toUpperCase);
                }
            }
            new LocalInnerProcessor().enhanceList(result);

            // No new exception (core logic safe)
            return result;
        }
    }

    // Helper method to create inner record instance
    public InnerSourceRecord createInnerRecord() {
        return new InnerSourceRecord(this.data + "_inner_rec");
    }
}

// Target package: com.target
package com.target;

/**
 * Final target record class (javadoc feature).
 * <p>Contains member inner class and super field inheritance.
 * @param value Core value of the record
 */
public final record FinalTargetRecord(String value) extends TargetSuperClass { // final modifier, javadoc feature
    /**
     * Member inner class (target_feature) with final accessor methods.
     * <p>Inherits super field from TargetSuperClass.
     */
    public final class InnerClass { // member inner class target_feature, javadoc feature
        private String innerValue;

        /**
         * Constructor initializing inner value from outer record.
         */
        public InnerClass() {
            this.innerValue = FinalTargetRecord.this.value;
        }

        /**
         * Final getter method (override_violation target).
         * @return String inner value
         */
        public final String getInnerValue() { // final method for override_violation
            return innerValue;
        }

        /**
         * Setter method for inner value.
         * @param value New inner value
         */
        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    /**
     * Record constructor with value validation (javadoc feature).
     * @param value Input value (non-null)
     */
    public FinalTargetRecord {
        if (value == null) {
            value = "default_target_value_5870";
        }
    }

    /**
     * Factory method to create inner class instance (javadoc feature).
     * @return InnerClass new instance
     */
    public InnerClass createInnerClass() {
        return new InnerClass();
    }
}