// Diff package target helper for BreakStatement pos
package com.refactoring.others;
import com.refactoring.movemethod.TargetClass;

public class OthersClass {
    // BreakStatement feature: private modifier, obj.field, 3, pos=diff_package_target
    private static <T> void breakStatementLogic(TargetClass<T>.TargetInnerRec inner) {
        // pos: diff_package_target
        for (int i = 0; i < 5; i++) {
            inner.innerField = 3; // obj.field feature, target_feature: 3
            if (i == 3) { // target_feature: 3
                break; // BreakStatement
            }
            inner.setInnerValue((T) (inner.getInnerValue() + "_loop_" + i));
        }

        // call_method: others_class type, default modifier, normal, OuterClass.InnerClass.methodName(), pos=functional interfaces, return void
        public static <T> void callMethod(TargetClass<T>.TargetInnerRec inner) {
            // pos: functional interfaces (Runnable as functional interface)
            Runnable runnable = () -> {
                // OuterClass.InnerClass.methodName()
                String result = TargetClass.TargetInnerRec.class.getSimpleName() + "." + inner.innerMethod(); // normal feature
                inner.setInnerValue((T) result);
            };
            runnable.run();
        }

        // Normal method for OuterClass.InnerClass.methodName()
        public static <T> String normalMethod(TargetClass<T>.TargetInnerRec inner) {
            return inner.getInnerValue().toString() + "_others_3"; // target_feature: 3
        }
    }
}

// Back to source package
package com.refactoring.movemethod;

import com.refactoring.others.OthersClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Base class for override_violation feature
class BaseMethodClass {
    // Base method with public access (for override_violation)
    public void baseMethod() {}
}

// Source class: protected normal class, same package as target, anonymous inner + member inner class
protected class SourceClass extends BaseMethodClass {
    // Instance field for access_instance_field feature
    private String instanceField = "instance_3"; // target_feature: 3

    // Member inner class (source_inner - method_position)
    public class SourceInnerClass {
        /**
         * Method to refactor: varargs, TargetClass return, default access, method javadoc
         * <p>Features: BreakStatement, if statement, used_by_reflection, variable call, override_violation, access_instance_field, requires_try_catch</p>
         * @param innerParams Varargs of target inner recursive class instances
         * @return Processed TargetClass instance
         * @param <T> Generic type parameter
         */
        public <T> TargetClass<T> methodToRefactor(TargetClass<T>.TargetInnerRec... innerParams) {
            // Per_condition: method contains target parameter
            if (innerParams == null || innerParams.length == 0) {
                throw new IllegalArgumentException("No target parameters provided");
            }

            // Override_violation feature (base: public, override: private)
            @Override
            private void baseMethod() {} // Access modifier violation

            // Create target instance for return value
            TargetClass<T> resultTarget = new TargetClass<>((T) "result_3"); // target_feature: 3

            // Process varargs parameters
            for (TargetClass<T>.TargetInnerRec inner : innerParams) {
                // If statement
                if (inner.getInnerValue() == null) {
                    inner.setInnerValue((T) "default_3"); // target_feature: 3
                    continue;
                }

                // BreakStatement feature call (diff_package_target)
                OthersClass.breakStatementLogic(inner);

                // Access_instance_field feature
                inner.setInnerValue((T) (inner.getInnerValue() + "_" + SourceClass.this.instanceField));

                // Used_by_reflection feature
                try {
                    // Reflectively access obj.field
                    Field field = TargetClass.TargetInnerRec.class.getDeclaredField("innerField");
                    field.setAccessible(true);
                    field.set(inner, 3); // target_feature: 3

                    // Reflectively invoke method
                    Method method = TargetClass.TargetInnerRec.class.getDeclaredMethod("innerMethod");
                    method.setAccessible(true);
                    String methodResult = (String) method.invoke(inner);
                    inner.setInnerValue((T) methodResult);
                } catch (Exception e) {
                    // Requires_try_catch feature
                    inner.setInnerValue((T) "reflection_error_3"); // target_feature: 3
                }

                // Variable call (target inner class)
                T innerValue = inner.getInnerValue();
                
                // Requires_try_catch feature
                try {
                    Integer.parseInt(innerValue.toString());
                } catch (NumberFormatException e) {
                    inner.setInnerValue((T) "safe_value_3"); // target_feature: 3
                }

                // Call call_method (others_class type)
                OthersClass.callMethod(inner);

                // Anonymous inner class (source feature)
                Runnable anonymous = new Runnable() {
                    @Override
                    public void run() {
                        // Access_instance_field in anonymous inner class
                        inner.setInnerValue((T) (inner.getInnerValue() + "_anonymous_" + SourceClass.this.instanceField));
                    }
                };
                anonymous.run();
            }

            // Set result target's inner value from first parameter
            resultTarget.getInnerRec().setInnerValue(innerParams[0].getInnerValue());
            return resultTarget;
        }

        // Override violation (additional)
        @Override
        public String toString() {
            return super.toString() + "_override_3"; // target_feature: 3
        }
    }

    // Override_violation (return type mismatch)
    @Override
    public Integer baseMethod() { // Return type violation (base: void, override: Integer)
        return 3; // target_feature: 3
    }

    // Helper method to invoke refactored method
    public <T> TargetClass<T> invokeMethod(TargetClass<T>.TargetInnerRec... inners) {
        SourceInnerClass inner = new SourceInnerClass();
        return inner.methodToRefactor(inners);
    }
}

// Target class: default modifier, member inner class feature
class TargetClass<T> {
    private T value;
    private final TargetInnerRec innerRec;

    public TargetClass(T initialValue) {
        this.value = initialValue;
        this.innerRec = new TargetInnerRec((T) (initialValue + "_inner_3")); // target_feature: 3
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;
        int innerField; // obj.field feature

        public TargetInnerRec(T value) {
            this.innerValue = value;
        }

        // Normal method for call_method
        public String innerMethod() {
            return this.innerValue.toString() + "_inner_method_3"; // target_feature: 3
        }

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }

        public int getInnerField() {
            return innerField;
        }
    }

    // Getter for inner recursive class (per_condition variable call)
    public TargetInnerRec getInnerRec() {
        return innerRec;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}