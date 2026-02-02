import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Parent class for source abstract class extends feature
class ParentClass {
    protected String parentField = "ParentValue";
}

// Source abstract class (protected modifier, same package as target, extends ParentClass)
protected abstract class SourceAbstractClass extends ParentClass {
    // Static nested class (source_class feature)
    static class SourceStaticNestedClass {}

    // Member inner class (source_class feature)
    class SourceMemberInnerClass {
        // Instance method to be moved (protected, Object return, source_inner position)
        protected Object processTarget(FinalTargetAbstractClass<String> targetParam) {
            // Per_condition: method contains target parameter
            if (Objects.isNull(targetParam)) {
                // NullPointerException (no_new_exception: no explicit new Exception())
                throw new NullPointerException("Target parameter cannot be null");
            }

            // Type declaration statement
            String fieldValue = targetParam.TargetStaticNestedClass.staticField;
            int counter = 0;

            // DoStatement (static modifier, pos: diff_package_target, target_feature: obj.field, 1)
            do {
                // obj.field access
                targetParam.TargetStaticNestedClass.staticField = "Updated" + counter;
                // Variable call
                counter = targetParam.getCounter();
                counter++;
            } while (counter <= 1); // target_feature: 1

            return fieldValue;
        }
    }
}

// Target abstract class (final modifier, type parameter, static nested class)
final abstract class FinalTargetAbstractClass<T> {
    // Static nested class (target_feature)
    static class TargetStaticNestedClass {
        static String staticField = "Initial";
    }

    private int counter = 0;

    // Call method (target type, protected, static, instanceReference::methodName, pos: for, return List<String>)
    protected static List<String> callProcessTarget(SourceAbstractClass sourceInstance) {
        List<String> results = new ArrayList<>();
        FinalTargetAbstractClass<String> targetInstance = new FinalTargetAbstractClass<String>() {};

        // Instance reference method reference: instanceReference::methodName
        SourceAbstractClass.SourceMemberInnerClass innerInstance = sourceInstance.new SourceMemberInnerClass();
        for (int i = 0; i < 3; i++) { // pos: for
            targetInstance.setCounter(i);
            // Method reference usage
            results.add(String.valueOf(innerInstance::processTarget));
            // Actual method call
            results.add(String.valueOf(innerInstance.processTarget(targetInstance)));
        }

        return results;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}