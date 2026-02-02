package com.refactoring.test;

import java.lang.reflect.Method;

// Source abstract class (abstract modifier, same package, local inner class, anonymous inner class)
abstract class SourceClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerProtectedValue";
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord() {
        // Method to be refactored (varargs, void return, public access)
        public void targetMethod(String... args) {
            // VariableDeclarationExpression (numbers:2, default modifier, exp:VariableDeclarationExpression)
            default int count = 2;
            
            // Requires try-catch
            try {
                // Uses outer this (uses_outer_this)
                SourceClass outerThis = SourceClass.this;
                // Access outer protected field
                String outerValue = outerThis.outerProtectedField;

                // Local inner class (source_class feature)
                class LocalInnerClass {
                    void processArgs() {
                        // Access instance field
                        String targetInstanceField = targetField.innerClass.instanceField;
                        // Variable call
                        for (String arg : args) {
                            targetField.innerClass.process(arg + count + outerValue);
                        }
                    }
                }
                LocalInnerClass local = new LocalInnerClass();
                local.processArgs();

                // Used by reflection
                Method method = InnerSourceRecord.class.getDeclaredMethod("targetMethod", String[].class);
                method.setAccessible(true);
                method.invoke(this, (Object) args);

            } catch (Exception e) { // requires_try_catch
                // Fallback for exception handling
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerSourceRecord record = new InnerSourceRecord();
            record.targetMethod("test1", "test2");
        }
    };
}

// Target abstract class (private modifier, member inner class target_feature)
private abstract class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        String instanceField = "targetInstanceField"; // access_instance_field

        void process(String value) {
            System.out.println(value);
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
}