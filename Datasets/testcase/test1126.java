/**
 * Super interface for source_class implements feature
 */
interface SourceSuperInterface {
    default String interfaceMethod() {
        return "interface_default";
    }
}

/**
 * Parent class for call_method (parent_class type)
 */
class ParentClass {
    // Overloading method 1 (overloading feature)
    public synchronized Object parentMethod(int num, String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        for (String arg : args) {
            sb.append("_").append(arg);
        }
        return sb.toString();
    }

    // Overloading method 2 (overloading feature)
    public synchronized Object parentMethod(TargetClass target, int num) {
        return target.getValue() + "_parent_" + num;
    }

    // Static method for ClassName.methodName(arguments)
    public static Object staticParentMethod(String str) {
        return str.toUpperCase();
    }
}

/**
 * Source normal class (strictfp modifier, implements, member inner class, local inner class)
 */
strictfp class SourceClass implements SourceSuperInterface {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private
    private String outerPrivateField = "source_private_5671";

    // Member inner class (source_class feature)
    class MemberInnerClass {
        <T> TargetClass processGeneric(T param, int num) {
            TargetClass target = new TargetClass();
            target.setValue(param.toString() + "_inner_" + num);
            return target;
        }
    }

    /**
     * Method Javadoc: Varargs method returning TargetClass, default access
     * @param param Target class parameter
     * @param args Varargs arguments
     * @return TargetClass instance
     */
    // Method to be refactored (varargs, TargetClass return, default access, position: source)
    TargetClass targetMethod(TargetClass param, String... args) {
        // Expression statement
        String targetValue = param.getValue() + "_processed";
        param.setValue(targetValue);

        // Access outer private field
        String privateVal = this.outerPrivateField; // access_outer_private

        // Generic method call in object initialization (pos: object initialization)
        TargetClass genericTarget = new TargetClass(
            (String) genericMethod(3, new MemberInnerClass(), param) // 3, inner_class, generic, instanceReference.methodName(arguments)
        );

        // While statement
        int count = 0;
        while (count < 3) {
            genericTarget.setValue(genericTarget.getValue() + "_loop_" + count);
            count++;
        }

        // Switch case
        int switchKey = genericTarget.getValue().length();
        switch (switchKey) {
            case 10:
                genericTarget.setValue(genericTarget.getValue() + "_case10");
                break;
            case 20:
                genericTarget.setValue(genericTarget.getValue() + "_case20");
                break;
            default:
                genericTarget.setValue(genericTarget.getValue() + "_default_" + privateVal);
                break;
        }

        // Local inner class (source_class feature)
        class LocalInnerClass {
            void updateTarget(TargetClass target) {
                // Call method in exception handling statements (pos: exception handling statements)
                try {
                    Object callResult = new ParentClass().parentMethod(3, args); // parent_class, synchronized, overloading
                    Object staticCall = ParentClass.staticParentMethod(target.getValue()); // ClassName.methodName(arguments)
                    target.setValue(target.getValue() + "_" + callResult + "_" + staticCall);
                } catch (Exception e) {
                    target.setValue(target.getValue() + "_error_" + e.getMessage());
                }
            }
        }
        new LocalInnerClass().updateTarget(genericTarget);

        // Variable call
        targetField.setValue(genericTarget.getValue());

        // No new exception
        return targetField;
    }

    // Generic method (private modifier, TargetClass return, 3, inner_class, generic)
    private <T> Object genericMethod(int num, MemberInnerClass innerInstance, T target) {
        // instanceReference.methodName(arguments)
        return innerInstance.processGeneric(target, num).getValue() + "_generic_" + num;
    }
}

/**
 * Target normal class (public modifier, javadoc, static nested class)
 */
public class TargetClass {
    private String value;

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static String format(String s) {
            return s + "_static_nested";
        }
    }

    // Constructors
    public TargetClass() {
        this.value = "target_default";
    }

    public TargetClass(String value) {
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