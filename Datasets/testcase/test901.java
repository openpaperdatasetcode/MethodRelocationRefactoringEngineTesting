package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Super class for super.field and super.methodName()
class SuperTargetClass {
    protected int superField = 1; // super.field + 1 for IfStatement

    protected List<String> superMethod(int value) {
        List<String> list = new ArrayList<>();
        list.add("super_" + value);
        return list;
    }
}

// Target abstract class: private modifier, empty target_feature, same package as source
class TargetContainer {
    private abstract static class TargetClass extends SuperTargetClass {
        public int targetField;

        public TargetClass(int targetField) {
            this.targetField = targetField;
        }

        // Overload method 1 for overload_exist feature
        public abstract int process(int... args);

        // Overload method 2 for overload_exist feature
        public abstract int process(String... args);
    }
}

// Concrete subclass of TargetClass
class ConcreteTargetClass extends TargetContainer.TargetClass {
    public ConcreteTargetClass(int targetField) {
        super(targetField);
    }

    @Override
    public int process(int... args) {
        int sum = 0;
        for (int arg : args) sum += arg;
        return sum;
    }

    @Override
    public int process(String... args) {
        return args.length;
    }
}

// Source abstract class: abstract modifier, two member inner classes (source_feature)
public abstract class SourceClass {
    // Source contains target field (per_condition)
    private TargetContainer.TargetClass targetField = new ConcreteTargetClass(1);

    // First member inner class (source_feature)
    class FirstMemberInner {
        public int getInnerValue() {
            return 1; // method_feature: 1
        }
    }

    // Second member inner class (source_feature - source_inner: method_position)
    class SecondMemberInner {
        // IfStatement feature: private modifier, super.field, 1, pos=source
        private int ifStatementFeature(TargetContainer.TargetClass target) {
            int result = 0;
            if (target.superField == 1) { // super.field + 1
                result = target.targetField;
            }
            return result;
        }

        // Instance method feature: default modifier, 1, inner_class, instance, super.methodName(), pos=switch, return_type=List<String>
        List<String> instanceHelperMethod(FirstMemberInner inner) {
            List<String> result = new ArrayList<>();
            int val = inner.getInnerValue(); // inner_class + 1
            switch (val) { // pos=switch
                case 1:
                    result = super.superMethod(val); // super.methodName(arguments)
                    break;
                default:
                    result.add("default");
            }
            return result;
        }

        // Varargs method: default access, base return type (int), method_position=source_inner
        int refactorMethod(int... args) {
            // Empty statement feature
            ;

            // Constructor invocation feature
            TargetContainer.TargetClass newTarget = new ConcreteTargetClass(1);

            // Variable call feature
            int varCall = targetField.targetField;

            // With_bounds feature
            int bound = args.length > 3 ? 3 : args.length; // Bounds check
            int sum = 0;
            for (int i = 0; i < bound; i++) sum += args[i];

            // Execute IfStatement feature
            int ifResult = ifStatementFeature(targetField);

            // Execute instance helper method
            FirstMemberInner inner = new FirstMemberInner();
            List<String> helperResult = instanceHelperMethod(inner);

            // Overload_exist feature (call overloaded methods)
            int overload1 = targetField.process(args);
            int overload2 = targetField.process("arg1", "arg2");

            // Used_by_reflection feature
            try {
                Method processMethod = TargetContainer.TargetClass.class.getMethod("process", int[].class);
                int reflectResult = (int) processMethod.invoke(targetField, (Object) args);
            } catch (Exception e) {
                // No new exception thrown feature
            }

            // No new exception thrown feature
            return varCall + sum + ifResult + helperResult.size() + overload1 + overload2;
        }
    }
}