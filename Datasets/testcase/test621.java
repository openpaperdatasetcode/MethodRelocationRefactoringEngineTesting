package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

// Source enum class: default modifier, generic (type parameter), anonymous inner, static nested class
enum SourceEnum<T extends CharSequence> {
    INSTANCE;

    // Static field for depends_on_static_field feature
    private static final int STATIC_FIELD = 1; // align with target_feature: 1
    // Per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.VALUE1;

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) (val.toString() + "_static_" + STATIC_FIELD);
        }
    }

    // Method to refactor: varargs, Object return, default access, in source enum
    Object methodToRefactor(TargetEnum.TargetInner... targetParams) {
        // VariableDeclarationStatement feature: private modifier, this.field, 1, pos=same_package_target
        private void varDeclLogic(TargetEnum.TargetInner inner) {
            // pos: same_package_target (operate on target inner class)
            int fieldVal = 1; // target_feature: 1
            // this.field feature (inner class field assignment)
            inner.innerField = fieldVal;
            // VariableDeclarationStatement
            String localVar = inner.getInnerValue() + "_varDecl_" + fieldVal;
            System.out.println(localVar);
        }

        // Instance method feature: public modifier, 3, target, instance, new ClassName().method(), pos=do-while, return base type (int)
        public int instanceHelperMethod(TargetEnum.TargetInner inner) {
            int count = 0;
            // pos: do-while
            do {
                // new ClassName().method() feature
                int result = new TargetEnum.TargetInner().processInt(3); // method_feature: 3, target
                count += result;
            } while (count < 3); // method_feature: 3
            return count;
        }

        Object result = null;
        // For statement
        for (int i = 0; i < targetParams.length; i++) {
            TargetEnum.TargetInner param = targetParams[i];
            // Variable call (target inner class)
            String innerValue = param.getInnerValue();
            
            // Call VariableDeclarationStatement logic
            varDeclLogic(param);
            
            // Depends on static field
            innerValue += SourceStaticNested.processValue(STATIC_FIELD);
            
            // Used by reflection feature
            try {
                Field field = TargetEnum.TargetInner.class.getDeclaredField("innerField");
                field.setAccessible(true);
                field.set(param, 1); // target_feature: 1
                
                Method method = TargetEnum.TargetInner.class.getDeclaredMethod("setInnerValue", String.class);
                method.invoke(param, innerValue + "_reflection");
            } catch (Exception e) {
                // Requires_try_catch feature
                param.setInnerValue("reflection_error_" + innerValue);
            }

            // Requires_try_catch feature
            try {
                Integer.parseInt(innerValue);
            } catch (NumberFormatException e) {
                param.setInnerValue("formatted_" + innerValue);
            }

            // Call instance helper method
            int helperResult = instanceHelperMethod(param);
            result = innerValue + "_helper_" + helperResult;
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Processed result: " + result);
                // Variable call for targetField (per_condition)
                System.out.println("Target field inner value: " + targetField.getInner().getInnerValue());
            }
        };
        anonymousRunnable.run();

        return result;
    }

    // call_method: target type, protected modifier, constructor, (parameters) -> methodBody, pos=do-while, return String
    protected String callMethod(TargetEnum.TargetInner... innerParams) {
        int loopCount = 0;
        String callResult = "";
        // pos: do-while
        do {
            // target_feature: constructor (new TargetInner instance)
            TargetEnum.TargetInner newInner = new TargetEnum.TargetInner();
            // target_feature: (parameters) -> methodBody (lambda expression)
            java.util.function.Function<TargetEnum.TargetInner, String> lambda = (inner) -> {
                inner.setInnerValue(inner.getInnerValue() + "_lambda_" + loopCount);
                return inner.getInnerValue();
            };
            callResult = lambda.apply(newInner);
            loopCount++;
        } while (loopCount < 3); // align with method_feature: 3
        return callResult;
    }
}

// Target enum class: no modifier, member inner class feature
enum TargetEnum {
    VALUE1, VALUE2;

    // Member inner class (target_feature)
    public class TargetInner {
        int innerField = 1; // align with target_feature: 1
        private String innerValue = "target_inner_" + 1;

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }

        public int processInt(int num) {
            return num * innerField;
        }
    }

    // Get inner class instance (for per_condition variable call)
    public TargetInner getInner() {
        return new TargetInner();
    }
}