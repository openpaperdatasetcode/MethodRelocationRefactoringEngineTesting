// Target enum package (different from source)
package com.target;

// Interface for TargetEnum implements feature
interface TargetProcessable {
    String process(String input);
}

// Target enum class: default modifier, implements interface, member inner class (target_feature)
enum TargetEnum implements TargetProcessable {
    TARGET_1("val1"), TARGET_2("val2");

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerData;

        public TargetInner(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return innerData;
        }
    }

    private final String enumValue;

    TargetEnum(String enumValue) {
        this.enumValue = enumValue;
    }

    // Instance method for access_instance_method feature
    public String getEnumValue() {
        return enumValue;
    }

    @Override
    public String process(String input) {
        return input + "_processed_by_" + enumValue;
    }
}

// Source enum package (different from target)
package com.source;

import com.target.TargetEnum;

// Source enum class: non-sealed modifier, two member inner classes (source_feature)
non-sealed enum SourceEnum {
    SOURCE_1, SOURCE_2;

    // First member inner class (source_feature)
    public class FirstMemberInner {
        public String formatData(String data) {
            return "formatted_" + data;
        }
    }

    // Second member inner class (source_feature)
    public class SecondMemberInner {
        public String combineData(String... args) {
            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(arg).append(",");
            }
            return sb.toString();
        }
    }

    // call_method: source type, default modifier, normal + ClassName.methodName(), pos=exception handling, return String
    String sourceCallMethod(TargetEnum target, String... args) {
        try {
            // ClassName.methodName(arguments) + pos=exception handling statements
            String processed = SourceEnum.classNameStaticMethod(target, args);
            return processed;
        } catch (NullPointerException e) {
            return "default_processed";
        }
    }

    // Static method for ClassName.methodName(arguments) feature
    public static String classNameStaticMethod(TargetEnum target, String... args) {
        if (target == null) throw new NullPointerException("TargetEnum is null");
        SecondMemberInner inner = SourceEnum.SOURCE_1.new SecondMemberInner();
        return target.process(inner.combineData(args));
    }

    // Varargs method: public access, void return type, target parameter (per_condition)
    public void refactorMethod(TargetEnum targetParam, String... args) {
        // Variable call feature
        String varCall = targetParam.getEnumValue();

        // Access instance method feature
        String processedValue = targetParam.process(varCall);

        // Execute call_method
        String callResult = sourceCallMethod(targetParam, args);

        // Use member inner classes
        FirstMemberInner firstInner = new FirstMemberInner();
        String formatted = firstInner.formatData(callResult);

        // No new exception thrown feature
        System.out.println("Variable call: " + varCall + ", Processed: " + processedValue + ", Formatted: " + formatted);
    }
}