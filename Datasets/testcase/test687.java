package com.refactoring.movemethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Interface for target record implements feature
interface TargetInterface {
    String processValue();
}

// Source record class: default modifier, same package as target, type parameter + local inner + member inner class
record SourceRecord<T extends CharSequence>(T value) {
    // Per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_1");

    // Member inner class (source feature)
    public class SourceInnerClass {
        // Method to refactor: instance, List<String> return, private access, in source_inner
        private List<String> methodToRefactor(TargetRecord<T> targetParam) {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                targetParam = new TargetRecord<>((T) "default_target_1");
            }

            List<String> result = new ArrayList<>();
            
            // Local inner class (source feature)
            class LocalInnerProcessor {
                String process(T input) {
                    return input.toString() + "_local_inner_1";
                }
            }

            // Variable call (target record)
            T targetValue = targetParam.value();
            result.add(targetValue.toString());

            // Used_by_reflection feature
            try {
                // Access target record field via reflection
                Field field = TargetRecord.class.getDeclaredField("value");
                field.setAccessible(true);
                T refValue = (T) field.get(targetParam);
                result.add("reflection_field: " + refValue);

                // Invoke target record method via reflection
                Method method = TargetRecord.class.getDeclaredMethod("processValue");
                method.setAccessible(true);
                String methodResult = (String) method.invoke(targetParam);
                result.add("reflection_method: " + methodResult);
            } catch (Exception e) {
                // No_new_exception feature (no throw new exception, handle only)
                result.add("reflection_error_handling_1");
            }

            // Local inner class usage
            LocalInnerProcessor processor = new LocalInnerProcessor();
            result.add(processor.process(targetValue));

            // Variable call for targetField (per_condition)
            result.add("target_field_value: " + targetField.value());

            // No_new_exception additional handling
            try {
                Integer.parseInt(targetValue.toString());
            } catch (NumberFormatException e) {
                result.add("format_error_handling_1");
            }

            return result;
        }

        // Helper method to invoke refactored method
        public List<String> invokeMethod(TargetRecord<T> target) {
            return methodToRefactor(target);
        }
    }
}

// Target record class: public modifier, implements + local inner class feature
public record TargetRecord<T extends CharSequence>(T value) implements TargetInterface {
    // Local inner class (target_feature)
    @Override
    public String processValue() {
        class TargetLocalInner {
            String process(T input) {
                return input.toString() + "_target_local_1";
            }
        }
        return new TargetLocalInner().process(this.value);
    }

    // Additional variable call method
    public T getValue() {
        return this.value;
    }
}