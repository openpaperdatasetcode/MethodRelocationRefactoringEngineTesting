package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Others class for method_feature: others_class
class OthersClass<T> {
    public TargetClass<T> processTarget(TargetClass<T> target) {
        target.innerClass.setValue((T) (target.getValue() + "_processed_1")); // method_feature: 1
        return target;
    }
}

// Source class: generic, final, same package as target, member inner + anonymous inner class
final class SourceClass<T extends CharSequence> {
    // Per_condition: source contains target class field
    private TargetClass<T> targetField = new TargetClass<>((T) "initialSourceTarget");
    private final Object lock = new Object();

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        private int innerField = 1; // For SynchronizedStatement target_feature: 1 (this.field)

        // SynchronizedStatement feature: private modifier, this.field, 1, pos=same_package_target
        private void syncLogic(TargetClass<T> targetParam) {
            // pos: same_package_target (operate on target object)
            synchronized (lock) { // SynchronizedStatement
                int fieldVal = this.innerField; // target_feature: this.field, 1
                targetParam.setValue((T) (targetParam.getValue() + "_sync_" + fieldVal));
            }
        }

        // Instance method feature: default modifier, 1, others_class, instance, instanceReference.methodName(), pos=switch, return TargetClass Type
        TargetClass<T> instanceHelperMethod(TargetClass<T> targetParam) {
            int switchKey = 1; // method_feature: 1
            // pos: switch
            switch (switchKey) {
                case 1:
                    // instanceReference.methodName(arguments) (call others class instance method)
                    OthersClass<T> othersInstance = new OthersClass<>(); // method_feature: instance
                    return othersInstance.processTarget(targetParam); // method_feature: others_class
                default:
                    return targetParam;
            }
        }

        // Method to refactor: instance, List<String> return, private access, in source_inner
        private List<String> methodToRefactor(TargetClass<T> targetParam) {
            List<String> result = new ArrayList<>();
            
            // Constructor invocation (target class and inner class)
            TargetClass<T> newTarget = new TargetClass<>((T) "newInstance_1"); // method_feature: 1
            TargetClass<T>.TargetInner newInner = newTarget.new TargetInner();

            // Variable call (target field and parameter)
            String targetValue = targetField.getValue().toString();
            targetValue += targetParam.getValue().toString();
            result.add(targetValue);

            // Expression statement
            targetValue += "_exp_stmt_1"; // method_feature: 1
            result.add(targetValue);

            // Switch case
            int caseVal = 1; // align with target_feature: 1
            switch (caseVal) {
                case 1:
                    result.add("switch_case_1_" + targetValue);
                    break;
                default:
                    result.add("switch_default_" + targetValue);
            }

            // Call SynchronizedStatement feature method
            syncLogic(targetParam);

            // Call instance helper method
            TargetClass<T> helperResult = instanceHelperMethod(targetParam);
            result.add(helperResult.getValue().toString());

            // Depends on inner class (local inner class)
            class LocalInnerProcessor { // depends_on_inner_class
                String process(String input) {
                    return input + "_local_inner_1"; // method_feature: 1
                }
            }
            LocalInnerProcessor processor = new LocalInnerProcessor();
            result.add(processor.process(targetValue));

            // Used by reflection feature
            try {
                Method getValueMethod = TargetClass.class.getDeclaredMethod("getValue");
                getValueMethod.setAccessible(true);
                T reflectValue = (T) getValueMethod.invoke(targetParam);
                result.add("reflection_" + reflectValue);
            } catch (Exception e) {
                // No new exception feature (no throw new exception)
                result.add("reflection_error_" + e.getMessage());
            }

            // Anonymous inner class (source class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Anonymous inner: " + result);
                }
            };
            anonymousRunnable.run();

            // No new exception feature
            try {
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                result.add("formatted_" + targetValue);
            }

            return result;
        }
    }

    // Anonymous inner class (source class feature)
    public void sourceAnonymousFeature() {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous class: " + targetField.getValue());
            }
        };
        anonymous.run();
    }
}

// Target class: generic, private, same package as source, member inner class feature
private class TargetClass<T> {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Member inner class (target_feature)
    public class TargetInner {
        private T innerValue;

        public T getValue() {
            return innerValue;
        }

        public void setValue(T value) {
            this.innerValue = value;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}