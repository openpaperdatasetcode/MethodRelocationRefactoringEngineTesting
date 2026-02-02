// Source package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Source class: public normal class, different package from target, static nested + local inner class
public class SourceClass {
    // Per_condition: source contains target class field
    private final TargetClass<String> targetField = new TargetClass<>("initial_target_1");

    // Static nested class (source feature)
    public static class SourceStaticNested<T> {
        public static <U> String staticMethod(U value) {
            return value.toString() + "_static_1"; // numbers=1
        }
    }

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec {
        // numbers=1, modifier=public, exp=Name
        public String featureName = "feature_1"; // numbers=1, public modifier, Name expression

        // call_method: inner_class type, protected modifier, normal, ClassName.methodName(), pos=do-while, return String
        protected <T> String callMethod(TargetClass<T>.TargetInnerRec inner) {
            String result = "";
            int count = 0;
            // pos: do-while statement
            do {
                // ClassName.methodName(arguments) + normal feature
                result = SourceStaticNested.staticMethod(inner.getInnerValue()); // normal feature
                count++;
            } while (count < 1); // numbers=1
            return result;
        }

        // Method to refactor: normal, Object return, default access, in source_inner_rec
        Object methodToRefactor(TargetClass<String>.TargetInnerRec innerParam) {
            // Per_condition: method contains target parameter
            if (innerParam == null) {
                return new Object();
            }

            // Local inner class (source feature)
            class LocalInnerProcessor {
                String process(String input) {
                    return input + "_local_inner_1"; // numbers=1
                }
            }

            // Variable call (target inner recursive class)
            String innerValue = innerParam.getInnerValue();
            
            // numbers=1, public modifier, exp=Name (use featureName)
            innerValue += "_" + this.featureName; // numbers=1, Name expression

            // Used_by_reflection feature
            try {
                // Access target inner field via reflection
                Field field = TargetClass.TargetInnerRec.class.getDeclaredField("innerValue");
                field.setAccessible(true);
                field.set(innerParam, field.get(innerParam) + "_reflection_1"); // numbers=1

                // Invoke target inner method via reflection
                Method method = TargetClass.TargetInnerRec.class.getDeclaredMethod("innerMethod");
                method.setAccessible(true);
                method.invoke(innerParam);
            } catch (Exception e) {
                // No_new_exception feature (no throw new exception, handle only)
                innerParam.setInnerValue("safe_value_1"); // numbers=1
            }

            // Local inner class usage
            LocalInnerProcessor processor = new LocalInnerProcessor();
            innerValue = processor.process(innerValue);

            // Call call_method (inner_class type)
            String callResult = callMethod(innerParam);
            innerValue += "_" + callResult;

            // Variable call for targetField (per_condition)
            innerParam.setInnerValue(innerValue + "_" + targetField.getValue());

            // No_new_exception additional handling
            try {
                Integer.parseInt(innerValue);
            } catch (NumberFormatException e) {
                innerParam.setInnerValue("formatted_1"); // numbers=1
            }

            return innerParam.getInnerValue();
        }
    }

    // Helper method to invoke refactored method
    public Object invokeMethod(TargetClass<String>.TargetInnerRec inner) {
        SourceInnerRec innerRec = new SourceInnerRec();
        return innerRec.methodToRefactor(inner);
    }
}

// Target package (different from source)
package com.refactoring.target;

// Interface for target class implements feature
interface TargetInterface {
    String interfaceMethod();
}

// Target class: protected normal class, different package from source, type parameter + implements + local inner class
protected class TargetClass<T> implements TargetInterface {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec() {
            this.innerValue = (T) "inner_rec_1"; // numbers=1
        }

        // Local inner class (target_feature)
        public String innerMethod() {
            class TargetLocalInner {
                String process(T input) {
                    return input.toString() + "_target_local_1"; // numbers=1
                }
            }
            return new TargetLocalInner().process(this.innerValue);
        }

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Implement interface method
    @Override
    public String interfaceMethod() {
        return this.value.toString() + "_interface_1"; // numbers=1
    }

    // Variable call getter
    public T getValue() {
        return value;
    }
}