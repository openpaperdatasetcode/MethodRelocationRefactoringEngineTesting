package refactoring.test;

import java.io.IOException;

/**
 * Target class Javadoc (target_feature: javadoc)
 * Public modifier, member inner class (target_feature)
 */
public class TargetClass {
    public String targetField = "targetValue";

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

    public TargetInner createInner(String data) {
        return new TargetInner(data);
    }
}

// Subclass of TargetClass for method_feature: sub_class
public class TargetSubClass extends TargetClass {
    // Overloading method 1
    protected TargetInner overloadMethod(int value) {
        return super.createInner("overload_" + value); // super.methodName()
    }

    // Overloading method 2 (overloading feature)
    protected TargetInner overloadMethod(String value) {
        return super.createInner("overload_" + value); // super.methodName()
    }
}

// Source class: public modifier, static nested + anonymous inner class (source_feature)
public class SourceClass {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "outerProtectedValue";
    private int x = 2; // For OuterClass.this.x feature

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static TargetClass.TargetInner createNestedInner(TargetClass target, String data) {
            return target.createInner(data);
        }
    }

    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // OuterClass.this.x feature
            System.out.println("Outer x: " + SourceClass.this.x);
        }
    };

    // Overloading method feature: protected modifier, 2, sub_class, overloading, super.methodName(), pos=for, return_type=TargetClass Type
    protected TargetClass.TargetInner overloadHelperMethod(TargetSubClass subClass) {
        TargetClass.TargetInner result = null;
        // pos=for
        for (int i = 0; i < 2; i++) { // method_feature: 2
            // overloading feature (call different overload methods)
            if (i == 0) {
                result = subClass.overloadMethod(i);
            } else {
                result = subClass.overloadMethod("val" + i);
            }
        }
        return result;
    }

    // Instance method: public access, void return type, target parameter (per_condition)
    public void refactorMethod(TargetClass targetParam) {
        // Variable call feature
        String varCall = targetParam.targetField;

        // Access outer protected feature
        String outerProtected = this.outerProtectedField;

        // OuterClass.this.x feature
        int outerX = SourceClass.this.x;

        // IOException feature (handled to ensure no_new_exception)
        try {
            if (varCall.isEmpty()) {
                throw new IOException("Target field is empty");
            }
        } catch (IOException e) {
            // No new exception thrown feature
            System.err.println("Handled IOException: " + e.getMessage());
        }

        // Execute overloading helper method (sub_class)
        TargetSubClass subClass = new TargetSubClass();
        TargetClass.TargetInner overloadResult = overloadHelperMethod(subClass);

        // Execute anonymous inner class
        anonymousInner.run();

        // Use static nested class
        SourceStaticNested.createNestedInner(targetParam, varCall + "_" + outerProtected + "_" + outerX);

        // No new exception thrown feature
    }
}