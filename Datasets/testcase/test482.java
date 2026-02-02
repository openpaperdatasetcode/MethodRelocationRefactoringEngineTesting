package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

// Source normal class (public, same package, member inner + static nested class)
public class SourceClass {
    private int outerField = 2;

    // Static nested class (source class feature)
    public static class StaticNestedClass {
        public int getValue(int num) {
            return num * 2;
        }
    }

    // Member inner class (source_inner position for refactor method)
    public class SourceInnerClass {
        private int innerVar = 10;

        // Method to refactor (instance, return TargetClass type, private access, source_inner)
        private TargetClass.TargetInner refactorMethod(TargetClass.TargetInner targetParam) {
            // Uses outer this feature
            SourceClass outerThis = SourceClass.this;
            // Variable call feature
            int localVar = this.innerVar + outerThis.outerField;
            StaticNestedClass staticNested = new StaticNestedClass();
            localVar = staticNested.getValue(localVar); // variable call

            // Constructor invocation feature
            TargetClass.TargetInner newInner = new TargetClass().new TargetInner("data", localVar);

            // Enhanced for statement feature
            List<TargetClass.TargetInner> innerList = Arrays.asList(targetParam, newInner);
            for (TargetClass.TargetInner inner : innerList) {
                if (inner == null) continue;
            }

            // Switch case feature
            switch (localVar) {
                case 2:
                    // Normal method in exception handling (pos: exception handling statements)
                    try {
                        Object result = this.normalMethod(2); // this.methodName(arguments), method_feature 2/inner_class/normal
                        newInner.setData(result.toString());
                    } catch (Exception e) {
                        // No new exception feature
                        newInner.setData("default");
                    }
                    break;
                default:
                    newInner.setData("other");
                    break;
            }

            // Used by reflection feature
            try {
                Method method = TargetClass.TargetInner.class.getMethod("getData");
                String reflectedData = (String) method.invoke(newInner);
                newInner.setData(reflectedData + "_reflected");
            } catch (Exception e) {
                // No new exception feature
                newInner.setData("reflection_error");
            }

            // Call method (inner_class, default modifier, pos: for, overloading + OuterClass.InnerClass.methodName)
            for (int i = 0; i < 2; i++) { // pos: for
                TargetClass.TargetInner.callMethod(i); // OuterClass.InnerClass.methodName()
                TargetClass.TargetInner.callMethod(i, "suffix"); // overloading feature
            }

            return newInner;
        }

        // Normal method (public modifier, return Object, pos: exception handling, method_feature)
        public Object normalMethod(int num) { // method_feature: 2/inner_class/normal
            return num * outerThis.outerField; // this.methodName(arguments) context
        }
    }

    // Constructor to initialize inner class context
    public SourceClass() {
        SourceInnerClass inner = new SourceInnerClass();
    }
}

// Target normal class (no modifier, local inner class feature)
class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private String data;
        private int value;

        // Constructor for invocation
        public TargetInner(String data, int value) {
            this.data = data;
            this.value = value;
        }

        // Call method (inner_class, default modifier, overloading + OuterClass.InnerClass.methodName)
        public void callMethod(int num) { // overloading 1
            this.data = String.valueOf(num);
        }

        public void callMethod(int num, String suffix) { // overloading 2
            this.data = String.valueOf(num) + suffix;
        }

        // Getter/Setter for reflection and variable access
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        // Local inner class (target_feature)
        public void processLocalInner() {
            class LocalInnerClass {
                public void updateData(TargetInner inner) {
                    inner.data = "local_" + inner.data;
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            local.updateData(this);
        }
    }

    // Constructor to initialize inner class
    public TargetClass() {
        TargetInner inner = new TargetInner("init", 0);
    }
}