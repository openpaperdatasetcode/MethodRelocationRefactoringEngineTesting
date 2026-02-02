import java.util.ArrayList;
import java.util.List;

// Parent class for overriding feature
class ParentSourceClass {
    protected void targetMethod(PrivateTargetClass param) {
        // Parent method to be overridden
    }
}

// Source normal class (public modifier, same package, empty feature list)
public class SourceClass extends ParentSourceClass {
    // Overriding method (overriding type, void return, protected access, position: source)
    @Override
    protected void targetMethod(PrivateTargetClass param) { // per_condition: contains target parameter
        // Variable call (target class member access)
        String targetVal = param.innerClass.getValue();
        param.value = targetVal + "_updated"; // access_instance_field

        // ConstructorInvocation (private modifier, super.field, 3, pos: source)
        private void constructorBlock() {
            PrivateTargetClass.SuperTargetClass superObj = new PrivateTargetClass.SuperTargetClass();
            String superField = superObj.superField; // super.field
            PrivateTargetClass newTarget = new PrivateTargetClass(superField + "_" + 3); // target_feature: 3
            param.setValue(newTarget.getValue());
        }
        constructorBlock();

        // Depends on inner class (depends_on_inner_class feature)
        MethodInnerClass innerHelper = new MethodInnerClass();
        List<String> recursionResult = innerHelper.recursiveMethod(2, param); // 2, inner_class, recursion

        // No new exception (no custom exception instantiation)
        System.out.println(recursionResult);
    }

    // Inner class for recursion and depends_on_inner_class
    public class MethodInnerClass {
        // Recursion method (public modifier, 2, inner_class, recursion, this.methodName(arguments))
        public List<String> recursiveMethod(int count, PrivateTargetClass param) {
            List<String> result = new ArrayList<>();
            // if/else conditions (pos: if/else conditions)
            if (count <= 0) {
                result.add(param.getValue());
                return result;
            } else {
                // this.methodName(arguments) - recursion
                result.addAll(this.recursiveMethod(count - 1, param));
                result.add(param.getValue() + "_recur_" + count);
                return result;
            }
        }
    }
}

// Target normal class (private modifier, member inner class target_feature)
private class PrivateTargetClass {
    // Instance field for access_instance_field
    public String value = "target_5672";

    // Super class for super.field feature
    public static class SuperTargetClass {
        public String superField = "super_target_field";
    }

    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue = "inner_target_value";

        public String getValue() {
            return innerValue;
        }
    }

    // Inner class instance for variable call
    public InnerClass innerClass = new InnerClass();

    // Constructor for ConstructorInvocation
    public PrivateTargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}