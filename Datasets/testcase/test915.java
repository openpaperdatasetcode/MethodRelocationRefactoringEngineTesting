package refactoring.test;

// Target abstract class: abstract modifier, static nested class (target_feature)
abstract class TargetClass {
    public int thisField = 1; // this.field + 1 for VariableDeclarationStatement

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String formatValue(int val) {
            return "formatted_" + val;
        }
    }

    // Instance method for access_instance_method feature
    public abstract int getProcessedValue(int... args);

    // Concrete method for variable call
    public int getThisField() {
        return thisField;
    }
}

// Concrete subclass of TargetClass
class ConcreteTargetClass extends TargetClass {
    @Override
    public int getProcessedValue(int... args) {
        int sum = 0;
        for (int arg : args) sum += arg;
        return sum + thisField;
    }
}

// Source class: public modifier, local inner + member inner class (source_feature)
public class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new ConcreteTargetClass();

    // Member inner class (source_feature)
    public class SourceMemberInner {
        // VariableDeclarationStatement feature: public modifier, this.field, 1, pos=inner class
        public void variableDeclarationFeature(TargetClass target) {
            int localVar = target.thisField; // this.field + 1, pos=inner class
            System.out.println("Inner class var: " + localVar);
        }
    }

    // Varargs method: default access, Object return type, no parameters (method types parameter is:none), method_position=source
    Object refactorMethod(int... args) {
        // Variable call feature
        int varCall = targetField.getThisField();

        // Access instance method feature
        int processedValue = targetField.getProcessedValue(args);

        // Execute VariableDeclarationStatement feature (pos=inner class)
        SourceMemberInner inner = new SourceMemberInner();
        inner.variableDeclarationFeature(targetField);

        // Assert statement feature
        assert varCall == 1 : "thisField must be 1";
        assert processedValue >= varCall : "Processed value must be >= thisField";

        // Local inner class (source_feature)
        class LocalInnerClass {
            public Object combineResults() {
                String formatted = TargetClass.TargetStaticNested.formatValue(processedValue);
                return new Object() {
                    @Override
                    public String toString() {
                        return varCall + "_" + processedValue + "_" + formatted;
                    }
                };
            }
        }

        // No new exception thrown feature
        return new LocalInnerClass().combineResults();
    }
}