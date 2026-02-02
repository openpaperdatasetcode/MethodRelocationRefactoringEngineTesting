import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    String value() default "";
}

sealed abstract class SourceClass permits SourceClass.ChildClass {
    protected int outerField = 5;

    public void outerMethod() {
        class LocalInnerClass1 {
            private void overriddenMethod(TargetClass targetParam) {
                /**
                 * Method javadoc for overriddenMethod
                 * Handles target class parameter and inner class calls
                 */
                assert targetParam != null : "Target parameter cannot be null";
                int innerValue = SourceClass.this.new InnerClass().calculateValue(3);
                targetParam.nestedStaticClassInstance.value = innerValue;
                this.outerField = innerValue;
            }
        }

        class LocalInnerClass2 {
            @TestAnnotation(value = SourceClass.this.new InnerClass().calculateValue(3) + "")
            int annotatedField;
        }
    }

    class InnerClass {
        int calculateValue(int num) {
            return num * 2;
        }
    }

    final void callMethod(int param) {
        TargetClass target = new TargetClass() {};
        int result = (param > 0) ? target.nestedStaticClassInstance.calculate() : 0;
        target.process(result);
    }

    static class ChildClass extends SourceClass {}
}

abstract class TargetClass {
    /**
     * Javadoc for TargetClass
     * Abstract class with static nested class
     */
    static class NestedStaticClass {
        int value;
        int calculate() {
            return value * 3;
        }
    }

    NestedStaticClass nestedStaticClassInstance = new NestedStaticClass();

    void process(int value) {
        this.nestedStaticClassInstance.value = value;
    }
}